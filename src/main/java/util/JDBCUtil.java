package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    private final String URL = "jdbc:postgresql://localhost:5432/banco_app";
    private final String USER = "postgres";
    private final String PASSWORD = "123456";
    private static JDBCUtil jdbc = null;

    private JDBCUtil() {
    }

    public static JDBCUtil getJdbc() {
        if (jdbc == null){
            jdbc = new JDBCUtil();
        }
        return jdbc;
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            if(conn != null) return conn;
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao PostgreSQL", e);
        }
    }

    public String getURL() {
        return URL;
    }

    public String getUSER() {
        return USER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }
}
