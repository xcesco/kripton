/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.processor.exceptions;

import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDaoDefinition;


/**
 * The Class IncompatibleAttributesInAnnotationException.
 */
public class IncompatibleAttributesInAnnotationException extends KriptonProcessorException {
 
	/**
	 * Instantiates a new incompatible attributes in annotation exception.
	 *
	 * @param msg the msg
	 */
	public IncompatibleAttributesInAnnotationException(String msg)
	{
		super(msg);
	}
	
	/**
	 * Instantiates a new incompatible attributes in annotation exception.
	 *
	 * @param daoDefinition the dao definition
	 * @param method the method
	 * @param annotation the annotation
	 * @param attribute1 the attribute 1
	 * @param attribute2 the attribute 2
	 */
	public IncompatibleAttributesInAnnotationException(SQLiteDaoDefinition daoDefinition, ModelMethod method, ModelAnnotation annotation, AnnotationAttributeType attribute1, AnnotationAttributeType attribute2)
	{
		super("In class '"+daoDefinition.getElement().getQualifiedName().toString()+"' method '"+method.getName()+"' has annotation @"+annotation.getSimpleName()+" with incompatible attributes '"+attribute1.getValue()+ "' and '"+attribute2.getValue()+"'. Remove one of those.");
	}
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8862365033337349246L;

}
