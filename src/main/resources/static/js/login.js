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

<!--아이디/비번찾기모달창기능-->
function openFindModal() {
    document.getElementById("findModal").style.display = "flex";
}

function closeFindModal() {
    document.getElementById("findModal").style.display = "none";
}

function backFindModal(event) {
    if (event.target.id == "findModal") {
        closeFindModal();
    }
}

<!--아이디/비번찾기모달창 탭 기능-->
function showTab(tab) {
    document.getElementById('idTab').classList.remove('active');
    document.getElementById('pwTab').classList.remove('active');
    document.getElementById(tab + 'Tab').classList.add('active');

    document.getElementById('idTab-btn').classList.remove('active');
    document.getElementById('pwTab-btn').classList.remove('active');
    document.getElementById(tab + 'Tab-btn').classList.add('active');
}

<!--아이디 찾기 표시 기능-->
document.getElementById("findIdForm").addEventListener("submit", async function (e) {
    e.preventDefault();
    const form = e.target;
    const formData = new FormData(form);
    const response = await fetch("/findId", {
        method: "POST",
        body: formData
    });
    const result = await response.text();
    document.getElementById("idResult").innerText = result;
});

<!--비밀번호 찾기 표시 기능-->
document.getElementById("findPwForm").addEventListener("submit", async function (e) {
    e.preventDefault();
    const form = e.target;
    const formData = new FormData(form);

    const newPw = formData.get("newPw"); // 비밀번호 가져오기
    const pwPattern = /^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$/;

    if (!pwPattern.test(newPw)) {
        document.getElementById("pwResult").style.color = "red";
        document.getElementById("pwResult").innerText = "비밀번호는 8자 이상이며 대문자, 숫자, 특수문자를 포함해야 합니다.";
        return;
    }

    const response = await fetch("/findPw", {
        method: "POST",
        body: formData
    });
    const result = await response.text();
    document.getElementById("pwResult").innerText = result;
});

// 마이페이지 모달 창 기능
function openMyPageModal() {
    document.getElementById("myPageModal").style.display = "flex";
}

function closeMyPageModal() {
    document.getElementById("myPageModal").style.display = "none";
}

function backMyPageModal(event) {
    if (event.target.id == "myPageModal") {
        closeSignModal();
    }
}

// 인벤토리 모달 창 기능
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

    if (totalItems === 0) {
        console.log("보유한 아이템이 없습니다. 슬라이드할 필요가 없습니다.");
        return;
    }

    const itemStyle = window.getComputedStyle(items[0]);
    const itemMarginRight = parseFloat(itemStyle.marginRight); // "20px" -> 20 (숫자로 변환)
    const itemTotalWidth = items[0].offsetWidth + itemMarginRight; // 예: 130px + 20px = 150px
    console.log(itemTotalWidth);

    // 한 번에 보이는 아이템 개수를 계산합니다.
    // 컨테이너의 너비(예: 300px)를 아이템 하나의 총 너비(예: 150px)로 나누어 몇 개가 보이는지 확인합니다.
    const visibleCount = Math.floor(container.offsetWidth / itemTotalWidth);
    // console.log(visibleCount);

    // 최대 슬라이드 가능한 인덱스(위치)를 계산합니다.
    // 총 아이템 개수에서 한 번에 보이는 아이템 개수를 뺀 값입니다.
    // Math.max(0, ...)를 사용하여 음수가 되는 것을 방지합니다 (총 아이템이 보이는 아이템보다 적을 경우).
    const maxSlide = Math.max(0, items.length - visibleCount);
    // console.log(maxSlide);

    // currentSlide 값을 업데이트하고 범위를 제한합니다.
    currentSlide += direction; // 슬라이드 방향(1 또는 -1)에 따라 currentSlide를 변경

    if (currentSlide < 0) {
        currentSlide = 0; // 0보다 작아지지 않도록 (맨 왼쪽)
    }
    if (currentSlide > maxSlide) {
        currentSlide = maxSlide; // maxSlide보다 커지지 않도록 (맨 오른쪽)
    }

    // track 요소의 CSS transform 속성을 업데이트하여 슬라이드를 실제로 움직입니다.
    const moveX = -currentSlide * itemTotalWidth + 40;
    track.style.transform = "translateX(" + moveX + "px)";
}

// 정보수정 모달 창 기능
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

// 회원가입 필수 입력 기능
function validateSignForm() {
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

// 삭제 확인 알람 기능
function checkDelete() {
    const checkUser = confirm("정말로 회원 탈퇴를 하시겠습니까?");
    if (checkUser) {
        document.getElementById("deleteUser").submit();
    } else {
        alert("취소되었습니다.")
    }
}

// alret 양식 통일용
window.alert = function (message) {
    const box = document.createElement("div");
    box.className = "cssAlert";
    box.textContent = message;
    document.body.appendChild(box);

    setTimeout(() => {
        box.classList.add("fade-out");
        setTimeout(() => box.remove(), 300)
    }, 2500);
};