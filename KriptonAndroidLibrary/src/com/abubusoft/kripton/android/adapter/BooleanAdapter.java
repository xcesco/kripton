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
		String value = cursor.getString(columnIndex);
		if (value == null)
			return null;
		return Boolean.valueOf("0".equals(value) ? false : true);
	}

	@Override
	public void writeValue(Boolean value, ContentValues content, String columnKey) throws Exception {
		content.put(columnKey, value ? 1 : 0);
	}

}
