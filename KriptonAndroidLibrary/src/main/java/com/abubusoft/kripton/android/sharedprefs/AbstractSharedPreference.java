/**
 * 
 */
package com.abubusoft.kripton.android.sharedprefs;

import java.util.HashMap;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

/**
 * @author xcesco
 *
 */
public class AbstractSharedPreference {

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
		Object result;
		if (converterMap.containsKey(key)) {
			result = converterMap.get(key).convertToConfig(value, preferenceType);
		} else {
			result = defaultConverter.convertToConfig(value, preferenceType);
		}

		return result;
	}
}
