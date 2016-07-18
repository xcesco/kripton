package com.abubusoft.kripton.processor.exceptions;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;

public class SQLPrimaryKeyNotValidTypeException extends KriptonProcessorException {

	private static final long serialVersionUID = 8462705406839489618L;

	public SQLPrimaryKeyNotValidTypeException(SQLEntity entity, ModelProperty property)
	{
		super("Bean '"+entity.getName()+"' have "+property.getName()+" as primary key but it is not Long or long type field");
	}
}
