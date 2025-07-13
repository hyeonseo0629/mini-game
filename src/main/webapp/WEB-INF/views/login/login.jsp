<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>mini game</title>
    <link rel="stylesheet" href="/resources/css/login.css" />
</head>
<body>
<br>
<div class="login-container">
    <!--로그인창-->
    <c:choose>
    <c:when test="${empty users}">
    <form action="login" method="post" class="login-form">
        <h2>로그인</h2>
        <input type="text" name="id" placeholder="아이디" required>
        <input type="password" name="pw" placeholder="비밀번호" required>
        <button type="submit">로그인</button>
        <div class="sub-actions">
            <button onclick="openSignModal()" type="button" >회원가입</button>
            <button type="button" onclick="location.href='findAccount'">아이디/비밀번호 찾기</button>
        </div>
    </form>
    </c:when>

    <c:otherwise>
        <!--로그인완료창-->
    <div class="welcome-box">
        <span>${users.user_id}님 환영합니다</span>
        <form action="logout" method="post">
            <button type="submit">로그아웃</button>
        </form>
        <form action="deleteUser" method="post">
            <input type="hidden" name="user_id" value="${sessionScope.loginUser.user_id}">
            <button type="submit">회원 탈퇴</button>
        </form>
    </div>
    </c:otherwise>
    </c:choose>
<br>
    <!--마이페이지+인벤토리-->
<c:if test="${not empty users}">
<div>
    <div>ID : ${users.user_id}</div>
    <div>PW : ${users.user_pw}</div>
    <div>이름 : ${users.user_name}</div>
    <div>닉네임 : ${users.user_nickname}</div>
    <div>머니 : ${users.user_money}</div>
</div>
    <br>
    <button onclick="openInvenModal()" type="button">인벤토리</button>
</c:if>
</div>

    <!--회원가입모달창-->
<div id="signModal"class="modal"style="display:none"onclick="backSignModal(event)">
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
    <button class="openModal-sign">회원가입</button>
    <button onclick="closeSignModal()"type="button">X</button>
</form>
</div>

    <!--인벤토리모달창-->
<div id="invenModal" class="modal"style="display: none" onclick="backInvenModal(event)">
    <div class="inven-modal-body">
       <div>인벤토리 </div>
       <div>인벤토리 내용</div>
    <button onclick="closeInvenModal()">X</button>
    </div>
</div>

    <!--로그인알람-->
    <c:if test = "${not empty alert}">
        <script>if (!window.shownAlert) {
            alert('${alert}');
            window.shownAlert = true;
        }
        </script>
    </c:if>
    <c:if test = "${not empty alert2}">
        <script>
            if (!window.shownAlert) {
                alert('${alert2}');
                window.shownAlert = true;
            }
        </script>
    </c:if>

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
    function openSignModal(){
        document.getElementById("signModal").style.display="flex";
    }
    function closeSignModal(){
        document.getElementById("signModal").style.display="none";
    }
    function backSignModal(event){
        if(event.target.id=="signModal"){
            closeSignModal();
        }
    }
</script>
<script>
    function openInvenModal(){
        document.getElementById("invenModal").style.display="flex";
    }
    function closeInvenModal(){
        document.getElementById("invenModal").style.display="none";
    }
    function backInvenModal(event){
        if(event.target.id=="invenModal"){
            closeInvenModal();
        }
    }
</script>

</body>
</html>