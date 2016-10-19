package com.abubusoft.kripton.android.sqlite;

import java.lang.ref.WeakReference;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

/**
 * This class become the parent class for every Dao generated. Every Dao have to be defined by an interface with {@link BindDao} annotation.
 * 
 * @author xcesco
 *
 */
public abstract class DaoBase {
	
	public DaoBase(AbstractDataSource dataSource)
	{
		this.dataSource=new WeakReference<AbstractDataSource>(dataSource);
	}

	protected WeakReference<AbstractDataSource> dataSource;

	
	/**
	 * Retrieve SQLite database instance
	 * @return
	 */
	protected SQLiteDatabase database()
	{
		SQLiteDatabase database=dataSource.get().database;
		if (database==null) throw(new KriptonRuntimeException("No database connection is opened before use "+this.getClass().getCanonicalName()));
		return database;
	}
	
	protected ContentValues contentValues()
	{
		return contentValues.get();
	}

	/**
	 * ContentValues used to fill query parameters. Thread safe
	 */
	private static final ThreadLocal<ContentValues> contentValues = new ThreadLocal<ContentValues>() {

		/* (non-Javadoc)
		 * @see java.lang.ThreadLocal#initialValue()
		 */
		@Override
		protected ContentValues initialValue() {
			return new ContentValues();
		}
		
	};

}
