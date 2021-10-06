<%@ page import="java.util.Date" %>
<html lang="en">
<body>
    <%!
        static class Today {
            static public Date getTodayDateTime(){
                return new Date();
            }
        }
    %>
    Today is
    <%=Today.getTodayDateTime()%>
    <form action="login" method="post">
        <input name="login"><br>
        <input type="submit" value="Go!">
    </form>
    <a href="user-app.jsp">Go to user app</a>
</body>
</html>