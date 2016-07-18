package com.abubusoft.kripton.processor.exceptions;

import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;

public class PropertyNotFoundException extends KriptonProcessorException {

	private static final long serialVersionUID = 8462705406839489618L;

	public PropertyNotFoundException(String msg)
	{
		super(msg);
	}
	
	public PropertyNotFoundException(SQLiteModelMethod method, String fieldName)
	{		
		super(String.format("in class '%s' method '%s' uses field '%s' that does not exists in bean '%s'", method.getParent().getName(), method.getName(), fieldName, method.getParent().getEntitySimplyClassName()));
	}
		
}
