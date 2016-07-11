package com.abubusoft.kripton.android.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * <p>
 * Interfaccia di configurazione dell'applicazione.
 * </p>
 * 
 * @author Francesco Benincasa
 * 
 */
public interface Config {

	/**
	 * <p>
	 * Legge le preferenze dell'applicazione.
	 * <p>
	 */
	public void readPreferences(Context context);

	/**
	 * Legge una singola preferenza
	 * 
	 * @param context
	 * @param configKey
	 */
	public void readPreference(Context context, String configKey);

	/**
	 * Legge le preferenze e le converte in config
	 * 
	 * @param sharedPreference
	 */
	public void readPreferences(SharedPreferences sharedPreference);

	/**
	 * Legge una particolare config dalla preference
	 * 
	 * @param sharedPreference
	 */
	public void readPreference(String configKey, SharedPreferences sharedPreference);

	/**
	 * <p>
	 * Salva la configurazione sulle preference dell'applicazione.
	 * </p>
	 * 
	 */
	public void writePreferences(Context context);

	/**
	 * Salva la configurazione e la rende persistente nelle prefs.
	 * 
	 * @param sharedPreference
	 */
	public void writePreferences(SharedPreferences sharedPreference);

	/**
	 * Scrive una singola preferenza
	 * 
	 * @param context
	 * @param configKey
	 */
	public void writePreference(Context context, String configKey);

	/**
	 * Salva su preference la configurazione la cui chiave viene passata sottoforma di parametro.
	 * 
	 * @param configKey
	 * @param sharedPreference
	 */
	public void writePreference(String configKey, SharedPreferences sharedPreference);

	/**
	 * Ripristina i valori iniziali
	 */
	public void reset();

}
