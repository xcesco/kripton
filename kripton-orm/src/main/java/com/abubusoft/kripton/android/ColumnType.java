/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.android;

/**
 * ColumnType.
 */
public enum ColumnType {

	/**
	 * <p>
	 * Primary key.
	 * </p>
	 */
	PRIMARY_KEY,
	/**
	 * <p>
	 * Unique. It can be considered as alternative key.
	 * </p>
	 */
	UNIQUE,
	/**
	 * <p>
	 * Standard column.
	 * </p>
	 */
	STANDARD,
	/**
	 * <p>
	 * Indexes are special lookup tables that the database search engine can use
	 * to speed up data retrieval. Simply put, an index is a pointer to data in
	 * a table. An index in a database is very similar to an index in the back
	 * of a book.
	 * </p>
	 * 
	 * <p>
	 * For example, if you want to reference all pages in a book that discuss a
	 * certain topic, you first refer to the index, which lists all topics
	 * alphabetically and are then referred to one or more specific page
	 * numbers.
	 * </p>
	 * 
	 * <p>
	 * An index helps speed up SELECT queries and WHERE clauses, but it slows
	 * down data input, with UPDATE and INSERT statements. Indexes can be
	 * created or dropped with no effect on the data.
	 * </p>
	 * 
	 * <p>
	 * Creating an index involves the CREATE INDEX statement, which allows you
	 * to name the index, to specify the table and which column or columns to
	 * index, and to indicate whether the index is in ascending or descending
	 * order.
	 * </p>
	 * 
	 * <p>
	 * Indexes can also be unique, similar to the UNIQUE constraint, in that the
	 * index prevents duplicate entries in the column or combination of columns
	 * on which there's an index.
	 * </p>
	 * 
	 * <p>
	 * See <a href=
	 * "https://www.tutorialspoint.com/sqlite/sqlite_indexes.htm">here</a> for
	 * full documentation.
	 * </p>
	 */
	INDEXED
}
