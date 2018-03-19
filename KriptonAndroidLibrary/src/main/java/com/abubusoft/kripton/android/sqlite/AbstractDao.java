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

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

/**
 * This class become the parent class for every Dao generated. Every Dao have to
 * be defined by an interface with {@link BindDao} annotation.
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public abstract class AbstractDao implements AutoCloseable {

	public AbstractDao(SQLContext context) {
		this._context = context;
	}

	/**
	 * This attribute is with _ prefix, because it's used on Dao's method and we
	 * won't to mix it with method's parameters (that usually does not have _
	 * prefix).
	 */
	protected SQLContext _context;

	/**
	 * Retrieve SQLite database instance
	 * 
	 * @return
	 */
	protected SQLiteDatabase database() {
		return _context.database();
	}

	@Override
	public void close() {
		// for the moment, we do nothing here.

	}

	protected KriptonContentValues contentValues() {
		return _context.contentValues(null);
	}

	protected KriptonContentValues contentValuesForUpdate() {
		return _context.contentValuesForUpdate(null);
	}

	protected KriptonContentValues contentValuesForUpdate(SQLiteStatement compiledStatement) {
		return _context.contentValuesForUpdate(compiledStatement);
	}

	protected KriptonContentValues contentValuesForContentProvider(ContentValues values) {
		return _context.contentValuesForContentProvider(values);
	}

	protected StringBuilder sqlBuilder() {
		return _context.sqlBuilder();
	}

}
