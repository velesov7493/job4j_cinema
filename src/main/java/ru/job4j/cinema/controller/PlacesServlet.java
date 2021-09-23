package ru.job4j.cinema.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.cinema.persistence.Ticket;
import ru.job4j.cinema.store.PsqlTicketStore;
import ru.job4j.cinema.store.TicketStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class PlacesServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        String ssid = req.getParameter("sessionId");
        int sessionId = ssid == null ? 0 : Integer.parseInt(ssid);
        TicketStore store = PsqlTicketStore.getInstance();
        List<Ticket> tickets = store.findAllBySessionId(sessionId);
        resp.setContentType("application/json; charset=utf-8");
        String json = GSON.toJson(tickets);
        OutputStream out = resp.getOutputStream();
        out.write(json.getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
    }
}
