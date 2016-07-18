package com.abubusoft.kripton.processor.exceptions;

import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;

public class PropertyInAnnotationNotFoundException extends KriptonProcessorException {

	private static final long serialVersionUID = 8462705406839489618L;

	public PropertyInAnnotationNotFoundException(SQLiteModelMethod method, String fieldName)
	{
		super(String.format("In class '%s', annotation of method '%s' uses field '%s' that does not exists in bean '%s'",  method.getParent().getName(), method.getName(), fieldName, method.getParent().getEntityClassName()));
	}
}
