/**
 * 
 */
package com.abubusoft.kripton.database;

/**
 * @author xcesco
 *
 */
public abstract class SQLStatement {

	/**
	 * name of the query
	 */
	public String name;

	/**
	 * cache for string sql
	 */
	protected String cachedSql;

	/**
	 * columns affected by the statement.
	 */
	public DatabaseColumn[] columns;

	/**
	 * table associated to statement
	 */
	public DatabaseTable table;

	/**
	 * Get SQL string
	 * @return
	 */
	public String getSQL() {
		if (cachedSql == null) {
			cachedSql = buildSQL();
		}

		return cachedSql;
	}

	/**
	 * Build SQL Statement
	 * 
	 * @return
	 */
	protected abstract String buildSQL();

}
