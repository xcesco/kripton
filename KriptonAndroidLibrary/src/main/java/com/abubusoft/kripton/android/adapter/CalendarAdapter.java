package com.abubusoft.kripton.android.adapter;

import java.util.Calendar;
import java.util.Date;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Transformer between a string and a java.util.Calendar object
 * 
 * @author bulldog
 *
 */
public class CalendarAdapter implements SqliteAdapter<Calendar> {
	
	private DateAdapter dateTransform = new DateAdapter();

	@Override
	public Calendar readCursor(Cursor cursor, int columnIndex) throws Exception {
		Date date=dateTransform.readCursor(cursor, columnIndex);
		if (date==null) return null;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	@Override
	public void writeValue(Calendar value, ContentValues content, String columnKey) throws Exception {
		Date date = value.getTime();
		dateTransform.writeValue(date, content, columnKey);
	}

}
