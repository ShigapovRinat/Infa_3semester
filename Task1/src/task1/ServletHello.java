package task1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletHello")
public class ServletHello extends HttpServlet {
    protected String name = null;
    protected String group = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        name = request.getParameter("login");
        group = request.getParameter("group");
        response.sendRedirect("/task/hello");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (name == "" || name == null) {
            name = "Anonymous";
        }
        ;
        if (group == "" || group == null) {
            group = "0";
        }


        request.setAttribute("name", name);
        request.setAttribute("group", group);
        request.getRequestDispatcher("mypage.jsp").forward(request, response);

//        PrintWriter printWriter = response.getWriter();
//        printWriter.print("<!DOCTYPE html>\n" +
//                "<html lang=\"en\">\n" +
//                "<head>\n" +
//                "    <meta charset=\"UTF-8\">\n" +
//                "    <title>Hello</title>\n" +
//                "</head>\n" +
//                "<body>\n" +
//                "<h1>Hello, " + name + "</h1>\n" +
//                "<h3>Welcome to " +
//                group +
//                "</h3>\n" +
//                "<form method=\"post\">" +
//                "<h2>" +
//                "Name: " +
//                "<input type= \"text\" login=\"login\"/><br>" +
//                "Group: " +
//                "<input type= \"text\" login=\"group\"/><br>" +
//                "<INPUT type=\"submit\" value=\"Send\">" +
//                "</h2>" +
//                "</form>" +
//                "</body>\n" +
//                "</html>");
    }
}
