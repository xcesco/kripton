/**
 * 
 */
package com.abubusoft.kripton.database;

/**
 * @author xcesco
 *
 */
public abstract class Delete extends SQLFilterStatement {
	
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
