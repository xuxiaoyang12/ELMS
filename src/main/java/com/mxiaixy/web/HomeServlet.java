package com.mxiaixy.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Mxia on 2017/1/18.
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("小梦梦");
//        resp.sendRedirect("/WEB-INF/views/home.jsp");
        //req.getRequestDispatcher("/WEB-INF/views/home.jsp");
        req.getRequestDispatcher("/home.jsp");
        // resp.sendRedirect("/home.jsp");

    }
}
