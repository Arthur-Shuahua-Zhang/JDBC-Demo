package jdbcDemo;

import jdbcDemo.utils.JdbcConfig;

import java.sql.*;

public class JdbcTransactionTest {

    public static void main(String[] args) {
        try (
                Connection conn = DriverManager.getConnection(
                        JdbcConfig.getUrl(),
                        JdbcConfig.getUser(),
                        JdbcConfig.getPassword()
                );
                Statement stmt = conn.createStatement();
        ) {
            conn.setAutoCommit(false);

            // before update
            String sqlSelect = "select * from books where title in ('Data', 'Math')";
            ResultSet resultSet = stmt.executeQuery(sqlSelect);
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String price = resultSet.getString("price");
                int qty = resultSet.getInt("qty");
                System.out.println(title + ", " + price + ", " + qty);
            }
            conn.commit();

            // update something
            stmt.executeUpdate("update books set qty = qty + 1 where title = 'Data'");
            stmt.executeUpdate("update books set qty = qty + 1 where title = 'Math'");
            conn.commit();

            // after update (commit)
            String sqlSelect2 = "select * from books where title in ('Data', 'Math')";
            ResultSet resultSet2 = stmt.executeQuery(sqlSelect2);
            while (resultSet2.next()) {
                String title = resultSet2.getString("title");
                String price = resultSet2.getString("price");
                int qty = resultSet2.getInt("qty");
                System.out.println(title + ", " + price + ", " + qty);
            }
            conn.commit();

            // update but rollback
            stmt.executeUpdate("update books set qty = qty + 1000 where title = 'Data'");
            stmt.executeUpdate("update books set qty = qty + 1000 where title = 'Math'");
            conn.rollback();

            // after update (rollback)
            String sqlSelect3 = "select * from books where title in ('Data', 'Math')";
            ResultSet resultSet3 = stmt.executeQuery(sqlSelect3);
            while (resultSet3.next()) {
                String title = resultSet3.getString("title");
                String price = resultSet3.getString("price");
                int qty = resultSet3.getInt("qty");
                System.out.println(title + ", " + price + ", " + qty);
            }
            conn.commit();



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }





}
