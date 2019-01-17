/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.abubusoft.kripton.android.sqlite;

// TODO: Auto-generated Javadoc
/**
 * Rapresents a SQLite table. Every table exposes its column names
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public interface SQLiteTable {

	/**
	 * Returns column name set.
	 *
	 * @return the string[]
	 */
	String[] columns();

	/**
	 * Name of table.
	 *
	 * @return the string
	 */
	String name();
}
