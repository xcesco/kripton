/**
 * 
 */
package com.abubusoft.kripton.processor.exceptions;

/**
 * @author xcesco
 *
 */
public class NoBindTypeElementsFound extends KriptonProcessorException {

	public NoBindTypeElementsFound() {
		super("No bean with @BindType annotation is present in to-compile sources");
	}

	private static final long serialVersionUID = 1L;

}
