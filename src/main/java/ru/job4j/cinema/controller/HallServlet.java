package ru.job4j.cinema.controller;

import ru.job4j.cinema.persistence.Session;
import ru.job4j.cinema.store.PsqlSessionStore;
import ru.job4j.cinema.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HallServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        Store<Integer, Session> store = PsqlSessionStore.getInstance();
        req.setAttribute("cinemaSession", store.getById(1));
        req.setAttribute("rows", 25);
        req.setAttribute("cols", 20);
        req.getRequestDispatcher("views/choosingPlace.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        HttpSession s = req.getSession();
        s.setAttribute("sessionId", Integer.parseInt(req.getParameter("nSession")));
        s.setAttribute("row", Integer.parseInt(req.getParameter("nRow")));
        s.setAttribute("col", Integer.parseInt(req.getParameter("nCol")));
        resp.sendRedirect(req.getContextPath() + "/payment.do");
    }
}
