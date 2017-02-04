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
package com.abubusoft.kripton.android.sqlite;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * This class become the parent class for every Dao generated. Every Dao have to be defined by an interface with {@link BindDao} annotation.
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public abstract class AbstractDao implements AutoCloseable {
	
	public AbstractDao(AbstractDataSource dataSource)
	{
		this.dataSource=dataSource;
	}

	protected AbstractDataSource dataSource;
	
	/**
	 * Retrieve SQLite database instance
	 * @return
	 */
	protected SQLiteDatabase database()
	{
		SQLiteDatabase database=dataSource.database;
		if (database==null) throw(new KriptonRuntimeException("No database connection is opened before use "+this.getClass().getCanonicalName()));
		return database;
	}
	
	@Override
	public void close() {
		// for the moment, we do nothing here. 
		
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
