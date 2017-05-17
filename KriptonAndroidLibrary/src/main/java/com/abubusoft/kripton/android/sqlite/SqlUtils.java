package com.abubusoft.kripton.android.sqlite;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
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
