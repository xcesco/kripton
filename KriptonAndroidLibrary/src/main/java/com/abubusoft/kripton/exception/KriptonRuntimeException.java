package com.abubusoft.kripton.exception;

/**
 * This exception is a generic kripton exception. 
 * 
 * @author xcesco
 *
 */
public class KriptonRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 7993865639459660322L;

	public KriptonRuntimeException() {
	}

	public KriptonRuntimeException(String arg0) {
		super(arg0);
	}

	public KriptonRuntimeException(Throwable arg0) {
		super(arg0);
	}

	public KriptonRuntimeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
