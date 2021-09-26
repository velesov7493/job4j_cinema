package ru.job4j.cinema.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorsServlet extends HttpServlet {

    private void handleError(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        String scode = req.getParameter("code");
        int code = scode != null ? Integer.parseInt(scode) : 0;
        switch (code) {
            case 409: req.getRequestDispatcher("views/error409.jsp").forward(req, resp); break;
            default: resp.sendRedirect(req.getContextPath() + "/film.do");
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        handleError(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        handleError(req, resp);
    }
}
