package com.abubusoft.kripton.database;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class QueryParams {

	public LinkedHashMap<String, QueryParam> map = new LinkedHashMap<String, QueryParam>();

	public ArrayList<QueryParam> list = new ArrayList<QueryParam>();

	boolean addParam(QueryParam param) {
		if (!map.containsKey(param.name)) {
			map.put(param.name, param);
			list.add(param);
			
			return true;
		}
		
		return false;
	}
	
	public QueryParam get(String name)
	{
		return map.get(name);
	}

	boolean contains(String paramName) {
		return map.containsKey(paramName);
	}

}
