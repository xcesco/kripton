package com.abubusoft.kripton.processor.sqlite.exceptions;

import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.sqlite.AnnotationAttributeType;
import com.abubusoft.kripton.processor.sqlite.DaoDefinition;

public class IncompatibleAttributesInAnnotationException extends SQLiteProcessorException {
 
	public IncompatibleAttributesInAnnotationException(DaoDefinition daoDefinition, ModelMethod method, ModelAnnotation annotation, AnnotationAttributeType attribute1, AnnotationAttributeType attribute2)
	{
		super("In class '"+daoDefinition.getElement().getQualifiedName().toString()+"' method '"+method.getName()+"' has annotation @"+annotation.getName()+" with incompatible attributes '"+attribute1.getValue()+ "' and '"+attribute2.getValue()+"'. Remove one of those.");
	}
	
	private static final long serialVersionUID = -8862365033337349246L;

}
