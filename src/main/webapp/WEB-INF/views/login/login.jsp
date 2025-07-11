<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
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