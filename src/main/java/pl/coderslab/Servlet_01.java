package pl.coderslab;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Servlet_01", urlPatterns = "/Servlet_01")
public class Servlet_01 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Double> currencyExchangeMap = createCurrencyExchangeMap();
        response.getWriter().print(webStart());

        try {
            Double quantity = Double.parseDouble(request.getParameter("cashDouble"));
            String currency = request.getParameter("currency");
            Double exchangeRate = currencyExchangeMap.get(currency);
            Double cash = exchangeRate * quantity;
            response.getWriter().print(createParagraph("Dla podanej ilosci " + quantity + " cena wyniesie " + cash));
        } catch (NumberFormatException e) {
            response.getWriter().print(createParagraph("Podano zly format danych"));
        }
        response.getWriter().print(createParagraph("<a href='index.html'>Wroc do kalkulatora</a>"));
        response.getWriter().print(webEnd());
    }


    protected Map<String, Double> createCurrencyExchangeMap() {
        Map<String, Double> currencyExchange = new HashMap<String, Double>();
        currencyExchange.put("eurUsd", 1.178915);
        currencyExchange.put("usdEur", 0.848237574);
        currencyExchange.put("eurPln", 4.29281748);
        currencyExchange.put("plnEur", 0.232947244);
        currencyExchange.put("usdPln", 3.64132909);
        currencyExchange.put("plnUsd", 0.274625);
        return currencyExchange;
    }


    public static String webStart() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"pl\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Tajtel</title>\n" +
                "</head>\n" +
                "<body>";
    }


    public static String webEnd() {
        return "</body>\n" +
                "</html>";
    }


    public static String createParagraph(String content) {
        return "<p>" + content + "</p>";
    }

}
