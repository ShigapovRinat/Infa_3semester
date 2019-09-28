package task2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "task2.ServletProfile")
public class ServletProfile extends HttpServlet {
    private String html1 = "<head>" +
            "    <meta charset=\"UTF-8\">" +
            "    <title>Profile</title>" +
            "</head>" +
            "<body>" +
            "<h1>Hello, ";
    private String html2 = "</h1>" +
            "<form action=\"goods\">" +
            "<input type=\"submit\" value=\"Goods\">" +
            "</form>" +
            "<form  action=\"logout\">" +
            "<input type=\"submit\" value=\"Log out\">" +
            "</form>" +
            "</body>\n" +
            "</html>";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        if (login != null) {
            PrintWriter printWriter = response.getWriter();
            printWriter.print(
                    html1 + login + html2);
            printWriter.close();
        } else {
            response.sendRedirect("/task/login");
        }
    }
}
