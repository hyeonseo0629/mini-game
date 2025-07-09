<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>mini game</title>
    <link rel="stylesheet" href="resources/css/community.css">
</head>
<body>
<div class="commu">
        <h1>게시판</h1><br>
        <hr>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
        </thead>
    <div style="width: 100%; display: flex; justify-content: center;">
        <div class="commu-container">
    <c:forEach var="commu" items="${community}">
        <div class="commu-wrap">
            <tr style="width: ">
                <td class="commu_id">${commu.text_id}</td>
                <td class="commu_title">${commu.text_title}</td>
                <td class="commu_user_no">${commu.text_user_no}</td>
                <td class="commu_date">${commu.text_write_date}</td>
            </tr>
        </div>
    </c:forEach>
        </div>
    </div>
    </table>
</div>
</body>
</html>