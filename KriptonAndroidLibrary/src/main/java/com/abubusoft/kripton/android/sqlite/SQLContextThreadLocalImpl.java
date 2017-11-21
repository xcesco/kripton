package com.abubusoft.kripton.android.sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Works on single thread. No thread local is needed
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class SQLContextThreadLocalImpl implements SQLContext {	
	private StringBuilder sqlBuilder;
	private KriptonContentValues contentValuesForUpdate;
	private AbstractDataSource dataSource;
	private KriptonContentValues contentValues;	

	public SQLContextThreadLocalImpl(AbstractDataSource dataSource) {		
		this.dataSource=dataSource;
		bindToThread();
	}
	
	public SQLContextThreadLocalImpl bindToThread() {
		this.contentValues=dataSource.context().contentValues();
		this.contentValuesForUpdate=dataSource.context().contentValuesForUpdate();
		this.sqlBuilder=dataSource.context().sqlBuilder();
		
		return this;
	}

	@Override
	public KriptonContentValues contentValuesForUpdate() {
		this.contentValuesForUpdate.clear();
		
		return this.contentValuesForUpdate;
	}

	@Override
	public KriptonContentValues contentValues() {
		this.contentValues.clear();
		
		return this.contentValues;
	}

	@Override
	public KriptonContentValues contentValues(ContentValues values) {
		this.contentValues.clear(values);
		
		return this.contentValues;
	}

	@Override
	public StringBuilder sqlBuilder() {
		sqlBuilder.delete(0, sqlBuilder.length());

		return sqlBuilder;
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
