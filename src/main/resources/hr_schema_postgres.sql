--create tables
BEGIN;

DROP TABLE IF EXISTS customers  CASCADE ;
CREATE TABLE customers (
                                   entity_id SERIAL PRIMARY KEY,

                                  name VARCHAR(250) NOT NULL,
                                  email  VARCHAR (250) NOT NULL,
                                  password VARCHAR (250) NOT NULL ,
                                  tel_num VARCHAR (250) NOT NULL ,
                                  age INT NOT NULL,
                                  creation_date TIMESTAMP NOT NULL ,
                                  last_modified_date TIMESTAMP NOT NULL



);

DROP TABLE IF EXISTS accounts CASCADE ;
CREATE TABLE accounts (
                                entity_id SERIAL PRIMARY KEY,

                                 number VARCHAR (250) NOT NULL,
                                 currency VARCHAR(250) NOT NULL ,
                                 balance INT NOT NULL ,
                                 customer_id  INTEGER REFERENCES customers (entity_id),
                                creation_date TIMESTAMP NOT NULL ,
                                last_modified_date TIMESTAMP NOT NULL


);


DROP TABLE IF EXISTS employee CASCADE ;
CREATE TABLE employee(
                                entity_id SERIAL PRIMARY KEY ,
                                employer_name VARCHAR (250) NOT NULL,
                                location VARCHAR(250) NOT NULL,
                                creation_date TIMESTAMP NOT NULL ,
                                last_modified_date TIMESTAMP NOT NULL



);
DROP TABLE IF EXISTS employee_customer CASCADE ;
CREATE TABLE employee_customer
(
    id SERIAL PRIMARY KEY ,
    customer_id INT NOT NULL,
    employee_id INT NOT NULL ,
    FOREIGN KEY (customer_id) REFERENCES customers(entity_id),
    FOREIGN KEY (employee_id) REFERENCES employee (entity_id)



);
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS roles CASCADE;

CREATE TABLE users (
                       user_id SERIAL  PRIMARY KEY,
                       user_name VARCHAR(36) NOT NULL,
                       encrypted_password VARCHAR(128) NOT NULL,
                       creation_date TIMESTAMP  NULL,
                       last_modified_date TIMESTAMP  NULL,
                       created_by INT NULL,
                       last_modified_by INT NULL,
                       enabled boolean NOT NULL
);

CREATE TABLE roles (
                       role_id SERIAL  PRIMARY KEY,
                       role_name VARCHAR(30) NOT NULL,
                       user_id INT
);


COMMIT;

