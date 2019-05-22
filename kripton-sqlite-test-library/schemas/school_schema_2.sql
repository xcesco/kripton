------------------------------------------------------------------------------------
--
-- Filename: school_schema_2.sql
--
-- Date: Tue Feb 19 00:30:47 CET 2019
--
------------------------------------------------------------------------------------

CREATE TABLE seminar (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, location TEXT, name TEXT);
CREATE TABLE student (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, location TEXT, name TEXT);
CREATE TABLE seminar_2_student (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, seminar_id INTEGER, student_id INTEGER, FOREIGN KEY(seminar_id) REFERENCES seminar(id), FOREIGN KEY(student_id) REFERENCES student(id), UNIQUE (student_id, seminar_id));
CREATE TABLE professor (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, birth_date TEXT, name TEXT, surname TEXT NOT NULL);

CREATE UNIQUE INDEX idx_seminar_2_student_0 on seminar_2_student (student_id asc,  seminar_id desc);
CREATE INDEX idx_professor_0 on professor (surname);