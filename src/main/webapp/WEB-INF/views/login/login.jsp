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
            <div class="inventory-wrapper">
                <button class="slide-btn left" onclick="slide(-1)">&#8592;</button>

                    <div class="inventory-contents">
                        <div class="inventory-track">
                            <c:if test="${empty sessionScope.inventoryItems}">
                                <div>보유한 아이템이 없습니다</div>
                            </c:if>

                            <c:forEach var="item" items="${sessionScope.inventoryItems}">
                                <div class="inventory-item">
                                    <img src="/resources/images/${item.item_avatar_img}" alt="아바타" width="80">
                                    <div>${item.item_name}</div>
                                    <form action="/shop/applyAvatar" method="post">
                                        <input type="hidden" name="avatarImg" value="${item.item_avatar_img}">
                                        <button type="submit">적용</button>
                                    </form>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                <button class="slide-btn right" onclick="slide(1)">&#8594;</button>
            </div>
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

<div class="avatar_zone">
    <c:if test="${not empty users.user_avatar_img}">
        <img src="/resources/images/${users.user_avatar_img}" alt="현재 아바타" width="100">
    </c:if>
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

    let currentSlide = 0;
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
        currentSlide = 0;
        slide(0);
    }
    function closeInvenModal() {
        document.getElementById("invenModal").style.display = "none";
    }
    function backInvenModal(event) {
        if (event.target.id == "invenModal") {
            closeInvenModal();
        }
    }
    //인벤토리 모달 슬라이드 기능
    function slide(direction) {
        const track = document.querySelector('.inventory-track');
        console.log('track:', track);
        const items = document.querySelectorAll('.inventory-item');
        const container = document.querySelector('.inventory-contents');
        const totalItems = items.length;

        if (items.length === 0){
            console.log("보유한 아이템이 없습니다. 슬라이드할 필요가 없습니다.");
            return;}

        const itemStyle = window.getComputedStyle(items[0]);
        const itemMarginRight = parseFloat(itemStyle.marginRight); // "20px" -> 20 (숫자로 변환)
        const itemTotalWidth = items[0].offsetWidth + itemMarginRight; // 예: 130px + 20px = 150px

        // 한 번에 보이는 아이템 개수를 계산합니다.
        // 컨테이너의 너비(예: 300px)를 아이템 하나의 총 너비(예: 150px)로 나누어 몇 개가 보이는지 확인합니다.
        const visibleCount = Math.floor(container.offsetWidth / itemTotalWidth);

        // 최대 슬라이드 가능한 인덱스(위치)를 계산합니다.
        // 총 아이템 개수에서 한 번에 보이는 아이템 개수를 뺀 값입니다.
        // Math.max(0, ...)를 사용하여 음수가 되는 것을 방지합니다 (총 아이템이 보이는 아이템보다 적을 경우).
        const maxSlide = Math.max(0, items.length - visibleCount);

        // currentSlide 값을 업데이트하고 범위를 제한합니다.
        currentSlide += direction; // 슬라이드 방향(1 또는 -1)에 따라 currentSlide를 변경

        if (currentSlide < 0) {
            currentSlide = 0; // 0보다 작아지지 않도록 (맨 왼쪽)
        }
        if (currentSlide > maxSlide) {
            currentSlide = maxSlide; // maxSlide보다 커지지 않도록 (맨 오른쪽)
        }

        // track 요소의 CSS transform 속성을 업데이트하여 슬라이드를 실제로 움직입니다.
        const moveX = currentSlide * itemTotalWidth;
        track.style.transform = `translateX(-\${moveX}px)`;

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
    const box = document.createElement("div");
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