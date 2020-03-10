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
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;

/**
 * This class become the parent class for every Dao generated. Every Dao have to
 * be defined by an interface with {@link BindDao} annotation.
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */ 
public abstract class Dao implements AutoCloseable {

	/**
	 * Instantiates a new dao.
	 *
	 * @param context the context
	 */
	public Dao(SQLContext context) {
		this._context = context;
	}

	/**
	 * This attribute is with _ prefix, because it's used on Dao's method and we
	 * won't to mix it with method's parameters (that usually does not have _
	 * prefix).
	 */
	protected SQLContext _context;

	/**
	 * Retrieve SQLite database instance.
	 *
	 * @return the SQLite database
	 */
	protected SupportSQLiteDatabase getDatabase() {
		return _context.getDatabase();
	}

	/* (non-Javadoc)
	 * @see java.lang.AutoCloseable#close()
	 */
	@Override
	public void close() {
		// for the moment, we do nothing here.

	}

	/**
	 * Content values.
	 *
	 * @return the kripton content values
	 */
	protected KriptonContentValues contentValues() {
		return _context.contentValues(null);
	}

	/**
	 * Content values for update.
	 *
	 * @return the kripton content values
	 */
	protected KriptonContentValues contentValuesForUpdate() {
		return _context.contentValuesForUpdate(null);
	}

	/**
	 * Content values for update.
	 *
	 * @param compiledStatement the compiled statement
	 * @return the kripton content values
	 */
	protected KriptonContentValues contentValuesForUpdate(SupportSQLiteStatement compiledStatement) {
		return _context.contentValuesForUpdate(compiledStatement);
	}

	/**
	 * Content values for content provider.
	 *
	 * @param values the values
	 * @return the kripton content values
	 */
	protected KriptonContentValues contentValuesForContentProvider(ContentValues values) {
		return _context.contentValuesForContentProvider(values);
	}

	/**
	 * Sql builder.
	 *
	 * @return the string builder
	 */
	protected StringBuilder sqlBuilder() {
		return _context.sqlBuilder();
	}

	/**
	 * <p>
	 * Invoked when a transation or a shared connection is opened
	 * </p>.
	 */
	protected void onSessionOpened() {
		latestEvent = null;
	}

	/**
	 * <p>
	 * Invoked when a SQL event is fired.
	 *
	 * @param event the event
	 */
	protected void onEvent(SQLiteEvent event) {
		latestEvent = event;
	}

	/** The latest event. */
	protected SQLiteEvent latestEvent;

	/**
	 * <p>
	 * Retrieve latest event.
	 * </p>
	 *
	 * @return the latest event
	 */
	protected SQLiteEvent getLatestEvent() {
		return latestEvent;
	}

	/**
	 * <p>
	 * Return true, if there is an event.
	 * </p>
	 *
	 * @return true, if successful
	 */
	protected boolean hasLatestEvent() {
		return latestEvent == null;
	}
	
	/**
	 * <p>clear latest event</p>.
	 */
	protected void clearEvents() {
		latestEvent=null;
	}
	
	

	/**
	 * <p>
	 * Invoked when a transation or a shared connection is closed
	 * </p>.
	 */
	protected void onSessionClosed() {
		latestEvent = null;
	}

}
