package striker.studing.jpa.servlet;



import striker.studing.jpa.entity.Department;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/get-all-departments")
public class AllDepartmentsServlet extends JPADefaultHttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final EntityManager manager = getManagerFactory().createEntityManager();
        List<Department> departments = manager.createQuery("SELECT d FROM Department d", Department.class).getResultList();//new DepartmentDAO().readAll();
        manager.close();
        req.setAttribute("departments", departments);
        getServletContext().getRequestDispatcher("/" + req.getParameter("redirect"))
            .forward(req, resp);
    }
}
