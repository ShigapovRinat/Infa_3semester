package ru.javalab.mywebapplication;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        String method = httpReq.getMethod();
        File file = new File("myfile.txt");
        PrintWriter writer = new PrintWriter(file);
        writer.println(method);
        writer.flush();
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
