package com.abubusoft.kripton.android.sharedprefs;

public interface ConfigStorage<E extends Config> {

	/**
	 * Restituisce la configurazione
	 * 
	 * @return
	 */
	E retrieveCurrentConfig();
	
}
