BEGIN;
-- copy old table to temp tables
ALTER TABLE student RENAME TO tmp_student;
ALTER TABLE seminar RENAME TO tmp_seminar;
ALTER TABLE seminar_2_student RENAME TO tmp_seminar_2_student;
ALTER TABLE professor RENAME TO tmp_professor;
DROP INDEX idx_seminar_2_student_0;
DROP INDEX idx_professor_0


-- create new tables
CREATE TABLE seminar (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, location TEXT, name TEXT);
CREATE TABLE student (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, location TEXT, name TEXT);
CREATE TABLE seminar_2_student (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, seminar_id INTEGER, student_id INTEGER, FOREIGN KEY(seminar_id) REFERENCES seminar(id), FOREIGN KEY(student_id) REFERENCES student(id));
CREATE UNIQUE INDEX idx_seminar_2_student_0 on seminar_2_student (student_id asc,  seminar_id desc);
CREATE TABLE professor (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, birth_date TEXT, name TEXT, surname TEXT NOT NULL);
CREATE INDEX idx_professor_0 on professor (surname);


-- TODO move data

-- DROP TABLES
DROP TABLE tmp_student;
DROP TABLE tmp_seminar;
DROP TABLE tmp_seminar_2_student;
DROP TABLE tmp_professor;


COMMIT;