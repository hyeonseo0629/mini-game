<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Ranking Page</title>
    <link rel="stylesheet" href="/resources/css/ranking.css">
<%--    <script src="/resources/js/main.js"></script>--%>
</head>
<body>
    <div class="rank-container">
        <div class="game-type">
            <button>야추다이스</button>
            <button>사다리타기</button>
        </div>
        <div class="ranking-type" style="display: flex;">
            <button onclick="location.href='RankingC'">레이팅왕</button>
            <form action="RankingC" method="post">
                <button name="rankingType" value="winningStack">연승왕</button>
                <button name="rankingType" value="money">코인왕</button>
            </form>
        </div>
        <div class="ranking-content">
            <div style="display: flex; justify-content: space-around">
                <div>등수</div>
                <div>사용자 ID</div>
                <div>닉네임</div>
                <div>${rankingType}</div>
            </div>
            <hr style="margin: 10px 0">
            <c:forEach var="r" items="${rankingList}" varStatus="i">
                <div style="display: flex; justify-content: space-around">
                    <div>${i.count}</div>
                    <div>${r.user_id}</div>
                    <div>${r.user_nickname}</div>
                    <div>${r.score_point}</div>
                </div>
                <hr style="margin: 10px 0">
            </c:forEach>
        </div>
    </div>
</body>
</html>
