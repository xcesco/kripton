------------------------------------------------------------------------------------
--
-- Filename: artist_schema_1.sql
--
<<<<<<< HEAD
-- Date: Thu May 24 13:12:42 CEST 2018
=======
-- Date: Wed May 16 22:59:51 CEST 2018
>>>>>>> branch 'v4.x' of https://github.com/xcesco/kripton
--
------------------------------------------------------------------------------------

CREATE TABLE track (id INTEGER PRIMARY KEY AUTOINCREMENT, album_id INTEGER, FOREIGN KEY(album_id) REFERENCES album(id) ON DELETE CASCADE ON UPDATE SET NULL);
CREATE TABLE artist (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);
CREATE TABLE album (id INTEGER PRIMARY KEY AUTOINCREMENT, artist_id INTEGER, name TEXT, FOREIGN KEY(artist_id) REFERENCES artist(id) ON DELETE CASCADE ON UPDATE CASCADE);
