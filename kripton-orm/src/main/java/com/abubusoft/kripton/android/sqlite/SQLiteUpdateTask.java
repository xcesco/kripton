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

import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * <p>
 * SQLite schema upgrade to version <i>currentVersion</i> from <i>currentVersion-1</i>.
 * When you create an update task, you just to specify <i>currentVersion</i>, because the previuos version is the same minus 1.
 * </p>
 *
 * <pre>
 *     @BindDataSourceOptions(updateTasks = {
 *   @BindDataSourceUpdateTask(version = 2, task = PersonUpdateTask.class)
 * })
 * @BindDataSource(daoSet = { DaoPerson.class }, fileName = "app.db")
 * public interface AppDataSource {
 *
 * }
 * </pre>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public interface SQLiteUpdateTask {	
	
	/**
	 * method to execute to launch build update from previous version to current version.
	 *
	 * @param database the database
	 * @param previousVersion the previous version
	 * @param targetVersion the target version
	 */
	void execute(SupportSQLiteDatabase database, int previousVersion ,int targetVersion);

}
