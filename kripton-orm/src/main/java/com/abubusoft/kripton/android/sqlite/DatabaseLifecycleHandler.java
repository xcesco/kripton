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

import android.database.sqlite.SQLiteDatabase;

// TODO: Auto-generated Javadoc
/**
 * Manage upgrade or downgrade of database.
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public interface DatabaseLifecycleHandler {

	/**
	 * <p>
	 * Method for DDL or DML.
	 *
	 * @param database            database
	 * @param oldVersion            current version of database
	 * @param newVersion            new version of database
	 * @param upgrade            if true is an upgrade operation, otherwise it's a
	 *            downgrade operation.
	 */
	void onUpdate(SQLiteDatabase database, int oldVersion, int newVersion, boolean upgrade);

	/**
	 * Invoked after execution of DDL necessary to create database.
	 *
	 * @param database the database
	 */
	void onCreate(SQLiteDatabase database);

	/**
	 * Invoked during database configuration.
	 *
	 * @param database the database
	 */
	void onConfigure(SQLiteDatabase database);
}