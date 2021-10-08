package striker.studing.jpa.servlet;
import striker.studing.jpa.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/get-all-users")
public class AllUsersServlet extends JPADefaultHttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final EntityManager manager = getManagerFactory().createEntityManager();
        final CriteriaBuilder builder = manager.getCriteriaBuilder();
        final CriteriaQuery<User> query = builder.createQuery(User.class);
        final Root<User> u = query.from(User.class);
        query.select(u);
        List<User> users = manager.createQuery(query).getResultList();//new UserDAO().readAll();
        manager.close();
        req.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/" + req.getParameter("redirect"))
            .forward(req, resp);
    }


}
