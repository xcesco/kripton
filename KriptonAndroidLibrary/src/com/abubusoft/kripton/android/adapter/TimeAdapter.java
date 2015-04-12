package com.abubusoft.kripton.android.adapter;

import java.sql.Time;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Transformer between a string and a custom Time object
 * 
 * @author bulldog
 *
 */
public class TimeAdapter implements SqliteAdapter<Time> {

	public Time read(String value) throws Exception {
		return Time.valueOf(value);
	}

	public String write(Time value) throws Exception {
		return value.toString();
	}

	@Override
	public Time readCursor(Cursor cursor, int columnIndex) throws Exception {
		return new Time(cursor.getLong(columnIndex));
	}

	@Override
	public void writeValue(Time value, ContentValues content, String columnKey) throws Exception {
		content.put(columnKey, value.getTime());
		
	}

}
