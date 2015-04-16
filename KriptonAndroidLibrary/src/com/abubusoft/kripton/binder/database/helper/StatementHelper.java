/**
 * 
 */
package com.abubusoft.kripton.binder.database.helper;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.abubusoft.kripton.binder.database.DatabaseColumn;
import com.abubusoft.kripton.binder.database.DatabaseTable;
import com.abubusoft.kripton.binder.database.Statement;
import com.abubusoft.kripton.exception.MappingException;

/**
 * Helper for statement
 * 
 * @author xcesco
 *
 */
public abstract class StatementHelper {
	
	/**
	 * Create statement and fill its columns 
	 * 
	 * @param table
	 * 		table for statement
	 * @param clazz
	 * 		statement clazz
	 * @param name
	 * 		name of the statement
	 * @param fields
	 * 		string contains list of comma separated fields name
	 * @return
	 */
	public static <S extends Statement> S buildAffectedColumns(DatabaseTable table, Class<S> clazz, String name, String fields) {
		S statement;
		try {
			statement = clazz.newInstance();
		} catch (Exception e) {
			throw new MappingException(e.getMessage());
		}
		
		String normalizedFields = fields.replaceAll("\\s", "");
		String tempName = name != null ? name : normalizedFields;

		ArrayList<DatabaseColumn> columns = new ArrayList<DatabaseColumn>();

		statement.name = tempName;
		statement.table = table;

		// fieldsPart
		LinkedHashMap<String, DatabaseColumn> field2ColumnMap = table.field2column;
		if ("*".equals(normalizedFields)) {
			for (DatabaseColumn item : table.columns) {
				columns.add(item);
			}
		} else {
			String[] fieldsArray = normalizedFields.split(",");

			for (String item : fieldsArray) {
				DatabaseColumn column = field2ColumnMap.get(item);

				if (column == null)
					throw new MappingException("No field has name " + item + " in class " + table.clazz.getName());

				columns.add(column);
			}
		}

		statement.columns = new DatabaseColumn[columns.size()];
		if (statement.columns.length > 0) {
			columns.toArray(statement.columns);
		}
		
		return statement;
	}
}
