package com.abubusoft.kripton.database;
/**
 * type of parameter
 */
public enum FilterOriginType  
{
	/**
	 * retrieve params from params bean passed to update
	 */
	PARAMS,
	/**
	 * retrieve params from field of bean mastered from table 
	 */
	BEAN
};