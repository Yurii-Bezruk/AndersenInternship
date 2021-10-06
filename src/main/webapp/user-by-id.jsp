<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<body>
    <form action="get-user" method="post">
        <label>
            Введите id:
            <input type="number" name="id">
        </label>
        <input type="submit" value="find user">
    </form>
    User data: <br>
    ${user}
</body>
</html>