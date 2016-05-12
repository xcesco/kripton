package com.abubusoft.kripton.processor.sqlite.exceptions;

import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;

public class TooManySQLPrimaryKeyFoundException extends Exception {
	private static final long serialVersionUID = 8462705406839489618L;

	public TooManySQLPrimaryKeyFoundException(SQLEntity entity)
	{
		super("Bean '"+entity.getName()+"' have too many primary keys");
	}
}
