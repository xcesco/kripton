/**
 * 
 */
package com.abubusoft.kripton.binder.database;

/**
 * @author xcesco
 *
 */
public class Query {
	
	public String name;

	public DatabaseColumn[] columns;

	public ParametrizedString where;
	
	public ParametrizedString order;
}
