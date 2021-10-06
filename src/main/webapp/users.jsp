<%@ page import="java.util.Date" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<body>
    <c:forEach items="${userList}" var="user">
        <h4>${user}</h4>
    </c:forEach>
</body>
</html>