package ru.job4j.cinema.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.cinema.AppSettings;
import ru.job4j.cinema.persistence.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PsqlTicketStore implements TicketStore {

    private static final Logger LOG = LoggerFactory.getLogger(PsqlTicketStore.class.getName());
    private static final PsqlTicketStore INSTANCE = new PsqlTicketStore();

    private final BasicDataSource pool;

    private PsqlTicketStore() {
        pool = AppSettings.getConnectionPool();
    }

    public static TicketStore getInstance() {
        return INSTANCE;
    }

    private boolean create(Ticket value) {
        boolean result = false;
        String query =
            "INSERT INTO tj_tickets (id_session, id_account, row, col) VALUES "
            + "(?, ?, ?, ?);";
        try (
                Connection cn = pool.getConnection();
                PreparedStatement ps = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setInt(1, value.getSessionId());
            ps.setInt(2, value.getAccountId());
            ps.setInt(3, value.getRow());
            ps.setInt(4, value.getCol());
            ps.execute();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                value.setId(keys.getInt(1));
                result = value.getId() > 0;
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            LOG.error("Ошибка выполнения запроса: это место на этот сеанс уже занято; ", ex);
        } catch (Exception ex) {
            LOG.error("Ошибка выполнения запроса: ", ex);
        }
        return result;
    }

    private boolean update(Ticket value) {
        boolean result = false;
        String query =
                "UPDATE tj_tickets SET id_session=?, id_account=?, row=?, col=? "
                + "WHERE id=?;";
        try (
                Connection cn = pool.getConnection();
                PreparedStatement ps = cn.prepareStatement(query)
        ) {
            ps.setInt(1, value.getSessionId());
            ps.setInt(2, value.getAccountId());
            ps.setInt(3, value.getRow());
            ps.setInt(4, value.getCol());
            ps.setInt(5, value.getId());
            result = ps.executeUpdate() > 0;
        } catch (SQLIntegrityConstraintViolationException ex) {
            LOG.error("Ошибка выполнения запроса: это место на этот сеанс уже занято; ", ex);
        } catch (Exception ex) {
            LOG.error("Ошибка выполнения запроса: ", ex);
        }
        return result;
    }

    @Override
    public List<Ticket> findAll() {
        List<Ticket> result = new ArrayList<>();
        String query = "SELECT * FROM tj_tickets;";
        try (
            Connection cn = pool.getConnection();
            PreparedStatement ps = cn.prepareStatement(query);
            ResultSet it = ps.executeQuery()
        ) {
            while (it.next()) {
                Ticket entry = new Ticket();
                entry.setId(it.getInt("id"));
                entry.setSessionId(it.getInt("id_session"));
                entry.setAccountId(it.getInt("id_account"));
                entry.setRow(it.getInt("row"));
                entry.setCol(it.getInt("col"));
                result.add(entry);
            }
        } catch (Exception ex) {
            LOG.error("Ошибка выполнения запроса: ", ex);
        }
        return result;
    }

    @Override
    public List<Ticket> findAllBySessionId(Integer sessionId) {
        List<Ticket> result = new ArrayList<>();
        String query = "SELECT * FROM tj_tickets WHERE id_session=?;";
        try (
                Connection cn = pool.getConnection();
                PreparedStatement ps = cn.prepareStatement(query)
        ) {
            ps.setInt(1, sessionId);
            ResultSet it = ps.executeQuery();
            while (it.next()) {
                Ticket entry = new Ticket();
                entry.setId(it.getInt("id"));
                entry.setSessionId(it.getInt("id_session"));
                entry.setAccountId(it.getInt("id_account"));
                entry.setRow(it.getInt("row"));
                entry.setCol(it.getInt("col"));
                result.add(entry);
            }
            it.close();
        } catch (Exception ex) {
            LOG.error("Ошибка выполнения запроса: ", ex);
        }
        return result;
    }

    @Override
    public Ticket getById(Integer id) {
        Ticket result = null;
        String query = "SELECT * FROM tj_tickets WHERE id=?;";
        try (
            Connection cn = pool.getConnection();
            PreparedStatement ps = cn.prepareStatement(query)
        ) {
            ps.setInt(1, id);
            ResultSet it = ps.executeQuery();
            if (it.next()) {
                result = new Ticket();
                result.setId(it.getInt("id"));
                result.setSessionId(it.getInt("id_session"));
                result.setAccountId(it.getInt("id_account"));
                result.setRow(it.getInt("row"));
                result.setCol(it.getInt("col"));
            }
            it.close();
        } catch (Exception ex) {
            LOG.error("Ошибка выполнения запроса: ", ex);
        }
        return result;
    }

    @Override
    public boolean save(Ticket value) {
        return
                value.getId() == 0
                ? create(value)
                : update(value);
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean result = false;
        String query = "DELETE FROM tj_tickets WHERE id=?;";
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
