/**
 * 
 */
package com.abubusoft.kripton.binder.database;

/**
 * @author xcesco
 *
 */
public abstract class Delete extends FilteredStatement {
	
	public String order;

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.database.SQLStatement#buildSQL()
	 */
	@Override
	protected String buildSQL() {
		StringBuilder sb = new StringBuilder();

		sb.append("delete ");
		sb.append(" from " + table.name);

		if (filter.sql!=null && filter.sql.length() > 0) {
			sb.append(" where " + filter.sql);
		}

		return sb.toString();		
	}
}
