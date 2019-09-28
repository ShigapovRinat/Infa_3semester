package task2;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UsersUT {
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String passwordTrue = "pas";
        String loginTrue = "admin";
        String login = request.getParameter("login");
        String password = request.getParameter("password");

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

    public void remember(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        HttpSession session = request.getSession();
        if (cookieLog != null && cookiePas != null || session.getAttribute("login") != null) {
            if (session.getAttribute("login") != null){
                session.setAttribute("login", request.getParameter("login"));
            } else {
                session.setAttribute("login", cookieLog.getValue());
            }
            response.sendRedirect("/task/profile");
        }

    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie cookieLog = new Cookie("login", "");
        cookieLog.setMaxAge(0);
        response.addCookie(cookieLog);
        Cookie pasLog = new Cookie("password", "");
        pasLog.setMaxAge(0);
        response.addCookie(pasLog);
        HttpSession session = request.getSession();
        session.invalidate();
    }
}
