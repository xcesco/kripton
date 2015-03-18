package com.abubusoft.kripton.binder.schema;

import java.util.Map;

public interface MapStrategy {

	Class<?> getKeyType();

	Class<?> getValueType();

	boolean isKey(String name);

	boolean isValue(String name);

	void setEntryKey(Object value);

	void setEntryValue(Object value);
	
	Object getEntryKey();

	Object getEntryValue();
	
	boolean isEntryReady();
	
	@SuppressWarnings("rawtypes")
	public void setMap(Map map);
	
	@SuppressWarnings("rawtypes")
	public Map getMap();

	void clear();

}
