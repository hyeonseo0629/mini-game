<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>home page</title>
    <link rel="stylesheet" href="/resources/css/main.css">
    <script src="/resources/js/main.js"></script>
</head>
<body onload="init('${isGamePage}')">
    <div class="main-container">
        <div class="hover-zone left"></div>
        <nav class="lrzone menu-area">
            <div>
                <button>게임</button>
            </div>
            <div>
                <button onclick="clicked(2)">상점</button>
            </div>
            <div>
              <button>랭킹</button>
            </div>
            <div>
               <button>게시판</button>
            </div>
            <div>
                <button>공지사항</button>
            </div>
            <div>
                <button>문의</button>
            </div>

        </nav>

        <div class="core-area">
            <jsp:include page="${content}"/>
        </div>

        <div class="lrzone info-area">
            I'm right zone.
            <form action="login"method="post">
               <input type="text"name="id"><br>
                <input type="text"name="pw"><br>
                <input type="submit"value="로그인">
                <input tyep="submit"value="회원가입">
                <input type="submit"value="아이디/비밀번호 찾기">

            </form>
            <c:if test = "${empty users}">
            <script>alert('${alert}')</script>
            </c:if>

        </div>
        <div class="hover-zone right"></div>
    </div>
</body>
</html>
