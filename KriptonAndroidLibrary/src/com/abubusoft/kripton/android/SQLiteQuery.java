package com.abubusoft.kripton.android;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.abubusoft.kripton.android.adapter.SqliteAdapter;
import com.abubusoft.kripton.database.FilterOriginType;
import com.abubusoft.kripton.database.Query;
import com.abubusoft.kripton.database.DatabaseColumn;
import com.abubusoft.kripton.database.QueryListener;
import com.abubusoft.kripton.exception.MappingException;

public class SQLiteQuery extends Query {

	/**
	 * schema adapter
	 */
	SQLiteSchema schema;

	/**
	 * list of adapters
	 */
	@SuppressWarnings("rawtypes")
	ArrayList<SqliteAdapter> columnAdapter = new ArrayList<>();

	@SuppressWarnings("rawtypes")
	ArrayList<SqliteAdapter> filterAdapter = new ArrayList<>();

	public <E> ArrayList<E> execute(SQLiteDatabase database, Class<E> beanClazz) {
		return execute(database, beanClazz, null);
	}

	public <E> void executeWithListener(SQLiteDatabase database, Class<E> beanClazz, QueryListener<E> listener) {
		executeWithListener(database, beanClazz, null, listener);
	}

	public <E> ArrayList<E> execute(SQLiteDatabase database, Class<E> beanClazz, Object params) {
		// check for supported bean clazz
		if (!beanClazz.isAssignableFrom(table.clazz)) {
			throw (new MappingException("Query '" + this.name + "' is for class " + table.clazz.getName() + ". It can not be used for " + beanClazz.getName()));
		}
		
		String[] filterValues = null;		
		if (filter.origin==FilterOriginType.PARAMS) {
			filterValues = getFilterValues(params, filter.inputClazz);
		}

		ArrayList<E> result = new ArrayList<E>();
		try {
			Cursor cursor = database.rawQuery(getSQL(), filterValues);

			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				do {
					E bean = beanClazz.newInstance();
					cursor2Bean(cursor, bean);
					result.add(bean);

				} while (cursor.moveToNext());

				cursor.close();
			}

			return result;
		} catch (Exception e) {
			throw (new MappingException(e.getMessage()));
		}
	}

	public <E> void executeWithListener(SQLiteDatabase database, Class<E> beanClazz, Object params, QueryListener<E> listener) {
		// check for supported bean clazz
		if (!beanClazz.isAssignableFrom(table.clazz)) {
			throw (new MappingException("Query '" + this.name + "' is for class " + table.clazz.getName() + ". It can not be used for " + beanClazz.getName()));
		}

		String[] filterValues = null;				
		if (filter.origin==FilterOriginType.PARAMS) {
			filterValues = getFilterValues(params, filter.inputClazz);
		}
		
		try {
			Cursor cursor = database.rawQuery(getSQL(), filterValues);

			if (cursor.getCount() > 0) {
				int index = 0;
				E bean = beanClazz.newInstance();

				cursor.moveToFirst();
				do {
					cursor2Bean(cursor, bean);
					listener.onRow(index, bean);
					index++;
				} while (cursor.moveToNext());

				cursor.close();
			}

		} catch (Exception e) {
			throw (new MappingException(e.getMessage()));
		}
	}

	protected void cursor2Bean(Cursor cursor, Object bean) {
		DatabaseColumn col = null;
		SqliteAdapter<?> adapter;

		try {
			for (int i = 0; i < columns.length; i++) {
				col = columns[i];
				adapter = columnAdapter.get(i);

				col.schema.setFieldValue(bean, adapter.readCursor(cursor, i));
			}
		} catch (Exception e) {
			if (col != null)
				throw (new MappingException("Error during mapping column " + col.name + " from bean. Reason " + e.getMessage()));

			throw (new MappingException("Error during mapping columns from bean. Reason " + e.getMessage()));
		}
	}

}
