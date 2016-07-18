package com.abubusoft.kripton.processor.exceptions;

import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;

public class InvalidMethodSignException extends KriptonProcessorException {

	private static final long serialVersionUID = 8462705406839489618L;

	public InvalidMethodSignException(SQLiteModelMethod method)
	{		
		super(String.format("In class '%s', method '%s' has an invalid signature", method.getParent().getName(), method.getName()));
	}
	
	public InvalidMethodSignException(SQLiteModelMethod method, String msg)
	{
		super(String.format("In class '%s', method '%s' has an invalid signature: %s", method.getParent().getName(), method.getName(), msg));		
	}
}
