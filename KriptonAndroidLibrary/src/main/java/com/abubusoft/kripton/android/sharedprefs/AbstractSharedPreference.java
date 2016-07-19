/**
 * 
 */
package com.abubusoft.kripton.android.sharedprefs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

public class AbstractSharedPreference {
	
	protected AbstractSharedPreference() {
		
	}

	public static final String STRING_ARRAY_SEPARATOR = ";##@@;";

	//protected static final DefaultConverter defaultConverter = new DefaultConverter();

	//protected HashMap<String, Converter> converterMap;

	protected SharedPreferences prefs;
		
	public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
		if (prefs != null)
			prefs.registerOnSharedPreferenceChangeListener(listener);
	}

	public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
		if (prefs != null)
			prefs.unregisterOnSharedPreferenceChangeListener(listener);
	}

	/*
	protected Object writePreference(String key, Object value, PreferenceType preferenceType) {
		Object result;
		if (converterMap.containsKey(key)) {
			result = converterMap.get(key).convertToPreference(value, preferenceType);
		} else {
			result = defaultConverter.convertToPreference(value, preferenceType);
		}

		return result;
	}*/

	/*
	protected Object readPreference(String key, Object value, PreferenceType preferenceType) {
		Object result = null;
		if (converterMap.containsKey(key)) {
			// result = converterMap.get(key).convertToConfig(value, preferenceType);
		} else {
			// result = defaultConverter.convertToConfig(value, preferenceType);
		}

		return result;
	}*/

	protected String list2String(List<String> array) {
		String result;
		String separator = "";
		StringBuilder buffer = new StringBuilder();
		for (String item : array) {
			if (item == null)
				continue;
			buffer.append(separator + item);
			separator = STRING_ARRAY_SEPARATOR;
		}
		result = buffer.toString();

		return result;
	}

	protected String array2String(String[] array) {
		String result;
		String separator = "";
		StringBuilder buffer = new StringBuilder();
		for (String item : array) {
			if (item == null)
				continue;
			buffer.append(separator + item);
			separator = STRING_ARRAY_SEPARATOR;
		}
		result = buffer.toString();

		return result;
	}

	protected List<String> string2list(String input, List<String> defaultValue) {
		if (input==null) return new ArrayList<String>(defaultValue);		
		
		String tempValues[] = input.split(STRING_ARRAY_SEPARATOR);
		ArrayList<String> values;
		values = new ArrayList<String>();
		// ripuliamo gli array dalle stringhe vuote
		for (String item : tempValues) {
			if (item != null && item.trim().length() > 0) {
				values.add(item);
			}
		}

		return values;
	}

	protected String[] string2array(String input, String[] defaultValue) {
		if (input==null) return Arrays.copyOf(defaultValue, defaultValue.length);
		
		String tempValues[] = input.split(STRING_ARRAY_SEPARATOR);
		List<String> values = new ArrayList<String>();

		// ripuliamo gli array dalle stringhe vuote
		for (String item : tempValues) {
			if (item != null && item.trim().length() > 0) {
				values.add(item);
			}
		}

		return values.toArray(new String[values.size()]);

	}

	public class AbstractEditor
	{
		protected AbstractEditor()
		{
			editor=prefs.edit();
		}
		
		protected final SharedPreferences.Editor editor;
		
		public void commit()
		{
			editor.commit();
		}
	}

}
