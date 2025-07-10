<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <title>mini game</title>
    <link rel="stylesheet" href="/resources/css/login.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script></script>
</head>
<body>
<c:choose>

<c:when test="${empty users}">
<form action="login"method="post">
    <input type="text"name="id"><br>
    <input type="text"name="pw"><br>
    <input type="submit"value="로그인">
    <input type="submit"value="회원가입">
    <input type="submit"value="아이디/비밀번호 찾기">
</form>
</c:when>

<c:otherwise>
    <span>${users.user_id}님</span>
          <br>
    <form action="logout" method="post">
    <button type="submit">로그아웃</button>
    </form>
</c:otherwise>

</c:choose>
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

<div id="modal" class="modal" style="display: none;">
    <div class="modal-body">
        <div>인벤토리 </div>
        <div>인벤토리 내용</div>
        <button id="closeModal">X</button>
    </div>

</div>

<!--로그인,로그아웃 알람-->
<c:if test = "${not empty alert}">
    <script>alert('${alert}')</script>
</c:if>

<c:if test = "${not empty alert2}">
    <script>alert('${alert2}')</script>
</c:if>
<!--인벤토리 모달창 기능-->
<script>

function centerModal(){
    var modal = document.getElementById("modal");
    var modalWidth = modal.offsetWidth;
    var modalHeight = modal.offsetHeight;
    var windowWidth = window.innerWidth;
    var windowHeight = window.innerHeight;

    var top = (windowHeight - modalHeight) / 2;
    var left = (windowWidth - modalWidth) / 2;

    modal.style.top = top + "px";
    modal.style.left = left + "px";

}

    $(document).ready(function() {
       $('#openModal').click(function() {
         $('#modal').fadeIn();
         centerModal();
       });
        $('#closeModal').click(function() {
         $('#modal').fadeOut();
         centerModal();
        });
    });

</script>

</body>
</html>