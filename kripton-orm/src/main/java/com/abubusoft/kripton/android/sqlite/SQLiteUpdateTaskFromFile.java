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

import android.database.sqlite.SQLiteDatabase;

/**
 * <p>
 * Database schema upgrade
 * </p>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class SQLiteUpdateTaskFromFile implements SQLiteUpdateTask {
	private String schemaDefinitionFile;
	private InputStream inputStream;

	public SQLiteUpdateTaskFromFile(String schemaDefinitionFileName) {
		this.schemaDefinitionFile = schemaDefinitionFileName;
	}
	
	public SQLiteUpdateTaskFromFile(InputStream inputStream) {		
		this.inputStream = inputStream;
	}

	@Override
	public void execute(SQLiteDatabase database, int previuosVersion, int currentVersion) {
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
