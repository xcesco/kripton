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

import java.lang.ref.WeakReference;
import java.util.List;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderOptions;
import com.abubusoft.kripton.BinderReader;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.exception.WriterException;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * This class become the parent class for every Dao generated. Every Dao have to be defined by an interface with {@link BindDao} annotation.
 * 
 * @author xcesco
 *
 */
public abstract class AbstractDao {
	
	public AbstractDao(AbstractDataSource dataSource)
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
