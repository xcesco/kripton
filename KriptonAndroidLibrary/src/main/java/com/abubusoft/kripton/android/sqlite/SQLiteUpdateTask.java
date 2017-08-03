package com.abubusoft.kripton.android.sqlite;

import android.database.sqlite.SQLiteDatabase;

/**
 * <p>
 * SQLite schema upgrade
 * </p>
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public abstract class SQLiteUpdateTask {

	
	public SQLiteUpdateTask(int currentVersion) {
		this.previousVersion = currentVersion-1;
		this.currentVersion = currentVersion;
	}

	public final int previousVersion;

	public final int currentVersion;

	public abstract void execute(SQLiteDatabase database);

}
