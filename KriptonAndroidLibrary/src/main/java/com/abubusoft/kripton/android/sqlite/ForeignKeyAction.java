package com.abubusoft.kripton.android.sqlite;

/**
 * Action type on update or delete.
 * 
 * @see https://sqlite.org/foreignkeys.html
 * 
 * @author xcesco
 *
 */
public enum ForeignKeyAction {
	NO_ACTION, CASCADE, SET_NULL
}
