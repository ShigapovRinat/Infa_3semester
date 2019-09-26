package task2;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletLogin extends javax.servlet.http.HttpServlet {


    protected String html = "<html>\n" +
            "<head>\n" +
            "    <title>login</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<form method=\"post\">\n" +
            "    <h2>\n" +
            "        Login:\n" +
            "        <input placeholder=\"your login\" type=\"text\" name=\"login\"/><br>\n" +
            "        Password:\n" +
            "        <input placeholder=\"your password\" type=\"password\" name=\"password\"/><br>\n" +
            "        Remember me\n" +
            "        <input type=\"checkbox\" name=\"remember\" value=\"true\"/><br>\n" +
            "        <input type=\"submit\" value=\"Log in\" checked>\n" +
            "    </h2>\n" +
            "</form>\n" +
            "</body>\n" +
            "</html>";
    protected String login = null;
    protected String password = null;
    protected String passwordTrue = "pas";
    protected String loginTrue = "admin";


    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        login = request.getParameter("login");
        password = request.getParameter("password");


        if ((password.equals(passwordTrue) && login.equals(loginTrue))) {

            if (request.getParameter("remember") != null) {
                Cookie cookieLog = new Cookie("login", login);
                cookieLog.setMaxAge(2629744);
                response.addCookie(cookieLog);
                Cookie cookiePas = new Cookie("password", password);
                cookiePas.setMaxAge(2629744);
                response.addCookie(cookiePas);
            }

            HttpSession session = request.getSession(true);
            session.setAttribute("login", login);
            response.sendRedirect("http://localhost:8080/task/profile");
        } else {
            response.sendRedirect("http://localhost:8080/task/login");
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

//        request.getRequestDispatcher("login.jsp").forward(request, response);

        PrintWriter out = response.getWriter();
        out.print(html);

        Cookie cookieLog = null;
        Cookie cookiePas = null;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("login")) {
                    cookieLog = c;
                }
                if (c.getName().equals("password")) {
                    cookiePas = c;
                }
            }
        }

        if (cookieLog != null && cookiePas != null) {
            HttpSession session = request.getSession();
            session.setAttribute("login", cookieLog.getValue());
            response.sendRedirect("/task/profile");
        }

        out.close();
    }
}
