package com.abubusoft.kripton.android.adapter;

import java.math.BigDecimal;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Transformer between a string and a java.math.BigDecimal object
 * 
 * @author bulldog
 *
 */
class BigDecimalAdapter implements SqliteAdapter<BigDecimal> {

	@Override
	public BigDecimal readCursor(Cursor cursor, int columnIndex) throws Exception {
		String value=cursor.getString(columnIndex);
		if (value==null) return null;
		
		return (new BigDecimal(value));
	}

	@Override
	public void writeValue(BigDecimal value, ContentValues content, String columnKey) throws Exception {
		content.put(columnKey, value.toString());
	}

}
