<%@ page import="com.study.connection.ConnectionTest" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%-- 시작 페이지를 /boardList로 설정--%>
<% response.sendRedirect("/board/free/list"); %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>


<%

    ConnectionTest t = new ConnectionTest();
    out.println(t.getConnection());

%>

</body>
</html>
