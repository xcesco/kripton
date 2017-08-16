/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.android.sqlite;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public abstract class SqlUtils {
	
	/**
	 * Prepare a where statement for LOG. If needed, it prepend the WHERE word.
	 * 
	 * @param needWhere
	 * 		true if WHERE is needed before where conditions.
	 * @param value
	 * 		where conditions
	 * @return
	 * 		WHERE statement
	 */
	public static String appendWhereForLog(boolean needWhere, String value) {
		return appendForLog((needWhere ? "WHERE ":"")+value);
	}
	
	/**
	 * Prepare a where statement for SQL. If needed, it prepend the WHERE word.
	 * 
	 * @param needWhere
	 * 		true if WHERE is needed before where conditions.
	 * @param value
	 * 		where conditions
	 * @return
	 * 		WHERE statement
	 */
	public static String appendWhereForSQL(boolean needWhere, String value) {
		return appendForSQL((needWhere ? "WHERE ":"")+value);
	}

	/**
	 * Prepare a string to be used as part of SQL statement in LOGCAT.
	 * 
	 * Replace % with %%
	 * 
	 * @param value
	 * 
	 * @return
	 */
	public static String appendForLog(String value) {	
		return appendForSQL(value).replaceAll("\\%", "\\%\\%");
	}
	
	/**
	 * Prepare a string to be used as part of SQL statement.
	 * 
	 * @param value
	 * 
	 * @return
	 */
	public static String appendForSQL(String value) {
		if (value==null) return "";
				
		return value;
	}
	
	/**
	 * Format sql
	 * 
	 * @param input
	 * @return
	 * 		formatted sql
	 */
	public static String formatSQL(String input, Object ... args)
	{
		return "SQL: "+String.format(input, args);
	}
	
	/**
	 * Display string <code>String.format(format, objects)</code> only if condition is true
	 * @param condition
	 * @param format
	 * @param objects
	 * @return
	 */
	public static String printIf(boolean condition, String format, Object ...objects)
	{
		return condition ? String.format(format, objects) : "";
	}
	
}
