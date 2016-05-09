package com.abubusoft.kripton.android;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.TimeZone;

import android.content.ContentValues;

import com.abubusoft.kripton.android.adapter.Adapter;
import com.abubusoft.kripton.android.adapter.SqliteAdapter;
import com.abubusoft.kripton.binder.database.ColumnType;
import com.abubusoft.kripton.binder.database.DatabaseColumn;
import com.abubusoft.kripton.binder.database.DatabaseHandler;
import com.abubusoft.kripton.binder.database.DatabaseTable;
import com.abubusoft.kripton.binder.database.Filter;
import com.abubusoft.kripton.binder.database.Statement;

@SuppressWarnings("rawtypes")
public class SQLiteHandler extends DatabaseHandler<SQLiteSchema, SQLiteInsert, SQLiteQuery, SQLiteUpdate, SQLiteDelete> {

	private static final long serialVersionUID = -8926461587267041987L;

	ThreadLocal<ContentValues> contentValues = new ThreadLocal<ContentValues>();

	public SQLiteHandler() {
		contentValues.set(new ContentValues());
	}

	@Override
	protected void onDefineCreateTableSQL(ArrayList<String> result, HashSet<DatabaseTable> alreadyParsedTables, SQLiteSchema schema, DatabaseTable table) {
		// if already parsed, skip immediately
		if (alreadyParsedTables.contains(table))
			return;

		alreadyParsedTables.add(table);

		DatabaseColumn column;
		String separator = "";
		StringBuffer sb = new StringBuffer();
		StringBuffer sbOther = new StringBuffer();

		sb.append("create table " + table.name + " (");

		for (int i = 0; i < table.columns.size(); i++) {
			column = table.columns.get(i);
			sb.append(separator + column.name);
			// type
			if (column.type != null) {
				sb.append(" " + column.type);
			}

			// index options
			switch (column.feature) {
			case PRIMARY_KEY:
				sb.append(" primary key autoincrement");
				break;
			case UNIQUE:
				sb.append(" unique");
				break;
			case FOREIGN_KEY:
				DatabaseTable primaryTable = checkedDatabaseTable(schema, column.schema);
				onDefineCreateTableSQL(result, alreadyParsedTables, schema, primaryTable);
				column.type = primaryTable.primaryKey.type;
				// column.schema.setField(primaryTable.primaryKey.schema.getField());
				table.foreignKeys.put(column, primaryTable.primaryKey);

				sb.append(" ");
				sb.append(primaryTable.primaryKey.type);

				sbOther.append(", foreign key(" + column.name + ") references " + primaryTable.name + "(" + primaryTable.primaryKey.name + ")");

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
		sb.append(sbOther.toString());
		sb.append(");");

		result.add(sb.toString());
	}

	private void findColumnAdapters(Statement statement, ArrayList<SqliteAdapter> columnAdapter, ArrayList<QueryForeignKey> foreignKeys) {
		DatabaseColumn col;		
		QueryForeignKey foreignKey;

		for (int i = 0; i < statement.columns.length; i++) {
			col = statement.columns[i];
			columnAdapter.add(Adapter.lookup(col.schema.getFieldType()));
			
			if (foreignKeys!=null && col.feature==ColumnType.FOREIGN_KEY)
			{
				foreignKey=new QueryForeignKey(i, col, statement.table.foreignKeys.get(col));
				foreignKeys.add(foreignKey);
			}
		}
	}

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
			Class<?> classes[] = { String.class, Enum.class, char.class, Character.class, Currency.class, Date.class, Locale.class, Time.class, TimeZone.class,
					URL.class };

			for (int i = 0; i < classes.length; i++) {
				map.put(classes[i], "TEXT");
			}
		}

		// INTEGER
		{
			Class<?> classes[] = { boolean.class, Boolean.class, int.class, Integer.class, long.class, Long.class, byte.class, Byte.class, short.class,
					Short.class, BigInteger.class };

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
	protected void onDefineDropTableSQL(ArrayList<String> result, HashSet<DatabaseTable> alreadyParsedTables, SQLiteSchema schema, DatabaseTable table) {
		// if already parsed, skip immediately
		if (alreadyParsedTables.contains(table))
			return;

		alreadyParsedTables.add(table);

		StringBuffer sb = new StringBuffer();
		sb.append("drop table if exists " + table.name + " ");
		DatabaseColumn column;

		for (int i = 0; i < table.columns.size(); i++) {
			column = table.columns.get(i);

			// index options
			switch (column.feature) {
			case FOREIGN_KEY:
				DatabaseTable primaryTable = checkedDatabaseTable(schema, column.schema);

				onDefineDropTableSQL(result, alreadyParsedTables, schema, primaryTable);
				break;
			default:
				break;
			}
		}

		result.add(sb.toString());
	}

	@Override
	protected void onCreateDelete(SQLiteDelete delete) {
		findColumnAdapters(delete, delete.columnAdapter, null);
		findFilterAdapters(delete.filter, delete.filterAdapter);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreateQuery(SQLiteQuery query) {		
		findColumnAdapters(query, query.columnAdapter, query.foreignKeys);
		findFilterAdapters(query.filter, query.filterAdapter);
	}

	@Override
	protected void onCreateInsert(SQLiteInsert insert) {
		findColumnAdapters(insert, insert.columnAdapter,null);
	}

	@Override
	protected void onCreateUpdate(SQLiteUpdate update) {
		findColumnAdapters(update, update.columnAdapter,null);
		findFilterAdapters(update.filter, update.filterAdapter);
	}

}
