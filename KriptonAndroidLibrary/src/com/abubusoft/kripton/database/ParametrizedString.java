package com.abubusoft.kripton.database;

public class ParametrizedString {
	public String value;

	public String[] params;
	
	public ThreadLocal<String[]> paramValues=new ThreadLocal<String[]>();
}