/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite.exceptions;

/**
 * @author xcesco
 *
 */
public class NoBindTypeElementsFound extends SQLiteProcessorException {

	public NoBindTypeElementsFound() {
		super("No bean with @BindType annotation is present in to-compile sources");
	}

	private static final long serialVersionUID = 1L;

}
