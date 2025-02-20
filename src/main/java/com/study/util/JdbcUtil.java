package com.study.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JDBC 공통화
 */
public class JdbcUtil {
    private final String DB_URL = "jdbc:mysql://localhost:3306/ebrainsoft_study";
    private final String USER = "ebsoft";
    private final String PASS = "ebsoft";

    private static JdbcUtil jdbcUtil;

    public static JdbcUtil getJdbcUtil() throws ClassNotFoundException {
        if (jdbcUtil == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            jdbcUtil = new JdbcUtil();
        }
        return jdbcUtil;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
