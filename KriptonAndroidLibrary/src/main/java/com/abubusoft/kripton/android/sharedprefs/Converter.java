package com.abubusoft.kripton.android.sharedprefs;

import java.lang.reflect.Field;

/**
 * Interface for shared preference converter to bean and viceversa.
 * 
 * @author xcesco
 * 
 */
public interface Converter {

	/**
	 * Data una configurazione, la converte in preference
	 * 
	 * @param configValue
	 * @param preferenceType
	 * @return
	 * 		
	 */
	public Object convertToPreference(Object configValue, PreferenceType preferenceType);

	/**
	 * Data una preferenza, la converte nella relativa config
	 * 
	 * @param field
	 * @param preferenceValue
	 * @return
	 */
	public Object convertToConfig(Field field, Object preferenceValue);
}
