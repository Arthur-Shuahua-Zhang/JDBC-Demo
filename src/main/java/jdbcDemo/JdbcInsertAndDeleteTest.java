package jdbcDemo;

import jdbcDemo.utils.JdbcConfig;

import java.sql.*;

public class JdbcInsertAndDeleteTest {
    public static void main(String[] args) {
        try(
                Connection conn  = DriverManager.getConnection(
                        JdbcConfig.getUrl(),
                        JdbcConfig.getUser(),
                        JdbcConfig.getPassword()
                );
                Statement stmt = conn.createStatement();
        ){
            System.out.println("---------delete-----------");
            String sqlDelete = "delete from books where title = 'Java'";
            int countDelete = stmt.executeUpdate(sqlDelete);
            System.out.println(countDelete + " records are deleted");


            System.out.println("---------insert one record-----------");
            String sqlInsert = "insert into books" +
                    " values ('Math', 100, 4)";
            int countInsert = stmt.executeUpdate(sqlInsert);
            System.out.println(countInsert + " records are inserted");


            System.out.println("---------insert multiple records-----------");
            String sqlMultiInsert = "insert into books values " +
                    "('English', 123, 4), "
                    + "('Net', 321, 5)";
            int countMultiInsert = stmt.executeUpdate(sqlMultiInsert);
            System.out.println(countMultiInsert + " records are inserted");


            System.out.println("---------partial insert-----------");
            String sqlPartialInsert = "insert into books (title, price)" +
                    "values ('Data', 458)";
            int countPartialInsert = stmt.executeUpdate(sqlPartialInsert);
            System.out.println(countPartialInsert + " records are inserted");




            String sqlSelect = "select * from books";
            ResultSet resultSet = stmt.executeQuery(sqlSelect);

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String price = resultSet.getString("price");
                int qty = resultSet.getInt("qty");
                System.out.println(title + ", " + price + ", " + qty);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
