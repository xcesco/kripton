package com.abubusoft.kripton.android.sqlite;

import com.abubusoft.kripton.android.BindSQLTypeAdapter;

public class NoAdapter implements BindSQLTypeAdapter<Object, Object> {

	@Override
	public Object toJava(Object dataValue) {
		return null;
	}

	@Override
	public Object toData(Object javaValue) {
		return null;
	}

	@Override
	public String toString(Object javaValue) {
		return null;
	}

}
