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

	@Override
	public Time readCursor(Cursor cursor, int columnIndex) throws Exception {
		String value=cursor.getString(columnIndex);
		if (value==null) return null;
		
		long valueLong=Long.parseLong(value);		
		
		return new Time(valueLong);
	}

	@Override
	public void writeValue(Time value, ContentValues content, String columnKey) throws Exception {
		content.put(columnKey, value.getTime());
		
	}

}
