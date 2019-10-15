package ru.javalab.downloader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import downloader.*;

public class DownloaderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println("<html>" +
                "<body>" +
                "<form action=\"downloader\" method=\"POST\" >" +
                "<input name=\"url\" type=\"text\">" +
                "<input type=\"submit\">" +
                "</form>" +
                "</body>" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] url = req.getParameter("url").split(" ");
        Main.main(url);
        doGet(req, resp);
    }
}
