<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <title>mini game</title>
  <script></script>
</head>
<body>
<c:choose>
<c:when test="${empty users}">
<form action="login"method="post">
    <input type="text"name="id"><br>
    <input type="text"name="pw"><br>
    <input type="submit"value="로그인">
    <input tyep="submit"value="회원가입">
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
<c:if test = "${not empty alert}">
    <script>alert('${alert}')</script>
</c:if>
<c:if test = "${not empty alert2}">
    <script>alert('${alert2}')</script>
</c:if>
</body>
</html>