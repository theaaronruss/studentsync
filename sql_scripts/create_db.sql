CREATE DATABASE IF NOT EXISTS studentsync;
USE studentsync;
CREATE TABLE IF NOT EXISTS user(
	username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY (username)
);
