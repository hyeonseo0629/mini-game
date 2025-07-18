<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<h1>${texts_type}</h1><br>
<hr>
<div style="display: flex; justify-content: center;">
    <form action="/text/update" method="post">
        <input type="hidden" name="text_type" value="${fn:toLowerCase(textVO.text_type)}">
        <input type="hidden" name="text_user_no" value="${textVO.text_user_no}" readonly>
        <input type="hidden" name="text_id" value="${textVO.text_id}">

        <div class="sup">
            <div>
                <div class="col-1">제목</div>
                <div class="col-2"><input name="text_title" value="${textVO.text_title}"></div>
            </div>
            <div>
                <div class="col-1">내용</div>
                <div class="col-2"><textarea name="text_content" rows="10" value="${textVO.text_content}">${textVO.text_content}</textarea></div>
            </div>
            <div>
                <button type="submit">수정</button>
            </div>
        </div>
    </form>
</div>
