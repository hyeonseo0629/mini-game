<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>mini game</title>
    <link rel="stylesheet" href="resources/css/detail.css">
    <script src="/resources/js/texts.js"></script>
</head>
<body>
<div class="board-text">
    <h1>${texts_type}</h1><br>
    <form action="TextUpdatePageC" method="POST">
        <input type="hidden" name="text" value="${text.text_id}">
        <table>
            <thead>
            <tr>
                <th >No.</th>
                <th>제목</th>
                <th>내용</th>
                <th>user_no</th>
                <th>text_write_date</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td class="text_id">${text.text_id}</td>
                    <td class="text_title">${text.text_title}</td>
                    <input type="hidden" name="text_title" value="${text.text_title}">
                    <td class="text_content">${text.text_content}</td>
                    <input type="hidden" name="text_content" value="${text.text_content}">
                    <td class="text_user_no">${text.text_user_no}</td>
                    <td class="text_date">
                        <fmt:formatDate value="${text.text_write_date}" pattern="MM월 dd일" />
                    </td>
                </tr>
            </tbody>
        </table>
        <div class="button-container">
            <button >게시물 수정</button>
            <button type="button" onclick="deleteTexts(${text.text_id})">게시물 삭제</button>
        </div>
    </form>

</div>
</body>
</html>