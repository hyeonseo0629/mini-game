<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Ranking Page</title>
    <link rel="stylesheet" href="/resources/css/ranking.css">
</head>
<body>
    <div class="rank-container">
<%--        <div class="game-type">--%>
<%--            <button>야추다이스</button>--%>
<%--            <button>사다리타기</button>--%>
<%--        </div>--%>
        <div class="ranking-type" style="display: flex;">
            <%-- GET 방식 --%>
            <form action="rank" method="get">
                <button type="submit">레이팅왕</button>
            </form>
            <%-- POST 방식 --%>
            <form action="rank" method="post">
                <button type="submit" name="rankingType" value="winningStack">연승왕</button>
            </form>
            <form action="rank" method="post">
                <button type="submit" name="rankingType" value="money">코인왕</button>
            </form>
        </div>
        <c:if test="${page == 1}">
            <div class="ranking-content">
                <!-- Top 3 특수 UI -->
                <div class="top3-wrapper">
                    <c:forEach var="r" items="${rankingList}" varStatus="i">
                        <c:if test="${i.index lt 3}">
                            <div class="top3-card rank-${i.index + 1}">
                                <img class="avatar" src="resources/images/${r.user_avatar_img}" alt="avatar">
                                <div class="username">${r.user_nickname}</div>
                                <div class="score">
                                    <c:choose>
                                        <c:when test="${rankingType=='레이팅 점수'}">${r.score_point}점</c:when>
                                        <c:when test="${rankingType=='연승 횟수'}">${r.stack_point}연승</c:when>
                                        <c:otherwise>${r.user_money}원</c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="badge">${i.index + 1}</div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>

                <!-- 4~10등 (Top3 이후 7개) -->
                <div class="ranking-header ranking-row">
                    <div>등수</div>
                    <div>아이디</div>
                    <div>닉네임</div>
                    <div>
                        <c:choose>
                            <c:when test="${rankingType == '레이팅 점수'}">레이팅 점수</c:when>
                            <c:when test="${rankingType == '연승 횟수'}">연승 횟수</c:when>
                            <c:otherwise>보유 금액</c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <c:forEach var="r" items="${rankingList}" varStatus="i">
                    <c:if test="${i.index >= 3 && i.index < 10}">
                        <div class="ranking-row">
                            <div>${i.index + 1}</div>
                            <div>${r.user_id}</div>
                            <div>${r.user_nickname}</div>
                            <div>
                                <c:choose>
                                    <c:when test="${rankingType=='레이팅 점수'}">${r.score_point}점</c:when>
                                    <c:when test="${rankingType=='연승 횟수'}">${r.stack_point}연승</c:when>
                                    <c:otherwise>${r.user_money}원</c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </c:if>
        <c:if test="${page > 1}">
            <!-- 11등 이후 페이징 -->
            <!-- 일반 변수 startIndex, endIndex 생성 -->
            <c:set var="startIndex" value="${10 + (page-2) * 10}" /> <!-- (page-1) * 10으로 대체 가능한지 차후 확인 -->
            <c:set var="endIndex" value="${startIndex + 10}" />
            <div class="ranking-header ranking-row">
                <div>등수</div>
                <div>아이디</div>
                <div>닉네임</div>
                <div>
                    <c:choose>
                        <c:when test="${rankingType == '레이팅 점수'}">레이팅 점수</c:when>
                        <c:when test="${rankingType == '연승 횟수'}">연승 횟수</c:when>
                        <c:otherwise>보유 금액</c:otherwise>
                    </c:choose>
                </div>
            </div>
            <c:forEach var="r" items="${rankingList}" varStatus="i">
                    <div class="ranking-row">
                        <div>${startIndex + i.index + 1}</div>
                        <div>${r.user_id}</div>
                        <div>${r.user_nickname}</div>
                        <div>
                            <c:choose>
                                <c:when test="${rankingType=='레이팅 점수'}">${r.score_point}점</c:when>
                                <c:when test="${rankingType=='연승 횟수'}">${r.stack_point}연승</c:when>
                                <c:otherwise>${r.user_money}원</c:otherwise>
                            </c:choose>
                        </div>
                    </div>
            </c:forEach>
        </c:if>
        <!-- 페이징 버튼 -->
        <c:set var="total" value="${totalCount}" />
        <c:set var="lastPage" value="${(total > 10) ? ((total-10) / 10 + ((total-10) % 10 == 0 ? 0 : 1)) + 1 : 1}" />
        <div class="pagination">
            <c:forEach var="p" begin="1" end="${lastPage}">
                <form action="rank" method="post">
                    <input type="hidden" name="rankingType" value="${rankingType == '연승 횟수' ? 'winningStack' : (rankingType == '보유 금액' ? 'money' : 'score')}" />
                    <input type="hidden" name="page" value="${p}" />
                    <button type="submit" class="${p == page ? 'active' : ''}">${p}</button>
                </form>
            </c:forEach>
        </div>
    </div>
</body>
</html>
