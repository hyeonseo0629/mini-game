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
    <div class="score-boards">
<%--        <div class="score-board" id="board-2">--%>
<%--            <h3>CPU</h3>--%>
<%--            <table class="score-table">--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th>카테고리</th>--%>
<%--                    <th>점수</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody>--%>
<%--                <tr><td>Ones</td><td class="score-ones">-</td></tr>--%>
<%--                <tr><td>Twos</td><td class="score-twos">-</td></tr>--%>
<%--                <tr><td>Threes</td><td class="score-threes">-</td></tr>--%>
<%--                <tr><td>Fours</td><td class="score-fours">-</td></tr>--%>
<%--                <tr><td>Fives</td><td class="score-fives">-</td></tr>--%>
<%--                <tr><td>Sixes</td><td class="score-sixes">-</td></tr>--%>
<%--                <tr><td>Three of a kind</td><td class="score-three">-</td></tr>--%>
<%--                <tr><td>Four of a kind</td><td class="score-four">-</td></tr>--%>
<%--                <tr><td>Full House</td><td class="score-full">-</td></tr>--%>
<%--                <tr><td>Small Straight</td><td class="score-small">-</td></tr>--%>
<%--                <tr><td>Large Straight</td><td class="score-large">-</td></tr>--%>
<%--                <tr><td>Yahtzee</td><td class="score-yahtzee">-</td></tr>--%>
<%--                <tr><td>Chance</td><td class="score-chance">-</td></tr>--%>
<%--                <tr><td>Total</td><td class="score-total"></td></tr>--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--        </div>--%>
<%--        <div class="score-board" id="board-1">--%>
<%--            <h3>Player</h3>--%>
<%--            <table class="score-table">--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th>카테고리</th>--%>
<%--                    <th>점수</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody>--%>
<%--                <tr><td>Ones</td><td class="score-ones">-</td></tr>--%>
<%--                <tr><td>Twos</td><td class="score-twos">-</td></tr>--%>
<%--                <tr><td>Threes</td><td class="score-threes">-</td></tr>--%>
<%--                <tr><td>Fours</td><td class="score-fours">-</td></tr>--%>
<%--                <tr><td>Fives</td><td class="score-fives">-</td></tr>--%>
<%--                <tr><td>Sixes</td><td class="score-sixes">-</td></tr>--%>
<%--                <tr><td>Three of a kind</td><td class="score-three">-</td></tr>--%>
<%--                <tr><td>Four of a kind</td><td class="score-four">-</td></tr>--%>
<%--                <tr><td>Full House</td><td class="score-full">-</td></tr>--%>
<%--                <tr><td>Small Straight</td><td class="score-small">-</td></tr>--%>
<%--                <tr><td>Large Straight</td><td class="score-large">-</td></tr>--%>
<%--                <tr><td>Yahtzee</td><td class="score-yahtzee">-</td></tr>--%>
<%--                <tr><td>Chance</td><td class="score-chance">-</td></tr>--%>
<%--                <tr><td>Total</td><td class="score-total"></td></tr>--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--        </div>--%>

        <%-- 디버깅용 --%>
            <div class="score-board" id="board-2">
                <h3>CPU</h3>
                <table class="score-table">
                    <thead>
                    <tr>
                        <th>카테고리</th>
                        <th>점수</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr><td>Ones</td><td class="score-ones">1</td></tr>
                    <tr><td>Twos</td><td class="score-twos">2</td></tr>
                    <tr><td>Threes</td><td class="score-threes">3</td></tr>
                    <tr><td>Fours</td><td class="score-fours">4</td></tr>
                    <tr><td>Fives</td><td class="score-fives">5</td></tr>
                    <tr><td>Sixes</td><td class="score-sixes">6</td></tr>
                    <tr><td>Three of a kind</td><td class="score-three">0</td></tr>
                    <tr><td>Four of a kind</td><td class="score-four">0</td></tr>
                    <tr><td>Full House</td><td class="score-full">0</td></tr>
                    <tr><td>Small Straight</td><td class="score-small">0</td></tr>
                    <tr><td>Large Straight</td><td class="score-large">0</td></tr>
                    <tr><td>Yahtzee</td><td class="score-yahtzee">0</td></tr>
                    <tr><td>Chance</td><td class="score-chance">14</td></tr>
                    <tr><td>Total</td><td class="score-total"></td></tr>
                    </tbody>
                </table>
            </div>
            <div class="score-board" id="board-1">
                <h3>Player</h3>
                <table class="score-table">
                    <thead>
                    <tr>
                        <th>카테고리</th>
                        <th>점수</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr><td>Ones</td><td class="score-ones">1</td></tr>
                    <tr><td>Twos</td><td class="score-twos">2</td></tr>
                    <tr><td>Threes</td><td class="score-threes">3</td></tr>
                    <tr><td>Fours</td><td class="score-fours">4</td></tr>
                    <tr><td>Fives</td><td class="score-fives">5</td></tr>
                    <tr><td>Sixes</td><td class="score-sixes">6</td></tr>
                    <tr><td>Three of a kind</td><td class="score-three">0</td></tr>
                    <tr><td>Four of a kind</td><td class="score-four">0</td></tr>
                    <tr><td>Full House</td><td class="score-full">0</td></tr>
                    <tr><td>Small Straight</td><td class="score-small">0</td></tr>
                    <tr><td>Large Straight</td><td class="score-large">0</td></tr>
                    <tr><td>Yahtzee</td><td class="score-yahtzee">0</td></tr>
                    <tr><td>Chance</td><td class="score-chance">-</td></tr>
                    <tr><td>Total</td><td class="score-total"></td></tr>
                    </tbody>
                </table>
            </div>

    </div>
    <div id="canvas" style="width: 800px; height: 680px; padding: 0"></div>
    <button class="game-button" name="user" value="${sessionScope.users}">Start Game</button>
    <div class="game-area" style="width: 800px; height: 680px; padding: 0"></div>
</div>

<div class="game-selection">
    <button class="select-button game-1" onclick="moveToController(1)">야추다이스</button>
    <button class="select-button game-2" onclick="moveToController(2)">게임 2</button>
    <button class="select-button game-3" onclick="moveToController(3)">게임 3</button>
    <button class="select-button game-4" onclick="moveToController(4)">게임 4</button>
    <button class="select-button game-5" onclick="moveToController(5)">게임 5</button>
    <button class="select-button game-6" onclick="moveToController(6)">게임 6</button>
</div>

<c:choose>
    <c:when test="${not empty result}">
        <div class="game-result-container">
    </c:when>
    <c:otherwise>
        <div class="game-result-container" style="display: none">
    </c:otherwise>
</c:choose>
    <div class="result-modal">
        <h1>${result} !</h1>
        <div class="result-content">
            <c:choose>
                <c:when test="${result == 'win'}">
                    <h3>${rewardMoney}원을 획득했습니다.</h3>
                    <h3>500점을 획득했습니다.</h3>
                    <h3>${stackPoint}연승 중!</h3>
                </c:when>
                <c:when test="${result == 'draw'}">
                    <h3>무승부!</h3>
                    <h3>다음을 노려봅시다!</h3>
                </c:when>
                <c:when test="${result == 'lose'}">
                    <h3>${-1 * rewardMoney}원을 잃었습니다.</h3>
                    <h3>300점을 잃었습니다.</h3>
                    <c:if test="${stackPoint > 0}"><h3>연승이 깨졌습니다...!</h3></c:if>
                </c:when>
                <c:when test="${result == 'No user'}">
                    <h3>로그인을 부탁드립니다.</h3>
                </c:when>
            </c:choose>
        </div>
        <button class="game-return-button" onclick="location.href='/GameC/1'">게임으로</button>
    </div>
</div>
<script>
    const contextPath = "${pageContext.request.contextPath}";
    console.log("jsp 내 contextPath : " + contextPath);
</script>
<script type="module" src="${pageContext.request.contextPath}/resources/js/dice_game.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/game.js"></script>