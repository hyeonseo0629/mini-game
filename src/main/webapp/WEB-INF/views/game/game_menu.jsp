<%--
  Created by IntelliJ IDEA.
  User: kwaksummer
  Date: 2025. 7. 3.
  Time: 오후 4:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="game-container">
    <div id="canvas" style="width: 800px; height: 1000px; padding: 0"></div>
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
                <tr><td>Ones</td><td id="score-ones">-</td></tr>
                <tr><td>Twos</td><td id="score-twos">-</td></tr>
                <tr><td>Threes</td><td id="score-threes">-</td></tr>
                <tr><td>Fours</td><td id="score-fours">-</td></tr>
                <tr><td>Fives</td><td id="score-fives">-</td></tr>
                <tr><td>Sixes</td><td id="score-sixes">-</td></tr>
                <tr><td>Three of a kind</td><td id="score-three">-</td></tr>
                <tr><td>Four of a kind</td><td id="score-four">-</td></tr>
                <tr><td>Full House</td><td id="score-full">-</td></tr>
                <tr><td>Small Straight</td><td id="score-small">-</td></tr>
                <tr><td>Large Straight</td><td id="score-large">-</td></tr>
                <tr><td>Yahtzee</td><td id="score-yahtzee">-</td></tr>
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
                <tr><td>Ones</td><td id="score-ones">-</td></tr>
                <tr><td>Twos</td><td id="score-twos">-</td></tr>
                <tr><td>Threes</td><td id="score-threes">-</td></tr>
                <tr><td>Fours</td><td id="score-fours">-</td></tr>
                <tr><td>Fives</td><td id="score-fives">-</td></tr>
                <tr><td>Sixes</td><td id="score-sixes">-</td></tr>
                <tr><td>Three of a kind</td><td id="score-three">-</td></tr>
                <tr><td>Four of a kind</td><td id="score-four">-</td></tr>
                <tr><td>Full House</td><td id="score-full">-</td></tr>
                <tr><td>Small Straight</td><td id="score-small">-</td></tr>
                <tr><td>Large Straight</td><td id="score-large">-</td></tr>
                <tr><td>Yahtzee</td><td id="score-yahtzee">-</td></tr>
                <tr><td>Chance</td><td id="score-chance">-</td></tr>
                <tr><td>Total</td><td id="score-total"></td></tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script type="module" src="resources/js/dice_game.js"></script>