BEGIN;
-- copy old table to temp tables
ALTER TABLE student RENAME TO tmp_student;
ALTER TABLE seminar RENAME TO tmp_seminar;
ALTER TABLE seminar_2_student RENAME TO tmp_seminar_2_student;
ALTER TABLE professor RENAME TO tmp_professor;

-- create new tables
CREATE TABLE student (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, location TEXT);
CREATE TABLE seminar (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, location TEXT);
CREATE TABLE seminar_2_student (id INTEGER PRIMARY KEY AUTOINCREMENT, student_id INTEGER, seminar_id INTEGER, FOREIGN KEY(student_id) REFERENCES student(id), FOREIGN KEY(seminar_id) REFERENCES seminar(id));
CREATE TABLE professor (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, birth_date TEXT, surname TEXT NOT NULL);
CREATE UNIQUE INDEX idx_seminar_2_student_0 on seminar_2_student (student_id asc, seminar_id desc);
CREATE INDEX idx_professor_0 on professor (surname);

-- TODO move data

-- DROP TABLES
DROP TABLE tmp_student;
DROP TABLE tmp_seminar;
DROP TABLE tmp_seminar_2_student;
DROP TABLE tmp_professor;

COMMIT;