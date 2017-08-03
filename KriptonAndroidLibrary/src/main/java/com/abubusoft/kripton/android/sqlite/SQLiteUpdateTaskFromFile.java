package com.abubusoft.kripton.android.sqlite;

import java.io.File;
import java.util.List;

import com.abubusoft.kripton.android.Logger;

import android.database.sqlite.SQLiteDatabase;

/**
 * <p>
 * Database schema upgrade
 * </p>
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class SQLiteUpdateTaskFromFile extends SQLiteUpdateTask {
	private String schemaDefinitionFile;

	public SQLiteUpdateTaskFromFile(int currentVersion, String schemaDefinitionFileName) {
		super(currentVersion);
		this.schemaDefinitionFile = schemaDefinitionFileName;
	}

	@Override
	public void execute(SQLiteDatabase database) {
		File f = new File(schemaDefinitionFile);

		final List<String> executionList = SQLiteUpdateTaskHelper.readSQLFromFile(schemaDefinitionFile);

		Logger.info("Load DDL from " + f.getAbsolutePath());
		for (String item : executionList) {
			Logger.info(item);
			database.execSQL(item);
		}

	}

}
