package com.abubusoft.kripton.exception;

/**
 * This exception indicates there is reading error at runtime.
 * 
 * @author bulldog
 *
 */
@SuppressWarnings("serial")
public class WriterException extends Exception {
	public WriterException() {
	}

	public WriterException(String arg0) {
		super(arg0);
	}

	public WriterException(Throwable arg0) {
		super(arg0);
	}

	public WriterException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
