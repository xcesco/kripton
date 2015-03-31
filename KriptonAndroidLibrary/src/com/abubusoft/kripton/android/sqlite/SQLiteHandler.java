package com.abubusoft.kripton.android.sqlite;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.ContentValues;

import com.abubusoft.kripton.android.DatabaseColumnSet;
import com.abubusoft.kripton.binder.database.DatabaseColumn;
import com.abubusoft.kripton.binder.database.DatabaseHandler;
import com.abubusoft.kripton.binder.database.DatabaseTable;

public class SQLiteHandler implements DatabaseHandler {

	public HashMap<Class<?>, String> mapToType;

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
				sb.append(" primary_key autoincrement");
				break;
			case UNIQUE_KEY:
				sb.append(" unique");
				break;
			default:
				break;
			}

			// nullable
			if (!column.schema.getColumnInfo().nullable) {
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

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.binder.database.DatabaseHandler#createColumnSet(com.abubusoft.kripton.binder.database.DatabaseTable, java.lang.String)
	 */
	@Override
	public DatabaseColumnSet createColumnSet(DatabaseTable table, String fields) {
		String normalizedFields = fields.replace(" ", "");
		DatabaseColumnSet set = table.columnsSet.get(normalizedFields);

		if (set == null) {
			set = new DatabaseColumnSet();
			set.name = normalizedFields;

			table.columnsSet.put(normalizedFields, set);
		} else {
			return set;
		}

		if ("*".equals(normalizedFields)) {
			ArrayList<DatabaseColumn> columns = table.columns;
			for (DatabaseColumn item : columns) {
				set.columns.add(item);
			}
		} else {
			String[] fieldsArray = normalizedFields.split(",");

			Map<String, DatabaseColumn> map = table.field2column;
			for (String item : fieldsArray) {
				DatabaseColumn column = map.get(item);
				set.columns.add(column);
			}
		}

		return set;
	}

}
