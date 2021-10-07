package striker.studing.jpa.servlet;

import striker.studing.database.Department;
import striker.studing.database.DepartmentDAO;
import striker.studing.database.User;
import striker.studing.database.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/get-all-departments")
public class AllDepartmentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> departments = new DepartmentDAO().readAll();
        req.setAttribute("departments", departments);
        getServletContext().getRequestDispatcher("/" + req.getParameter("redirect"))
            .forward(req, resp);
    }
}
