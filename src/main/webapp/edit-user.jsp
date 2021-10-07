<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <script type="text/javascript" src="js/scripts.js"></script>
</head>
<body>
    <form action="edit-user" method="post">
        <label>
            Выберите пользователя:
            <select name="user" id="user" onchange="refreshData()">
                <c:forEach items="${users}" var="user">
                    <option value="${user.id}" label="${user.department.id}">${user.name}</option>
                </c:forEach>
            </select>
        </label><br>
        <label>
            Новое имя: <input type="text" name="name" id="name" value="${users.get(0).getName()}">
        </label><br>
        <label>
            Новое отделение:
            <select name="department" id="department">
                <c:forEach items="${departments}" var="dep">
                    <option value="${dep.id}">${dep.name}</option>
                </c:forEach>
            </select>
        </label><br>
        <input type="submit" value="edit user">
    </form>
    <c:if test="${success}">
        User edited.
    </c:if>
    <br><a href="user-app.jsp">Back</a>
</body>
</html>