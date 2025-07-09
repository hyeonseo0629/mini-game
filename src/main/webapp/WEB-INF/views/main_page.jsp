<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>home page</title>
    <link rel="stylesheet" href="/resources/css/main.css">
    <script src="/resources/js/main.js"></script>
</head>
<body>
    <div class="main-container">
        <div class="hover-zone left"></div>
        <nav class="lrzone menu-area">
            <div>
                <button onclick="clicked(1)">게임</button>
            </div>
            <div>
                <button onclick="clicked(2)">상점</button>
            </div>
            <div>
                <button onclick="clicked(3)">랭킹</button>
            </div>
            <div>
                <button onclick="clicked(4)">게시판</button>
            </div>
            <div>
                <button onclick="clicked(5)">공지사항</button>
            </div>
            <div>
                <button onclick="clicked(6)">문의</button>
            </div>

        </nav>

        <div class="core-area">
            <jsp:include page="${content}"/>
        </div>

        <div class="lrzone info-area">
            <jsp:include page="login/login.jsp" />

            <br>
            <div>
                <div>마이 페이지</div>
                <div>ID : ${users.user_id}</div>
                <div>PW : ${users.user_pw}</div>
                <div>이름 : ${users.user_name}</div>
                <div>닉네임 : </div>
                <div>머니 : </div>

                <button>인벤토리</button>
            </div>
         </div>

        <div class="hover-zone right"></div>
    </div>
</body>
</html>
