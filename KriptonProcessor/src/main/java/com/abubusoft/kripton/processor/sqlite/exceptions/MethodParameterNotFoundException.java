package com.abubusoft.kripton.processor.sqlite.exceptions;

import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;

public class MethodParameterNotFoundException extends SQLiteProcessorException {
 
	public MethodParameterNotFoundException(SQLDaoDefinition daoDefinition, ModelMethod method, String paramName)
	{
		super("In class '"+daoDefinition.getElement().getQualifiedName().toString()+"' method '"+method.getName()+"' has not a parameter '"+paramName+"'");
	}
	
	private static final long serialVersionUID = -8862365033337349246L;

}
