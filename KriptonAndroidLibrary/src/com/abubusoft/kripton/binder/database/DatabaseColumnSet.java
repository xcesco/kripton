/**
 * 
 */
package com.abubusoft.kripton.binder.database;

import java.util.ArrayList;

/**
 * @author xcesco
 *
 */
public class DatabaseColumnSet {
	
	public String name;

	public ArrayList<DatabaseColumn> columns=new ArrayList<DatabaseColumn>();

	public ParametrizedString where;
	
	public ParametrizedString order;
}
