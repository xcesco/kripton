package com.abubusoft.kripton.android.adapter;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Transformer between a string and a Java Character object
 * 
 * @author bulldog
 *
 */
class CharacterAdapter implements SqliteAdapter<Character> {

	@Override
	public Character readCursor(Cursor cursor, int columnIndex) throws Exception {
		String value = cursor.getString(columnIndex);

		if (value != null && value.length() == 1)
			return value.charAt(0);
		if (value == null)
			return null;
		throw new IllegalArgumentException("Cannot transfrom " + value + " to a character");

	}

	@Override
	public void writeValue(Character value, ContentValues content, String columnKey) throws Exception {
		content.put(columnKey, Character.toString(value));
	}

}
