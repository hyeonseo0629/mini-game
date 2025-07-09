<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <button onclick="location.href='CommunityPostC'">게시물 작성</button>
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
            <div class="commu-container">
                <c:forEach var="commu" items="${community}">
                    <div class="commu-wrap">
                        <tr style="width: ">
                            <td class="commu_id">${commu.text_id}</td>
                            <td class="commu_title">${commu.text_title}</td>
                            <td class="commu_user_no">${commu.text_user_no}</td>
                            <td class="commu_date">
                                <fmt:formatDate value="${commu.text_write_date}" pattern="MM월 dd일" />
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