package ru.javalab.servlets;

import ru.javalab.contex.ApplicationContext;
import ru.javalab.models.Good;
import ru.javalab.repositories.GoodRepository;
import ru.javalab.repositories.GoodRepositoryImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

    private ApplicationContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.context = (ApplicationContext) servletContext.getAttribute("context");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GoodRepositoryImpl goodRepository = context.getComponent(GoodRepositoryImpl.class , "goodRepository");
        List<Good> goodList = goodRepository.findAll();
        req.setAttribute("goods", goodList);
        req.getRequestDispatcher("/jsp/main.jsp").forward(req, resp);
    }
}
