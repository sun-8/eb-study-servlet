<%@ page import="com.study.util.CommonUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: rhdqn
  Date: 2025-02-17
  Time: 오후 2:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
    <link rel="stylesheet" type="text/css"  href="../../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/boardList.css"/>
    <title>게시판 목록</title>
</head>
<body class="body">
    <div class="search">
        <form id="srchForm" method="get" action="/board/free/list">
            <label for="srchRegDateStart">등록일</label>
            <input type="date" name="srchRegDateStart" id="srchRegDateStart"
                   class="input srchDateInput" max="9999-12-31" value="${boardSrchData.srchRegDateStart}">
            ~
            <input type="date" name="srchRegDateEnd" id="srchRegDateEnd"
                   class="input srchDateInput" max="9999-12-31" value="${boardSrchData.srchRegDateEnd}">
            <div class="distance"></div>
            <select class="select srchSelect" name="srchCategory">
                <option value="" selected>카테고리 선택</option>
                <c:forEach var="item" items="${categoryDTOList}">
                <option value="${item.id}" <c:if test="${item.id eq boardSrchData.srchCategory}"> selected </c:if>>
                    ${item.name}
                </option>
                </c:forEach>
            </select>
            <input type="text" name="srchWord" id="srchWord"
                   class="input srchWordInput" placeholder="검색어를 입력해 주세요. (제목+작성자+내용)"
                   value="${boardSrchData.srchWord}">
            <div class="distance"></div>
            <button class="button srchButton" id="searchBtn">검색</button>
        </form>
    </div>
    <div>
        <span>총 ${boardListCnt}건</span>
        <table class="table">
            <thead>
            <tr>
                <th class="wper7">카테고리</th>
                <th class="wper3"></th>
                <th class="wper60">제목</th>
                <th class="wper5">작성자</th>
                <th class="wper5">조회수</th>
                <th class="wper10">등록 일시</th>
                <th class="wper10">수정 일시</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${boardDTOList}">
            <tr>
                <td class="center">${item.categoryId}</td>
                <td class="center">
                    <c:if test="${!CommonUtil.isEmpty(item.file1)
                            || !CommonUtil.isEmpty(item.file2)
                            || !CommonUtil.isEmpty(item.file3)}">
                        <i class="fas fa-file"></i>
                    </c:if>
                </td>
                <td class="left">${CommonUtil.stringCut(item.title, 80)}</td>
                <td class="center">${item.userName}</td>
                <td class="center">${item.view}</td>
                <td class="center">${CommonUtil.nvl(item.regDttm, "-")}</td>
                <td class="center">${CommonUtil.nvl(item.modDttm, "-")}</td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="footer">

    </div>


    <script src="../../js/boardList.js"></script>
</body>
</html>
