/**
 * 
 */
package com.abubusoft.kripton.database;

/**
 * @author xcesco
 *
 */
public class SQLStatement {

	public DatabaseColumn[] columns;
	/**
	 * name of the query
	 */
	public String name;
	/**
	 * name of table
	 */
	public String tableName;
	
	/**
	 * params of query
	 */
	public SQLStatementParams params = new SQLStatementParams();

}
