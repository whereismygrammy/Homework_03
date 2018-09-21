package pl.coderslab;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "Servlet_02", urlPatterns = "/Servlet_02")
public class Servlet_02 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String binarNumberStr = request.getParameter("binarNumber");
        Pattern pattern = Pattern.compile("[01]*");
        Matcher matcher = pattern.matcher(binarNumberStr);

        response.getWriter().print(Servlet_01.webStart());

        if (matcher.matches()) {
            Double decimal = 0.0;
            int[] ints = new int[binarNumberStr.length()];

            for (int i = 0; i < binarNumberStr.length(); i++) {
                ints[i] = Character.digit(binarNumberStr.charAt(i), 10);
            }

            int j = 0;
            for (int i = ints.length - 1; i >= 0; i--) {
                double tempNumber = ints[i] * Math.pow(2, j);
                decimal += tempNumber;
                j++;
            }
            int dec = decimal.intValue();
            response.getWriter().print(Servlet_01.createParagraph(binarNumberStr + " to liczba " + dec));

        } else {
            response.getWriter().print(Servlet_01.createParagraph("Niepoprawna liczba binarna. <br><a href='index2.html'>Podaj poprana liczbe</a>"));
        }

        response.getWriter().print(Servlet_01.webEnd());
    }
}
