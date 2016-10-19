package com.abubusoft.kripton.android.adapter;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Transformer between a string and a Java Float object
 * 
 * @author bulldog
 *
 */
public class FloatAdapter implements SqliteAdapter<Float> {

	@Override
	public Float readCursor(Cursor cursor, int columnIndex) throws Exception {
		String value=cursor.getString(columnIndex);
		if (value==null) return null;
		return Float.valueOf(value);
	}

	@Override
	public void writeValue(Float value, ContentValues content, String columnKey) throws Exception {
		content.put(columnKey, value);
	}

}
