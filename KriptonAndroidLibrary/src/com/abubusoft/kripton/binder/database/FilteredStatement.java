package com.abubusoft.kripton.binder.database;

public abstract class FilteredStatement extends Statement {
	
	public static final String DEFAULT_BY_ID="defaultById";
	
	/**
	 * filter to apply to sql.
	 */
	public Filter filter;

}
