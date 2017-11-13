package com.abubusoft.kripton.android.sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * This context provides all necessary support classes to invoke queries.
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class SQLContextImpl implements SQLContext {
	
	private AbstractDataSource dataSource;
	
	public SQLContextImpl(AbstractDataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	private final ThreadLocal<KriptonContentValues> contentValues = new ThreadLocal<KriptonContentValues>() {

		@Override
		protected KriptonContentValues initialValue() {
			return new KriptonContentValues();
		}

	};

	private final KriptonContentValues contentValuesForUpdate = new KriptonContentValues();

	private ThreadLocal<StringBuilder> sqlStringBuilder = new ThreadLocal<StringBuilder>() {

		@Override
		protected StringBuilder initialValue() {
			return new StringBuilder();
		}

	};

	public KriptonContentValues contentValues() {
		KriptonContentValues content = contentValues.get();
		content.clear();

		return content;
	}

	public KriptonContentValues contentValues(ContentValues values) {
		KriptonContentValues content = contentValues.get();
		content.clear(values);

		return content;
	}

	public KriptonContentValues contentValuesForUpdate() {
		contentValuesForUpdate.clear();

		return contentValuesForUpdate;
	}

	@Override
	public StringBuilder sqlBuilder() {
		StringBuilder builder = this.sqlStringBuilder.get();
		builder.delete(0, builder.length());

		return builder;
	}

	@Override
	public SQLiteDatabase database() {
		return dataSource.database();
	}

	@Override
	public boolean isLogEnabled() {
		return dataSource.logEnabled;
	}
}
