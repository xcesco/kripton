package com.abubusoft.kripton.binder.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractDatabaseHandler implements Serializable, DatabaseHandler {
	
	Pattern pattern = Pattern.compile("\\#\\s*\\{\\s*([\\w\\.]*)\\s*\\}");

	public ParametrizedString splitParams(String input) {
		ArrayList<String> paramsArray=new ArrayList<String>();
		ParametrizedString result = new ParametrizedString();

		Matcher matcher = pattern.matcher(input);
		StringBuffer sb = new StringBuffer();

		if (matcher.find()) {
			int previousIndex = 0;
			int currentIndex;

			do {
				currentIndex = matcher.start();
				sb.append(input.substring(previousIndex, currentIndex));
				sb.append("?");
				paramsArray.add(matcher.group(1));

				previousIndex = matcher.end();
			} while (matcher.find());

			if (previousIndex <= input.length() - 1) {
				sb.append(input.substring(previousIndex));
			}

		} else {
			sb.append(input);
		}

		
		result.params=new String[paramsArray.size()];
		if (paramsArray.size()>0)
		{
			paramsArray.toArray(result.params);
		}
		result.value = sb.toString();
		return result;

	}

	private static final long serialVersionUID = 6083581270213311588L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.binder.database.DatabaseHandler#createColumnSet
	 * (com.abubusoft.kripton.binder.database.DatabaseTable, java.lang.String)
	 */
	@Override
	public Query createColumnSet(DatabaseTable table, String fieldsPart, String wherePart, String orderPart) {
		String normalizedFields = fieldsPart.replaceAll("\\s", "");
		Query set = table.columnsSet.get(normalizedFields);
		ArrayList<DatabaseColumn> columns=new ArrayList<DatabaseColumn>();

		if (set == null) {
			set = new Query();
			set.name = normalizedFields;

			table.columnsSet.put(normalizedFields, set);
		} else {
			return set;
		}

		// fieldsPart
		if ("*".equals(normalizedFields)) {			
			for (DatabaseColumn item : columns) {
				columns.add(item);
			}
		} else {
			String[] fieldsArray = normalizedFields.split(",");

			Map<String, DatabaseColumn> map = table.field2column;
			for (String item : fieldsArray) {
				DatabaseColumn column = map.get(item);
				columns.add(column);
			}
		}
		
		set.columns=new DatabaseColumn[columns.size()];
		if (set.columns.length>0)
		{
			columns.toArray(set.columns);
		}
		

		// wherePart
		set.where=splitParams(wherePart);
		
		// orderPart
		set.order=splitParams(orderPart);

		return set;
	}

}
