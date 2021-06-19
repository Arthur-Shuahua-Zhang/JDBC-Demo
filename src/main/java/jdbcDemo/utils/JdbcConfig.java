package jdbcDemo.utils;

public class JdbcConfig {

    private static final String url = "jdbc:mysql://localhost:3306/jdbc-demo";
    private static final String user = "root";
    private static final String password = "password";

    public JdbcConfig() {
    }

    public static String getUrl() {
        return url;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }
}
