package com.abubusoft.kripton.database;

public class Insert extends SQLStatement {
	public String getSQL() {
		StringBuilder sb = new StringBuilder();
		String separator = "";

		sb.append("insert into ");
		sb.append(tableName);
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
