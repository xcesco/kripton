package com.abubusoft.kripton.android.sqlite;

import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;

/**
 * Default open helper for standard sqlite databases.
 * 
 * @author Francesco Benincasa (xcesco@gmail.com)
 *
 */
public class KriptonSQLiteOpenHelperFactory implements SupportSQLiteOpenHelper.Factory {
	
	public KriptonSQLiteOpenHelperFactory() {		
	}

	public static KriptonSQLiteOpenHelperFactory build() {
		return new KriptonSQLiteOpenHelperFactory();
	}

	@Override
	public SupportSQLiteOpenHelper create(Configuration configuration) {
		return new KriptonSQLiteOpenHelper(configuration);		
	}

}
