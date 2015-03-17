/**
 * 
 */
package com.abubusoft.kripton.binder.xml;


import java.util.Map;

import com.abubusoft.kripton.binder.schema.ElementSchema.MapInfo;
import com.abubusoft.kripton.common.Pair;

/**
 * @author xcesco
 *
 */
public class MapEntry extends Pair<Object, Object> {
	public void setKey(Object value) {
		this.value0 = value;
		this.definedKey = true;
	}

	public void setValue(Object value) {
		this.value1 = value;
		this.definedValue = false;
	}

	boolean definedKey;
	
	boolean definedValue;

	public Map map;

	public MapInfo mapInfo;

	public boolean isReady() {
		return definedKey && definedValue;
	}

	public void clearEntry() {
		value0=null;
		value1=null;
		
		definedKey=false;
		definedValue=false;
		
	}

	

}
