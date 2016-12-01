package mybatis;

import mybatis.entity.Order;
import mybatis.interceptor.PageParams;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Administrator on 2015/12/12.
 */
public class MyBatisComplicateMapperTest {

    public static void main(String[] args) throws FileNotFoundException {
        new MyBatisComplicateMapperTest().runUseConfiguration();
    }

    public void runUseConfiguration() throws FileNotFoundException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(this.getClass().getClassLoader().getResourceAsStream("mybatis/mybatis.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();

//        insertOrderUserEntity(sqlSession);
//        insertOrderUserMap(sqlSession);
//        inserOrderUseList(sqlSession);
//        inserOrderUseArray(sqlSession);
//        insertOrderUserEntityBatch(sqlSession);
//        insertOrderWithGenerateKey(sqlSession);

//        selectOrderWithMutiCondtion(sqlSession);
//        selectCommonTable(sqlSession);
//        selectOrderUseWhereNode(sqlSession);
//        insertOrderTestTransation(sqlSession);

//        selectOrderWithFenYeUseLimit(sqlSession);

//        selectOrderWithFenYeUseRowBounds(sqlSession);

//        selectOrderWithFenYeUseInterceptor(sqlSession);

        selectOrderWithFenyeUseInterceptorAndVo(sqlSession);
    }

    private void selectOrderWithFenyeUseInterceptorAndVo(SqlSession sqlSession) {
        PageQueryVO vo = new PageQueryVO();
        vo.setId(1);
        vo.setName("%use%");
        PageParams pageParams = new PageParams();
        pageParams.setCount(10);
        pageParams.setNeedQueryTotal(true);
        vo.setPage(pageParams);

        List<Object> list = sqlSession.selectList("selectOrderWithFenyeUseInterceptorAndVo", vo);
        System.out.println(list.size());
    }

    /**Interceptor方式的分页*/
    private void selectOrderWithFenYeUseInterceptor(SqlSession sqlSession) {
        PageParams pageParams = new PageParams();
        pageParams.setIndex(0);
        pageParams.setCount(8);
        List<Object> list = sqlSession.selectList("selectOrderWithFenYeUseInterceptor", pageParams);
        System.out.println(list.size());
    }

    /**查询分页使用mybatis提供的RowBounds**/
    private void selectOrderWithFenYeUseRowBounds(SqlSession sqlSession) {
        List<Object> list = sqlSession.selectList("selectOrderWithFenYeUseRowBounds", null, new RowBounds(1, 5));
        System.out.println("count " + list.size());
        for(Iterator iterator = list.iterator();iterator.hasNext();){
            System.out.println(iterator.next());
        }
    }

    /**分页测试---使用原始的设置参数的方式**/
    private void selectOrderWithFenYeUseLimit(SqlSession sqlSession) {
        Map<String,Object> fenYeParams = new HashMap<String, Object>();
        fenYeParams.put("index",10);
        fenYeParams.put("count",5);
        List<Object> list = sqlSession.selectList("selectOrderWithFenYeUseLimit", fenYeParams);
        System.out.println("count " + list.size());
        for(Iterator iterator = list.iterator();iterator.hasNext();){
            System.out.println(iterator.next());
        }
    }

    private void insertOrderTestTransation(SqlSession sqlSession) {

        try {
                sqlSession.getConnection().setAutoCommit(false);

                Order order = Order.buildOrderUseSpecifyId(54);
                Order order2 = Order.buildOrderUseSpecifyId(55);

                sqlSession.insert("insertOrderTestTransation",order);

                sqlSession.insert("insertOrderTestTransation",order2);

//                sqlSession.commit();
            }catch (Exception e){
                e.printStackTrace();
                sqlSession.rollback();
            }

    }

    private void selectOrderUseWhereNode(SqlSession sqlSession) {
        Order order = new Order();
        order.setId(0);
//        order.setAddress("%hun%");
//        order.setBuyerUserName("%ma%");
        List<Order> list = sqlSession.selectList("selectOrderUseWhereNode",order);
        for (Iterator<Order> iterator = list.iterator();iterator.hasNext();){
            System.out.println(iterator.next());
        }
    }

    // 测试#、$区别
    private void selectCommonTable(SqlSession sqlSession) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("tableName","T_ORDER");
        map.put("id",1);
        List<Order> list = sqlSession.selectList("selectCommonTable",map);
        for (Iterator<Order> iterator = list.iterator();iterator.hasNext();){
            System.out.println(iterator.next());
        }
    }

    private void selectOrderWithMutiCondtion(SqlSession sqlSession) {
        Order order = new Order();
        order.setId(0);
        order.setAddress("%hun%");
        List list = sqlSession.selectList("selectOrderWithMutiCondtion",order);
        System.out.println("size---" + list.size());
        for(Iterator<Order> iterator = list.iterator();iterator.hasNext();){
            System.out.println(iterator.next());
        }

    }

    /**插入并返回auto-generate-key*/
    private void insertOrderWithGenerateKey(SqlSession sqlSession) {
        Order order = Order.buildOrderUseAutoGenerateKey(29);
        int insertOrderWithGenerateKey = sqlSession.insert("insertOrderWithGenerateKey", order);
        System.out.println("lastInsertKey--" + order.getId());
    }

    /**批量插入(无法换回auto-generate-key)**/
    private void insertOrderUserEntityBatch(SqlSession sqlSession) {
        List<Order> orders = new ArrayList<Order>();
        orders.add(Order.buildOrderUseAutoGenerateKey(8));
        orders.add(Order.buildOrderUseAutoGenerateKey(9));
        orders.add(Order.buildOrderUseAutoGenerateKey(10));
        int r = sqlSession.insert("insertOrderUserEntityBatch",orders);
        System.out.println(r);

        for(Iterator<Order> iterator = orders.iterator(); iterator.hasNext();){
            System.out.println(iterator.next());
        }
    }


    /**使用数组插入*/
    private void inserOrderUseArray(SqlSession sqlSession) {
        Object[] objects = new Object[]{6,new Date(),13,"fds","hunan"};
//        Object[] objects = new Object[]{6,new Date(),13,null,"hunan"}; //支持null的形式
        int r = sqlSession.insert("inserOrderUseArray",objects);
        System.out.println(r);
    }

    /*插入Order，使用list*/
    private void inserOrderUseList(SqlSession sqlSession) {
        List<Object> list = new ArrayList<Object>();
        list.add(5);
        list.add(new Date());
        list.add(12);
        list.add("fasd");
        list.add("yongzhou");
        int i = sqlSession.insert("inserOrderUserList",list);
        System.out.println(i);

    }

    /**使用map的方式插入*/
    private void insertOrderUserMap(SqlSession sqlSession) {
        Map<String,Object> user = new HashMap<String, Object>();
        user.put("order_Id",4);
        user.put("create_Date",new Date());
        user.put("price",12);
        user.put("buyer_User_Name","heliyuhaha");
        user.put("address","yongzhou");
        int i = sqlSession.insert("insertOrderUserMap",user);
        System.out.println(i);
    }

    /*参数使用用户实体对象插入的方式*/
    private void insertOrderUserEntity(SqlSession sqlSession) {
        Order order = new Order();
        order.setOrderId(1);
        order.setCreateDate(new Date());
        order.setAddress("hunan yongzhou");
        order.setBuyerUserName("heliyu");
        order.setPrice(100);
        int i = sqlSession.insert("mybatis.mapper.ComplicateMapper.insertOrderUserEntity",order);
        System.out.println(i);
    }


}
