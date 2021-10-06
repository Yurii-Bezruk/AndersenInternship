package striker.studing.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/login", initParams = @WebInitParam(name = "x", value = "12"))
public class FormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getQueryString());
        resp.getWriter().print(req.getHttpServletMapping().getPattern());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<body>");
        writer.println("<p>Hi, " + req.getParameter( "login") + getInitParameter("x") + "</p>");
        writer.println("<form action=\"hello\" method=\"post\">");
        writer.println("<input type=\"submit\" value=\"Go!\">");
        writer.println("</form>");
        writer.println("</body>");
        writer.println("</html>");
    }
}
