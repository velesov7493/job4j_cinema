package ru.job4j.cinema.controller;

import ru.job4j.cinema.persistence.Account;
import ru.job4j.cinema.persistence.Session;
import ru.job4j.cinema.persistence.Ticket;
import ru.job4j.cinema.store.PsqlAccountStore;
import ru.job4j.cinema.store.PsqlSessionStore;
import ru.job4j.cinema.store.PsqlTicketStore;
import ru.job4j.cinema.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PaymentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        HttpSession s = req.getSession();
        Store<Integer, Session> store = PsqlSessionStore.getInstance();
        req.setAttribute("cinemaSession", store.getById((Integer) s.getAttribute("sessionId")));
        req.setAttribute("row", s.getAttribute("row"));
        req.setAttribute("col", s.getAttribute("col"));
        req.getRequestDispatcher("views/payment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        Store<Integer, Account> accStore = PsqlAccountStore.getInstance();
        Store<Integer, Ticket> ticketStore = PsqlTicketStore.getInstance();
        HttpSession s = req.getSession();
        Account user = new Account();
        user.setName(req.getParameter("nUserName"));
        user.setEmail(req.getParameter("nEmail"));
        user.setPhone(req.getParameter("nPhone"));
        accStore.save(user);
        if (user.getId() != 0) {
            Ticket t = new Ticket();
            t.setAccountId(user.getId());
            t.setSessionId((Integer) s.getAttribute("sessionId"));
            t.setRow((Integer) s.getAttribute("row"));
            t.setCol((Integer) s.getAttribute("col"));
            ticketStore.save(t);
        }
        resp.sendRedirect(req.getContextPath() + "/place.do");
    }
}
