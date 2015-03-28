package com.abubusoft.kripton.exception;

/**
 * This exception indicates there is mapping error event the mapping schema is scanned.
 * 
 * @author bulldog
 *
 */
public class MappingException extends RuntimeException {

	private static final long serialVersionUID = 7993865639459660322L;

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
