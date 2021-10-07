<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<body>
    <form action="new-user" method="post">
        <label>
            Введите имя:
            <input type="text" name="name"><br>
            Выберите отделение:
            <select name="department">
                <c:forEach items="${departments}" var="dep">
                    <option value="${dep.id}">${dep.name}</option>
                </c:forEach>
            </select>
        </label><br>
        <input type="submit" value="add user">
    </form>
    <c:if test="${success}">
        User added.
    </c:if>
    <br><a href="user-app.jsp">Back</a>
</body>
</html>