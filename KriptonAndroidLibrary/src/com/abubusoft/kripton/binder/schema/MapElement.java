/**
 * 
 */
package com.abubusoft.kripton.binder.schema;

import java.util.Map;

import com.abubusoft.kripton.annotation.BindElement;
import com.abubusoft.kripton.annotation.BindRoot;

/**
 * @author xcesco
 *
 */
@BindRoot
public class MapElement implements MapStrategy {

	@BindElement
	public Object key;
	
	public Class<?> keyClazz;

	public Map<?,?> map;

	@BindElement
	public Object value;

	public Class<?> valueClazz;

	@Override
	public void clear() {
		key = null;
		value = null;
		keyClazz=null;
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

	@Override
	public Class<?> getKeyType() {
		return keyClazz;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map getMap() {
		return map;
	}

	@Override
	public Class<?> getValueType() {
		return valueClazz;
	}

	@Override
	public boolean isEntryReady() {
		return key != null && value != null;
	}

	@Override
	public boolean isKey(String name) {
		return "key".equals(name);
	}

	@Override
	public boolean isValue(String name) {
		return "value".equals(name);
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
	public void setMap(Map map) {
		this.map=map;
	}
	
}
