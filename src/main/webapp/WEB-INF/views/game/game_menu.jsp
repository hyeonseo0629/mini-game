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
    <div id="canvas" style="width: 800px; padding: 0"></div>
<%--    <div class="score-boards">--%>
<%--        <div class="score-board" id="board-1">--%>
<%--            <h3>Player</h3>--%>
<%--            <table id="score-table">--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th>카테고리</th>--%>
<%--                    <th>점수</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody>--%>
<%--                <tr><td>Ones</td><td id="score-ones">-</td></tr>--%>
<%--                <tr><td>Twos</td><td id="score-twos">-</td></tr>--%>
<%--                <tr><td>Threes</td><td id="score-threes">-</td></tr>--%>
<%--                <tr><td>Fours</td><td id="score-fours">-</td></tr>--%>
<%--                <tr><td>Fives</td><td id="score-fives">-</td></tr>--%>
<%--                <tr><td>Sixes</td><td id="score-sixes">-</td></tr>--%>
<%--                <tr><td>Three of a kind</td><td id="score-three">-</td></tr>--%>
<%--                <tr><td>Four of a kind</td><td id="score-four">-</td></tr>--%>
<%--                <tr><td>Full House</td><td id="score-full">-</td></tr>--%>
<%--                <tr><td>Small Straight</td><td id="score-small">-</td></tr>--%>
<%--                <tr><td>Large Straight</td><td id="score-large">-</td></tr>--%>
<%--                <tr><td>Yahtzee</td><td id="score-yahtzee">-</td></tr>--%>
<%--                <tr><td>Chance</td><td id="score-chance">-</td></tr>--%>
<%--                <tr><td>Total</td><td id="score-total"></td></tr>--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--        </div>--%>
<%--        <div class="score-board" id="board-2">--%>
<%--            <h3>CPU</h3>--%>
<%--            <table id="score-table">--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th>카테고리</th>--%>
<%--                    <th>점수</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody>--%>
<%--                <tr><td>Ones</td><td id="score-ones">-</td></tr>--%>
<%--                <tr><td>Twos</td><td id="score-twos">-</td></tr>--%>
<%--                <tr><td>Threes</td><td id="score-threes">-</td></tr>--%>
<%--                <tr><td>Fours</td><td id="score-fours">-</td></tr>--%>
<%--                <tr><td>Fives</td><td id="score-fives">-</td></tr>--%>
<%--                <tr><td>Sixes</td><td id="score-sixes">-</td></tr>--%>
<%--                <tr><td>Three of a kind</td><td id="score-three">-</td></tr>--%>
<%--                <tr><td>Four of a kind</td><td id="score-four">-</td></tr>--%>
<%--                <tr><td>Full House</td><td id="score-full">-</td></tr>--%>
<%--                <tr><td>Small Straight</td><td id="score-small">-</td></tr>--%>
<%--                <tr><td>Large Straight</td><td id="score-large">-</td></tr>--%>
<%--                <tr><td>Yahtzee</td><td id="score-yahtzee">-</td></tr>--%>
<%--                <tr><td>Chance</td><td id="score-chance">-</td></tr>--%>
<%--                <tr><td>Total</td><td id="score-total"></td></tr>--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--        </div>--%>
<%--    </div>--%>
    <!-- 디버깅용 -->
    <div class="score-boards">
        <div class="score-board" id="board-1">
            <h3>Player</h3>
            <table id="score-table">
                <thead>
                <tr>
                    <th>카테고리</th>
                    <th>점수</th>
                </tr>
                </thead>
                <tbody>
                <tr><td>Ones</td><td id="score-ones">1</td></tr>
                <tr><td>Twos</td><td id="score-twos">2</td></tr>
                <tr><td>Threes</td><td id="score-threes">3</td></tr>
                <tr><td>Fours</td><td id="score-fours">4</td></tr>
                <tr><td>Fives</td><td id="score-fives">5</td></tr>
                <tr><td>Sixes</td><td id="score-sixes">6</td></tr>
                <tr><td>Three of a kind</td><td id="score-three">0</td></tr>
                <tr><td>Four of a kind</td><td id="score-four">0</td></tr>
                <tr><td>Full House</td><td id="score-full">0</td></tr>
                <tr><td>Small Straight</td><td id="score-small">0</td></tr>
                <tr><td>Large Straight</td><td id="score-large">0</td></tr>
                <tr><td>Yahtzee</td><td id="score-yahtzee">0</td></tr>
                <tr><td>Chance</td><td id="score-chance">-</td></tr>
                <tr><td>Total</td><td id="score-total"></td></tr>
                </tbody>
            </table>
        </div>
        <div class="score-board" id="board-2">
            <h3>CPU</h3>
            <table id="score-table">
                <thead>
                <tr>
                    <th>카테고리</th>
                    <th>점수</th>
                </tr>
                </thead>
                <tbody>
                <tr><td>Ones</td><td id="score-ones">1</td></tr>
                <tr><td>Twos</td><td id="score-twos">2</td></tr>
                <tr><td>Threes</td><td id="score-threes">3</td></tr>
                <tr><td>Fours</td><td id="score-fours">4</td></tr>
                <tr><td>Fives</td><td id="score-fives">5</td></tr>
                <tr><td>Sixes</td><td id="score-sixes">6</td></tr>
                <tr><td>Three of a kind</td><td id="score-three">0</td></tr>
                <tr><td>Four of a kind</td><td id="score-four">0</td></tr>
                <tr><td>Full House</td><td id="score-full">0</td></tr>
                <tr><td>Small Straight</td><td id="score-small">0</td></tr>
                <tr><td>Large Straight</td><td id="score-large">0</td></tr>
                <tr><td>Yahtzee</td><td id="score-yahtzee">0</td></tr>
                <tr><td>Chance</td><td id="score-chance">14</td></tr>
                <tr><td>Total</td><td id="score-total"></td></tr>
                </tbody>
            </table>
        </div>
    </div>
    <!-- 디버깅용 -->
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
        <button class="game-return-button" onclick="location.href='GameC'">게임으로</button>
    </div>
</div>
<script type="module" src="resources/js/dice_game.js"></script>