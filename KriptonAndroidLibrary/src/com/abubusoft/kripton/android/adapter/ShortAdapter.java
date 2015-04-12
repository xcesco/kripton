package com.abubusoft.kripton.android.adapter;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Transformer between a string and a Java Short object
 * 
 * @author bulldog
 *
 */
public class ShortAdapter implements SqliteAdapter<Short> {

	@Override
	public Short readCursor(Cursor cursor, int columnIndex) throws Exception {
		return cursor.getShort(columnIndex);
	}

	@Override
	public void writeValue(Short value, ContentValues content, String columnKey) throws Exception {
		content.put(columnKey, value);
	}

}
