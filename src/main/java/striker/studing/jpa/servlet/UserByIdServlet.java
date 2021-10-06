package striker.studing.jpa.servlet;

import striker.studing.database.User;
import striker.studing.database.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/get-user")
public class UserByIdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new UserDAO().read(Long.parseLong(req.getParameter("id")));
        req.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/user-by-id.jsp")
                .forward(req, resp);
    }
}
