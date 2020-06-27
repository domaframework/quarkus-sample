DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
GRANT ALL ON SCHEMA public TO quarkus_test;
GRANT ALL ON SCHEMA public TO public;

CREATE SEQUENCE known_fruits_id_seq INCREMENT BY 1 START WITH 10;

CREATE TABLE known_fruits(
    ID INTEGER NOT NULL PRIMARY KEY,
    NAME VARCHAR(40) UNIQUE
);

INSERT INTO known_fruits(id, name) VALUES (1, 'Cherry');
INSERT INTO known_fruits(id, name) VALUES (2, 'Apple');
INSERT INTO known_fruits(id, name) VALUES (3, 'Banana');
