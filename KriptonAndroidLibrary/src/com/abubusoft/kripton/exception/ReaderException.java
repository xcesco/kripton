package com.abubusoft.kripton.exception;

/**
 * This exception indicates there is reading error at runtime.
 * 
 * @author bulldog
 *
 */
@SuppressWarnings("serial")
public class ReaderException extends Exception {
	public ReaderException() {
	}

	public ReaderException(String arg0) {
		super(arg0);
	}

	public ReaderException(Throwable arg0) {
		super(arg0);
	}

	public ReaderException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
