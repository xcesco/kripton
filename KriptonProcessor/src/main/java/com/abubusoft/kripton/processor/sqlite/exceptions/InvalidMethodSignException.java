package com.abubusoft.kripton.processor.sqlite.exceptions;

import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;

public class InvalidMethodSignException extends SQLiteProcessorException {

	private static final long serialVersionUID = 8462705406839489618L;

	public InvalidMethodSignException(SQLDaoDefinition daoDefinition, ModelMethod method)
	{
		super("Method '"+method.getName()+"' of class '"+daoDefinition.getName()+"' has an invalid signature");
	}
	
	public InvalidMethodSignException(SQLDaoDefinition daoDefinition, ModelMethod method, String msg)
	{
		super("Method '"+method.getName()+"' of class '"+daoDefinition.getName()+"' has an invalid signature: "+msg);
	}
}
