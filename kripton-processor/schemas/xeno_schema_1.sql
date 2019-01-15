------------------------------------------------------------------------------------
--
-- Filename: xeno_schema_1.sql
--
-- Date: Tue Jan 15 14:27:24 CET 2019
--
------------------------------------------------------------------------------------

CREATE TABLE prefix_config (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, default_country TEXT, dialog_timeout INTEGER, dual_billing_prefix TEXT, enabled INTEGER);
CREATE TABLE phone_number (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, action TEXT, contact_id TEXT, contact_name TEXT, country_code TEXT, number TEXT);
CREATE TABLE person (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, age INTEGER, name TEXT);
CREATE TABLE country (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, area INTEGER, calling_code TEXT NOT NULL, code TEXT UNIQUE NOT NULL, name TEXT NOT NULL, region TEXT, translated_name BLOB);
CREATE TABLE person_phone_number (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, person_id INTEGER NOT NULL, phone_number_id INTEGER NOT NULL, FOREIGN KEY(person_id) REFERENCES person(id) ON DELETE CASCADE, FOREIGN KEY(phone_number_id) REFERENCES phone_number(id) ON DELETE CASCADE, UNIQUE (person_id, phone_number_id)); CREATE INDEX idx_person_phone_number_person_id ON person_phone_number(person_id); CREATE INDEX idx_person_phone_number_phone_number_id ON person_phone_number(phone_number_id); CREATE UNIQUE INDEX idx_person_phone_number_0 on person_phone_number (person_id, phone_number_id);
