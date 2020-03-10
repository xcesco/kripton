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

import android.content.ContentValues;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;

/**
 * Works on single thread. No thread local is needed.
 * 
 * Moreover, notification events for rx and arch system are done only at the end of transaction
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class SQLContextInSessionImpl extends AbstractSQLContext {	
	
	/** The sql builder. */
	private StringBuilder sqlBuilder;
	
	/** The content values for update. */
	private KriptonContentValues contentValuesForUpdate;
	
	/** The data source. */
	private AbstractDataSource dataSource;
	
	/** The content values. */
	private KriptonContentValues contentValues;	

	/**
	 * Instantiates a new SQL context in session impl.
	 *
	 * @param dataSource the data source
	 */
	public SQLContextInSessionImpl(AbstractDataSource dataSource) {
		super(true);
		this.dataSource=dataSource;
		this.contentValues=new KriptonContentValues();
		this.contentValuesForUpdate=new KriptonContentValues();
		this.sqlBuilder=new StringBuilder();
	}
	
	
	/**
	 * Bind to thread.
	 *
	 * @return the SQL context in session impl
	 */
	public SQLContextInSessionImpl bindToThread() {				
		return this;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.android.sqlite.SQLContext#contentValuesForUpdate(android.database.sqlite.SQLiteStatement)
	 */
	@Override
	public KriptonContentValues contentValuesForUpdate(SupportSQLiteStatement compiledStatement) {
		this.contentValuesForUpdate.clear(compiledStatement);
		
		return this.contentValuesForUpdate;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.android.sqlite.SQLContext#contentValues(android.database.sqlite.SQLiteStatement)
	 */
	@Override
	public KriptonContentValues contentValues(SupportSQLiteStatement compiledStatement) {
		this.contentValues.clear(compiledStatement);
		
		return this.contentValues;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.android.sqlite.SQLContext#contentValuesForContentProvider(android.content.ContentValues)
	 */
	@Override
	public KriptonContentValues contentValuesForContentProvider(ContentValues values) {
		this.contentValues.clear(values);
		
		return this.contentValues;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.android.sqlite.SQLContext#sqlBuilder()
	 */
	@Override
	public StringBuilder sqlBuilder() {
		sqlBuilder.delete(0, sqlBuilder.length());

		return sqlBuilder;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.android.sqlite.SQLContext#database()
	 */
	@Override
	public SupportSQLiteDatabase getDatabase() {
		return dataSource.getDatabase();
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.android.sqlite.SQLContext#isLogEnabled()
	 */
	@Override
	public boolean isLogEnabled() {
		return dataSource.logEnabled;
	}


}
