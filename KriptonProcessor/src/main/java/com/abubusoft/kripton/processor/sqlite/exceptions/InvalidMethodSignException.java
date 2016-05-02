package com.abubusoft.kripton.processor.sqlite.exceptions;

import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.sqlite.DaoDefinition;

public class InvalidMethodSignException extends SQLiteProcessorException {

	private static final long serialVersionUID = 8462705406839489618L;

	public InvalidMethodSignException(DaoDefinition daoDefinition, ModelMethod method)
	{
		super("Method '"+method.getName()+"' of class '"+daoDefinition.getName()+"' has an invalid signature");
	}
}
