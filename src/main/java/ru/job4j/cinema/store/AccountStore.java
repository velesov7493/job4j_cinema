package ru.job4j.cinema.store;

import ru.job4j.cinema.persistence.Account;

public interface AccountStore extends Store<Integer, Account> {

    Account getByPhone(String phone);
}
