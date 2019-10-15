package ru.javalab.downloader;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        String method = httpReq.getMethod();
        Date time = new Date();
        String url = httpReq.getRequestURI();
//        String ipAddress = httpReq.getHeader("Remote_Addr");
        String ipAddress = httpReq.getHeader("HTTP_X_FORWARDED_FOR");
        if (ipAddress == null) {
            ipAddress = httpReq.getRemoteAddr();
        }

        File file = new File("myfile.txt");
        FileWriter writer = new FileWriter(file, true);
//        PrintWriter writer = new PrintWriter(file, String.valueOf(true));
        writer.write(method + "\n" +
                time + "\n" +
                url + "\n" +
                ipAddress + "\n");
        writer.flush();
        writer.close();
        filterChain.doFilter(servletRequest, servletResponse);


    }

    @Override
    public void destroy() {

    }
}
