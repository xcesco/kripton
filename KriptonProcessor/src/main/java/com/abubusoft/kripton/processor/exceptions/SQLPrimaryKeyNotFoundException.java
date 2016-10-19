package com.abubusoft.kripton.processor.exceptions;

import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;

public class SQLPrimaryKeyNotFoundException extends KriptonProcessorException {

	private static final long serialVersionUID = 8462705406839489618L;

	public SQLPrimaryKeyNotFoundException(SQLEntity entity)
	{
		super("Bean '"+entity.getName()+"' does not have a primary key");
	}
}
