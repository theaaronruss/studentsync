CREATE DATABASE IF NOT EXISTS studentsync;
USE studentsync;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS teacher;
CREATE TABLE IF NOT EXISTS user(
	username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    PRIMARY KEY (username)
);
CREATE TABLE IF NOT EXISTS student(
	id VARCHAR(50) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    birth_date DATE,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS teacher(
	id VARCHAR(50) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    start_date DATE,
    PRIMARY KEY (id)
);
