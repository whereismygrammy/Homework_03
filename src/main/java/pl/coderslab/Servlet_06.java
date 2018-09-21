package pl.coderslab;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Servlet_06", urlPatterns = "/Servlet_06")
public class Servlet_06 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] nums = request.getParameterValues("num");
        int sum = 0;
        int mult = 1;
        int numbers = 0;

        response.getWriter().println(Servlet_05_1.webStart());
        response.getWriter().println("Liczby:<br>");
        for (String s : nums) {
            response.getWriter().print(" - " + s + "<br>");
            int tempNum = Integer.parseInt(s);
            sum += tempNum;
            mult *= tempNum;
            numbers++;
        }

        response.getWriter().println("Srednia:<br>");
        double avg = 1.0 * sum / numbers;

        response.getWriter().println(" - " + avg + "<br>");
        response.getWriter().println("Suma:<br>");
        response.getWriter().println(" - " + sum + "<br>");
        response.getWriter().println("Iloczyn:<br>");
        response.getWriter().println(" - " + mult + "<br>");
        response.getWriter().println(Servlet_05_1.webEnd());
    }
}
