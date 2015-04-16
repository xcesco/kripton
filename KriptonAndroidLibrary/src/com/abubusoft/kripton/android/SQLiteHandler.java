package com.abubusoft.kripton.android;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;

import com.abubusoft.kripton.android.adapter.Adapter;
import com.abubusoft.kripton.android.adapter.SqliteAdapter;
import com.abubusoft.kripton.binder.database.ColumnType;
import com.abubusoft.kripton.binder.database.DatabaseColumn;
import com.abubusoft.kripton.binder.database.DatabaseHandler;
import com.abubusoft.kripton.binder.database.DatabaseTable;
import com.abubusoft.kripton.binder.database.Filter;
import com.abubusoft.kripton.binder.database.Statement;

public class SQLiteHandler extends DatabaseHandler<SQLiteSchema, SQLiteInsert, SQLiteQuery, SQLiteUpdate, SQLiteDelete> {

	private static final long serialVersionUID = -8926461587267041987L;

	ThreadLocal<ContentValues> contentValues = new ThreadLocal<ContentValues>();

	public SQLiteHandler() {
		contentValues.set(new ContentValues());
	}

	@Override
	protected String onDefineCreateTableSQL(SQLiteSchema schema, DatabaseTable table) {
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
			case FOREIGN_KEY:
				DatabaseTable primaryTable = schema.getTableFromBeanClass(column.schema.getFieldType());
				sb.append(" foreign key(" + column.name + ") REFERENCES " + primaryTable.name + "(" + primaryTable.primaryKey.name + ")");
				break;
			default:
				break;
			}

			// nullable
			if (column.feature != ColumnType.PRIMARY_KEY && !column.schema.getColumnInfo().nullable) {
				sb.append(" not null");
			}

			separator = ", ";
		}
		sb.append(");");

		return sb.toString();
	}

	private void findColumnAdapters(Statement statement, @SuppressWarnings("rawtypes") ArrayList<SqliteAdapter> columnAdapter) {
		DatabaseColumn col;

		for (int i = 0; i < statement.columns.length; i++) {
			col = statement.columns[i];
			columnAdapter.add(Adapter.lookup(col.schema.getFieldType()));
		}
	}

	@SuppressWarnings("rawtypes")
	private void findFilterAdapters(Filter filter, ArrayList<SqliteAdapter> filterAdapter) {
		Field field;

		switch (filter.origin) {
		case BEAN:
		case PARAMS:
			for (int i = 0; i < filter.fieldNames.length; i++) {
				field = filter.field[i];
				filterAdapter.add(Adapter.lookup(field.getType()));
			}
			break;
		case NONE:
			break;
		case ONE_PARAM:
			filterAdapter.add(Adapter.lookup(filter.inputClazz));
			break;
		}

	}

	@Override
	protected String onDefineColumnType(Class<?> fieldType) {
		if (fieldType.isEnum()) {
			return mapToType.get(Enum.class);
		}
		return mapToType.get(fieldType);
	}

	protected HashMap<Class<?>, String> onDefineFieldToColumnTypeMap(HashMap<Class<?>, String> map) {
		if (map != null)
			return map;
		map = new HashMap<>();

		// TEXT
		{
			Class<?> classes[] = { String.class, Enum.class };

			for (int i = 0; i < classes.length; i++) {
				map.put(classes[i], "TEXT");
			}
		}

		// INTEGER
		{
			Class<?> classes[] = { boolean.class, Boolean.class, int.class, Integer.class, long.class, Long.class, BigInteger.class };

			for (int i = 0; i < classes.length; i++) {
				map.put(classes[i], "INTEGER");
			}
		}

		// NUMERIC

		// REAL
		{
			Class<?> classes[] = { float.class, Float.class, double.class, Double.class, BigDecimal.class };

			for (int i = 0; i < classes.length; i++) {
				map.put(classes[i], "REAL");
			}
		}

		// BLOB
		{
			Class<?> classes[] = { (new byte[0]).getClass() };

			for (int i = 0; i < classes.length; i++) {
				map.put(classes[i], "BLOB");
			}
		}

		return map;

	}

	@Override
	protected String onDefineDropTableSQL(SQLiteSchema schema, DatabaseTable table) {
		StringBuffer sb = new StringBuffer();
		sb.append("drop table if exists " + table.name + " ");
		return sb.toString();
	}

	@Override
	protected void onDeleteCreated(SQLiteDelete delete) {
		findColumnAdapters(delete, delete.columnAdapter);
		findFilterAdapters(delete.filter, delete.filterAdapter);
	}

	@Override
	protected void onQueryCreated(SQLiteQuery query) {
		findColumnAdapters(query, query.columnAdapter);
		findFilterAdapters(query.filter, query.filterAdapter);
	}

	@Override
	protected void onInsertCreated(SQLiteInsert insert) {
		findColumnAdapters(insert, insert.columnAdapter);
	}

	@Override
	protected void onUpdateCreated(SQLiteUpdate update) {
		findColumnAdapters(update, update.columnAdapter);
		findFilterAdapters(update.filter, update.filterAdapter);
	}


}
