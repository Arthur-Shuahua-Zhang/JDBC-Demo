package jdbcDemo;

import jdbcDemo.utils.JdbcConfig;

import java.sql.*;

public class JdbcUpdateTest {
    public static void main(String[] args) {
        try(
                Connection conn  = DriverManager.getConnection(
                        JdbcConfig.getUrl(),
                        JdbcConfig.getUser(),
                        JdbcConfig.getPassword()
                );
                Statement stmt = conn.createStatement();
        ){
            String strUpdate = "update books set price = price * 2, qty = qty - 1 where title = 'Java'";

            System.out.println("The sql query is " + strUpdate);
            System.out.println();

            int countUpdate = stmt.executeUpdate(strUpdate);
            System.out.println(countUpdate + " records are updated");


            String strSelect = "select * from books where title = 'Java'";
            System.out.println("the sql query is " + strSelect);
            ResultSet resultSet = stmt.executeQuery(strSelect);

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
