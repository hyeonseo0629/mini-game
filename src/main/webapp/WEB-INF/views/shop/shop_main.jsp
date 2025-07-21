<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <form action="buyitem" method="post">
            <div class="shop-item-container">
                <div class="item-img
                    <c:if test="${i.item_avatar_img eq 'base_avatar.webp'}"> base_avatar</c:if>
                    <c:if test="${i.item_avatar_img eq 'dinosaur.webp'}"> dinosaur</c:if>
                    <c:if test="${i.item_avatar_img eq 'wizard.webp'}"> wizard</c:if>
                    <c:if test="${i.item_avatar_img eq 'polar_bear.webp'}"> polar_bear</c:if>">
                    <img src="/resources/images/${i.item_avatar_img}" alt="img">
                </div>
                <div class="name-price-zone">
                    <div class="item-name">${i.item_name}</div>
                    <div class="underside">
                        <div class="item-price">${i.item_price}원</div>
                        <input type="hidden" name="itemId" value="${i.item_id}">

                        <c:set var="alreadyOwned" value="false"/>
                        <c:forEach var="inv" items="${sessionScope.inventoryItems}">
                            <c:if test="${inv.item_id == i.item_id}">
                                <c:set var="alreadyOwned" value="true"/>
                            </c:if>
                        </c:forEach>

                        <c:choose>
                            <c:when test="${alreadyOwned}">
                                <button type="button" disabled>보유중</button>
                            </c:when>
                            <c:otherwise>
                                <button type="button" class="buy-btn" data-price="${i.item_price}"
                                        onclick="shopbuy(this)">구매
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </div>
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
                <a href="/shopC?page=${p}">[${p}]</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>