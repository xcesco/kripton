package com.abubusoft.kripton.processor.sharedprefs;

public interface ConfigStorage<E extends Config> {

	/**
	 * Restituisce la configurazione
	 * 
	 * @return
	 */
	E retrieveCurrentConfig();
	
}
