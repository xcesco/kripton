------------------------------------------------------------------------------------
--
-- Filename: school_schema_2.sql
--
<<<<<<< HEAD
-- Date: Thu May 24 13:12:09 CEST 2018
=======
-- Date: Wed May 16 22:59:39 CEST 2018
>>>>>>> branch 'v4.x' of https://github.com/xcesco/kripton
--
------------------------------------------------------------------------------------

CREATE TABLE seminar (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, location TEXT);
CREATE TABLE student (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, location TEXT);
CREATE TABLE seminar_2_student (id INTEGER PRIMARY KEY AUTOINCREMENT, student_id INTEGER, seminar_id INTEGER, FOREIGN KEY(student_id) REFERENCES student(id), FOREIGN KEY(seminar_id) REFERENCES seminar(id)); CREATE UNIQUE INDEX idx_seminar_2_student_0 on seminar_2_student (student_id asc,  seminar_id desc);
CREATE TABLE professor (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, birth_date TEXT, surname TEXT NOT NULL); CREATE INDEX idx_professor_0 on professor (surname);
