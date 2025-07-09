<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>mini game</title>
    <link rel="stylesheet" href="resources/css/support.css">
</head>
<body>
<div>
    <h1>문의</h1><br>
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
            <div class="sup-container">
        <c:forEach var="sup" items="${support}">
            <div class="sup-wrap">
                <tr style="width: ">
                    <td class="sup_id">${sup.text_id}</td>
                    <td class="sup_title">${sup.text_title}</td>
                    <td class="sup_user_no">${sup.text_user_no}</td>
                    <td class="sup_date">${sup.text_write_date}</td>
                </tr>
            </div>
        </c:forEach>
        </div>
    </div>

    </table>
</div>
</body>
</html>