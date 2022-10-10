create database SMART_DATA_SERVICE;
use SMART_DATA_SERVICE;
CREATE TABLE firstname (id INT(6) UNSIGNED, name VARCHAR(50), regioncode VARCHAR(10));
CREATE TABLE lastname (id INT(6) UNSIGNED, name VARCHAR(50), regioncode VARCHAR(10));
CREATE TABLE email (domain VARCHAR(50), address VARCHAR(50));
CREATE TABLE address (id int(6), address VARCHAR(50), city VARCHAR(20), state VARCHAR(50), country VARCHAR(50), zip VARCHAR(10), region VARCHAR(50));
CREATE TABLE words(id INT NOT NULL, words VARCHAR(25), PRIMARY KEY(id));