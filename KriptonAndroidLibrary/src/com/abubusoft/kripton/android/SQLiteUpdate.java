package com.abubusoft.kripton.android;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.abubusoft.kripton.android.adapter.SqliteAdapter;
import com.abubusoft.kripton.binder.database.Update;
import com.abubusoft.kripton.exception.MappingException;

public class SQLiteUpdate extends Update {
 
	SQLiteHandler handler;
	
	@SuppressWarnings("rawtypes")
	ArrayList<SqliteAdapter> filterAdapter = new ArrayList<>();

	ThreadLocal<String[]> filterValues=new ThreadLocal<String[]>();

	/**
	 * list of adapters
	 */
	@SuppressWarnings("rawtypes")
	ArrayList<SqliteAdapter> columnAdapter = new ArrayList<>();

	public int execute(SQLiteDatabase database, Object bean, Object paramValues) {
		// check for supported bean clazz
		if (!bean.getClass().isAssignableFrom(table.clazz)) {
			throw (new MappingException("Query '" + this.name + "' is for class " + table.clazz.getName() + ". It can not be used for "
					+ bean.getClass().getName()));
		}

		try {
			ContentValues value = SQLiteHelper.bean2Values(this, this.columnAdapter, handler.contentValues, bean);

			String[] filterValuesArray = null;
			switch(filter.origin)
			{
			case PARAMS:
				filterValuesArray = SQLiteHelper.getFilterValuesFromParams(filter, filterValues, paramValues, filter.inputClazz);
				break;
			case BEAN:
				filterValuesArray = SQLiteHelper.getFilterValuesFromParams(filter, filterValues,bean, table.clazz);
				break;
			case NONE:
				break;
			case ONE_PARAM:
				filterValuesArray=SQLiteHelper.getFilterValuesFromOneParam(filter, filterValues,paramValues, filter.inputClazz);
				break;
			}			

			int affectedRows = database.update(table.name, value, filter.sql, filterValuesArray);

			return affectedRows;
		} catch (Exception e) {
			throw (new MappingException(e.getMessage()));

		}
	}
}
