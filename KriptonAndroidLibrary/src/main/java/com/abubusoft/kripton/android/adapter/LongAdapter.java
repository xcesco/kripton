package com.abubusoft.kripton.android.adapter;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Transformer between a string and a Java Long object
 * 
 * @author bulldog
 *
 */
public class LongAdapter implements SqliteAdapter<Long> {

	@Override
	public Long readCursor(Cursor cursor, int columnIndex) throws Exception {
		String value=cursor.getString(columnIndex);
		if (value==null) return null;
		return Long.valueOf(value);
	}

	@Override
	public void writeValue(Long value, ContentValues content, String columnKey) throws Exception {
		content.put(columnKey, value);
	}


}
