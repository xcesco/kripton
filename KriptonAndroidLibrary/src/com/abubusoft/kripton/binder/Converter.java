package com.abubusoft.kripton.binder;

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
public interface Converter {

	/**
	 * Data una configurazione, la converte in preference
	 * 
	 * @param configValue
	 * @param preferenceType
	 * @return
	 * 		
	 */
	public Object javaToDatabase(Object value);

	/**
	 * Data una preferenza, la converte nella relativa config
	 * 
	 * @param field
	 * @param preferenceValue
	 * @return
	 */
	public Object databaseToJava(Object value);
}
