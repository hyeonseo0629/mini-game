<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

    <link rel="stylesheet" href="/resources/css/shop.css">
    <script src="/resources/js/shop.js"></script>
    <title>shop main</title>
</head>
<body>
<h1>상점</h1>
<br>
<hr>
<br>
<c:forEach var="i" items="${itemsInfo}">
<form action="/buyItem" method="post">
    <div class="shop-container">
        <div class="item-img">${i.item_avatar_img}</div>
        <div class="item-name">${i.item_name}</div>
        <div class="underside">
            <div class="item-price">${i.item_price}</div>
            <input type="hidden" name="itemId" value="${i.item_id}">
            <button type="button" class="buy-btn" onclick="shopbuy()">구매</button>
        </div>
    </div>
</form>
</c:forEach>

</body>
</html>