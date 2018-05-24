------------------------------------------------------------------------------------
--
-- Filename: xeno_schema_1.sql
--
<<<<<<< HEAD
-- Date: Thu May 24 13:12:44 CEST 2018
=======
-- Date: Wed May 16 22:59:52 CEST 2018
>>>>>>> branch 'v4.x' of https://github.com/xcesco/kripton
--
------------------------------------------------------------------------------------

CREATE TABLE prefix_config (id INTEGER PRIMARY KEY AUTOINCREMENT, default_country TEXT, dual_billing_prefix TEXT, enabled INTEGER, dialog_timeout INTEGER);
CREATE TABLE phone_number (id INTEGER PRIMARY KEY AUTOINCREMENT, action TEXT, number TEXT, country_code TEXT, contact_name TEXT, contact_id TEXT);
CREATE TABLE person (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER);
CREATE TABLE country (id INTEGER PRIMARY KEY AUTOINCREMENT, area INTEGER, code TEXT UNIQUE NOT NULL, calling_code TEXT NOT NULL, region TEXT, name TEXT NOT NULL, translated_name BLOB);
CREATE TABLE person_phone_number (id INTEGER PRIMARY KEY AUTOINCREMENT, person_id INTEGER NOT NULL, phone_number_id INTEGER NOT NULL, FOREIGN KEY(person_id) REFERENCES person(id) ON DELETE CASCADE, FOREIGN KEY(phone_number_id) REFERENCES phone_number(id) ON DELETE CASCADE); CREATE INDEX idx_person_phone_number_person_id ON person_phone_number(person_id); CREATE INDEX idx_person_phone_number_phone_number_id ON person_phone_number(phone_number_id); CREATE UNIQUE INDEX idx_person_phone_number_0 on person_phone_number (person_id, phone_number_id);
