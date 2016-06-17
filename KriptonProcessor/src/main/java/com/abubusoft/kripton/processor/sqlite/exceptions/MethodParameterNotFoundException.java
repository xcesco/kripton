package com.abubusoft.kripton.processor.sqlite.exceptions;

import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;

public class MethodParameterNotFoundException extends SQLiteProcessorException {
 
	public MethodParameterNotFoundException(SQLiteModelMethod method, String paramName)
	{
		super(String.format("In class '%s', annotation of method '%s' uses method parameter '%s' that does not exists",  method.getParent().getName(), method.getName(), paramName));		
	}
	
	private static final long serialVersionUID = -8862365033337349246L;

}
