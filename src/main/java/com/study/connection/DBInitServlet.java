package com.study.connection;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

public class DBInitServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        // servlet이 시작될 때 JDBC 드라이버 미리 로드
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver Loaded Successfully!");
        } catch (ClassNotFoundException ex) {
            System.out.println("MySQL JDBC Driver Not Found!");
        }
    }
}
