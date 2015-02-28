package com.abubusoft.kripton.binder.exception;

/**
 * This exception indicates there is mapping error event the mapping schema is scanned.
 * 
 * @author bulldog
 *
 */
@SuppressWarnings("serial")
public class MappingException extends Exception {

	public MappingException() {
	}

	public MappingException(String arg0) {
		super(arg0);
	}

	public MappingException(Throwable arg0) {
		super(arg0);
	}

	public MappingException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
