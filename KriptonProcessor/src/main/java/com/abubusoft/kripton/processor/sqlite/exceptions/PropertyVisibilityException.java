package com.abubusoft.kripton.processor.sqlite.exceptions;

import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;

public class PropertyVisibilityException extends SQLiteProcessorException {

	private static final long serialVersionUID = 8462705406839489618L;

	public PropertyVisibilityException(String msg)
	{
		super(msg);
	}
	
}
