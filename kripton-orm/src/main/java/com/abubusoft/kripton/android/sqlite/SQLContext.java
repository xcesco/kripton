/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.abubusoft.kripton.android.sqlite;

import java.util.Set;

import android.content.ContentValues;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;

/**
 * SQL context for queries execution. There are two kind of SQLContext:
 * <ul>
 * <li>Standard context:</li>
 * <li></li>
 * </ul>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public interface SQLContext {

	/**
	 * Content values for update.
	 *
	 * @param compiledStatement the compiled statement
	 * @return the kripton content values
	 */
	KriptonContentValues contentValuesForUpdate(SupportSQLiteStatement compiledStatement);

	/**
	 * Content values.
	 *
	 * @param compiledStatement the compiled statement
	 * @return the kripton content values
	 */
	KriptonContentValues contentValues(SupportSQLiteStatement compiledStatement);

	/**
	 * Content values for content provider.
	 *
	 * @param values the values
	 * @return the kripton content values
	 */
	KriptonContentValues contentValuesForContentProvider(ContentValues values);

	/**
	 * StringBuilder used to generate SQL.
	 *
	 * @return the string builder
	 */
	StringBuilder sqlBuilder();

	/**
	 * Return true if log is enabled.
	 *
	 * @return true, if is log enabled
	 */
	boolean isLogEnabled();

	/**
	 * Get SQLite database.
	 *
	 * @return the SQ lite database
	 */
	SupportSQLiteDatabase getDatabase();

	/**
	 * Fired when transaction or shared connection is opened.
	 */
	void onSessionOpened();

	/**
	 * In the standar SQLContext you can not use session (transaction and shared
	 * connection), but this is allow to support LiveData.
	 *
	 * @return true, if is in session
	 */
	boolean isInSession();

	/**
	 * Registry an SQL event for a DAO.
	 *
	 * @param daoKey the dao key
	 */
	void registrySQLEvent(int daoKey);

	/**
	 * Fired when transaction or shared connection is closed.
	 *
	 * @return 		all daoKey that registry an event
	 */
	Set<Integer> onSessionClosed();
}
