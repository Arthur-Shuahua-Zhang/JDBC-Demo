package jdbcDemo;

import jdbcDemo.utils.JdbcConfig;

import java.sql.*;

public class JdbcRollBackInCatchTest {
    public static void main(String[] args) throws SQLException {
        try (
                Connection conn = DriverManager.getConnection(
                        JdbcConfig.getUrl(),
                        JdbcConfig.getUser(),
                        JdbcConfig.getPassword()
                );
                Statement stmt = conn.createStatement();
        ) {
            try {
                conn.setAutoCommit(false);
                // insert two statements
                stmt.executeUpdate("insert into books values ('AWS', 98, 12)");
                // duplicate primary key, which will trigger a SQLException
                stmt.executeUpdate("insert into books values ('AWS', 90, 15)");
                conn.commit();
            } catch (SQLException throwables) {
                System.out.println("rolling back changes");
                conn.rollback();
                throwables.printStackTrace();
            }


        }
    }
}
