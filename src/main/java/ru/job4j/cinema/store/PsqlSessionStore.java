package ru.job4j.cinema.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.cinema.AppSettings;
import ru.job4j.cinema.persistence.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PsqlSessionStore implements Store<Integer, Session> {

    private static final Logger LOG = LoggerFactory.getLogger(PsqlSessionStore.class.getName());
    private static final PsqlSessionStore INSTANCE = new PsqlSessionStore();

    private final BasicDataSource pool;

    private PsqlSessionStore() {
        pool = AppSettings.getConnectionPool();
    }

    public static Store<Integer, Session> getInstance() {
        return INSTANCE;
    }

    private boolean create(Session value) {
        boolean result = false;
        String query =
                "INSERT INTO tz_sessions (film) VALUES (?);";
        try (
            Connection cn = pool.getConnection();
            PreparedStatement ps = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, value.getFilmName());
            ps.execute();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                value.setId(keys.getInt(1));
                result = value.getId() > 0;
            }
        } catch (Exception ex) {
            LOG.error("Ошибка выполнения запроса: ", ex);
        }
        return result;
    }

    private boolean update(Session value) {
        boolean result = false;
        String query =
                "UPDATE tz_sessions SET film=? WHERE id=?;";
        try (
            Connection cn = pool.getConnection();
            PreparedStatement ps = cn.prepareStatement(query)
        ) {
            ps.setString(1, value.getFilmName());
            ps.setInt(2, value.getId());
            result = ps.executeUpdate() > 0;
        } catch (Exception ex) {
            LOG.error("Ошибка выполнения запроса: ", ex);
        }
        return result;
    }

    @Override
    public List<Session> findAll() {
        List<Session> result = new ArrayList<>();
        String query = "SELECT * FROM tz_sessions;";
        try (
                Connection cn = pool.getConnection();
                PreparedStatement ps = cn.prepareStatement(query);
                ResultSet it = ps.executeQuery()
        ) {
            while (it.next()) {
                Session entry = new Session();
                entry.setId(it.getInt("id"));
                entry.setFilmName(it.getString("film"));
                result.add(entry);
            }
        } catch (Exception ex) {
            LOG.error("Ошибка при выполнении запроса: ", ex);
        }
        return result;
    }

    @Override
    public Session getById(Integer id) {
        Session result = null;
        String query = "SELECT * FROM tj_tickets WHERE id=?;";
        try (
                Connection cn = pool.getConnection();
                PreparedStatement ps = cn.prepareStatement(query)
        ) {
            ps.setInt(1, id);
            ResultSet it = ps.executeQuery();
            if (it.next()) {
                result = new Session();
                result.setId(it.getInt("id"));
                result.setFilmName(it.getString("film"));
            }
            it.close();
        } catch (Exception ex) {
            LOG.error("Ошибка выполнения запроса: ", ex);
        }
        return result;
    }

    @Override
    public boolean save(Session value) {
        return
                value.getId() == 0
                ? create(value)
                : update(value);
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean result = false;
        String query = "DELETE FROM tz_sessions WHERE id=?;";
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