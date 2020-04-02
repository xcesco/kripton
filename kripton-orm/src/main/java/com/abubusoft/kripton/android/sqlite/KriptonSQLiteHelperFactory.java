package com.abubusoft.kripton.android.sqlite;

import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;

/**
 * Default open helper for standard sqlite databases.
 * 
 * @author Francesco Benincasa (xcesco@gmail.com)
 *
 */
public class KriptonSQLiteHelperFactory implements SupportSQLiteOpenHelper.Factory {
	
	public KriptonSQLiteHelperFactory() {		
	}

	public static KriptonSQLiteHelperFactory build() {
		return new KriptonSQLiteHelperFactory();
	}

	@Override
	public SupportSQLiteOpenHelper create(Configuration configuration) {
		return new KriptonSQLiteHelper(configuration);		
	}

}
