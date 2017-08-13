package com.abubusoft.kripton.android.sqlite;

import android.database.sqlite.SQLiteDatabase;

/**
 * <p>
 * SQLite schema upgrade to version <i>currentVersion</i> from <i>currentVersion-1</i>.
 * When you create an update task, you just to specify <i>currentVersion</i>, because the previuos version is the same minus 1.
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

	/**
	 * method to execute to launch build update from previous version to current version
	 * @param database
	 */
	public abstract void execute(SQLiteDatabase database);

}
