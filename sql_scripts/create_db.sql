CREATE DATABASE IF NOT EXISTS studentsync;
USE studentsync;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS academic_term;
DROP TABLE IF EXISTS course_enrollment;
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
    email_address VARCHAR(50),
    phone_number VARCHAR(50),
    start_date DATE,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS academic_term(
	id VARCHAR(50) NOT NULL,
    name VARCHAR(50),
    start_date DATE,
    end_date DATE,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS course(
	id VARCHAR(50) NOT NULL,
    name VARCHAR(50),
    teacher_id VARCHAR(50) NOT NULL,
    academic_term_id VARCHAR(50) NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (teacher_id) REFERENCES teacher(id),
    FOREIGN KEY (academic_term_id) REFERENCES academic_term(id)
);
CREATE TABLE IF NOT EXISTS course_enrollment(
	student_id VARCHAR(50) NOT NULL,
    course_id VARCHAR(50) NOT NULL,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (course_id) REFERENCES course(id)
);