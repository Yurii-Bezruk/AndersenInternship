<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<body>
    User data: <br>
    <c:forEach items="${users}" var="user">
        ${user}<br>
    </c:forEach>
    <br><a href="user-app.jsp">Back</a>
</body>
</html>