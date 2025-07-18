<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="board-text">
    <h1>${textVO.text_type}</h1><br>
    <c:if test="${not empty sessionScope.users}">
        <input type="hidden" id="loginCheck" value="true" />
    </c:if>
    <c:if test="${empty sessionScope.users}">
        <input type="hidden" id="loginCheck" value="false" />
    </c:if>
    <button onclick="location.href='/text/${fn:toLowerCase(textVO.text_type)}'">목록</button>
    <form action="TextUpdatePageC" method="POST">
        <input type="hidden" name="text" value="${textVO.text_id}">
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
                    <td class="text_id">${textVO.text_id}</td>
                    <td class="text_title">${textVO.text_title}</td>
                    <input type="hidden" name="text_title" value="${textVO.text_title}">
                    <td class="text_content">${textVO.text_content}</td>
                    <input type="hidden" name="text_content" value="${textVO.text_content}">
                    <td class="text_user_no">${textVO.text_user_no}</td>
                    <td class="text_date">
                        <fmt:formatDate value="${textVO.text_write_date}" pattern="MM월 dd일" />
                    </td>
                </tr>
            </tbody>
        </table>
        <div class="button-container">
            <button type="button" onclick="updateTexts(${textVO.text_id})">게시물 수정</button>
            <button type="button" onclick="deleteTexts(${textVO.text_id}, '${textVO.text_type}')">게시물 삭제</button>
        </div>
    </form>

</div>

<%--<button style="display: none" id="openBtn">모달 열기</button>--%>
<%--<dialog id="myModal">--%>
<%--    <h2>${type}</h2>--%>
<%--    <div>--%>
<%--        <div>title</div>--%>
<%--        <div><input type="text"></div>--%>
<%--    </div>--%>
<%--    <div>--%>
<%--        <div>content</div>--%>
<%--        <div><textarea name="" id="" cols="30" rows="10"></textarea></div>--%>
<%--    </div>--%>
<%--    <button id="closeBtn">닫기</button>--%>
<%--</dialog>--%>
<%--</div>--%>

<%--&lt;%&ndash; modal area &ndash;%&gt;--%>
<%--<script src="/resources/js/modal.js" defer></script>--%>
