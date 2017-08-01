package com.abubusoft.kripton.android.sqlite.migration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Select_stmtContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Sql_stmtContext;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class MigrationHelper {
	
	private static void clear(SQLiteDatabase db, String type) {
		String sql;
	    try (Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type == '"+type+"'", null)) {		    
		      if (cursor.moveToFirst()) {
		        int index0=cursor.getColumnIndex("name");	        
		        do
		         {		       
		        	sql="drop "+type+" "+cursor.getString(index0);
		        	Logger.info(sql);
		        	db.execSQL(sql);			          		         		         
		        } while (cursor.moveToNext());
		      }
	    }
	}
	
	private static void clearAll(SQLiteDatabase db) {		
		clear(db, "table");
		clear(db, "index");				
	}

	public static SQLiteDatabase createDatabase(int version, final String schemaDefinitionFile) {
		final List<String> executionList=new ArrayList<>();		
		MigrationDatabaseListener listener = new MigrationDatabaseListener() {

			@Override
			public void onCreate(SQLiteDatabase db) {
				try {
					//Logger.info("Clear all");
					//clearAll(db);
					File f = new File(schemaDefinitionFile);
					Logger.info("Load DDL from " + f.getAbsolutePath());
					final String ddl = IOUtils.toString(new FileInputStream(f), Charset.forName("utf-8"));

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

							//Logger.info(statement);
						};
					});
					
					for (String item: executionList) {
						Logger.info(item);
						db.execSQL(item);
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				try {
					File f = new File(schemaDefinitionFile);
					Logger.info("UPDATE DDL from " + f.getAbsolutePath());
					final String ddl = IOUtils.toString(new FileInputStream(f), Charset.forName("utf-8"));

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

							//Logger.info(statement);
						};
					});
					
					for (String item: executionList) {
						Logger.info(item);
						db.execSQL(item);
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		};

		MigrationDatabase helper = new MigrationDatabase(KriptonLibrary.context(), null, version, null, listener);

		return helper.getWritableDatabase();
	}

}
