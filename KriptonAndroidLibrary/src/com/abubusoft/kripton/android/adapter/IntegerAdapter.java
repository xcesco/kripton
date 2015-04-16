package com.abubusoft.kripton.android.adapter;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Transformer between a string and a Java Integer object
 * 
 * @author bulldog
 *
 */
public class IntegerAdapter implements SqliteAdapter<Integer> {

	@Override
	public Integer readCursor(Cursor cursor, int columnIndex) throws Exception {
		String value=cursor.getString(columnIndex);
		if (value==null) return null;
		return Integer.valueOf(value);
	}

	@Override
	public void writeValue(Integer value, ContentValues content, String columnKey) throws Exception {
		content.put(columnKey, value);		
	}

}
