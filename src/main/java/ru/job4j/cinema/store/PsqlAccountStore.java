package ru.job4j.cinema.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.cinema.AppSettings;
import ru.job4j.cinema.persistence.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PsqlAccountStore implements Store<Integer, Account> {

    private static final Logger LOG = LoggerFactory.getLogger(PsqlAccountStore.class.getName());
    private static final PsqlAccountStore INSTANCE = new PsqlAccountStore();

    private final BasicDataSource pool;

    private PsqlAccountStore() {
        pool = AppSettings.getConnectionPool();
    }

    public static Store<Integer, Account> getInstance() {
        return INSTANCE;
    }

    private boolean create(Account value) {
        boolean result = false;
        String query =
                "INSERT INTO tz_accounts (name, email, phone) VALUES (?, ?, ?);";
        try (
            Connection cn = pool.getConnection();
            PreparedStatement ps = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, value.getName());
            ps.setString(2, value.getEmail());
            ps.setString(3, value.getPhone());
            ps.execute();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                value.setId(keys.getInt(1));
                result = value.getId() > 0;
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            LOG.error("Ошибка выполнения запроса: email или телефон уже зарегистрирован; ", ex);
        } catch (Exception ex) {
            LOG.error("Ошибка выполнения запроса: ", ex);
        }
        return result;
    }

    private boolean update(Account value) {
        boolean result = false;
        String query =
                "UPDATE tz_accounts SET name=?, email=?, phone=? WHERE id=?;";
        try (
            Connection cn = pool.getConnection();
            PreparedStatement ps = cn.prepareStatement(query)
        ) {
            ps.setString(1, value.getName());
            ps.setString(2, value.getEmail());
            ps.setString(3, value.getPhone());
            ps.setInt(4, value.getId());
            result = ps.executeUpdate() > 0;
        } catch (SQLIntegrityConstraintViolationException ex) {
            LOG.error("Ошибка выполнения запроса: email или телефон уже зарегистрирован; ", ex);
        } catch (Exception ex) {
            LOG.error("Ошибка выполнения запроса: ", ex);
        }
        return result;
    }

    @Override
    public List<Account> findAll() {
        List<Account> result = new ArrayList<>();
        String query = "SELECT * FROM tz_accounts;";
        try (
            Connection cn = pool.getConnection();
            PreparedStatement ps = cn.prepareStatement(query);
            ResultSet it = ps.executeQuery()
        ) {
            while (it.next()) {
                Account entry = new Account();
                entry.setId(it.getInt("id"));
                entry.setName(it.getString("name"));
                entry.setEmail(it.getString("email"));
                entry.setPhone(it.getString("phone"));
                result.add(entry);
            }
        } catch (Exception ex) {
            LOG.error("Ошибка при выполнении запроса: ", ex);
        }
        return result;
    }

    @Override
    public Account getById(Integer id) {
        Account result = null;
        String query = "SELECT * FROM tz_accounts WHERE id=?;";
        try (
            Connection cn = pool.getConnection();
            PreparedStatement ps = cn.prepareStatement(query)
        ) {
            ps.setInt(1, id);
            ResultSet it = ps.executeQuery();
            if (it.next()) {
                result = new Account();
                result.setId(it.getInt("id"));
                result.setName(it.getString("name"));
                result.setEmail(it.getString("email"));
                result.setPhone(it.getString("phone"));
            }
            it.close();
        } catch (Exception ex) {
            LOG.error("Ошибка выполнения запроса: ", ex);
        }
        return result;

    }

    @Override
    public boolean save(Account value) {
        return
                value.getId() == 0
                ? create(value)
                : update(value);
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean result = false;
        String query = "DELETE FROM tz_accounts WHERE id=?;";
        try (
                Connection cn = pool.getConnection();
                PreparedStatement ps = cn.prepareStatement(query)
        ) {
            ps.setInt(1, id);
            result = ps.executeUpdate() > 0;
        } catch (Exception ex) {
            LOG.error("Ошибка выполнения запроса: ", ex);
        }
        return result;
    }
}
