package se.jimmy.iths;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        resp.getWriter().println("Hej från /hello");

//        String name = req.getParameter("name");
//        resp.getWriter().println("Hej " + name);

        String path = req.getPathInfo();
//kolla om värdet finns och ta bort / från värdet
        String name = (path != null && path.length() > 1)
                ? path.substring(1)
                : "";
//skriv ut värdet
        resp.getWriter().println("Hej " + name);
    }
}
