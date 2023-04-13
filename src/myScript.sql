DROP DATABASE IF EXISTS testapp;
CREATE DATABASE IF NOT EXISTS testapp;
SHOW DATABASES ;
USE testapp;

DROP TABLE IF EXISTS mydb;
CREATE TABLE IF NOT EXISTS mydb(
    Sample1          VARCHAR (10),
    Sample2          VARCHAR (10),
    Sample3          VARCHAR (10),
    CONSTRAINT      PRIMARY KEY (Sample1)
    );

INSERT INTO mydb VALUES('C-001','sample','sample');
INSERT INTO mydb VALUES('C-002','sample','sample');
INSERT INTO mydb VALUES('C-003','sample','sample');
INSERT INTO mydb VALUES('C-004','sample','sample');
