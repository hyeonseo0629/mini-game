<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ page
contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>home page</title>
    <link rel="stylesheet" href="/resources/css/main.css" />
    <link rel="stylesheet" href="/resources/css/login.css" />
    <link rel="stylesheet" href="/resources/css/shop.css" />
    <link rel="stylesheet" href="/resources/css/board.css" />
    <link rel="stylesheet" href="/resources/css/game.css" />
    <link rel="stylesheet" href="/resources/css/ranking.css" />

    <link
      rel="icon"
      href="/resources/images/favicon.ico?v=2"
      sizes="32x32"
      type="image/x-icon"
    />
    <%--
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    --%>
  </head>
  <body>
    <div class="main-container">
      <nav class="lrzone menu-area" style="background-color: red">
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
        <jsp:include page="${content}" />
      </div>

      <nav class="lrzone info-area">
        <jsp:include page="login/login.jsp" />
      </nav>
    </div>
  </body>
  <script src="/resources/js/main.js"></script>
  <script src="/resources/js/login.js"></script>
  <script src="/resources/js/shop.js"></script>
  <script src="/resources/js/texts.js"></script>
</html>
