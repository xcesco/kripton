package com.abubusoft.kripton.android.adapter;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Base64;

/**
 * Transformer between a base64 encoded string and a byte[]
 * 
 * @author bulldog
 *
 */
public class Base64Adapter implements SqliteAdapter<byte[]> {

	@Override
	public byte[] readCursor(Cursor cursor, int columnIndex) throws Exception {
		byte[] buffer=cursor.getBlob(columnIndex);		
		return buffer==null?null:Base64.decode(buffer,0);
	}

	@Override
	public void writeValue(byte[] value, ContentValues content, String columnKey) throws Exception {
		if (value==null) return;
		content.put(columnKey, Base64.encode(value, 0));
		
	}



}
