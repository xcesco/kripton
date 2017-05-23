package com.abubusoft.kripton.processor.sqlite.grammar;

public class JQLMethod {

	/**
	 * JQL statement
	 */
	public String jql;
	
	/**
	 * <code>true</code> if method's annotation contains <code>where</code> attribute
	 */
	public boolean annotatedWhere;
	
	/**
	 * <code>true</code> if method's annotation contains <code>where</code> attribute
	 */
	public boolean annotatedGroupBy;
	
	/**
	 * <code>true</code> if method's annotation contains <code>where</code> attribute
	 */
	public boolean annotatedHaving;
	
	/**
	 * <code>true</code> if method's annotation contains <code>where</code> attribute
	 */
	public boolean annotatedOrderBy;
	
	/**
	 * <code>true</code> if method's annotation contains <code>where</code> attribute
	 */
	public boolean annotatedPageSize;
	
	public String paramReadBeanListener;
	
	public String paramReadBeanCursor;
	
	/**
	 * parameter's name of managed type of the dao
	 */
	public String paramBean;
	
	/**
	 * parameter's name with {@link BindD
	 */
	public String paramWhere;
	
	public String paramWhereArgs;
	
	public String paramPageSize;
	
	public String paramOrderBy;	
	
	public boolean hasParamReadBeanListener() { return paramReadBeanListener!=null; }
	
	public boolean hasParamReadBeanCursor() { return paramReadBeanCursor!=null; }
	
	public boolean hasParamBean() { return paramBean!=null; }
	
	public boolean hasParamWhere() { return paramWhere!=null; }
	
	public boolean hasParamWhereArgs() { return paramWhereArgs!=null; }
	
	public boolean hasParamPageSize() { return paramPageSize!=null; }
	
	public boolean hasParamOrderBy() { return paramOrderBy!=null; }
			
}
