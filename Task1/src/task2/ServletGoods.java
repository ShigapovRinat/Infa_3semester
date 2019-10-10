package task2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ServletGoods")
public class ServletGoods extends HttpServlet {
    private String html = "<head>\n" +
            "    <title>goods</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<h1>Products</h1><br>\n" +
            "<h2>" +
            "<table>\n" +
//            "<caption>Products</caption>" +
            "  <thread>" +
            "    <tr>\n" +
            "        <th>Name</th>\n" +
            "        <th>Price</th>\n" +
            "    </tr>\n" +
            "  </thread>";

    private String html2 = "</table><br>\n" +
            "<form action=\"goods\" method=\"POST\">" +
            "<input placeholder=\"name product\" type=\"text\" name=\"nameGood\"/>\n" +
            "<input placeholder=\"price product\" type=\"text\" name=\"priceGood\"/>\n" +
            "<input type=\"submit\" value=\"Add\">" +
            "</form>" +
//            "<form action=\"goods\" method=\"POST\">" +
//            "<input placeholder=\"id product\" type=\"text\" name=\"idGood\"/>\n" +
//            "<input type=\"submit\" value=\"The most expensive good\">" +
//            "</form>" +
            "The most expensive good: ";


    private String html3 = "</h2>" + "</body>";

    GoodBL goodBL = new GoodBL();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("nameGood") != null && request.getParameter("nameGood") != null) {
            goodBL.addGood(request.getParameter("nameGood"), Integer.parseInt(request.getParameter("priceGood")));
        } else if (request.getParameter("idGood") != null) {
            goodBL.removeGood(Integer.parseInt(request.getParameter("idGood")));
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        HttpSession session = request.getSession(true);
//        List<Good> goods = goodBL.getGoods();
        try {
            session.setAttribute("list", goodBL.getGoods());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Good> goods = null;
        try {
            goods = goodBL.getGoods();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String htmlTable = "";
        for (Good g : goods) {
            htmlTable += "<tr><td>" + g.getName() + "</td>" +
                    "<td>" + g.getPrice() + "</td><td>" +
                    "<form action=\"goods\" method=\"POST\">" +
                    "<input type=\"hidden\" name=\"idGood\" value=\"" + g.getId() + "\"/>\n" +
                    "<input type=\"submit\" value=\"Delete\">" +
                    "</form>" +
                    "</td><tr>";
        }
        printWriter.print(html + htmlTable + html2 + goodBL.getTheMostExpensivePrice() + html3);
        printWriter.close();
    }
}
