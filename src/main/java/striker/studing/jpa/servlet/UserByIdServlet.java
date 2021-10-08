package striker.studing.jpa.servlet;

import striker.studing.jpa.entity.User;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/get-user")
public class UserByIdServlet extends JPADefaultHttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final EntityManager manager = getManagerFactory().createEntityManager();
        User user = manager.find(User.class, Long.parseLong(req.getParameter("id")));
        req.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/user-by-id.jsp")
                .forward(req, resp);
    }
}
