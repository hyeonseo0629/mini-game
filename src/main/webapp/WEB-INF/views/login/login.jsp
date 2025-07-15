<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>mini game</title>
    <link rel="stylesheet" href="/resources/css/login.css"/>
</head>
<body>
<br>
<div class="login-container">
    <!--로그인창-->
    <c:choose>
        <c:when test="${empty users}">
            <form action="login" method="post" class="login-form">
                <h2>로그인</h2>
                <input type="text" name="id"
                       value="${alert == 'id 불일치' ? '' : param.id}"
                       placeholder="${alert == 'id 불일치' ? '아이디 미존재' : '아이디'}" required>
                       <!--alert가 "id 불일치"이면 비우고/ 아니면 param.id(아이디)-->
                       <!--alert가 "id 불일치"이면 '아이디 미존재'/아니면 '아이디'-->
                <input type="password" name="pw"
                       value="${alert == 'pw 불일치' ? '' : param.pw}"
                       placeholder="${alert == 'pw 불일치' ? '비밀번호 불일치' : '비밀번호'}" required>
                <button type="submit">로그인</button>
                <div class="sub-actions">
                    <button onclick="openSignModal()" type="button">회원가입</button>
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

        <form id="deleteUser"action="deleteUser" method="post">
            <input type="hidden" name="user_id" value="${users.user_id}">
            <button type="button"onclick="checkDelete()">회원 탈퇴</button>
        </form>
    </div>
    </c:otherwise>
    </c:choose>
    <br>
    <!--마이페이지+인벤토리-->
    <c:if test="${not empty users}">
    <div>
        <div>ID : ${users.user_id}</div>
        <div>이름 : ${users.user_name}</div>
        <div>닉네임 : ${users.user_nickname}</div>
        <div>머니 : ${users.user_money}</div>
    </div>
    <br>

<button onclick="openInvenModal()" type="button">인벤토리</button>
<button onclick="openUpdateUser()"type="button">회원정보 수정</button>
</c:if>
</div>

<!--회원가입모달창-->
<div id="signModal" class="modal" style="display:none" onclick="backSignModal(event)"
onsubmit="return validateSignForm()">
    <form action="/sign" method="post">
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
        <button onclick="closeSignModal()" type="button">X</button>
    </form>
</div>

<!--인벤토리모달창-->
<div id="invenModal" class="modal" style="display: none" onclick="backInvenModal(event)">
    <div class="inven-modal-body">
        <div>인벤토리</div>
        <div>인벤토리 내용</div>
        <button onclick="closeInvenModal()">X</button>
    </div>
</div>

<!--회원정보수정모달창-->
<div id="updateModal" class="modal" style="display: none"onclick="backUpdateModal(event)">
    <div>
        <form action="/updateUser" method="post">
            <input type="hidden" name="originalId" value="${user.user_id}">
            <div>회원 정보 수정</div>
            <div>
                <label for="user_id">아이디</label>
                <input type="text" id="user_id" name="user_id" value="${user.user_id}">
            </div>
            <div>
                <label for="user_pw">비밀번호</label>
                <input type="text" name="user_pw"required placeholder="변경할 비밀번호를 입력하세요">
            </div>
            <div>
                <label for="user_name">이름</label>
                <input type="text" id="user_name"name="user_name" value="${user.user_name}">
            </div>
            <div>
                <label for="user_nickname">닉네임</label>
                <input type="text" id="user_nickname"name="user_nickname" value="${user.user_nickname}">
            </div>
            <button type="submit">정보수정</button>
            <button onclick="closeUpdateUser()" type="button">X</button>
        </form>
    </div>
</div>

<!--로그인알람-->
<c:if test="${alert == '로그인 성공'}">
    <script>
        if (!window.shownAlert) {
        alert('${alert}');
        window.shownAlert = true;
    }
    </script>
</c:if>

<!--로그아웃알람-->
<c:if test="${not empty alert2}">
    <script>
        if (!window.shownAlert) {
            alert('${alert2}');
            window.shownAlert = true;
        }
    </script>
</c:if>

<!-- 누락된 알림들도 처리하는 공통 alert 처리 -->
<c:if test="${not empty alert}">
    <script>
        if (!window.shownAlert) {
            alert('${alert}');
            window.shownAlert = true;
        }
    </script>
</c:if>

<script>
    <!--비밀번호토글기능-->
    function toggle() {
        const pwField = document.getElementById("pw");
        const isHidden = pwField.getAttribute("type");
        if (isHidden == "password") {
            pwField.setAttribute("type", "text");
        } else {
            pwField.setAttribute("type", "password");
        }
    }
</script>

<script>
    <!--회원가입모달창기능-->
    function openSignModal() {
        document.getElementById("signModal").style.display = "flex";
    }
    function closeSignModal() {
        document.getElementById("signModal").style.display = "none";
    }
    function backSignModal(event) {
        if (event.target.id == "signModal") {
            closeSignModal();
        }
    }
</script>

<script>
    <!--인벤토리모달창기능-->
    function openInvenModal() {
        document.getElementById("invenModal").style.display = "flex";
    }
    function closeInvenModal() {
        document.getElementById("invenModal").style.display = "none";
    }
    function backInvenModal(event) {
        if (event.target.id == "invenModal") {
            closeInvenModal();
        }
    }
</script>

<script>
    <!--정보수정모달창기능-->
    function openUpdateUser() {
        document.getElementById("updateModal").style.display = "flex";
    }
    function closeUpdateUser() {
        document.getElementById("updateModal").style.display = "none";
    }
    function backUpdateModal(event) {
        if (event.target.id == "updateModal") {
            closeUpdateUser();
        }
    }
</script>



<script>
    <!--회원가입필수입력기능-->
function validateSignForm(){
    const id = document.querySelector('input[name="user_id"]').value.trim();
    const pw = document.querySelector('input[name="user_pw"]').value.trim();
    const name = document.querySelector('input[name="user_name"]').value.trim();
    const nickname = document.querySelector('input[name="user_nickname"]').value.trim();
    if (!id) {
        alert("아이디를 입력해주세요.");
        return false;
    }
    if (!pw) {
        alert("비밀번호를 입력해주세요.");
        return false;
    }
    if (!name) {
        alert("이름을 입력해주세요.");
        return false;
    }
    if (!nickname) {
        alert("닉네임을 입력해주세요.");
        return false;
    }
    return true;
}
</script>

<script>
    <!--회원가입필수입력기능-->
    function validateSignForm(){
        const id = document.querySelector('input[name="user_id"]').value.trim();
        const pw = document.querySelector('input[name="user_pw"]').value.trim();
        const name = document.querySelector('input[name="user_name"]').value.trim();
        const nickname = document.querySelector('input[name="user_nickname"]').value.trim();
        if (!id) {
            alert("아이디를 입력해주세요.");
            return false;
        }
        if (!pw) {
            alert("비밀번호를 입력해주세요.");
            return false;
        }
        if (!name) {
            alert("이름을 입력해주세요.");
            return false;
        }
        if (!nickname) {
            alert("닉네임을 입력해주세요.");
            return false;
        }
        return true;
    }
</script>

<script>
    <!--삭제확인알람기능-->
    function checkDelete(){
        const checkUser = confirm("정말로 회원 탈퇴를 하시겠습니까?");
        if(checkUser){
            document.getElementById("deleteUser").submit();
        }else{
            alert("취소되었습니다.")
        }
    }
</script>
<script>
    <!--alret양식통일용-->
window.alert = function(message){
    const box = document.createElement("<div>");
    box.className = "cssAlert";
    box.textContent = message;
    document.body.appendChild(box);

    setTimeout(()=>{
     box.classList.add("fade-out");
     setTimeout(()=>box.remove(),300)
    },2500);
};
</script>

</body>
</html>