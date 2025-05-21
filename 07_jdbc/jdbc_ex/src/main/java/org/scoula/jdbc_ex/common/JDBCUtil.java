package org.scoula.jdbc_ex.common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
    static Connection conn = null;

    public static Connection getConnection() {

        if(conn!=null) return conn;

        Properties prop = new Properties();

        try {
            prop.load(JDBCUtil.class.getResourceAsStream("/application.properties"));

            System.out.println("props" + prop);

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String id = prop.getProperty("user");
            String password = prop.getProperty("password");

            Class.forName(driver);

            conn = DriverManager.getConnection(url, id, password);
            System.out.println("연결성공 연결객체 : " + conn);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
    public static void close() {
        try {
            if(conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
