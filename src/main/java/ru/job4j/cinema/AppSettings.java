package ru.job4j.cinema;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.Properties;

public class AppSettings {

    private static final Logger LOG = LoggerFactory.getLogger(AppSettings.class.getName());
    private static SoftReference<Properties> settings;
    private static BasicDataSource pool;

    private static Properties loadProperties() {
        Properties s = settings == null ? null : settings.get();
        if (s == null) {
            s = new Properties();
            try (InputStream in =
                         AppSettings.class
                         .getClassLoader()
                         .getResourceAsStream("app.properties")
            ) {
                s.load(in);
                settings = new SoftReference<>(s);
            } catch (Throwable ex) {
                LOG.error(
                        "Критическая ошибка - невозможно прочитать свойства подключения к БД: ", ex
                );
                LOG.info("Выключаюсь...");
                System.exit(2);
            }
        }
        return s;
    }

    public static synchronized BasicDataSource getConnectionPool() {
        if (pool == null) {
            Properties cfg = loadProperties();
            try {
                Class.forName(cfg.getProperty("jdbc.driver"));
            } catch (ClassNotFoundException ex) {
                throw new IllegalStateException(ex);
            }
            pool = new BasicDataSource();
            pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
            pool.setUrl(cfg.getProperty("jdbc.url"));
            pool.setUsername(cfg.getProperty("jdbc.username"));
            pool.setPassword(cfg.getProperty("jdbc.password"));
            pool.setMinIdle(5);
            pool.setMaxIdle(10);
            pool.setMaxOpenPreparedStatements(100);
        }
        return pool;
    }
}
