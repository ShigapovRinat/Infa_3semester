package task2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletLogout")
public class ServletLogout extends HttpServlet {

    private String html1 = "<h1>Successfully</h1>" +
            "<form  action=\"login\">" +
            "<input type=\"submit\" value=\"Back\">" +
            "<form>";
    private UsersUT user = new UsersUT();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Cookie cookieLog = new Cookie("login", "");
//        cookieLog.setMaxAge(0);
//        response.addCookie(cookieLog);
//        Cookie pasLog = new Cookie("password", "");
//        pasLog.setMaxAge(0);
//        response.addCookie(pasLog);
//        HttpSession session = request.getSession();
//        session.invalidate();

        user.logout(request, response);
        PrintWriter out = response.getWriter();
        out.print(html1);
        out.close();

//        Cookie[] cookies = request.getCookies();
//        String loginCookie = "login";
//        String passwordCookie = "password";
//        if (cookies != null) {
//            for (int i = 0; i < cookies.length; i++) {
//                if (cookies[i].getName().equals(loginCookie)) {
//                    cookies[i].setMaxAge(0);
//                    response.addCookie(cookies[i]);
//                    System.out.println("Delete log");
//                }
//                if (cookies[i].getName().equals(passwordCookie)) {
//                    cookies[i].setMaxAge(0);
//                    response.addCookie(cookies[i]);
//                    System.out.println("Delete pas");
//                }
//            }
//        }

    }
}
