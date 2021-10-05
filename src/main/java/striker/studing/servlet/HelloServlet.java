package striker.studing.servlet;

import striker.studing.database.Country;
import striker.studing.database.Department;
import striker.studing.database.UserDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        UserDAO manager = new UserDAO();
        manager.readAll().forEach(user -> {
            Department department = user.getDepartment();
            Country country = department.getCountry();
            System.out.printf("%d %s %d %s %d %s%n",
                    user.getId(), user.getName(),
                    department.getId(), department.getName(),
                    country.getId(), country.getName());
        });
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
