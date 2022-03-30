CREATE TABLE IF NOT EXISTS city
(
    id SERIAL PRIMARY KEY,
    name TEXT
);

INSERT INTO city(name) VALUES ('Москва');
INSERT INTO city(name) VALUES ('СПб');
INSERT INTO city(name) VALUES ('Екб');

CREATE TABLE IF NOT EXISTS post
(
    id      SERIAL PRIMARY KEY,
    name    TEXT,
    visible BOOLEAN,
    city_id INT REFERENCES city(id)
);

CREATE TABLE IF NOT EXISTS candidate
(
    id   SERIAL PRIMARY KEY,
    name TEXT,
    description TEXT,
    created TIMESTAMP,
    photo bytea
);
