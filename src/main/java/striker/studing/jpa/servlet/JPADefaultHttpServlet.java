package striker.studing.jpa.servlet;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public abstract class JPADefaultHttpServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("UserAppJpa");
        getServletContext().setAttribute("factory", factory);
    }
    public EntityManagerFactory getManagerFactory(){
        return (EntityManagerFactory) getServletContext().getAttribute("factory");
    }
    @Override
    public void destroy() {
        getManagerFactory().close();
    }
}
