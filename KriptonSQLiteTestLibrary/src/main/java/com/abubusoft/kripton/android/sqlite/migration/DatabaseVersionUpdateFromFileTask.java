package com.abubusoft.kripton.android.sqlite.migration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.DatabaseVersionUpdateTask;
import com.abubusoft.kripton.android.sqlite.migration.internals.MigrationSQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Select_stmtContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Sql_stmtContext;

import android.database.sqlite.SQLiteDatabase;

/**
 * <p>Database schema upgrade</p>
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class DatabaseVersionUpdateFromFileTask extends DatabaseVersionUpdateTask {
	private String schemaDefinitionFile;

	public DatabaseVersionUpdateFromFileTask(int previousVersion, int currentVersion, String schemaDefinitionFile ) 
	{
		super(previousVersion, currentVersion);		
		this.schemaDefinitionFile=schemaDefinitionFile;
	}

	@Override
	public void execute(SQLiteDatabase database) {
		try {
			//Logger.info("Clear all");
			//clearAll(db);
			File f = new File(schemaDefinitionFile);
			Logger.info("Load DDL from " + f.getAbsolutePath());
			final String ddl = IOUtils.toString(new FileInputStream(f), Charset.forName("utf-8"));
			final List<String> executionList=new ArrayList<>();
			
			MigrationSQLChecker.getInstance().analyze(ddl, new JqlBaseListener() {
				public void enterSelect_stmt(Select_stmtContext ctx) {
				};

				public void enterSql_stmt(Sql_stmtContext ctx) {;						
					int start = ctx.getStart().getStartIndex();
					int stop = ctx.getStop().getStopIndex()+1;

					if (start == stop)
						return;

					String statement = ddl.substring(start, stop);
					
					executionList.add(statement);
				};
			});
			
			for (String item: executionList) {
				Logger.info(item);
				database.execSQL(item);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} 

		
	}
		
}
