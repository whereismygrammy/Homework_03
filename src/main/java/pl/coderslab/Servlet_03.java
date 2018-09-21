package pl.coderslab;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Servlet_03", urlPatterns = "/Servlet_03")
public class Servlet_03 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        for (int i = 1; i < 6; i++){
            String sessionValue = request.getParameter(i+"");
            session.setAttribute(i+"", sessionValue);
        }
        response.getWriter().println("Twoje dane zosaly zapamietane");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.getWriter().print(webStart());

        for (int i = 1; i < 6 ; i++){
            String sessionValue = (String) session.getAttribute(i+"");
            formPrinter(response, i+"", "Wpisz pole "+i, sessionValue);
        }
        response.getWriter().print(webEnd());
    }

    private static String webStart() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"pl\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Tajtel</title>\n" +
                "</head>\n" +
                "<body>" +
                "<form action='/Servlet_03' method='post'>";
    }

    private static String webEnd() {
        return "" +
                "<input type=\"submit\">" +
                "</form>" +
                "</body>\n" +
                "</html>";
    }

    private static void formPrinter(HttpServletResponse response, String name, String desc, String value) throws IOException {
        if (value != null) {
            response.getWriter().print(createInput(name,desc,value));
        } else {
            response.getWriter().print(createInput(name,desc));
        }
    }

    private static String createInput(String name, String desc) {
        return desc + " <input type='text' name=" + name + "><br>";
    }

    private static String createInput(String name, String desc, String value) {
        return desc + " <input type='text' name=" + name + " value=" + value + "><br>";
    }
}