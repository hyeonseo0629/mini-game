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


<c:choose>
    <c:when test="${not empty sessionScope.users}">
        <input type="hidden" id="loginCheck" value="true">
    </c:when>
    <c:otherwise>
        <input type="hidden" id="loginCheck" value="false">
    </c:otherwise>
</c:choose>





<c:if test="${not empty errorMsg}">
    <input type="hidden" id="errorMsg" value="${errorMsg}">
</c:if>

<input type="hidden" id="userMoney" value="${userMoney}">
<input type="hidden" id="userMoney" value="${user.user_money}">




<div class="Shop-Top-Line">
<h1>상점</h1>
    <div class="Coin-Zone">
        <div class="Shop-Top-Line-img"></div>
        <div class="Shop-Top-Line-Money">${userMoney} 원</div>
    </div>
</div>

<br>
<hr>
<div class="container">
    <c:forEach var="i" items="${pagedItems}">
        <form action="/BuyItem" method="post">
            <div class="shop-item-container">
                <div class="item-img">
                    <img src="resources/images/${i.item_avatar_img}" alt="img">
                </div>
                <div class="item-name">${i.item_name}</div>
                <div class="underside">
                    <div class="item-price">${i.item_price}원</div>
                    <input type="hidden" name="itemId" value="${i.item_id}">
                    <button type="button" class="buy-btn" data-price="${i.item_price}" onclick="shopbuy(this)">구매</button>
                </div>
            </div>
        </form>
    </c:forEach>
</div>

<div class="pagination">
    <c:forEach var="p" begin="1" end="${totalPage}">
        <c:choose>
            <c:when test="${p == currentPage}">
                <span>[${p}]</span>
            </c:when>
            <c:otherwise>
                <a href="/ShopC?page=${p}">[${p}]</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>


</body>
</html>