/**
 * 
 */
package com.abubusoft.kripton.database;

/**
 * @author xcesco
 *
 */
public class Query  {
	
	public String name;
	
	protected QueryParams params=new QueryParams();

	public DatabaseColumn[] columns;

	public ParametrizedString where;
	
	public ParametrizedString order;

}
