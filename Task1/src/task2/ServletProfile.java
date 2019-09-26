package task2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "task2.ServletProfile")
public class ServletProfile extends HttpServlet {
    protected String login;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        login = (String) session.getAttribute("login");
        if (login != null) {
            PrintWriter printWriter = response.getWriter();
            printWriter.print(
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Profile</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>Hello, " + login + "</h1>\n" +
                    "<form  action=\"logout\">" +
                    "<input type=\"submit\" value=\"Log out\">" +
                    "</form>" +
                    "</body>\n" +
                    "</html>");
            printWriter.close();
        } else {
            response.sendRedirect("/task/login");
        }
    }
}
