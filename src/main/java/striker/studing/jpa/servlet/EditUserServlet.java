package striker.studing.jpa.servlet;

import striker.studing.database.DAO;
import striker.studing.jpa.entity.Department;
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

@WebServlet("/edit-user")
public class EditUserServlet extends JPADefaultHttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final EntityManager manager = getManagerFactory().createEntityManager();
        manager.getTransaction().begin();
        final TypedQuery<User> query = manager.createQuery("SELECT u FROM User u WHERE u.id = :p", User.class);
        query.setParameter("p", Long.parseLong(req.getParameter("user")));
        final User user = query.getSingleResult();
        final TypedQuery<Department> depQuery = manager.createQuery("SELECT d FROM Department d WHERE d.id = :p", Department.class);
        depQuery.setParameter("p", Long.parseLong(req.getParameter("department")));
        final Department department = depQuery.getSingleResult();
        user.setName(req.getParameter("name"));
        user.setDepartment(department);
        manager.merge(user);
        manager.getTransaction().commit();
        manager.close();
//        User user = new User();
//        user.setName(req.getParameter("name"));
//        Department department = new Department();
//        department.setId(Long.parseLong(req.getParameter("department")));
//        user.setDepartment(department);
//        DAO<User> userDAO = new UserDAO();
//        userDAO.update(user);
        req.setAttribute("success", true);
        req.getRequestDispatcher("/edit-user.jsp").forward(req, resp);
    }
}
