package com.abubusoft.kripton.android.sqlite;

import com.abubusoft.kripton.android.annotation.BindDao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * This class become the parent class for every Dao generated. Every Dao have to be defined by an interface with {@link BindDao} annotation.
 * 
 * @author xcesco
 *
 */
public abstract class AbstractBindDao {

	/**
	 * sqlite database instance.
	 */
	protected SQLiteDatabase database;

	/**
	 * @param database
	 *            the database to set
	 */
	public void setDatabase(SQLiteDatabase database) {
		this.database = database;
	}

	/**
	 * ContentValues used to fill query parameters.
	 */
	protected ContentValues contentValues = new ContentValues();

}
