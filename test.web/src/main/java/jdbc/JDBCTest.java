package jdbc;


import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2015/12/7.
 */
public class JDBCTest {

    private static String JDBC_URL = "jdbc:mysql://192.168.120.188:3306/test";

    private static String USER_NAME = "root";

    private static String PASSWORD = "123456";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        openConnectionWithEditable();
//        queryCount();
//        queryMutiTables();
//        testDataFormat();
//        insertTimer();
//        getTimer();
    }

    private static void getTimer() throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
//        String selectSQL = "SELECT date from TIMETEST where id = ?";  //date
//        String selectSQL = "SELECT time from TIMETEST where id = ?";    //date
//        String selectSQL = "SELECT datetime from TIMETEST where id = ?";    //datetime
        String selectSQL = "SELECT timestamp from TIMETEST where id = ?";    //timestamp

        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setInt(1,9);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            System.out.println(resultSet.getTimestamp(1));
//            System.out.println(resultSet.getTimestamp(1));
//            Time time = resultSet.getTime(1);
//            System.out.println(time);
//            System.out.println(format(resultSet.getDate(1)));
        }

    }

    private static String format(java.util.Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");

        return dateFormat.format(date);
    }

    private static void testDataFormat() {
        java.util.Date data = new java.util.Date(System.currentTimeMillis());
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dataFormat.format(data));
    }

    /**插入各种时间..*/
    private static void insertTimer() throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
        connection.setAutoCommit(false);
        try{
//            String insertSql = "INSERT INTO TIMETEST (`date`) VALUES (?)";    //date
//            String insertSql = "INSERT INTO TIMETEST (`time`) VALUES (?)";    //time
//            String insertSql = "INSERT INTO TIMETEST (`datetime`) VALUES (?)";  //datetime
            String insertSql = "INSERT INTO TIMETEST (`timestamp`) VALUES (?)";    //timestamp

            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);

//            preparedStatement.setDate(1, new Date(1));
//            preparedStatement.setTime(1,new Time(System.currentTimeMillis()));
//            preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setTimestamp(1,new Timestamp(System.currentTimeMillis()));
            int count = preparedStatement.executeUpdate();
            System.out.println(count);
            connection.commit();
        }catch (Exception e){
            e.printStackTrace();
            connection.rollback();
        }finally {

        }

    }

    private static void queryCount() throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) as User_count FROM USER");
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        for (int i = 0 ; i < metaData.getColumnCount();i++){
            System.out.println(metaData.getColumnName(i+1));
            System.out.println(metaData.getColumnLabel(i+1));
            System.out.println(metaData.getColumnType(i+1));

        }
        if(resultSet.next()){ //必须要next一下
            System.out.println(resultSet.getLong(1));
        }

    }


    /**
     * 查询多表
     */
    private static void queryMutiTables() throws SQLException {
        Connection conn = (Connection) DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);//获取连接
        PreparedStatement ps = conn.prepareStatement("select t1.id,t1.name as t1name,t2.name as t2name,t2.address from USER t1,CLASS t2 where t1.grade = t2.id;");

        ResultSet resultSet = ps.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        System.out.println("colunmCount:" + columnCount);
        for (int i = 0; i < columnCount;i++){
            System.out.println("colunmName:" + metaData.getColumnName(i+1));
            System.out.println("colunmLabel:" + metaData.getColumnLabel(i+1));
            System.out.println("colunmType:" + metaData.getColumnType(i+1));
            System.out.println("colunmClassName" + metaData.getColumnClassName(i+1));
            System.out.println("colunmSchemaName" + metaData.getSchemaName(i+1));
            System.out.println("-----------------");
        }

    }

    /**
     * 可对结果集更改的Connection
     **/
    private static void openConnectionWithEditable() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");//指定连接类型
        Connection conn = (Connection) DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);//获取连接
        PreparedStatement pst = conn.prepareStatement("select * from USER", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        ResultSet resultSet = pst.executeQuery();
//        int i = 0;
//        while(resultSet.next()){
//            System.out.println(++i);
//            resultSet.updateInt(4,10);
//        }
        resultSet.last();
        System.out.println(resultSet.getRow());
    }


    /***
     * CREATE TABLE `USER` (
     `id` int(4) NOT NULL,
     `name` char(20) NOT NULL,
     `sex` int(4) NOT NULL,
     `grade` int(11) DEFAULT NULL,
     PRIMARY KEY (`id`)
     ) ENGINE=InnoDB DEFAULT CHARSET=latin1


     CREATE TABLE `CLASS` (
     `id` int(11) NOT NULL,
     `name` varchar(20) DEFAULT NULL,
     `address` varchar(20) DEFAULT NULL
     ) ENGINE=InnoDB DEFAULT CHARSET=latin1


     */

}
