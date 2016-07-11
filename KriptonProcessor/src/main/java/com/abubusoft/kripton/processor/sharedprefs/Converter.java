package com.abubusoft.kripton.processor.sharedprefs;

import java.lang.reflect.Field;

import org.abubu.elio.Uncryptable;

/**
 * Interfaccia per adattare la configurazione in una preferenza e viceversa.
 * 
 * @author Francesco Benincasa
 * 
 * @param <P>
 *            parametro di preference
 * 
 * @param <C>
 *            parametro di config
 */
public interface Converter extends Uncryptable {

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
