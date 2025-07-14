<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>mini game</title>
</head>
<body>
<h1>게시판</h1><br>
<hr>
<div style="display: flex; justify-content: center;">
    <form action="commu-update-texts" method="post">
        <input type="hidden" name="text_type" value="community">
        <input type="hidden" name="text_user_no" value="${text.text_user_no}" readonly>
        <input type="hidden" name="text_id" value="${text.text_id}">

        <div class="sup">
            <div>
                <div class="col-1">제목</div>
                <div class="col-2"><input name="text_title" value="${text.text_title}"></div>
            </div>
            <div>
                <div class="col-1">내용</div>
                <div class="col-2"><textarea name="text_content" rows="10" value="${text.text_content}">${text.text_content}</textarea></div>
            </div>
            <div>
                <button type="submit">수정</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>