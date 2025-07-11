<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<br>

<c:if test="${not empty users}">
<div>
    <div>ID : ${users.user_id}</div>
    <div>PW : ${users.user_pw}</div>
    <div>이름 : ${users.user_name}</div>
    <div>닉네임 : ${users.user_nickname}</div>
    <div>머니 : ${users.user_money}</div>
</div>
    <br>
    <button id="openModal">인벤토리</button>
</c:if>

<div id="modal-sign"class="modal"style="display:none">
<form action="/sign"method="post">
    <div>회원 가입</div>
    <div>
        <input type="text" name="user_id" placeholder="id">
    </div>
    <div>
        <input type="text" name="user_pw" placeholder="pw">
    </div>
    <div>
        <input type="text" name="user_name" placeholder="이름">
    </div>
    <div>
        <input type="text" name="user_nickname" placeholder="닉네임">
    </div>
    <button id="openModal-sign">회원가입</button>
    <button id="closeModal-sign">X</button>
</form>
</div>
<div id="modal" class="modal" style="display: none;">
    <div class="modal-body">
        <div>인벤토리 </div>
        <div>인벤토리 내용</div>
        <button id="closeModal">X</button>
    </div>

</div>
<script>
<!--비밀번호토글기능-->
function toggle(){
    const pwField = document.getElementById("pw");
    const isHidden = pwField.getAttribute("type");

    if(isHidden=="password"){
        pwField.setAttribute("type","text");
    }else{
        pwField.setAttribute("type","password");
    }
}
</script>
<script>
<!--모달창중앙위치기능-->
function centerModal(modalId){
    var modal = document.getElementById(modalId);
    var modalWidth = modal.offsetWidth;
    var modalHeight = modal.offsetHeight;
    var windowWidth = window.innerWidth;
    var windowHeight = window.innerHeight;

    var top = (windowHeight - modalHeight) / 2;
    var left = (windowWidth - modalWidth) / 2;

    modal.style.top = top + "px";
    modal.style.left = left + "px";
}
// <!--회원가입 모달창 기능-->
$(document).ready(function() {
    $('#openModal-sign').click(function () {
        $('#modal-sign').fadeIn();
        centerModal('modal-sign');
    });
    $('#closeModal-sign').click(function () {
        $('#modal-sign').fadeOut();
    });

// <!--인벤토리 모달창 기능-->
        $('#openModal').click(function () {
            $('#modal').fadeIn();
            centerModal('modal');
        });
        $('#closeModal').click(function () {
            $('#modal').fadeOut();
        });
        $('#modal').click(function (e) {// 클릭한 대상 modal-body가 아닌 경우 닫기
            if (!$(e.target).closest('.modal-body').length) {
                $('#modal').fadeOut();
            }

        });
    });

</script>

    <title>mini game</title>
    <link rel="stylesheet" href="/resources/css/login.css" />
</head>
<body>
<div class="login-container">
    <c:choose>
        <c:when test="${empty users}">
            <form action="login" method="post" class="login-form">
                <h2>로그인</h2>
                <input type="text" name="id" placeholder="아이디" required>
                <input type="password" name="pw" placeholder="비밀번호" required>
                <button type="submit">로그인</button>
                <div class="sub-actions">
                    <button type="button" onclick="location.href='register'">회원가입</button>
                    <button type="button" onclick="location.href='findAccount'">아이디/비밀번호 찾기</button>
                </div>
            </form>
        </c:when>

        <c:otherwise>
            <div class="welcome-box">
                <span>${users.user_id}님 환영합니다</span>
                <form action="logout" method="post">
                    <button type="submit">로그아웃</button>
                </form>
            </div>
        </c:otherwise>
    </c:choose>

    <c:if test = "${not empty alert}">
        <script>alert('${alert}')</script>
    </c:if>
    <c:if test = "${not empty alert2}">
        <script>alert('${alert2}')</script>
    </c:if>
</div>
</body>
</html>