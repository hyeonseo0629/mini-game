<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kwaksummer
  Date: 2025. 7. 3.
  Time: 오후 4:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="game-container">
    <div>게임 6</div>
</div>
<div class="game-selection">
    <button class="select-button game-1" onclick="moveToController(1)">야추다이스</button>
    <button class="select-button game-2" onclick="moveToController(2)">게임 2</button>
    <button class="select-button game-3" onclick="moveToController(3)">게임 3</button>
    <button class="select-button game-4" onclick="moveToController(4)">게임 4</button>
    <button class="select-button game-5" onclick="moveToController(5)">게임 5</button>
    <button class="select-button game-6" onclick="moveToController(6)">게임 6</button>
</div>
<script src="${pageContext.request.contextPath}/resources/js/game.js"></script>