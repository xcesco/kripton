package com.abubusoft.kripton.processor.sqlite.grammars.jql;

/**
 * Listener to replace variable parts of a sql statement. 
 * 
 * If event return null, replacement will no executed.
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public interface JQLReplaceVariableStatementListener {

	/**
	 * If event return null, replacement will no executed.
	 * 
	 * @param statement
	 * @return
	 * 		<code>null</code> to avoid replacement.
	 */
	String onWhere(String statement);
	
	/**
	 * If event return null, replacement will no executed.
	 * 
	 * @param statement
	 * @return
	 * 		<code>null</code> to avoid replacement.
	 */
	String onOrderBy(String statement);

	/**
	 * If event return null, replacement will no executed.
	 * 
	 * @param statement
	 * @return
	 * 		<code>null</code> to avoid replacement.
	 */
	String onLimit(String statement);

	/**
	 * If event return null, replacement will no executed.
	 * 
	 * @param statement
	 * @return
	 * 		<code>null</code> to avoid replacement.
	 */
	String onOffset(String statement);

	/**
	 * If event return null, replacement will no executed.
	 * 
	 * @param statement
	 * @return
	 * 		<code>null</code> to avoid replacement.
	 */
	String onGroup(String statement);

	/**
	 * If event return null, replacement will no executed.
	 * 
	 * @param statement
	 * @return
	 * 		<code>null</code> to avoid replacement.
	 */
	String onHaving(String statement);

	/**
	 * If event return null, replacement will no executed.
	 * 
	 * @param statement
	 * @return
	 * 		<code>null</code> to avoid replacement.
	 */
	String onProjectedColumns(String statement);

	/**
	 * If event return null, replacement will no executed.
	 * 
	 * @param statement
	 * @return
	 * 		<code>null</code> to avoid replacement.
	 */
	String onColumnNameSet(String statement);
	
	/**
	 * If event return null, replacement will no executed.
	 * 
	 * @param statement
	 * @return
	 * 		<code>null</code> to avoid replacement.
	 */
	String onColumnValueSet(String statement);

}