package com.abubusoft.kripton.binder.database;

public interface QueryListener<E> {
	void onRow(int count, E bean);
}