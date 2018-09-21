package pl.coderslab;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Servlet_05_2", urlPatterns = "/Servlet_05_2")
public class Servlet_05_2 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String[][] basket = (String[][]) session.getAttribute("basket");
        int sum = 0;

        response.getWriter().print(Servlet_05_1.webStart());
        response.getWriter().print(tableStart());
        for (String[] product : basket) {
            int quan = Integer.parseInt(product[1]);
            int price = Integer.parseInt(product[2]);
            int finalPrice = quan * price;
            response.getWriter().print(tableRow(product[0], quan, price, finalPrice));
            sum += finalPrice;
        }
        response.getWriter().print(tableRow(sum));
        response.getWriter().print(tableEnd());
        response.getWriter().print(Servlet_05_1.webEnd());

    }

    private String tableStart() {
        return "<table>";
    }

    private String tableRow(String prodName, int prodQuantity, int prodPrice, int finalPrice) {
        return "<tr>" +
                "  <td>" + prodName + "</td>" +
                "  <td> - </td>" +
                "  <td>" + prodQuantity + "</td>" +
                "  <td>x</td> \n" +
                "  <td>" + prodPrice + "</td>\n" +
                "  <td><center>     = </center></td>\n" +
                "  <td>" + finalPrice + " </td>\n" +
                "</tr>";
    }

    private String tableRow(int sumPrice) {
        return "<tr>" +
                "  <td></td>" +
                "  <td></td>" +
                "  <td></td>" +
                "  <td></td>" +
                "  <td></td>" +
                "  <td> SUMA: </td>" +
                "  <td>" + sumPrice + " </td>" +
                "</tr>";
    }

    private String tableEnd() {
        return "</table>\n";
    }

}
