package com.abubusoft.kripton.android.adapter;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Transformer between a string and a Java Byte object
 * 
 * @author bulldog
 *
 */
class ByteAdapter implements SqliteAdapter<Byte> {

	@Override
	public Byte readCursor(Cursor cursor, int columnIndex) throws Exception {
		String value=cursor.getString(columnIndex);
		if (value==null) return null;
		
		return (byte) Byte.parseByte(value);
	}

	@Override
	public void writeValue(Byte value, ContentValues content, String columnKey) throws Exception {
		content.put(columnKey, value);

	}

}
