package com.abubusoft.kripton.processor.sqlite.exceptions;

import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;

public class PropertyInAnnotationNotFoundException extends SQLiteProcessorException {

	private static final long serialVersionUID = 8462705406839489618L;

	public PropertyInAnnotationNotFoundException(SQLDaoDefinition daoDefinition, ModelMethod method, String fieldName)
	{
		super("Annotation of method '"+method.getName()+"' of class '"+daoDefinition.getName()+"' uses field '"+fieldName+"' that does not exists in bean '"+daoDefinition.getEntityClassName()+"'");
	}
}
