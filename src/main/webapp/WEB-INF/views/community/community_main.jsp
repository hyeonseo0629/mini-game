<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>mini game</title>
    <link rel="stylesheet" href="resources/css/board1.css">
</head>
<body>
<div class="board-text">
    <h1>게시판</h1><br>
    <div class="button-container">
        <button onclick="location.href='CommunityPostC'">게시물 작성</button>
    </div>
    <table>
        <thead>
        <tr>
            <th>No.</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="t" items="${communityTexts}">
            <tr>
                <td class="text_id">${t.text_id}</td>
                <td class="text_title">${t.text_title}</td>
                <td class="text_user_no">${t.text_user_no}</td>
                <td class="text_date">
                    <fmt:formatDate value="${t.text_write_date}" pattern="MM월 dd일" />
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="paging">
        <!-- [1] [2] [3] ... -->
        <c:forEach begin="1" end="${totalPage}" var="p">
            <c:choose>
                <c:when test="${p == currentPage}">
                    <span>[${p}]</span>
                </c:when>
                <c:otherwise>
                    <a href="/CommunityC?page=${p}">[${p}]</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
</div>



</div>
</body>
</html>