<%@ page import="com.study.web.dto.BoardListDTO" %>
<%--
  Created by IntelliJ IDEA.
  User: rhdqn
  Date: 2025-02-17
  Time: 오후 2:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    BoardListDTO boardListDTO = (BoardListDTO)request.getAttribute("boardListDTO");
%>
<html>
<head>
    <link rel="stylesheet" type="text/css"  href="../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../css/boardList.css"/>
    <title>게시판 목록</title>
</head>
<body class="body">
    <div class="search">
        <label for="srchRegDateStart">등록일</label>
        <input type="date" name="srchRegDateStart" id="srchRegDateStart"
               class="input srchDateInput" max="9999-12-31" value=<%=boardListDTO.getSrchRegDateStart()%>>
        ~
        <input type="date" name="srchRegDateEnd" id="srchRegDateEnd"
               class="input srchDateInput" max="9999-12-31" value=<%=boardListDTO.getSrchRegDateEnd()%>>
        <div class="distance"></div>
        <select class="select srchSelect">
            <option name="srchCategory" value="">카테고리 선택</option>
            <option name="srchCategory" value="01">JAVA</option>
            <option name="srchCategory" value="02">SERVLET</option>
            <option name="srchCategory" value="03">JSP</option>
            <option name="srchCategory" value="04">SQL</option>
        </select>
        <input type="text" name="srchWord" id="srchWord"
               class="input srchWordInput" placeholder="검색어를 입력해 주세요. (제목+작성자+내용)"
               value=<%=boardListDTO.getSrchWord()%>>
        <div class="distance"></div>
        <button class="button srchButton">검색</button>
    </div>
    <div class="list">

    </div>
    <div class="footer">

    </div>
</body>
</html>
