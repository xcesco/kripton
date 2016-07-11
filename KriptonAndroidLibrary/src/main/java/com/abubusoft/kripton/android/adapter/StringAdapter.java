package com.abubusoft.kripton.android.adapter;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Transformer between a string and a Java String object
 * 
 * @author bulldog
 *
 */
public class StringAdapter implements SqliteAdapter<String> {

	@Override
	public String readCursor(Cursor cursor, int columnIndex) throws Exception {		
		return cursor.getString(columnIndex);
	}

	@Override
	public void writeValue(String value, ContentValues content, String columnKey) throws Exception {
		content.put(columnKey, value);
	}

}
