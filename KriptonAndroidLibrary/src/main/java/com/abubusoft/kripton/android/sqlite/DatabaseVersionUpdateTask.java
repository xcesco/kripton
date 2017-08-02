package com.abubusoft.kripton.android.sqlite;

import android.database.sqlite.SQLiteDatabase;

/**
 * <p>Database schema upgrade</p>
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public abstract class DatabaseVersionUpdateTask {
	public DatabaseVersionUpdateTask(int previousVersion, int currentVersion) 
	{
		this.previousVersion=previousVersion;
		this.currentVersion=currentVersion;
	}
	
	public final int previousVersion;
	
	public final int currentVersion;
	
	public abstract void execute(SQLiteDatabase database);

}
