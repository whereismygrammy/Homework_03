package pl.coderslab;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "Servlet_05_1", urlPatterns = "/Servlet_05_1")
public class Servlet_05_1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String productName = request.getParameter("productName");
        String quantity = request.getParameter("quantity");
        String price = request.getParameter("price");
        String[] product = {productName, quantity, price};

        HttpSession session = request.getSession();
        String[][] basket = (String[][]) session.getAttribute("basket");

        if (basket == null) {
            System.out.println("Nie ma basketu");
            basket = new String[1][];
        } else {
            basket = Arrays.copyOf(basket, basket.length + 1);
        }
        basket[basket.length - 1] = product;
        session.setAttribute("basket", basket);

        response.getWriter().print(webStart());
        response.getWriter().print(createParagraph("Produkt dodany"));
        response.getWriter().print(formStart());
        response.getWriter().print(createInput("text", "productName", "Nazwa produktu"));
        response.getWriter().print(createInput("number", "quantity", "Ilosc"));
        response.getWriter().print(createInput("number", "price", "Cena"));
        response.getWriter().print(formEnd());
        response.getWriter().print(createParagraph("<a href='/Servlet_05_2'>Przejdz do koszyka</a>"));
        response.getWriter().print(webEnd());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print(webStart());
        response.getWriter().print(formStart());
        response.getWriter().print(createInput("text", "productName", "Nazwa produktu"));
        response.getWriter().print(createInput("number", "quantity", "Ilosc"));
        response.getWriter().print(createInput("number", "price", "Cena"));
        response.getWriter().print(formEnd());
        response.getWriter().print(createParagraph("<a href='/Servlet_05_2'>Przejdz do koszyka</a>"));
        response.getWriter().print(webEnd());
    }

    public static String webStart() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"pl\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Koszyk</title>\n" +
                "</head>\n" +
                "<body>";

    }

    private static String formStart() {
        return "<p>" + "<form action='/Servlet_05_1' method='post'>";
    }

    private static String formEnd() {
        return "</p> <input type=\"submit\"></form>";
    }

    private static String createInput(String type, String name, String desc) {
        return desc + " <input type='" + type + "' name=" + name + "><br>";
    }

    private static String createParagraph(String content) {
        return "<p>" + content + "</p>";
    }

    public static String webEnd() {
        return "</body>" + "</html>";
    }


}
