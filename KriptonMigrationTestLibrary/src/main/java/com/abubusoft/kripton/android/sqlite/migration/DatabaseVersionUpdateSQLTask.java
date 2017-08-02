package com.abubusoft.kripton.android.sqlite.migration;

import com.abubusoft.kripton.android.sqlite.DatabaseVersionUpdateTask;

/**
 * <p>Database schema upgrade</p>
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public abstract class DatabaseVersionUpdateSQLTask extends DatabaseVersionUpdateTask {
	public DatabaseVersionUpdateSQLTask(int previousVersion, int currentVersion) 
	{
		super(previousVersion, currentVersion);
	}
		

}
