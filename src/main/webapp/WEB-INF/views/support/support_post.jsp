<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>mini game</title>
</head>
<body>
<h1>문의</h1><br>
<hr>
<div style="display: flex; justify-content: center;">
    <form action="add-post" method="post">
        <div class=""></div>
        <div>
            <div>제목</div>
            <div>
                <input name="text_title" placeholder="제목을 입력해주세요.">
            </div>
        </div>

        <div>
            <div>내용</div>
            <div>
                <textarea rows="5" cols="40" name="story"></textarea>
            </div>
            <div>
                <div>
                    <button type="button" onclick="">업로드</button>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>