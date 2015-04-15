/**
 * 
 */
package com.abubusoft.kripton.binder.database;

/**
 * @author xcesco
 *
 */
public class QueryOptions {

	public String fields = "*";

	public String where;

	public String order;

	public Class<?> paramsClass;

	public String name;

	public QueryOptions where(String value) {
		where = value;
		return this;
	}

	public QueryOptions order(String value) {
		order = value;
		return this;
	}

	public QueryOptions select(String value) {
		fields = value;
		return this;
	}

	public QueryOptions paramsClass(Class<?> value) {
		paramsClass = value;
		return this;
	}

	public static QueryOptions build() {
		return new QueryOptions();
	}

	public QueryOptions name(String value) {
		name = value;
		return this;
	}

}
