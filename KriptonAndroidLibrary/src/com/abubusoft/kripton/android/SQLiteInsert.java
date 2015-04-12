package com.abubusoft.kripton.android;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.abubusoft.kripton.android.adapter.SqliteAdapter;
import com.abubusoft.kripton.database.Insert;
import com.abubusoft.kripton.exception.MappingException;

public class SQLiteInsert extends Insert {

	/**
	 * schema adapter
	 */
	SQLiteSchema schema;

	/**
	 * list of adapters
	 */
	@SuppressWarnings("rawtypes")
	ArrayList<SqliteAdapter> columnAdapter = new ArrayList<>();

	public boolean execute(SQLiteDatabase database, Object bean) {
		// check for supported bean clazz
		if (!bean.getClass().isAssignableFrom(table.clazz)) {
			throw (new MappingException("Query '" + this.name + "' is for class " + table.clazz.getName() + ". It can not be used for "
					+ bean.getClass().getName()));
		}

		ContentValues value = bean2Values(bean);
		
		value.remove(table.primaryKey.name);
		
		long id = database.insert(table.name, null, value);

		if (id == SQLiteSchema.INVALID_ID) {
			throw (new MappingException("Can not insert row in table " + table.name));
		}

		if (table.primaryKey != null) {
			try {
				table.primaryKey.schema.setFieldValue(bean, id);
			} catch (Exception e) {
				throw new MappingException(e.getMessage());
			}
		}

		return true;

	}

	@SuppressWarnings("unchecked")
	private ContentValues bean2Values(Object bean) {
		ContentValues value = schema.values.get();
		if (value == null) {
			value = new ContentValues();
			schema.values.set(value);
		}

		value.clear();
		Object v;

		@SuppressWarnings("rawtypes")
		SqliteAdapter adapter = null;
		try {
			int n = columns.length;
			for (int i = 0; i < n; i++) {

				v = columns[i].schema.getFieldValue(bean);
				adapter = columnAdapter.get(i);

				if (v != null) {
					adapter.writeValue(v, value, columns[i].name);
				}
			}

			return value;
		} catch (Exception e) {
			throw new MappingException(e.getMessage());
		}
	}
}
