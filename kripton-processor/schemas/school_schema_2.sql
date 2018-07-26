------------------------------------------------------------------------------------
--
-- Filename: school_schema_2.sql
--
-- Date: Thu Jul 26 01:35:17 CEST 2018
--
------------------------------------------------------------------------------------

CREATE TABLE seminar_2_student (id INTEGER PRIMARY KEY AUTOINCREMENT, seminar_id INTEGER, student_id INTEGER, FOREIGN KEY(seminar_id) REFERENCES seminar(id), FOREIGN KEY(student_id) REFERENCES student(id)); CREATE UNIQUE INDEX idx_seminar_2_student_0 on seminar_2_student (student_id asc,  seminar_id desc);
CREATE TABLE professor (id INTEGER PRIMARY KEY AUTOINCREMENT, birth_date TEXT, name TEXT, surname TEXT NOT NULL); CREATE INDEX idx_professor_0 on professor (surname);
CREATE TABLE student (id INTEGER PRIMARY KEY AUTOINCREMENT, location TEXT, name TEXT);
CREATE TABLE seminar (id INTEGER PRIMARY KEY AUTOINCREMENT, location TEXT, name TEXT);
