package com.abubusoft.kripton.android;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.content.ContentValues;

import com.abubusoft.kripton.android.adapter.SqliteAdapter;
import com.abubusoft.kripton.binder.database.ColumnType;
import com.abubusoft.kripton.binder.database.DatabaseColumn;
import com.abubusoft.kripton.binder.database.DatabaseTable;
import com.abubusoft.kripton.binder.database.Filter;
import com.abubusoft.kripton.binder.database.Statement;
import com.abubusoft.kripton.binder.transform.Transformable;
import com.abubusoft.kripton.exception.MappingException;

public class SQLiteHelper {

	@SuppressWarnings("unchecked")
	public static ContentValues bean2ContentValues(Statement statement, @SuppressWarnings("rawtypes") ArrayList<SqliteAdapter> columnAdapter,
			ThreadLocal<ContentValues> values, Object bean) {
		LinkedHashMap<DatabaseColumn, DatabaseColumn> foreignKeys = statement.table.foreignKeys;
		ContentValues value = values.get();
		if (value == null) {
			value = new ContentValues();
			values.set(value);
		}

		DatabaseColumn[] columns = statement.columns;
		value.clear();
		Object v;

		@SuppressWarnings("rawtypes")
		SqliteAdapter adapter = null;
		try {
			int n = columns.length;
			for (int i = 0; i < n; i++) {

				if (columns[i].feature != ColumnType.FOREIGN_KEY) {
					v = columns[i].schema.getFieldValue(bean);
					adapter = columnAdapter.get(i);

				} else {
					v = columns[i].schema.getFieldValue(bean);
					DatabaseColumn primaryKeyColumn = foreignKeys.get(columns[i]);
					v = primaryKeyColumn.schema.getFieldValue(v);
				}
				if (v != null) {
					adapter.writeValue(v, value, columns[i].name);
				}

			}

			return value;
		} catch (Exception e) {
			throw new MappingException(e.getMessage());
		}
	}

	/**
	 * It's made for test scope. Application does not need to invoke this
	 * methods for statement usage.
	 * 
	 * @param parameters
	 * @return
	 */
	public static String[] getFilterValues(Filter filter, ThreadLocal<String[]> filterValues, DatabaseTable table, Object parameters) {

		String[] filterValuesArray = null;
		switch (filter.origin) {
		case NONE:
			break;
		case ONE_PARAM:
			filterValuesArray = getFilterValuesFromOneParam(filter, filterValues, parameters, filter.inputClazz);
			break;
		case PARAMS:
			filterValuesArray = getFilterValuesFromParams(filter, filterValues, parameters, filter.inputClazz);
			break;
		case BEAN:
			filterValuesArray = getFilterValuesFromParams(filter, filterValues, parameters, table.clazz);
			break;
		}

		return filterValuesArray;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected static String[] getFilterValuesFromOneParam(Filter filter, ThreadLocal<String[]> filterValues, Object parameters, Class<?> checkClazz) {
		String[] values = filterValues.get();
		if (values == null) {
			values = new String[filter.fieldNames.length];
			filterValues.set(values);
		}

		if (parameters == null) {
			throw new MappingException("Parameter is null, but parameter of type " + filter.inputClazz + " is needed");
		}

		try {
			Transformable t = filter.fieldTransform[0];
			values[0] = t.write(parameters);

		} catch (Exception e) {
			throw new MappingException("Wrong params type, " + e.getMessage());
		}

		return values;
	}

	@SuppressWarnings("unchecked")
	protected static String[] getFilterValuesFromParams(Filter filter, ThreadLocal<String[]> filterValues, Object parameters, Class<?> checkClazz) {
		if (!parameters.getClass().isAssignableFrom(checkClazz)) {
			throw (new MappingException("Wrong class for query parameters: aspected " + checkClazz + ", but used " + parameters.getClass()));
		}

		String[] values = filterValues.get();
		if (values == null) {
			values = new String[filter.fieldNames.length];
			filterValues.set(values);
		}

		int n = filter.field.length;
		Field f;
		@SuppressWarnings("rawtypes")
		Transformable t;
		try {

			for (int i = 0; i < n; i++) {
				f = filter.field[i];
				t = filter.fieldTransform[i];
				values[i] = t.write(f.get(parameters));
			}
		} catch (Exception e) {
			throw new MappingException("Unable to get parameter " + e.getMessage());
		}

		return values;
	}
}
