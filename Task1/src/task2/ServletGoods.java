package task2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
            "        <th>ID</th>\n" +
            "        <th>Name</th>\n" +
            "    </tr>\n" +
            "  </thread>";

    private String html2 = "</table><br>\n" +
            "<form action=\"goods\" method=\"POST\">" +
            "<input placeholder=\"name product\" type=\"text\" name=\"nameGood\"/>\n" +
            "<input type=\"submit\" value=\"Add\">" +
            "</form>" +
            "<form action=\"goods\" method=\"POST\">" +
            "<input placeholder=\"id product\" type=\"text\" name=\"idGood\"/>\n" +
            "<input type=\"submit\" value=\"Delete\">" +
            "</form>" +
            "</h2>" + "</body>";

    OrdersBL ordersBL = new OrdersBL();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("nameGood") != null) {
            ordersBL.addGood(request.getParameter("nameGood"));
        } else if (request.getParameter("idGood") != null) {
            ordersBL.removeGood(Integer.parseInt(request.getParameter("idGood")));
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        List<Good> goods = ordersBL.getGoods();
        String htmlTable = "";
        for (Good g : goods) {
            htmlTable += "<tr><td>" + g.getId() + "</td><td>" + g.getName() + "</td><tr>";
        }
        printWriter.print(html + htmlTable + html2);
        printWriter.close();
    }
}
