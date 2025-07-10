<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>mini game</title>
    <link rel="stylesheet" href="resources/css/board.css">
</head>
<body>
<div class="board-text">
    <h1>공지사항</h1><br>
    <hr>
    <table>
        <thead>
        <tr>
            <th>No.</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
        </thead>
        <div style="width: 100%; display: flex; justify-content: center;">
            <div class="text-container">
                <c:forEach var="t" items="${notice}">
                    <div class="text-wrap">
                        <tr style="width: ">
                            <td class="text_id">${t.text_id}</td>
                            <td class="text_title">${t.text_title}</td>
                            <td class="text_user_no">${t.text_user_no}</td>
                            <td class="text_date">
                                <fmt:formatDate value="${t.text_write_date}" pattern="MM월 dd일" />
                            </td>
                        </tr>
                    </div>
                </c:forEach>
            </div>
        </div>
    </table>
</div>
</body>
</html>