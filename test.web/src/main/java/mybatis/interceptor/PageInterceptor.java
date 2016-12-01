package mybatis.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMap;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * Created by Administrator on 2015/12/17.
 */

@Intercepts({@Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class})})
public class PageInterceptor implements Interceptor {

    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);

        BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");

        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");

        Object parameterObject = boundSql.getParameterObject();
        if (parameterObject == null) {
            throw new NullPointerException("fenye parameterObject is null!");
        }
        PageParams pageParams = null;
        if (parameterObject instanceof PageParams) {
            pageParams = (PageParams) parameterObject;
        } else {
            MetaObject mateParameterObject = MetaObject.forObject(parameterObject, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
            pageParams = (PageParams) mateParameterObject.getValue("page");
        }
        if(pageParams == null) throw new NullPointerException("fenye parameterObject is null!");

        if (pageParams.isNeedQueryTotal()) { //如果需要查询总记录数
            PreparedStatement countPreparedStatement = null;
            ResultSet resultSet = null;
            try {
                String countSql = "select count(0) from ( " + boundSql.getSql() + " ) as countView";
                Connection connection = (Connection) invocation.getArgs()[0];
                countPreparedStatement = connection.prepareStatement(countSql);
                ParameterMap parameterMap = mappedStatement.getParameterMap();
                List<ParameterMapping> parameterMappings = parameterMap.getParameterMappings();

                BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(),
                                                        countSql,
                                                        boundSql.getParameterMappings(),
                                                        boundSql.getParameterObject());

                this.setTotolCount(countPreparedStatement, mappedStatement, countBoundSql.getParameterObject(), countBoundSql);
                resultSet = countPreparedStatement.executeQuery();
                if (resultSet.next()) {
                    pageParams.setTotalCount(resultSet.getInt(1));
                }

            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }finally {
                try {
                    if(countPreparedStatement != null){
                        countPreparedStatement.close();
                    }
                    if (resultSet != null){
                        resultSet.close();
                    }
                }catch (SQLException sqlEx){

                }
            }
        }

        String newSql = boundSql.getSql() + " LIMIT " + pageParams.getIndex() + " , " + pageParams.getCount();

        metaStatementHandler.setValue("delegate.boundSql.sql", newSql);

        return invocation.proceed();

    }

    private void setTotolCount(PreparedStatement countStatement, MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) throws SQLException {
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
        parameterHandler.setParameters(countStatement);
    }


    //返回一个代理对象
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);

    }

    @Override
    public void setProperties(Properties properties) {

    }


}