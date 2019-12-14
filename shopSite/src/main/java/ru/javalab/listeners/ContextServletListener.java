package ru.javalab.listeners;

import ru.javalab.contex.ApplicationContext;
import ru.javalab.contex.ApplicationContextReflectionBased;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        ApplicationContext context = new ApplicationContextReflectionBased();
        servletContext.setAttribute("context", context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
