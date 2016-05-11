package com.abubusoft.kripton.android.sqlite;

import com.abubusoft.kripton.android.annotation.BinderDaoDefinition;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * This class become the parent class for every Dao generated. Every Dao have to be defined by an interface with {@link BinderDaoDefinition} annotation. 
 * 
 * @author xcesco
 *
 */
public abstract class AbstractBinderDao {

	 protected SQLiteDatabase database;

	 /**
	 * @param database the database to set
	 */
	public void setDatabase(SQLiteDatabase database) {
		this.database = database;
	}

	protected ContentValues contentValues = new ContentValues();
	
}
