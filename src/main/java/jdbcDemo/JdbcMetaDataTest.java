package jdbcDemo;

import jdbcDemo.utils.JdbcConfig;

import java.sql.*;

public class JdbcMetaDataTest {

    public static void main(String[] args) throws SQLException {
        try (
                Connection conn = DriverManager.getConnection(
                        JdbcConfig.getUrl(),
                        JdbcConfig.getUser(),
                        JdbcConfig.getPassword()
                );
                Statement stmt = conn.createStatement();
        ) {
            ResultSet rset = stmt.executeQuery("select * from books");
            ResultSetMetaData rsetMD = rset.getMetaData();
            int numColumns = rsetMD.getColumnCount();

            // print column class names
            for (int i=1; i<= numColumns; i++) {
                System.out.printf("%-30s", rsetMD.getColumnName(i));
            }
            System.out.println();

            // print column contents for all the rows
            while (rset.next()) {
                for (int i=1; i<=numColumns; i++) {
                    System.out.printf("%-30s", rset.getString(i));
                }
                System.out.println();
            }


        }
    }
}
