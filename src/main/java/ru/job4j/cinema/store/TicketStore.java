package ru.job4j.cinema.store;

import ru.job4j.cinema.persistence.Ticket;

import java.util.List;

public interface TicketStore extends Store<Integer, Ticket> {

    List<Ticket> findAllBySessionId(Integer sessionId);
}
