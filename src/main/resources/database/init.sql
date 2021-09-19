DROP TABLE IF EXISTS tj_tickets;
DROP TABLE IF EXISTS tz_accounts;
DROP TABLE IF EXISTS tz_sessions;

DROP SEQUENCE IF EXISTS tj_tickets_id_seq;
DROP SEQUENCE IF EXISTS tz_accounts_id_seq;
DROP SEQUENCE IF EXISTS tz_sessions_id_seq;

CREATE TABLE tz_sessions (
    id SERIAL PRIMARY KEY,
    film VARCHAR(250) NOT NULL
);

CREATE TABLE tz_accounts (
  id SERIAL PRIMARY KEY,
  name VARCHAR(120) NOT NULL,
  email VARCHAR(90) NOT NULL UNIQUE,
  phone VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE tj_tickets (
    id SERIAL PRIMARY KEY,
    id_session INT REFERENCES tz_sessions(id) ON DELETE SET NULL,
    id_account INT NOT NULL REFERENCES tz_accounts(id) ON DELETE CASCADE,
    row INT NOT NULL,
    col INT NOT NULL,
    UNIQUE (id_session, row, col)
);