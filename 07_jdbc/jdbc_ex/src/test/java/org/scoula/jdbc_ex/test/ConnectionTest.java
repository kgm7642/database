package org.scoula.jdbc_ex.test;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConnectionTest {

    @Test
    void testConnection() throws ClassNotFoundException, SQLException {
        Connection con = null;

        // 1. 드라이버설정
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("1. 드라이버설정성공..");
        // 2. 데이터베이스연결
        String url = "jdbc:mysql://localhost:3306/jdbc_ex";
        String user = "scoula";
        String password = "1234";
        con = DriverManager.getConnection(url, user, password);
        System.out.println("2. db연결성공.");
        assertNotNull(con); // con 이 null이면 연결 안된거임
    }

    @Test
    void testConnection2() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String id = "root";
        String password = "1234";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try(Connection conn = DriverManager.getConnection(url, id, password)) {
            System.out.println("연결 성공!");

            assertNotNull(conn); // con 이 null이면 연결 안된거임
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
