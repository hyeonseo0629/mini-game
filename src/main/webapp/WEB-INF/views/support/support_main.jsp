<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <%@ page
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <title>mini game</title>
    <link rel="stylesheet" href="resources/css/board.css" />
  </head>
  <body>
    <div class="board-text">
      <h1>문의</h1>
      <br />
      <button onclick="location.href='SupportPostC'">문의 작성</button>
      <hr />
      <table>
        <thead>
          <tr>
            <th>No.</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
          </tr>
        </thead>
        <div style="width: 100%; display: flex; justify-content: center">
          <div class="text-container">
            <c:forEach var="sup" items="${support}">
              <div class="text-wrap">
                <tr>
                  <td class="text_id">${sup.text_id}</td>
                  <td class="text_title">${sup.text_title}</td>
                  <td class="text_user_no">${sup.text_user_no}</td>
                  <td class="text_date">
                    <fmt:formatDate
                      value="${sup.text_write_date}"
                      pattern="MM월 dd일"
                    />
                  </td>
                </tr>
              </div>
            </c:forEach>
          </div>
        </div>
      </table>
    </div>
  </body>
</html>
