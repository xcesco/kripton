package com.abubusoft.kripton.android.adapter;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Transformer between a string and a Java Boolean object
 * 
 * @author bulldog
 *
 */
class BooleanAdapter implements SqliteAdapter<Boolean> {

	@Override
	public Boolean readCursor(Cursor cursor, int columnIndex) throws Exception {
		return Boolean.valueOf(cursor.getString(columnIndex));
	}

	@Override
	public void writeValue(Boolean value, ContentValues content,String columnKey) throws Exception {
		content.put(columnKey, value);
	}

}
