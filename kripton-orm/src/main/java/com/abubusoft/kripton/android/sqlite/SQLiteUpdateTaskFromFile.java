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

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.abubusoft.kripton.android.Logger;

import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * <p>
 * Database schema upgrade
 * </p>.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class SQLiteUpdateTaskFromFile implements SQLiteUpdateTask {
	
	/** The schema definition file. */
	private String schemaDefinitionFile;
	
	/** The input stream. */
	private InputStream inputStream;

	/**
	 * Instantiates a new SQ lite update task from file.
	 *
	 * @param schemaDefinitionFileName the schema definition file name
	 */
	public SQLiteUpdateTaskFromFile(String schemaDefinitionFileName) {
		this.schemaDefinitionFile = schemaDefinitionFileName;
	}
	
	/**
	 * Instantiates a new SQ lite update task from file.
	 *
	 * @param inputStream the input stream
	 */
	public SQLiteUpdateTaskFromFile(InputStream inputStream) {		
		this.inputStream = inputStream;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask#execute(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void execute(SupportSQLiteDatabase database, int previuosVersion, int targetVersion) {
		List<String> executionList=null;
	
		if (inputStream==null) {
			File f = new File(schemaDefinitionFile);
			executionList = SQLiteUpdateTaskHelper.readSQLFromFile(schemaDefinitionFile);
			Logger.info("Load DDL from " + f.getAbsolutePath());
		} else {
			executionList = SQLiteUpdateTaskHelper.readSQLFromFile(inputStream);
			Logger.info("Load DDL from stream");
		}
		
		for (String item : executionList) {
			Logger.info(item);
			database.execSQL(item);
		}

	}

}
