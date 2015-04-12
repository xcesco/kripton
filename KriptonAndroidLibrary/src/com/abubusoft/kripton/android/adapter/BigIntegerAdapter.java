package com.abubusoft.kripton.android.adapter;

import java.math.BigInteger;

import android.content.ContentValues;
import android.database.Cursor;


/**
 * Transformer between a string and a java.math.BigInteger object
 * 
 * @author bulldog
 *
 */
class BigIntegerAdapter implements SqliteAdapter<BigInteger> {

	@Override
	public BigInteger readCursor(Cursor cursor, int columnIndex) throws Exception {
		return (new BigInteger(cursor.getString(columnIndex)));
	}

	@Override
	public void writeValue(BigInteger value, ContentValues content, String columnKey) throws Exception {
		content.put(columnKey, value.toString());
	}

}
