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
		if (database == null) {
			throw (new KriptonRuntimeException("No database connection is opened before use " + this.getClass().getCanonicalName()));
		}

		return database;
	}

	@Override
	public void close() {
		// for the moment, we do nothing here.

	}

	protected KriptonContentValues contentValues() {
		KriptonContentValues content = contentValues.get();
		content.clear();

		return content;
	}

	protected KriptonContentValues contentValuesForUpdate() {
		contentValuesForUpdate.clear();

		return contentValuesForUpdate;
	}

	protected KriptonContentValues contentValues(ContentValues values) {
		KriptonContentValues content = contentValues.get();
		content.clear(values);

		return content;
	}

	private static final KriptonContentValues contentValuesForUpdate = new KriptonContentValues();

	/**
	 * <p>
	 * ContentValues used to fill query parameters. Thread safe
	 * </p>
	 */
	private static final ThreadLocal<KriptonContentValues> contentValues = new ThreadLocal<KriptonContentValues>() {

		@Override
		protected KriptonContentValues initialValue() {
			return new KriptonContentValues();
		}

	};

	private ThreadLocal<StringBuilder> sqlStringBuilder = new ThreadLocal<StringBuilder>() {

		@Override
		protected StringBuilder initialValue() {
			return new StringBuilder();
		}

	};

	protected StringBuilder getSQLStringBuilder() {
		StringBuilder builder = this.sqlStringBuilder.get();
		builder.delete(0, builder.length());

		return builder;

	}

}
