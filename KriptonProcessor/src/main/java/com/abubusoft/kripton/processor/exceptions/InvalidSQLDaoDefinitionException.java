package com.abubusoft.kripton.processor.exceptions;

import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;

public class InvalidSQLDaoDefinitionException extends KriptonProcessorException {

	private static final long serialVersionUID = 8462705406839489618L;

	public InvalidSQLDaoDefinitionException(SQLDaoDefinition daoDefinition)
	{
		super("In class "+daoDefinition.getName()+" is used @SQLDao annotation for unmanaged bean type "+daoDefinition.getEntityClassName()+". Please check if it has @BindType annotation.");
	}
}
