package com.abubusoft.kripton.android.adapter;

import java.net.URL;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Transformer between a string and a java.net.URL object
 * 
 * @author bulldog
 *
 */
public class UrlAdapter implements SqliteAdapter<URL> {

	@Override
	public URL readCursor(Cursor cursor, int columnIndex) throws Exception {
		return new URL(cursor.getString(columnIndex));
	}

	@Override
	public void writeValue(URL value, ContentValues content, String columnKey) throws Exception {
		content.put(columnKey, value.toString());
	}

}
