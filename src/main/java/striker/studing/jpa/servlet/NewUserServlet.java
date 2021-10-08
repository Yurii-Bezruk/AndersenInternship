package striker.studing.jpa.servlet;



import striker.studing.jpa.entity.Department;
import striker.studing.jpa.entity.User;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/new-user")
public class NewUserServlet extends JPADefaultHttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final EntityManager manager = getManagerFactory().createEntityManager();
        manager.getTransaction().begin();
        User user = new User();
        user.setName(req.getParameter("name"));
        Department department = manager.find(Department.class, Long.parseLong(req.getParameter("department")));
        user.setDepartment(department);
        manager.persist(user);
        manager.getTransaction().commit();
        manager.close();
        req.setAttribute("success", true);
        req.getRequestDispatcher("/new-user.jsp").forward(req, resp);
    }
}
