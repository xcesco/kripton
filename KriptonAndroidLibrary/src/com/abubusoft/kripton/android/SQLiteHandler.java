package com.abubusoft.kripton.android;

import java.math.BigDecimal;
import java.util.HashMap;

import com.abubusoft.kripton.database.AbstractDatabaseHandler;
import com.abubusoft.kripton.database.ColumnType;
import com.abubusoft.kripton.database.DatabaseColumn;
import com.abubusoft.kripton.database.DatabaseTable;

import android.content.ContentValues;

public class SQLiteHandler extends AbstractDatabaseHandler<SQLiteQuery, SQLiteInsert> {

	private static final long serialVersionUID = -8926461587267041987L;

	protected ThreadLocal<ContentValues> values = new ThreadLocal<ContentValues>();

	public SQLiteHandler() {
		values.set(new ContentValues());
	}

	public void init() { 
		if (mapToType == null) {
			mapToType = new HashMap<>();

			// TEXT
			{
				Class<?> classes[] = { String.class, Enum.class };

				for (int i = 0; i < classes.length; i++) {
					mapToType.put(classes[i], "TEXT");
				}
			}

			// INTEGER
			{
				Class<?> classes[] = { boolean.class, Boolean.class, int.class, Integer.class, long.class, Long.class };

				for (int i = 0; i < classes.length; i++) {
					mapToType.put(classes[i], "INTEGER");
				}
			}

			// NUMERIC

			// REAL
			{
				Class<?> classes[] = { float.class, Float.class, double.class, Double.class, BigDecimal.class };

				for (int i = 0; i < classes.length; i++) {
					mapToType.put(classes[i], "REAL");
				}
			}
			
			// BLOB
			{
				Class<?> classes[] = { (new byte[0]).getClass() };

				for (int i = 0; i < classes.length; i++) {
					mapToType.put(classes[i], "BLOB");
				}
			}
		}
	}

	public String createTableSQL(DatabaseTable table) {
		DatabaseColumn column;
		String separator = "";
		StringBuffer sb = new StringBuffer();

		sb.append("create table " + table.name + " (");

		for (int i = 0; i < table.columns.size(); i++) {
			column = table.columns.get(i);
			sb.append(separator + column.name);
			// type
			sb.append(" " + column.type);

			// index options
			switch (column.feature) {
			case PRIMARY_KEY:
				sb.append(" primary key autoincrement");
				break;
			case UNIQUE_KEY:
				sb.append(" unique");
				break;
			default:
				break;
			}

			// nullable
			if (column.feature!=ColumnType.PRIMARY_KEY && !column.schema.getColumnInfo().nullable) {
				sb.append(" not null");
			}

			separator = ", ";
		}
		sb.append(");");

		return sb.toString();
	}

	public String dropTableSQL(DatabaseTable table) {
		StringBuffer sb = new StringBuffer();
		sb.append("drop table if exists " + table.name + " ");
		return sb.toString();
	}

	@Override
	protected SQLiteQuery createNewQuery() {
		return new SQLiteQuery();
	}

	@Override
	public String getColumnType(Class<?> fieldType) {
		return mapToType.get(fieldType);
	}

	@Override
	protected SQLiteInsert createNewInsert() {
		return new SQLiteInsert();
	}
}
