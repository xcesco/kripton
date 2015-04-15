package com.abubusoft.kripton.binder.database;

public class Update extends FilteredStatement {

	@Override
	protected String buildSQL() {
		StringBuilder sb = new StringBuilder();
		String separator = "";

		sb.append("update ");
		sb.append(table.name);
		sb.append(" set (");
		for (DatabaseColumn item : columns) {
			sb.append(separator + item.name);
			sb.append(" = ?");

			separator = ", ";
		}
		sb.append(") ");
		
		if (filter.sql!=null)
		{
			sb.append(" where ");
			sb.append(filter.sql);	
		}		
		
		sb.append(";");
		
		return sb.toString();
	}

}
