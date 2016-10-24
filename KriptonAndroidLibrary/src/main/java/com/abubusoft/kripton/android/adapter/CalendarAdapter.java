/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
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
