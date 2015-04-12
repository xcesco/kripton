package com.abubusoft.kripton.android;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.abubusoft.kripton.android.adapter.SqliteAdapter;
import com.abubusoft.kripton.database.FilterOriginType;
import com.abubusoft.kripton.database.Update;
import com.abubusoft.kripton.exception.MappingException;

public class SQLiteUpdate extends Update {

	/**
	 * schema adapter
	 */
	SQLiteSchema schema;

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
			ContentValues value = SQLiteHelper.bean2Values(this, this.columnAdapter, schema.values, bean);

			String[] filterValues = null;
			switch(filter.origin)
			{
			case PARAMS:
				filterValues = getFilterValuesFromParams(paramValues, filter.inputClazz);
				break;
			case BEAN:
				filterValues = getFilterValuesFromParams(bean, table.clazz);
				break;
			case NONE:
				break;
			case ONE_PARAM:
				filterValues=getFilterValuesFromOneParam(paramValues, filter.inputClazz);
				break;
			}			

			int affectedRows = database.update(table.name, value, filter.sql, filterValues);

			return affectedRows;
		} catch (Exception e) {
			throw (new MappingException(e.getMessage()));

		}
	}
}
