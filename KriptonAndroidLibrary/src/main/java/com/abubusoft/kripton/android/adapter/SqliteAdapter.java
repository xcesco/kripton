package com.abubusoft.kripton.android.adapter;

import android.content.ContentValues;
import android.database.Cursor;


/**
 * 
 * Class implementing this interface can be used to adapt 
 * cursor properties to java object
 * 
 * @author xcesco
 *
 */
public interface SqliteAdapter<T> {

	public T readCursor(Cursor cursor, int columnIndex) throws Exception;
	
	public void writeValue(T value, ContentValues content, String columnKey) throws Exception;
	
	
}
