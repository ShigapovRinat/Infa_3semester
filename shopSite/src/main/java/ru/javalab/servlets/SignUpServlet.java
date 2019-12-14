package ru.javalab.servlets;

import ru.javalab.contex.ApplicationContext;
import ru.javalab.dto.UserDto;
import ru.javalab.services.SignUpServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class SignUpServlet extends HttpServlet {

    private ApplicationContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.context = (ApplicationContext) servletContext.getAttribute("context");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        SignUpServiceImpl service = context.getComponent(SignUpServiceImpl.class, "signUpService");
        try {
            UserDto user = service.signUp(login, password);
        } catch (IllegalArgumentException e){
            req.setAttribute("registrationStatus", "Такой логин уже существует");
            req.getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
        }
        resp.sendRedirect("/main");
    }


}
