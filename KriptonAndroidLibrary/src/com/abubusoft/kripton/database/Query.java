/**
 * 
 */
package com.abubusoft.kripton.database;

import com.abubusoft.kripton.exception.MappingException;

/**
 * @author xcesco
 *
 */
/**
 * @author xcesco
 *
 */
public class Query extends SQLStatement {

	public String orderStatement;

	/**
	 * params of query
	 */
	protected QueryParams params = new QueryParams();

	/**
	 * class of parameters
	 */
	protected Class<?> paramsClass;

	public ParametrizedString whereStatement;

	public String[] getParams(Object parameters) {
		if (parameters.getClass() != paramsClass) {
			throw (new MappingException("Wrong class for query parameters: aspected " + paramsClass + ", but used " + params.getClass()));
		}

		String[] values = whereStatement.paramValues.get();
		if (values == null) {
			values = new String[whereStatement.params.length];
			whereStatement.paramValues.set(values);
		}

		int i = 0;
		QueryParam p;
		try {
			for (String item : whereStatement.params) {
				p = params.get(item);
				values[i++] = p.trans.write(p.field.get(parameters));
			}
		} catch (Exception e) {
			throw new MappingException("Unable to get parameter " + e.getMessage());
		}
		
		return values;
	}

	public String getSQL() {
		StringBuilder sb = new StringBuilder();
		String separator = "";

		sb.append("select ");
		for (DatabaseColumn item : columns) {
			sb.append(separator + item.name);

			separator = ", ";
		}
		sb.append(" from " + tableName);

		if (whereStatement.value.length() > 0) {
			sb.append(" where " + whereStatement.value);
		}

		if (orderStatement.length() > 0) {
			sb.append(" order by " + orderStatement);
		}

		return sb.toString();
	}

}
