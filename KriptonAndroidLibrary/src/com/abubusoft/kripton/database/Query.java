/**
 * 
 */
package com.abubusoft.kripton.database;

/**
 * @author xcesco
 *
 */
public abstract class Query extends FilteredStatement {
	
	public static final String QUERY_ALL = "defaultAll";
	
	
	public String order;

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.database.SQLStatement#buildSQL()
	 */
	@Override
	protected String buildSQL() { 
		StringBuilder sb = new StringBuilder();
		String separator = "";

		sb.append("select ");
		for (DatabaseColumn item : columns) {
			sb.append(separator + item.name);

			separator = ", ";
		}
		sb.append(" from " + table.name);

		if (filter.sql!=null && filter.sql.length() > 0) {
			sb.append(" where " + filter.sql);
		}

		if (order!=null && order.length() > 0) {
			sb.append(" order by " + order);
		}

		return sb.toString();		
	}
}
