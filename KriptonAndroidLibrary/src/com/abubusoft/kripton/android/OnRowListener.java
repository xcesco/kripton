package com.abubusoft.kripton.android;

import java.util.ArrayList;

import android.database.Cursor;

public interface OnRowListener<E> {
	void onRow(Cursor cursor, int rowIndex, E bean, ArrayList<QueryForeignKey> foreignKeys);
}