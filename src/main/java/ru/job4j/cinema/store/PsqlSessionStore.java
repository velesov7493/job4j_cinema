package ru.job4j.cinema.store;

import ru.job4j.cinema.persistence.Session;

import java.util.List;

public class PsqlSessionStore implements Store<Integer, Session> {

    @Override
    public List<Session> findAll() {
        return null;
    }

    @Override
    public Session getById(Integer id) {
        return null;
    }

    @Override
    public boolean save(Session value) {
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }
}