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
 * This context provides all necessary support classes to invoke queries.
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class SQLContextImpl extends AbstractSQLContext {

	/** The data source. */
	private AbstractDataSource dataSource;

	/**
	 * Instantiates a new SQL context impl.
	 *
	 * @param dataSource the data source
	 */
	public SQLContextImpl(AbstractDataSource dataSource) {
		super(false);
		this.dataSource = dataSource;
	}

	/** The content values. */
	private final ThreadLocal<KriptonContentValues> contentValues = new ThreadLocal<KriptonContentValues>() {

		@Override
		protected KriptonContentValues initialValue() {
			return new KriptonContentValues();
		}

	};
	

	/** The content values for update. */
	private final KriptonContentValues contentValuesForUpdate = new KriptonContentValues();

	/** The sql string builder. */
	private ThreadLocal<StringBuilder> sqlStringBuilder = new ThreadLocal<StringBuilder>() {

		@Override
		protected StringBuilder initialValue() {
			return new StringBuilder();
		}

	};

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.android.sqlite.SQLContext#contentValues(android.database.sqlite.SQLiteStatement)
	 */
	public KriptonContentValues contentValues(SupportSQLiteStatement compiledStatement) {
		KriptonContentValues content = contentValues.get();
		content.clear(compiledStatement);

		return content;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.android.sqlite.SQLContext#contentValuesForUpdate(android.database.sqlite.SQLiteStatement)
	 */
	public KriptonContentValues contentValuesForUpdate(SupportSQLiteStatement compiledStatement) {
		contentValuesForUpdate.clear(compiledStatement);

		return contentValuesForUpdate;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.android.sqlite.SQLContext#sqlBuilder()
	 */
	@Override
	public StringBuilder sqlBuilder() {
		StringBuilder builder = this.sqlStringBuilder.get();
		builder.delete(0, builder.length());

		return builder;
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

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.android.sqlite.SQLContext#contentValuesForContentProvider(android.content.ContentValues)
	 */
	@Override
	public KriptonContentValues contentValuesForContentProvider(ContentValues values) {
		KriptonContentValues content = contentValues.get();
		content.clear(values);

		return content;
	}

}
