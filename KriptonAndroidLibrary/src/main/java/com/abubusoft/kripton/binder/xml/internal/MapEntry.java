package com.abubusoft.kripton.binder.xml.internal;

import java.util.Map;

import com.abubusoft.kripton.binder.schema.ElementSchema.MapInfo;

/**
 * Rapresent a map entry for xml reader/writer. Contain key/value value pair and
 * clazz too.
 * 
 * @author xcesco
 *
 */
public interface MapEntry {
	
	/**
	 * getter of map info
	 * @return
	 */
	public MapInfo getMapInfo();

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
	public void set(Map map, MapInfo mapInfo);

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

	public boolean isKeyPresent();

}
