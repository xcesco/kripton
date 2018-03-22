package com.abubusoft.kripton.android.sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

/**
 * Works on single thread. No thread local is needed.
 * 
 * Moreover, notification events for rx and arch system are done only at the end of transaction
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class SQLContextInSessionImpl extends AbstractSQLContext {	
	private StringBuilder sqlBuilder;
	private KriptonContentValues contentValuesForUpdate;
	private AbstractDataSource dataSource;
	private KriptonContentValues contentValues;	

	public SQLContextInSessionImpl(AbstractDataSource dataSource) {
		super(true);
		this.dataSource=dataSource;
		this.contentValues=new KriptonContentValues();
		this.contentValuesForUpdate=new KriptonContentValues();
		this.sqlBuilder=new StringBuilder();
	}
	
	
	public SQLContextInSessionImpl bindToThread() {				
		return this;
	}

	@Override
	public KriptonContentValues contentValuesForUpdate(SQLiteStatement compiledStatement) {
		this.contentValuesForUpdate.clear(compiledStatement);
		
		return this.contentValuesForUpdate;
	}

	@Override
	public KriptonContentValues contentValues(SQLiteStatement compiledStatement) {
		this.contentValues.clear(compiledStatement);
		
		return this.contentValues;
	}

	@Override
	public KriptonContentValues contentValuesForContentProvider(ContentValues values) {
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
