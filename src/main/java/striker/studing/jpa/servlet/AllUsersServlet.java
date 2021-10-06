package striker.studing.jpa.servlet;

import striker.studing.database.User;
import striker.studing.database.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/get-all-users")
public class AllUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = new UserDAO().readAll();
        req.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/all-users.jsp")
            .forward(req, resp);
    }
}
