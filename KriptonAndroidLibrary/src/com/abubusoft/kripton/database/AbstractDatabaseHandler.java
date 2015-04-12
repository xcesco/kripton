package com.abubusoft.kripton.database;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.abubusoft.kripton.binder.transform.Transformable;
import com.abubusoft.kripton.exception.MappingException;

/**
 * Base for database handler.
 * 
 * @author xcesco
 *
 * @param <Q>
 * @param <I>
 */
public abstract class AbstractDatabaseHandler<Q extends Query, I extends Insert, U extends Update, D extends Delete> implements Serializable,
		DatabaseHandler<Q, I, U, D> {

	protected HashMap<Class<?>, String> mapToType;

	static final Pattern patternPlaceHolder = Pattern.compile("\\#\\s*\\{\\s*([\\w\\.]*)\\s*\\}");

	static final Pattern patternFieldName = Pattern.compile("([\\w\\.]+)");

	/**
	 * Get input string, replace field name with relative column name
	 * 
	 * @param input
	 *            sql with field names
	 * @param field2ColumnMap
	 * @return sql with column names
	 */
	public static String createSql(String input, Filter filter, LinkedHashMap<String, DatabaseColumn> field2ColumnMap) {
		Matcher matcher = patternFieldName.matcher(input);
		StringBuffer sb = new StringBuffer();

		if (matcher.find()) {
			int previousIndex = 0;
			int currentIndex;
			DatabaseColumn column;
			String temp;

			do {
				currentIndex = matcher.start();
				sb.append(input.substring(previousIndex, currentIndex));

				temp = matcher.group(1);
				column = field2ColumnMap.get(temp);

				if (column != null) {
					sb.append(column.name);
				} else {
					sb.append(matcher.group(1));
				}

				previousIndex = matcher.end();
			} while (matcher.find());

			if (previousIndex <= input.length() - 1) {
				sb.append(input.substring(previousIndex));
			}

		} else {
			sb.append(input);
		}

		return sb.toString();
	}

	/**
	 * In sgl replace the placeholders with ? and retrieve field name
	 * 
	 * @param input
	 * @return
	 */
	public static Filter createFilterAndFieldNames(String input) {
		Filter result = new Filter();
		ArrayList<String> paramsArray = new ArrayList<String>();

		if (input == null || "".equals(input.trim())) {
			result.fieldNames = new String[0];
			result.sql = "";

			return result;
		}

		Matcher matcher = patternPlaceHolder.matcher(input);
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

		result.fieldNames = new String[paramsArray.size()];
		result.field = new Field[paramsArray.size()];
		result.fieldTransform = new Transformable[paramsArray.size()];

		if (paramsArray.size() > 0) {
			paramsArray.toArray(result.fieldNames);
		}
		result.sql = sb.toString();
		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.database.DatabaseHandler#insert(com.abubusoft.kripton
	 * .database.DatabaseTable, com.abubusoft.kripton.database.InsertOptions)
	 */
	@Override
	public I createInsert(DatabaseTable table, InsertOptions options) {
		I insert = newInsert();
		buildAffectedColumns(table, insert, options.name, options.fields);

		table.inserts.put(insert.name, insert);
		return insert;
	}

	private static final long serialVersionUID = 6083581270213311588L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.database.DatabaseHandler#getQuery(com.abubusoft
	 * .kripton.database.DatabaseTable, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Q getQuery(DatabaseTable table, String name) {
		return (Q) table.queries.get(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.database.DatabaseHandler#createUpdate(com.abubusoft
	 * .kripton.database.DatabaseTable,
	 * com.abubusoft.kripton.database.UpdateOptions)
	 */
	@Override
	public U createUpdate(DatabaseTable table, UpdateOptions options) {
		U update = newUpdate();

		// build columns
		buildAffectedColumns(table, update, options.name, options.fields);

		// create filter part
		buildFilter(table, update, options.where, options.paramsClass);

		table.updates.put(update.name, update);
		return update;
	}

	protected abstract U newUpdate();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.database.DatabaseHandler#getInsert(com.abubusoft
	 * .kripton.database.DatabaseTable, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public I getInsert(DatabaseTable table, String name) {
		return (I) table.inserts.get(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.database.DatabaseHandler#getUpdate(com.abubusoft
	 * .kripton.database.DatabaseTable, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public U getUpdate(DatabaseTable table, String name) {
		return (U) table.updates.get(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.database.DatabaseHandler#getDelete(com.abubusoft
	 * .kripton.database.DatabaseTable, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public D getDelete(DatabaseTable table, String name) {
		return (D) table.deletes.get(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.binder.database.DatabaseHandler#createColumnSet
	 * (com.abubusoft.kripton.binder.database.DatabaseTable, java.lang.String)
	 */
	@Override
	public Q createQuery(DatabaseTable table, QueryOptions options) {
		Q query = newQuery();

		// build columns
		buildAffectedColumns(table, query, options.name, options.fields);

		// create filter part
		buildFilter(table, query, options.where, options.paramsClass);

		// orderPart
		LinkedHashMap<String, DatabaseColumn> field2ColumnMap = table.field2column;
		Filter tempOrder = createFilterAndFieldNames(options.order);
		if (tempOrder.fieldNames.length > 0)
			throw new MappingException("No param is allowed in in order statement of the query " + query.name + ".");
		query.order = tempOrder.sql;
		query.order = createSql(query.order, null, field2ColumnMap);

		table.queries.put(query.name, query);
		return query;
	}

	/**
	 * build filter section
	 * 
	 * @param table
	 * @param statement
	 * @param where
	 * @param paramsClazz
	 */
	private void buildFilter(DatabaseTable table, SQLFilterStatement statement, String where, Class<?> paramsClazz) {
		LinkedHashMap<String, DatabaseColumn> field2ColumnMap = table.field2column;
		Filter filter = createFilterAndFieldNames(where);
		statement.filter = filter;

		filter.sql = createSql(filter.sql, filter, field2ColumnMap);
		filter.inputClazz = paramsClazz;

		filter.origin = FilterOriginType.BEAN;
		if (filter.fieldNames.length > 0) {
			// there are parameters
			if (paramsClazz == null) {
				filter.origin = FilterOriginType.BEAN;
				DatabaseHelper.scanSchema(statement.name, filter, table);
			} else {
				statement.filter.origin = FilterOriginType.PARAMS;
				DatabaseHelper.scanClass(statement.name, filter, paramsClazz);

				// check if filter fields are same type of bean fields
				Field filterField;
				Field beanField;
				DatabaseColumn column;
				for (int i = 0; i < statement.filter.field.length; i++) {
					filterField = statement.filter.field[i];
					column = table.field2column.get(statement.filter.fieldNames[i]);
					if (column != null) {
						beanField = column.schema.getField();

						if (!filterField.getType().equals(beanField.getType())) {
							throw (new MappingException("A field with name " + beanField.getName() + " is present both in " + beanField.getDeclaringClass()
									+ " like " + beanField.getType().getName() + " and " + filterField.getDeclaringClass() + " like "
									+ filterField.getType().getName() + ", but their type are different!"));
						}
					}
				}
			}
		}

	}

	private void buildAffectedColumns(DatabaseTable table, SQLStatement query, String name, String fields) {
		String normalizedFields = fields.replaceAll("\\s", "");
		String tempName = name != null ? name : normalizedFields;

		ArrayList<DatabaseColumn> columns = new ArrayList<DatabaseColumn>();

		query.name = tempName;
		query.table = table;

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

		query.columns = new DatabaseColumn[columns.size()];
		if (query.columns.length > 0) {
			columns.toArray(query.columns);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.database.DatabaseHandler#createDelete(com.abubusoft
	 * .kripton.database.DatabaseTable,
	 * com.abubusoft.kripton.database.DeleteOptions)
	 */
	@Override
	public D createDelete(DatabaseTable table, DeleteOptions options) {
		D delete = newDelete();

		// build columns
		buildAffectedColumns(table, delete, options.name, options.fields);

		// create filter part
		buildFilter(table, delete, options.where, options.paramsClass);

		table.deletes.put(delete.name, delete);
		return delete;
	}

	protected abstract D newDelete();

	protected abstract Q newQuery();

	protected abstract I newInsert();

}
