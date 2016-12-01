package jdbc;

import java.sql.*;

/**
 * Created by Administrator on 2015/12/12.
 */
public class JDBCBatchTest {

    private static String JDBC_URL = "jdbc:mysql://192.168.120.188:3306/test";

    private static String USER_NAME = "root";

    private static String PASSWORD = "123456";


    public static void main(String[] args) throws SQLException {
        new JDBCBatchTest().run();
    }

    public void run() throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
//        batchInsert(connection);
//        batchInsertWithReturnGenerateKey(connection);
//        batchUpdate(connection);
        testDiff_JING_And_Dollar(connection);
    }

    /**测试mybatis#号和$的区别*/
    private void testDiff_JING_And_Dollar(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ? WHERE id = ?");
        preparedStatement.setString(1,"batchTest");
        preparedStatement.setInt(2,1);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getInt(1) + " "+resultSet.getString(2));
        }
    }

    /**批量插入并且获取autogenerat key*/
    private void batchInsertWithReturnGenerateKey(Connection connection) {
        int[] ints = new int[0];
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO batchTest (name) VALUES (?)",Statement.RETURN_GENERATED_KEYS);
            for (int i = 26; i < 28; i++) {
                preparedStatement.setString(1, "name_" + i);
                preparedStatement.addBatch();
            }
            ints = preparedStatement.executeBatch();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()){
                System.out.println("aoto-generator Id = " + generatedKeys.getLong(1));
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        System.out.println("---------");
        printArray(ints);

    }

    private void batchUpdate(Connection connection) {

        int[] ints = new int[0];
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE batchTest SET name = ? WHERE id = ?");
            for (int i = 20; i < 25; i++) {
                preparedStatement.setString(1, "name_" + i);
                preparedStatement.setInt(2, i);
                preparedStatement.addBatch();
            }
            ints = preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        printArray(ints);
    }

    private void batchInsert(Connection connection) {
        int[] ints = new int[0];
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO batchTest (name) VALUES (?)");
            for (int i = 22; i < 26; i++) {
                preparedStatement.setString(1, "name_" + i);
                preparedStatement.addBatch();
            }
            ints = preparedStatement.executeBatch();
            ResultSet resultSet = preparedStatement.executeQuery("select Last_insert_ID() as ID;");
            if(resultSet.next()){
                System.out.println(resultSet.getLong("ID"));

            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        System.out.println("---------");
        printArray(ints);
    }

    private static void printArray(int[] objects){
        for(int i = 0 ; i < objects.length;i++){
            System.out.println(objects[i]);
        }
    }
}
