package com.abubusoft.kripton.processor.sqlite.exceptions;

import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.sqlite.DaoDefinition;
import com.squareup.javapoet.TypeName;

public class InvalidReturnTypeException extends SQLiteProcessorException {

	private static final long serialVersionUID = -864898010888025423L;
	
	public InvalidReturnTypeException(DaoDefinition daoDefinition, ModelMethod method, TypeName foundReturnType, TypeName aspectedReturnType)
	{
		super("In class '"+daoDefinition.getName()+"' method '"+method.getName()+"'  has an invalid return type. Found '"+foundReturnType.toString()+"' aspected '"+aspectedReturnType.toString()+"'");
	}

}
