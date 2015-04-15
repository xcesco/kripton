package com.abubusoft.kripton.binder.database;
/**
 * type of parameter
 */
public enum FilterOriginType  
{
	/**
	 * retrieve params from field of bean mastered from table 
	 */
	BEAN,
	
	/**
	 * no parameters 
	 */
	NONE,
	
	/**
	 * there is only one parameter.
	 */
	ONE_PARAM,
	
	/**
	 * retrieve params from params bean passed to update
	 */
	PARAMS
};