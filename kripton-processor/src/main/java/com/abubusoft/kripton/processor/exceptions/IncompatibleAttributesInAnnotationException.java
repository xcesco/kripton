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
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;

public class IncompatibleAttributesInAnnotationException extends KriptonProcessorException {
 
	public IncompatibleAttributesInAnnotationException(String msg)
	{
		super(msg);
	}
	
	public IncompatibleAttributesInAnnotationException(SQLDaoDefinition daoDefinition, ModelMethod method, ModelAnnotation annotation, AnnotationAttributeType attribute1, AnnotationAttributeType attribute2)
	{
		super("In class '"+daoDefinition.getElement().getQualifiedName().toString()+"' method '"+method.getName()+"' has annotation @"+annotation.getSimpleName()+" with incompatible attributes '"+attribute1.getValue()+ "' and '"+attribute2.getValue()+"'. Remove one of those.");
	}
	
	private static final long serialVersionUID = -8862365033337349246L;

}
