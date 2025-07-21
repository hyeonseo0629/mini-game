<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="right-zone">
    <div class="login-container">
        <!--로그인창-->
        <c:choose>
            <c:when test="${empty users}">
                <form action="/login" method="post" class="login-form">
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
                        <button onclick="openFindModal()" type="button">아이디/비밀번호 찾기</button>
                    </div>
                </form>
            </c:when>
            <c:otherwise>
                <!--로그인 완료창-->
                <div class="welcome-box">
                    <button onclick="openInvenModal()" type="button">Inventory</button>
                    <button onclick="openMyPageModal()" type="button">Profile</button>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <br>

    <!--마이페이지+인벤토리-->
    <div id="myPageModal" class="myPageModal" style="display: none" onclick="backMyPageModal(event)">
        <div class="mypage-box">
            <span>마이페이지</span>
            <div class="mypage-info">
                <div>ID : ${users.user_id}</div>
                <div>이름 : ${users.user_name}</div>
                <div>닉네임 : ${users.user_nickname}</div>
                <div>머니 : ${users.user_money}</div>
            </div>

            <div class="another-box">
                <form action="/logout" method="post">
                    <button type="submit">로그아웃</button>
                </form>
                <button onclick="openUpdateUser()" type="button">회원정보 수정</button>
                <form id="deleteUser" action="/deleteUser" method="post">
                    <input type="hidden" name="user_id" value="${users.user_id}">
                    <button type="button" onclick="checkDelete()">회원 탈퇴</button>
                </form>
            </div>
            <button onclick="closeMyPageModal()" type="button" class="close-btn">X</button>
        </div>
    </div>

    <!--회원가입모달창-->
    <div id="signModal" class="userInfoModal" style="display:none" onclick="backSignModal(event)"
         onsubmit="return validateSignForm()">
        <div class="signModal-box">
            <form action="/sign" method="post">
                <div>회원 가입</div>
                <div class="group-form">
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
                    <div>
                        <input type="text" name="user_email" placeholder="이메일">
                    </div>
                </div>
                <button class="openModal-sign">회원가입</button>
                <button onclick="closeSignModal()" type="button" class="close-btn">X</button>
            </form>
        </div>
    </div>

    <!--아이디/비번찾기모달창-->
    <div id="findModal" class="userInfoModal" style="display: none" onclick="backFindModal(event)">
        <div class="findModal-box">

            <div class="tab-button">
                <button onclick="showTab('id')" id="idTab-btn" class="active">아이디 찾기</button>
                <button onclick="showTab('pw')" id="pwTab-btn">비밀번호 찾기</button>
            </div>

            <div id="idTab">
                <div>아이디</div>
                <form id="findIdForm">
                    <div>이름 :</div>
                    <input type="text" id="user_name" name="user_name" required>
                    <div>이메일 :</div>
                    <input type="text" id="user_email" name="user_email" required>
                    <button type="submit">아이디 찾기</button>
                </form>
                <div id="idResult" style="margin-top:10px; color:blue;"></div>
                <button onclick="closeFindModal()" type="button" class="close-btn">X</button>
            </div>

            <div id="pwTab">
                <div>비밀번호</div>
                <form id="findPwForm" action="/findPw" method="post">
                    <div>이름 :</div>
                    <input type="text" id="user_name_pw" name="user_name" required>
                    <div>아이디 :</div>
                    <input type="text" id="user_id_pw" name="user_id" required>
                    <div>이메일 :</div>
                    <input type="text" id="user_email_pw" name="user_email" required>
                    <div>새로운 비밀번호 :</div>
                    <input type="password" id="new_pw" name="new_pw" required>
                    <button type="submit">비밀번호 변경</button>
                </form>
                <div id="pwResult" style="margin-top:10px; color:blue;"></div>
                <button onclick="closeFindModal()" type="button" class="close-btn">X</button>
            </div>
        </div>
    </div>

    <!--인벤토리모달창-->
    <div id="invenModal" class="userInfoModal" style="display: none" onclick="backInvenModal(event)">
        <div class="inven-modal-body">
            <span>인벤토리</span>
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
            <button onclick="closeInvenModal()" class="close-btn">X</button>
        </div>

        <!--회원정보수정모달창-->
        <div id="updateModal" class="userInfoModal" style="display: none" onclick="backUpdateModal(event)">
            <div class="updateModal-box">
                <form action="/updateUser" method="post">
                    <input type="hidden" name="originalId" value="${user.user_id}">
                    <div>회원 정보 수정</div>
                    <div>
                        <label for="user_id">아이디</label>
                        <input type="text" id="user_id" name="user_id" value="${user.user_id}">
                    </div>
                    <div>
                        <label for="user_pw">비밀번호</label>
                        <input type="text" name="user_pw" required placeholder="변경할 비밀번호를 입력하세요">
                    </div>
                    <div>
                        <label for="user_name">이름</label>
                        <input type="text" id="user_name" name="user_name" value="${user.user_name}">
                    </div>
                    <div>
                        <label for="user_nickname">닉네임</label>
                        <input type="text" id="user_nickname" name="user_nickname" value="${user.user_nickname}">
                    </div>
                    <div>
                        <label for="user_email">이메일</label>
                        <input type="text" id="user_email" name="user_email" value="${user.user_email}">
                    </div>
                    <button type="submit">정보수정</button>
                    <button onclick="closeUpdateUser()" type="button" class="close-btn">X</button>
                </form>
            </div>
        </div>
    </div>
    <div class="avatar_zone">
        <c:if test="${not empty users.user_avatar_img}">
            <img src="/resources/images/${users.user_avatar_img}" alt="현재 아바타" width="100">
        </c:if>
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