package com.abubusoft.kripton.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.abubusoft.kripton.exception.MappingException;

public abstract class AbstractDatabaseHandler<Q extends Query> implements Serializable, DatabaseHandler<Q> {

	protected HashMap<Class<?>, String> mapToType;

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

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.database.DatabaseHandler#insert(com.abubusoft.kripton.database.DatabaseTable, com.abubusoft.kripton.database.InsertOptions)
	 */
	@Override
	public Insert insert(DatabaseTable table, InsertOptions options) {
		String normalizedFields = options.fields.replaceAll("\\s", "");
		@SuppressWarnings("unchecked")
		Insert insert = table.inserts.get(normalizedFields);
		ArrayList<DatabaseColumn> columns = new ArrayList<DatabaseColumn>();

		if (insert == null) {
			insert = new Insert();
			insert.name = normalizedFields;
			insert.tableName=table.name;

			table.inserts.put(normalizedFields, insert);
		} else {
			return new Insert();
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

		insert.columns = new DatabaseColumn[columns.size()];
		if (insert.columns.length > 0) {
			columns.toArray(insert.columns);
		}

		return insert;
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
	public Q createQuery(DatabaseTable table, QueryOptions options) {
		String normalizedFields = options.fields.replaceAll("\\s", "");
		@SuppressWarnings("unchecked")
		Q query = (Q) table.queries.get(normalizedFields);
		ArrayList<DatabaseColumn> columns = new ArrayList<DatabaseColumn>();

		if (query == null) {
			query = createNewQuery();
			query.name = normalizedFields;
			query.tableName=table.name;
			query.paramsClass=options.paramsClass;

			table.queries.put(normalizedFields, query);
		} else {
			return createNewQuery();
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
		query.whereStatement = splitParams(options.where);

		// orderPart
		ParametrizedString tempOrder = splitParams(options.order);
		if (tempOrder.params.length > 0)
			throw new MappingException("No param is allowed in in order statement of the query " + query.name + ".");
		query.orderStatement=tempOrder.value;

		query.params = new QueryParams();

		if (options.paramsClass == null) {
			// no params
			if (query.whereStatement.params.length > 0)
				throw new MappingException("No params class is defined, but in where statement of the query " + query.name + " are defined parameters ");
			
		} else {
			DatabaseHelper.validateParams(query.params, options.paramsClass);

			// check for params
			for (String item : query.whereStatement.params) {
				if (!query.params.contains(item))
					throw new MappingException("Param " + item + ", used in where statement of the query " + query.name + " is not defined in class "
							+ options.paramsClass.getName());
			}
		}

		return query;
	}

	protected abstract Q createNewQuery();

}
