package com.abubusoft.kripton.database;

public class Insert extends SQLStatement {
	
	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.database.SQLStatement#buildSQL()
	 */
	@Override
	protected String buildSQL() {
		StringBuilder sb = new StringBuilder();
		String separator = "";

		sb.append("insert into ");
		sb.append(table.name);
		sb.append(" (");
		for (DatabaseColumn item : columns) {
			sb.append(separator + item.name);

			separator = ", ";
		}
		sb.append(") values (");
		separator = "";
		for (int i = 0; i < columns.length; i++) {
			sb.append(separator + "?");

			separator = ", ";
		}
		sb.append(")");
		
		return sb.toString();
	}
	
}
