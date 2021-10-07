package striker.studing.jpa.servlet;

import striker.studing.database.DAO;
import striker.studing.database.Department;
import striker.studing.database.User;
import striker.studing.database.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-user")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setId(Long.parseLong(req.getParameter("user")));
        DAO<User> userDAO = new UserDAO();
        userDAO.delete(user);
        req.setAttribute("success", true);
        req.getRequestDispatcher("/delete-user.jsp").forward(req, resp);
    }
}