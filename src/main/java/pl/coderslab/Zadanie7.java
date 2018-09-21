package pl.coderslab;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Zadanie7", urlPatterns = "/favImages")
public class Zadanie7 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String submit = request.getParameter("submit");
        HttpSession session = request.getSession();
        int pagesVisited = (int) session.getAttribute("pagesVisited");

        if (submit.equals("Yes")) {
            session.setAttribute("choice_" + pagesVisited, "true");
        }
        pagesVisited++;
        session.setAttribute("pagesVisited", pagesVisited);

        if (pagesVisited < 5) {
            redirect(response, pagesVisited);
        } else {
            response.getWriter().println("Te obrazy Ci sie podobaly:");

            for (int i = 1; i < 5; i++) {
                Object attribute = session.getAttribute("choice_" + i);
                if (attribute != null) {
                    response.getWriter().println(i);
                }
            }
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Object pagesVisitedObj = session.getAttribute("pagesVisited");
        int pagesVisited = 0;

        if (pagesVisitedObj != null) {
            pagesVisited = (int) pagesVisitedObj + 1;
            session.setAttribute("pagesVisited", pagesVisited);
        } else {
            pagesVisited = 1;
            session.setAttribute("pagesVisited", pagesVisited);
        }
        redirect(response, pagesVisited);
    }

    protected void redirect(HttpServletResponse response, int pagesVisited) throws IOException {
        switch (pagesVisited) {
            case 1:
                response.sendRedirect("wybor_1.html");
                break;

            case 2:
                response.sendRedirect("wybor_2.html");
                break;

            case 3:
                response.sendRedirect("wybor_3.html");
                break;

            case 4:
                response.sendRedirect("wybor_4.html");
                break;

            default:
                response.getWriter().println("Dzięki za wypełnienie testu");
        }
    }
}

