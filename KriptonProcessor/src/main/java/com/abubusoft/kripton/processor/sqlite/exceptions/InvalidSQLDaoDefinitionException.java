package com.abubusoft.kripton.processor.sqlite.exceptions;

import com.abubusoft.kripton.processor.sqlite.DaoDefinition;

public class InvalidSQLDaoDefinitionException extends SQLiteProcessorException {

	private static final long serialVersionUID = 8462705406839489618L;

	public InvalidSQLDaoDefinitionException(DaoDefinition daoDefinition)
	{
		super("In class "+daoDefinition.getName()+" is used @SQLDao annotation for unmanaged bean type "+daoDefinition.getEntityClassName()+". Please check if it has @BindType annotation.");
	}
}
