/*
 * When the application is launched this file with the name data.sql gets called and 
 * the data in this file is used to initialize the database, this is a Spring Boot 
 * auto configuration feature.
 */

CREATE TABLE person 
(
id integer NOT NULL,
name varchar(255) NOT NULL,
location varchar(255),
birth_date timestamp,
PRIMARY KEY(id)
);

INSERT INTO person (ID, NAME, LOCATION, BIRTH_DATE)
VALUES(10001, 'Bob', 'New York', sysdate());
INSERT INTO person (ID, NAME, LOCATION, BIRTH_DATE)
VALUES(10002, 'John', 'Stockholm', sysdate());
INSERT INTO person (ID, NAME, LOCATION, BIRTH_DATE)
VALUES(10003, 'Jill', 'Hamburg', sysdate());