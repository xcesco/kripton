------------------------------------------------------------------------------------
--
-- Filename: school_schema_2.sql
--
-- Date: Mon Jul 15 22:19:18 CEST 2019
--
------------------------------------------------------------------------------------

CREATE TABLE seminar (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, location TEXT, name TEXT);
CREATE TABLE student (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, location TEXT, name TEXT);
CREATE TABLE seminar_2_student (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, seminar_id INTEGER, student_id INTEGER, FOREIGN KEY(seminar_id) REFERENCES seminar(id), FOREIGN KEY(student_id) REFERENCES student(id), UNIQUE (student_id, seminar_id)); CREATE UNIQUE INDEX idx_seminar_2_student_0 on seminar_2_student (student_id asc,  seminar_id desc);
CREATE TABLE professor (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, birth_date TEXT, name TEXT, surname TEXT NOT NULL); CREATE INDEX idx_professor_0 on professor (surname);
