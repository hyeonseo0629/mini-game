<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>mini game</title>
</head>
<body>
    <h1>게시판</h1><br>
    <hr>

<c:forEach var="commu" items="${community}">
    ${commu.text_id},${commu.text_title},${commu.text_user_no},
    ${commu.text_content},${commu.text_date},${commu.text_type}
</c:forEach>


</body>
</html>