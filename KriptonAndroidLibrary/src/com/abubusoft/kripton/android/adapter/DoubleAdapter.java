package com.abubusoft.kripton.android.adapter;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Transformer between a string and a Java Double object
 * 
 * @author bulldog
 *
 */
public class DoubleAdapter implements SqliteAdapter<Double> {

	@Override
	public Double readCursor(Cursor cursor, int columnIndex) throws Exception {
		return cursor.getDouble(columnIndex);
	}

	@Override
	public void writeValue(Double value, ContentValues content, String columnKey) throws Exception {
		content.put(columnKey, value);
	}

}
