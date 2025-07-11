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
    <form action="commu-insert-texts" method="post">
        <input type="hidden" name="text_type" value="COMMUNITY">
        ${users} <br>
        ${sessionScope.users}
        <input name="text_user_no" value="${users.user_no}">

        <div class="sup">
            <div>
                <div class="col-1">제목</div>
                <div class="col-2"><input name="text_title"></div>
            </div>
            <div>
                <div class="col-1">내용</div>
                <div class="col-2"><textarea name="text_content" rows="10"></textarea></div>
            </div>
            <div>
                <button type="submit">등록</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>