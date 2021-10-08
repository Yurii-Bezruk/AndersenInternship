package striker.studing.jpa.servlet;


import striker.studing.database.DAO;
import striker.studing.database.UserDAO;
import striker.studing.jpa.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-user")
public class DeleteUserServlet extends JPADefaultHttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final EntityManager manager = getManagerFactory().createEntityManager();
        manager.getTransaction().begin();
        final TypedQuery<User> query = manager.createQuery("SELECT u FROM User u WHERE u.id = :p", User.class);
        query.setParameter("p", Long.parseLong(req.getParameter("user")));
        final User user = query.getSingleResult();
        manager.remove(user);
        manager.getTransaction().commit();
        manager.close();
//        striker.studing.database.User user = new striker.studing.database.User();
//        user.setId(Long.parseLong(req.getParameter("user")));
//        DAO<striker.studing.database.User> userDAO = new UserDAO();
//        userDAO.delete(user);
        req.setAttribute("success", true);
        req.getRequestDispatcher("/delete-user.jsp").forward(req, resp);
    }
}
