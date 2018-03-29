package com.abubusoft.kripton.android.orm;

/**
 * Rapresents a SQLite table. Every table exposes its column names
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public interface SQLiteTable {

	/**
	 * Returns column name set
	 * 
	 * @return
	 */
	String[] columns();

	/**
	 * Name of table
	 * @return
	 */
	String name();
}
