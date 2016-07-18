package com.abubusoft.kripton.processor.exceptions;

import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.squareup.javapoet.TypeName;

public class InvalidReturnTypeException extends KriptonProcessorException {

	private static final long serialVersionUID = -864898010888025423L;
	
	public InvalidReturnTypeException(SQLDaoDefinition daoDefinition, ModelMethod method, TypeName foundReturnType, TypeName aspectedReturnType)
	{
		super("In class '"+daoDefinition.getName()+"' method '"+method.getName()+"'  has an invalid return type. Found '"+foundReturnType.toString()+"' aspected '"+aspectedReturnType.toString()+"'");
	}

}
