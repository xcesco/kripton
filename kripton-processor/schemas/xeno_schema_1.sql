------------------------------------------------------------------------------------
--
-- Filename: xeno_schema_1.sql
--
-- Date: Sat Jul 28 01:27:59 CEST 2018
--
------------------------------------------------------------------------------------

CREATE TABLE prefix_config (id INTEGER PRIMARY KEY AUTOINCREMENT, default_country TEXT, dialog_timeout INTEGER, dual_billing_prefix TEXT, enabled INTEGER);
CREATE TABLE phone_number (id INTEGER PRIMARY KEY AUTOINCREMENT, action TEXT, contact_id TEXT, contact_name TEXT, country_code TEXT, number TEXT);
CREATE TABLE person (id INTEGER PRIMARY KEY AUTOINCREMENT, age INTEGER, name TEXT);
CREATE TABLE country (id INTEGER PRIMARY KEY AUTOINCREMENT, area INTEGER, calling_code TEXT NOT NULL, code TEXT UNIQUE NOT NULL, name TEXT NOT NULL, region TEXT, translated_name BLOB);
CREATE TABLE person_phone_number (id INTEGER PRIMARY KEY AUTOINCREMENT, person_id INTEGER NOT NULL, phone_number_id INTEGER NOT NULL, FOREIGN KEY(person_id) REFERENCES person(id) ON DELETE CASCADE, FOREIGN KEY(phone_number_id) REFERENCES phone_number(id) ON DELETE CASCADE); CREATE INDEX idx_person_phone_number_person_id ON person_phone_number(person_id); CREATE INDEX idx_person_phone_number_phone_number_id ON person_phone_number(phone_number_id); CREATE UNIQUE INDEX idx_person_phone_number_0 on person_phone_number (person_id, phone_number_id);
