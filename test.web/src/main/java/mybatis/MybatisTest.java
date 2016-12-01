package mybatis;

import mybatis.entity.User;
import mybatis.entity.UserAndClass;
import mybatis.mapper.TestMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MybatisTest {

    public static void main(String[] args) throws FileNotFoundException {
//        runUseSpring();
        new MybatisTest().runUseConfiguration();
    }

    private static void runUseSpring() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");

        String[] strings = applicationContext.getBeanDefinitionNames();

        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }

        TestMapper mapper = applicationContext.getBean(TestMapper.class);
//        System.out.println(mapper.getCount());
    }

    public void runUseConfiguration() throws FileNotFoundException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(this.getClass().getClassLoader().getResourceAsStream("mybatis/mybatis.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();

//        insert(sqlSession);
//        getUser(sqlSession);
//        update(sqlSession);
//        getUserAndClass(sqlSession);
        getUserEntity(sqlSession);
//        getUserAndClassEntity(sqlSession);
//        getUserEntityByAnnotation(sqlSession);
//        selectCount(sqlSession);
//        selectCountByInterface(sqlSession);
    }

    private void getUserEntityByAnnotation(SqlSession sqlSession) {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        User user = testMapper.getUserFromAnnotation(1);
        System.out.println(user);
    }

    private void getUserAndClassEntity(SqlSession sqlSession) {
        List<UserAndClass> list = sqlSession.selectList("selectUserAndClassEntity",1);
        Iterator<UserAndClass> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    private void getUserEntity(SqlSession sqlSession) {
        User user = sqlSession.selectOne("getUserEntity",1);
        System.out.println(user);
    }

    private void getUserAndClass(SqlSession sqlSession) {
        List<Map<String, Object>> list = sqlSession.selectList("selectUserAndClass");
        System.out.println("list count:" + list.size());
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                System.out.println("key " + key + " value " + map.get(key));
            }
        }
    }

    private void update(SqlSession sqlSession) {
        User user = new User();
        user.setId(3);
        user.setGrade(3);
        int r = sqlSession.update("updateUser", user);
        sqlSession.commit();
    }

    private void insert(SqlSession sqlSession) {
        User user = new User();
        user.setId(5);
        user.setName("fuck");
        user.setSex(1);
        user.setGrade(1);

        int i = sqlSession.insert("insertUser", user);
        System.out.println(i);
        sqlSession.commit();
    }
    private void getUser(SqlSession sqlSession) {
        Map<String, Object> map = sqlSession.selectOne("getUser", 1);
        System.out.println(map);
    }

    private void selectCount(SqlSession sqlSession) {
        int count = sqlSession.selectOne("selectCount");
        System.out.println(count);
    }

    private void selectCountByInterface(SqlSession sqlSession){
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        int count = testMapper.selectCount();
        System.out.println(count);
    }

}
