package com.abubusoft.kripton.android;

import java.util.ArrayList;

import android.content.ContentValues;

import com.abubusoft.kripton.android.adapter.SqliteAdapter;
import com.abubusoft.kripton.database.DatabaseColumn;
import com.abubusoft.kripton.database.SQLStatement;
import com.abubusoft.kripton.exception.MappingException;

public class SQLiteHelper {

	@SuppressWarnings("unchecked")
	public static ContentValues bean2Values(SQLStatement statement, @SuppressWarnings("rawtypes") ArrayList<SqliteAdapter> columnAdapter, ThreadLocal<ContentValues> values, Object bean) {
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
