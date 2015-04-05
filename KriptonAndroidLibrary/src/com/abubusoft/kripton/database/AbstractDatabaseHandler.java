package com.abubusoft.kripton.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.abubusoft.kripton.exception.MappingException;

public abstract class AbstractDatabaseHandler implements Serializable, DatabaseHandler {

	static final Pattern pattern = Pattern.compile("\\#\\s*\\{\\s*([\\w\\.]*)\\s*\\}");

	public static ParametrizedString splitParams(String input) {
		ArrayList<String> paramsArray = new ArrayList<String>();
		ParametrizedString result = new ParametrizedString();

		if (input == null || "".equals(input.trim())) {
			result.params = new String[0];
			result.value = "";

			return result;
		}

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

		result.params = new String[paramsArray.size()];
		if (paramsArray.size() > 0) {
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
	public Query createQuery(DatabaseTable table, QueryOptions options) {
		String normalizedFields = options.fields.replaceAll("\\s", "");
		Query query = table.queries.get(normalizedFields);
		ArrayList<DatabaseColumn> columns = new ArrayList<DatabaseColumn>();

		if (query == null) {
			query = new Query();
			query.name = normalizedFields;

			table.queries.put(normalizedFields, query);
		} else {
			return query;
		}

		// fieldsPart
		if ("*".equals(normalizedFields)) {
			for (DatabaseColumn item : table.columns) {
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

		query.columns = new DatabaseColumn[columns.size()];
		if (query.columns.length > 0) {
			columns.toArray(query.columns);
		}

		// wherePart
		query.where = splitParams(options.where);

		// orderPart
		query.order = splitParams(options.order);

		query.params = new QueryParams();

		if (options.paramsClass == null) {
			// no params
			if (query.where.params.length>0)
				throw new MappingException("No params class is defined, but in where statement of the query " + query.name + " is defined parameters "
						+ options.paramsClass.getName());
			if (query.order.params.length>0)
				throw new MappingException("No params class is defined, but in order statement of the query " + query.name + " is defined parameters "
						+ options.paramsClass.getName());
		} else {
			DatabaseHelper.validateParams(query.params, options.paramsClass);

			// check for params
			for (String item : query.where.params) {
				if (!query.params.contains(item))
					throw new MappingException("Param " + item + ", used in where statement of the query " + query.name + " is not defined in class "
							+ options.paramsClass.getName());
			}

			for (String item : query.order.params) {
				if (!query.params.contains(item))
					throw new MappingException("Param " + item + ", used in order statement of the query " + query.name + " is not defined in class "
							+ options.paramsClass.getName());
			}
		}

		return query;
	}

}
