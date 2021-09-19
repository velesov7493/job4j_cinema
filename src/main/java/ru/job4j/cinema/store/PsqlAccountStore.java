package ru.job4j.cinema.store;

import ru.job4j.cinema.persistence.Account;

import java.util.List;

public class PsqlAccountStore implements Store<Integer, Account> {

    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public Account getById(Integer id) {
        return null;
    }

    @Override
    public boolean save(Account value) {
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }
}
