package com.abubusoft.kripton.android;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.abubusoft.kripton.android.adapter.SqliteAdapter;
import com.abubusoft.kripton.binder.database.ColumnType;
import com.abubusoft.kripton.binder.database.DatabaseColumn;
import com.abubusoft.kripton.binder.database.Query;
import com.abubusoft.kripton.exception.MappingException;

public class SQLiteQuery<E> extends Query implements OnRowListener<E> {

	ThreadLocal<Object> cachedBeans = new ThreadLocal<Object>();

	@SuppressWarnings("rawtypes")
	ArrayList<SqliteAdapter> columnAdapter = new ArrayList<>();

	@SuppressWarnings("rawtypes")
	ArrayList<SqliteAdapter> filterAdapter = new ArrayList<>();

	ThreadLocal<String[]> filterValues = new ThreadLocal<String[]>();
	
	OnRowListener<E> listener;
	
	ArrayList<QueryForeignKey> foreignKeys=new ArrayList<QueryForeignKey>();

	/**
	 * @return the listener
	 */
	public OnRowListener<E> getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public void setListener(OnRowListener<E> listener) {
		this.listener = listener;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.database.SQLStatement#buildSQL()
	 */
	protected String buildSQLCount() {
		StringBuilder sb = new StringBuilder();

		sb.append("select count(*) ");
		sb.append(" from " + table.name);

		if (filter.sql != null && filter.sql.length() > 0) {
			sb.append(" where " + filter.sql);
		}

		if (order != null && order.length() > 0) {
			sb.append(" order by " + order);
		}

		return sb.toString();
	}

	protected void cursor2Bean(Cursor cursor, Object bean) {
		DatabaseColumn col = null;
		SqliteAdapter<?> adapter;

		try {
			for (int i = 0; i < columns.length; i++) {
				col = columns[i];
				
				if (col.feature==ColumnType.FOREIGN_KEY) continue;
				
				adapter = columnAdapter.get(i);

				col.schema.setFieldValue(bean, adapter.readCursor(cursor, i));
			}
		} catch (Exception e) {
			if (col != null)
				throw (new MappingException("Error during mapping column " + col.name + " from bean. Reason " + e.getMessage()));

			throw (new MappingException("Error during mapping columns from bean. Reason " + e.getMessage()));
		}
	}

	/**
	 * Retrieve a cursor. The extracted cursor has to be closed once it is used.
	 * 
	 * @param database
	 * @param params
	 * @return
	 */
	public Cursor executeCursor(SQLiteDatabase database, Object params) {
		String[] filterValues = getFilterValues(params);
		Cursor cursor = null;
		try {
			cursor = database.rawQuery(buildSQLCount(), filterValues);

			return cursor;
		} catch (Exception e) {
			if (cursor != null)
				cursor.close();
			throw (new MappingException(e.getMessage()));
		}
	}

	public long executeCount(SQLiteDatabase database, Object params) {
		long result = 0;

		String[] filterValues = getFilterValues(params);

		try {
			Cursor cursor = database.rawQuery(buildSQLCount(), filterValues);

			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				
				result=cursor.getLong(0);

				cursor.close();
			}

			return result;

		} catch (Exception e) {
			throw (new MappingException(e.getMessage()));
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<E> executeList(SQLiteDatabase database, Object params) {
		if (listener == null)
			listener = this;

		String[] filterValues = getFilterValues(params);

		ArrayList<E> result = new ArrayList<E>();
		try {
			Cursor cursor = database.rawQuery(getSQL(), filterValues);

			if (cursor.getCount() > 0) {
				E bean = (E) cachedBeans.get();
				if (bean == null) {
					bean = (E) table.clazz.newInstance();
					cachedBeans.set(bean);
				}

				int counter = 0;
				cursor.moveToFirst();
				do {
					cursor2Bean(cursor, bean);
					listener.onRow(cursor, counter, bean, this.foreignKeys);
					result.add(bean);

					counter++;

				} while (cursor.moveToNext());

				cursor.close();
			}

			return result;
		} catch (Exception e) {
			throw (new MappingException(e.getMessage()));
		}
	}

	public ArrayList<E> executeList(SQLiteDatabase database) {
		return executeList(database, null);
	}

	/**
	 * Execute query for extract one bean. Every query instance new bean
	 * 
	 * @param database
	 * @param beanClazz
	 * @param params
	 * @return
	 */
	public E executeOne(SQLiteDatabase database, Object params) {
		return executeOne(database, params, false);
	}

	/**
	 * Execute query for extract one bean. Extracted bean can ben cached (one
	 * value for thread).
	 * 
	 * @param database
	 * @param beanClazz
	 * @param params
	 * @param cachedValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public E executeOne(SQLiteDatabase database, Object params, boolean cachedValue) {

		String[] filterValues = getFilterValues(params);

		try {
			E bean = null;
			Cursor cursor = database.rawQuery(getSQL(), filterValues);

			if (cursor.getCount() > 0) {
				if (cachedValue) {
					bean = (E) cachedBeans.get();
					if (bean == null) {
						bean =  (E) table.clazz.newInstance();
						cachedBeans.set(bean);
					}
				} else {
					bean = (E) table.clazz.newInstance();
				}

				cursor.moveToFirst();
				cursor2Bean(cursor, bean);
				cursor.close();
			}

			return bean;

		} catch (Exception e) {
			throw (new MappingException(e.getMessage()));
		}
	}

	@SuppressWarnings("unchecked")
	public void executeWithListener(SQLiteDatabase database, Object params) {
		String[] filterValues = getFilterValues(params);

		try {
			Cursor cursor = database.rawQuery(getSQL(), filterValues);

			if (cursor.getCount() > 0) {
				int index = 0;
				E bean = (E) cachedBeans.get();
				if (bean == null) {
					bean = (E) table.clazz.newInstance();
					cachedBeans.set(bean);
				}

				cursor.moveToFirst();
				do {
					cursor2Bean(cursor, bean);
					listener.onRow(cursor, index, bean, foreignKeys);
					index++;
				} while (cursor.moveToNext());

				cursor.close();
			}

		} catch (Exception e) {
			throw (new MappingException(e.getMessage()));
		}
	}

	public void executeWithListener(SQLiteDatabase database) {
		executeWithListener(database, null);
	}

	public String[] getFilterValues(Object params) {
		String[] filterValuesArray = null;
		switch (filter.origin) {
		case NONE:
			break;
		case ONE_PARAM:
			filterValuesArray = SQLiteHelper.getFilterValuesFromOneParam(filter, filterValues, params, filter.inputClazz);
			break;
		case PARAMS:
			filterValuesArray = SQLiteHelper.getFilterValuesFromParams(filter, filterValues, params, filter.inputClazz);
			break;
		case BEAN:
			filterValuesArray = SQLiteHelper.getFilterValuesFromParams(filter, filterValues, params, table.clazz);
			break;
		}

		return filterValuesArray;
	}

	@Override
	public void onRow(Cursor cursor, int rowIndex, E bean, ArrayList<QueryForeignKey> foreignKeys) {
		// TODO Auto-generated method stub
		
	}

}
