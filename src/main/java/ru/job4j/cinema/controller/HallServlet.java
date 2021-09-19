package ru.job4j.cinema.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HallServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        req.setAttribute("rows", 35);
        req.setAttribute("cols", 30);
        req.getRequestDispatcher("views/choosingPlace.jsp").forward(req, resp);
    }
}
