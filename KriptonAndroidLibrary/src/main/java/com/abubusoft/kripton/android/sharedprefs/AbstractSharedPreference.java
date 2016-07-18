/**
 * 
 */
package com.abubusoft.kripton.android.sharedprefs;

import java.util.HashMap;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderReader;
import com.abubusoft.kripton.BinderWriter;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

/**
 * @author xcesco
 *
 */
public class AbstractSharedPreference {

	protected AbstractSharedPreference() {
		jsonReader = BinderFactory.getJSONReader();
		jsonWriter = BinderFactory.getJSONWriter();
	}

	protected BinderWriter jsonWriter;

	protected BinderReader jsonReader;

	protected static final DefaultConverter defaultConverter = new DefaultConverter();

	protected HashMap<String, Converter> converterMap;

	protected SharedPreferences prefs;

	public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
		if (prefs != null)
			prefs.registerOnSharedPreferenceChangeListener(listener);
	}

	public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
		if (prefs != null)
			prefs.unregisterOnSharedPreferenceChangeListener(listener);
	}

	protected Object writePreference(String key, Object value, PreferenceType preferenceType) {
		Object result;
		if (converterMap.containsKey(key)) {
			result = converterMap.get(key).convertToPreference(value, preferenceType);
		} else {
			result = defaultConverter.convertToPreference(value, preferenceType);
		}

		return result;
	}

	protected Object readPreference(String key, Object value, PreferenceType preferenceType) {
		Object result = null;
		if (converterMap.containsKey(key)) {
			// result = converterMap.get(key).convertToConfig(value, preferenceType);
		} else {
			// result = defaultConverter.convertToConfig(value, preferenceType);
		}

		return result;
	}
	
	protected String array2String(Object[] array)
	{
		String result;
		String separator="";
		StringBuilder buffer=new StringBuilder();						
		for (Object item: array)
		{
			if (item==null) continue;
			buffer.append(separator+item);
			separator=ConfigBase.STRING_ARRAY_SEPARATOR;
		}
		result=buffer.toString();
		
		return result;
	}

}
