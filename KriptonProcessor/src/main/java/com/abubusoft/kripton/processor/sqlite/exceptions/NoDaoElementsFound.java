/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite.exceptions;

/**
 * @author xcesco
 *
 */
public class NoDaoElementsFound extends SQLiteProcessorException {

	public NoDaoElementsFound() {
		super("No bean with @Dao annotation is present in to-compile sources");
	}

	private static final long serialVersionUID = 1L;

}
