/**
 * 
 */
package com.abubusoft.kripton.binder.xml.internal;

import java.util.Map;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.binder.schema.ElementSchema.MapInfo;

/**
 * Map entry. It's a usefull implementation for xml
 * @author xcesco
 *
 */
@BindType
public class MapEntryImpl implements MapEntry {

	@Bind
	public Object key;
	
	public Map<?,?> map;
	
	protected MapInfo mapInfo;

	@Bind
	public Object value;

	public Class<?> valueClazz;

	@Override
	public void clear() {
		key = null;
		value = null;
		mapInfo=null;
		valueClazz=null;
		map=null;
	}

	@Override
	public Object getEntryKey() {
		return key;
	}

	@Override
	public Object getEntryValue() {
		return value;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map getMap() {
		return map;
	}

	@Override
	public boolean isEntryReady() {
		return key != null && value != null;
	}

	@Override
	public boolean isKey(String name) {
		return mapInfo.keyName.equals(name);
	}

	@Override
	public boolean isValue(String name) {
		return mapInfo.valueName.equals(name);
	}

	@Override
	public void setEntryKey(Object value) {
		key = value;

	}

	@Override
	public void setEntryValue(Object value) {
		this.value = value;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void set(Map map, MapInfo mapInfo) {
		this.map=map;
		this.mapInfo=mapInfo;
	}

	@Override
	public MapInfo getMapInfo() {
		return mapInfo;
	}

	@Override
	public boolean isKeyPresent() {
		return key != null;
	}


	
}
