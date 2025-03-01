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
            <input type="hidden" name="nowPage" id="nowPage" value="${pageDTO.nowPage}">
            <input type="hidden" name="startPage" id="startPage" value="${pageDTO.startPage}">
            <input type="hidden" name="endPage" id="endPage" value="${pageDTO.endPage}">

            <label for="srchRegDateStart">등록일</label>
            <input type="date" name="srchRegDateStart" id="srchRegDateStart"
                   class="input srchDateInput" max="9999-12-31" value="${boardSrchData.srchRegDateStart}">
            ~
            <input type="date" name="srchRegDateEnd" id="srchRegDateEnd"
                   class="input srchDateInput" max="9999-12-31" value="${boardSrchData.srchRegDateEnd}">
            <div class="disIn m20"></div>

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
            <div class="disIn m20"></div>

            <button class="button srchButton" id="searchBtn">검색</button>
        </form>
    </div>
    <div class="list">
        <span>총 ${pageDTO.dataCnt}건</span>
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
                <td class="center">${item.categoryName}</td>
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
    <div>
        <div class="center mt20">
            <ul id="page">
                <c:choose>
                    <c:when test="${pageDTO.endPage > pageDTO.lookPageCnt}"><%-- 화면에 보여줘야 하는 갯수보다 페이지가 많으면 --%>
                        <li class="disInBl mbt5" id="goFirst" value="${pageDTO.startPage}"><<</li>
                        <li class="disInBl mbt5" id="goBefore" value="${pageDTO.nowPage - 1}"><</li>

                        <c:choose>
                            <c:when test="${pageDTO.nowPage < pageDTO.lookPageCnt}"><%-- 1 2 3 ... 10 --%>
                                <c:forEach var="i" begin="${pageDTO.startPage}" end="${pageDTO.lookPageCnt}">
                                    <li class="disInBl mbt5 <c:if test="${i == pageDTO.nowPage}">txtRed</c:if>"
                                        value="${i}">${i}</li>
                                </c:forEach>
                                ...
                                <li class="disInBl mbt5" value="${pageDTO.endPage}">${pageDTO.endPage}</li>
                            </c:when>
                            <c:when test="${pageDTO.nowPage + 1 >= pageDTO.endPage}"><%-- 1 ... 8 9 10 --%>
                                <li class="disInBl mbt5" value="${pageDTO.startPage}">${pageDTO.startPage}</li>
                                ...
                                <c:forEach var="i" begin="${pageDTO.endPage - pageDTO.lookPageCnt + 1}" end="${pageDTO.endPage}">
                                    <li class="disInBl mbt5 <c:if test="${i == pageDTO.nowPage}">txtRed</c:if>"
                                        value="${i}">${i}</li>
                                </c:forEach>
                            </c:when>
                            <c:otherwise><%-- 1 ... 4 5 6 ... 10 --%>
                                <li class="disInBl mbt5" value="${pageDTO.startPage}">${pageDTO.startPage}</li>
                                ...
                                <c:forEach var="i" begin="${pageDTO.nowPage - 1}" end="${pageDTO.nowPage + 1}">
                                    <li class="disInBl mbt5 <c:if test="${i == pageDTO.nowPage}">txtRed</c:if>"
                                        value="${i}">${i}</li>
                                </c:forEach>
                                ...
                                <li class="disInBl mbt5" value="${pageDTO.endPage}">${pageDTO.endPage}</li>
                            </c:otherwise>
                        </c:choose>

                        <li class="disInBl mbt5" id="goAfter" value="${pageDTO.nowPage + 1}">></li>
                        <li class="disInBl mbt5" id="goLast" value="${pageDTO.endPage}">>></li>
                    </c:when>
                    <c:otherwise><%-- 화면에 보여줘야 하는 갯수보다 페이지가 많지 않으면 --%>
                        <li class="disInBl mbt5" id="goBefore" value="${pageDTO.nowPage - 1}"><</li>

                        <c:forEach var="i" begin="${pageDTO.startPage}" end="${pageDTO.endPage}">
                            <li class="disInBl mbt5 <c:if test="${i == pageDTO.nowPage}">txtRed</c:if>"
                                value="${i}">${i}</li>
                        </c:forEach>

                        <li class="disInBl mbt5" id="goAfter" value="${pageDTO.nowPage + 1}">></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>


    <script src="../../js/boardList.js"></script>
</body>
</html>
