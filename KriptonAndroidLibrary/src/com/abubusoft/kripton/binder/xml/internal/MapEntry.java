package com.abubusoft.kripton.binder.xml.internal;

import java.util.Map;

/**
 * Rapresent a map entry for xml reader/writer. Contain key/value value pair and
 * clazz too.
 * 
 * @author xcesco
 *
 */
public interface MapEntry {

	/**
	 * getter of key type
	 * 
	 * @return
	 */
	Class<?> getKeyType();

	/**
	 * getter of value type
	 * 
	 * @return
	 */
	Class<?> getValueType();

	/**
	 * given a attribute/tag name, return true if it is the name of
	 * attribute/tag name.
	 * 
	 * @param name
	 * @return
	 * 
	 */
	boolean isKey(String name);

	/**
	 * given a attribute/tag name, return true if it is the name of
	 * attribute/tag value.
	 * 
	 * @param name
	 * @return
	 */
	boolean isValue(String name);

	/**
	 * setter of key
	 * 
	 * @param value
	 */
	void setEntryKey(Object value);

	/**
	 * setter of value
	 * 
	 * @param value
	 */
	void setEntryValue(Object value);

	/**
	 * getter of key
	 * 
	 * @return
	 */
	Object getEntryKey();

	/**
	 * getter of value
	 * 
	 * @return
	 */
	Object getEntryValue();

	/**
	 * 
	 * @return true if there both key and value
	 */
	boolean isEntryReady();

	/**
	 * setter of associated map
	 * @param map
	 */
	@SuppressWarnings("rawtypes")
	public void setMap(Map map);

	/**
	 * getter of associated map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getMap();

	/**
	 * clear map, key and value
	 */
	void clear();

}
