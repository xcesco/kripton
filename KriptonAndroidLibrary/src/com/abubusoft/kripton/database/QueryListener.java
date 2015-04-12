package com.abubusoft.kripton.database;

public interface QueryListener<E> {
	void onRow(int count, E bean);
}