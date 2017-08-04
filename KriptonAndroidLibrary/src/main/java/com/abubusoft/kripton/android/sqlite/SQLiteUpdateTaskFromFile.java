package com.abubusoft.kripton.android.sqlite;

import java.io.File;
import java.io.InputStream;
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
	private InputStream inputStream;

	public SQLiteUpdateTaskFromFile(int currentVersion, String schemaDefinitionFileName) {
		super(currentVersion);
		this.schemaDefinitionFile = schemaDefinitionFileName;
	}
	
	public SQLiteUpdateTaskFromFile(int currentVersion, InputStream inputStream) {
		super(currentVersion);
		this.inputStream = inputStream;
	}

	@Override
	public void execute(SQLiteDatabase database) {
		List<String> executionList=null;
	
		if (inputStream==null) {
			File f = new File(schemaDefinitionFile);
			executionList = SQLiteUpdateTaskHelper.readSQLFromFile(schemaDefinitionFile);
			Logger.info("Load DDL from " + f.getAbsolutePath());
		} else {
			executionList = SQLiteUpdateTaskHelper.readSQLFromFile(inputStream);
			Logger.info("Load DDL from stream");
		}
		
		for (String item : executionList) {
			Logger.info(item);
			database.execSQL(item);
		}

	}

}
