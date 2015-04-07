package com.abubusoft.kripton.database;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SQLStatementParams {

	public LinkedHashMap<String, SQLStatementParam> map = new LinkedHashMap<String, SQLStatementParam>();

	public ArrayList<SQLStatementParam> list = new ArrayList<SQLStatementParam>();

	boolean addParam(SQLStatementParam param) {
		if (!map.containsKey(param. field.getName())) {
			map.put(param.field.getName(), param);
			list.add(param);
			
			return true;
		}
		
		return false;
	}
	
	public SQLStatementParam get(String name)
	{
		return map.get(name);
	}

	boolean contains(String paramName) {
		return map.containsKey(paramName);
	}

}
