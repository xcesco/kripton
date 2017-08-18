/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.android.sqlite;

import java.util.ArrayList;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * This class become the parent class for every Dao generated. Every Dao have to
 * be defined by an interface with {@link BindDao} annotation.
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public abstract class AbstractDao implements AutoCloseable {

	public AbstractDao(AbstractDataSource dataSource) {
		this.dataSource = dataSource;
	}

	protected AbstractDataSource dataSource;

	/**
	 * Retrieve SQLite database instance
	 * 
	 * @return
	 */
	protected SQLiteDatabase database() {
		SQLiteDatabase database = dataSource.database;
		if (database == null)
			throw (new KriptonRuntimeException("No database connection is opened before use " + this.getClass().getCanonicalName()));
		return database;
	}

	@Override
	public void close() {
		// for the moment, we do nothing here.

	}

	protected ContentValues contentValues() {
		return contentValues.get();
	}

	/**
	 * <p>
	 * ContentValues used to fill query parameters. Thread safe
	 * </p>
	 */
	private static final ThreadLocal<ContentValues> contentValues = new ThreadLocal<ContentValues>() {

		@Override
		protected ContentValues initialValue() {
			return new ContentValues();
		}

	};

	/**
	 * Thread safe array for query parameters. It's used to avoid creation of
	 * new array everytime a query is invoked.
	 */
	private ThreadLocal<ArrayList<String>> whereParamsArray = new ThreadLocal<ArrayList<String>>() {

		@Override
		protected ArrayList<String> initialValue() {
			return new ArrayList<String>();
		}

	};

	private ThreadLocal<StringBuilder> sqlStringBuilder = new ThreadLocal<StringBuilder>() {

		@Override
		protected StringBuilder initialValue() {
			return new StringBuilder();
		}

	};

	/**
	 * retrieve whereParamsArray attribute that allow to work with where
	 * parameters
	 * 
	 * @return
	 */
	protected ArrayList<String> getWhereParamsArray() {
		ArrayList<String> array = whereParamsArray.get();
		array.clear();
		return array;
	}

	protected StringBuilder getSQLStringBuilder() {
		StringBuilder builder = this.sqlStringBuilder.get();
		builder.delete(0, builder.length());

		return builder;

	}

}
