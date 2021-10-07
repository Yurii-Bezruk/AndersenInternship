<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
</head>
<body>
    <form action="delete-user" method="post">
        <label>
            Выберите пользователя:
            <select name="user">
                <c:forEach items="${users}" var="user">
                    <option value="${user.id}">${user.name}, ${user.department.name}</option>
                </c:forEach>
            </select>
        </label><br>
        <input type="submit" value="delete user">
    </form>
    <c:if test="${success}">
        User deleted.
    </c:if>
    <br><a href="user-app.jsp">Back</a>
</body>
</html>