package pl.coderslab;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Servlet_04", urlPatterns = "/Servlet_04")
public class Servlet_04 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = null;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("visits".equals(c.getName())) {
                    int value = Integer.parseInt(c.getValue()) + 1;
                    response.getWriter().print("Witaj, odwiedziles nas juz " + value + " razy");
                    cookie = new Cookie("visits", value + "");
                    break;
                }
            }
        }

        if (cookie == null) {
            response.getWriter().print("Witaj pierwszy raz na naszej stronie ");
            cookie = new Cookie("visits", "1");
        }

        cookie.setMaxAge(3600);
        response.addCookie(cookie);
    }
}
