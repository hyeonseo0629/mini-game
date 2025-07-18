<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<h1>${texts_type}</h1><br>
<hr>
<div style="display: flex; justify-content: center;">
    <form action="TextInsertC" method="post">
        <input type="hidden" name="b" value="${param.b}">

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
                <button name="b" value="${param.b}">등록</button>
            </div>
        </div>
    </form>
</div>
