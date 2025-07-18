<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="board-text">
    <h1>${type}</h1><br>
    <c:if test="${not empty sessionScope.users}">
        <input type="hidden" id="loginCheck" value="true" />
    </c:if>
    <c:if test="${empty sessionScope.users}">
        <input type="hidden" id="loginCheck" value="false" />
    </c:if>
    <div class="button-container">
        <button type="button" onclick="postTexts()">작성</button>
    </div>
    <table>
        <thead>
        <tr>
            <th class="col-no">No.</th>
            <th class="col-title">제목</th>
            <th class="col-writer">작성자</th>
            <th class="col-date">작성일</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="t" items="${texts}">
            <tr class="text-row" data-id="${t.text_id}" data-title="${t.text_title}" data-content="${t.text_content}" data-nickname="${t.user_nickname}" data-date="${t.text_write_date}">
                <td class="text_id">${t.text_id}</td>
                <td class="text_title">${t.text_title}</td>
                <td class="text_user_no">${t.user_nickname}</td>
                <td class="text_date">
                    <fmt:formatDate value="${t.text_write_date}" pattern="MM월 dd일" />
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="paging">
        <c:forEach begin="1" end="${totalPage}" var="p">
            <a href="javascript:void(0);"
               class="page-link ${p == currentPage ? 'active' : ''}"
               data-page="${p}"
               data-type="${type}">${p}</a>
        </c:forEach>
    </div>


    <!-- 게시물 작성 모달 -->
<button id="openInsertBtn" style="display: none">입력 모달 열기</button>
<dialog id="insertModal">
        <h2>${type}</h2>
        <div>
            <div>title</div>
            <div><input type="text" name="text_title"></div>
        </div>
        <div>
            <div>content</div>
            <div><textarea name="text_content" id="" cols="30" rows="10"></textarea></div>
        </div>
        <button id="addBtn" data-type="${type}">업로드</button>
        <button id="closeBtn">닫기</button>
</dialog>
</div>

<!-- detail 모달 -->
<button id="openDetailBtn" style="display: none">상세 페이지 모달 열기</button>
<dialog id="detailModal">
    <h2><input id="detailTitle"  disabled></h2>
    <p ><textarea id="detailContent"  disabled></textarea></p>
    <p ><input id="detailNickname"  disabled ></p>
    <p id="detailDate">내용</p>

    <div class="btns">
        <button id="editBtn">수정</button>
        <button id="saveEditBtn" style="display: none;">저장</button> <%-- 이 부분이 추가되었는지 확인! --%>
        <button id="deleteBtn">삭제</button>
        <button id="closeDetailBtn">닫기</button>
    </div>
</dialog>

<%-- modal area --%>
<script src="/resources/js/modal.js" defer></script>
