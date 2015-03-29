package com.abubusoft.kripton.android.sqlite;

import java.math.BigDecimal;
import java.util.HashMap;

import android.content.ContentValues;

import com.abubusoft.kripton.binder.database.DatabaseHandler;
import com.abubusoft.kripton.binder.database.DatabaseTable;

public class SQLiteHandler implements DatabaseHandler {

	public static HashMap<Class<?>, String> mapToType;
	
	protected ThreadLocal<ContentValues> values = new ThreadLocal<ContentValues>();

	public SQLiteHandler() {
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
					mapToType.put(classes[i], "INTEGER");
				}
			}
		}
	}

	@Override
	public String getColumnType(Class<?> fieldType) {
		return mapToType.get(fieldType);
	}

	public String createTableSQL(DatabaseTable table) {
		String separator = "";
		StringBuffer sb = new StringBuffer();

		sb.append("CREATE TABLE " + table.name + " (");

		for (int i = 0; i < table.columns.size(); i++) {
			sb.append(separator + table.columns.get(i).name);
			switch (table.columns.get(i).feature) {
			case PRIMARY_KEY:
				sb.append(" PRIMARY_KEY");
				break;
			default:
				break;
			}

			sb.append(" " + table.columns.get(i).type);
			separator = ", ";
		}
		sb.append(")");

		return sb.toString();
	}

	public String dropTableSQL(DatabaseTable table) {
		StringBuffer sb = new StringBuffer();
		sb.append("DROP TABLE IF EXISTS " + table.name + " ");
		return sb.toString();
	}

}
