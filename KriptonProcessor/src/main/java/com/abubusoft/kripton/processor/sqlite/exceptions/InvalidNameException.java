package com.abubusoft.kripton.processor.sqlite.exceptions;


public class InvalidNameException extends SQLiteProcessorException {

	private static final long serialVersionUID = 8462705406839489618L;

	public InvalidNameException(String msg)
	{
		super(msg);
	}
}
