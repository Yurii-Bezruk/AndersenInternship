<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<body>
    <a href="user-by-id.jsp">User by ID</a><br>
    <a href="get-all-users?redirect=all-users.jsp">All users</a><br>
    <a href="get-all-departments?redirect=new-user.jsp">Add new user</a><br>
    <a href="get-all-users?redirect=get-all-departments%3Fredirect=edit-user.jsp">Edit user</a><br>
    <a href="get-all-users?redirect=delete-user.jsp">Delete user</a><br>
</body>
</html>