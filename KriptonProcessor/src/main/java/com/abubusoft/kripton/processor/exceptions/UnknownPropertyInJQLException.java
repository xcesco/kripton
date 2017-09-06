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

import java.lang.annotation.Annotation;

import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;

public class UnknownPropertyInJQLException extends KriptonProcessorException {

	private static final long serialVersionUID = 8462705406839489618L;

	public UnknownPropertyInJQLException(SQLiteModelMethod method, String propertyName) {
		super(String.format("In DAO '%s' method '%s' unknown property '%s' is used", method.getParent().getName(), method.getName(), propertyName));
	}
	
	public UnknownPropertyInJQLException(SQLiteModelMethod method, Class<? extends Annotation> annotation, AnnotationAttributeType attribute, String propertyName) {
		super(String.format("In DAO '%s' method '%s' unknown property '%s' is used in annotation '%s' attribute '%s'", method.getParent().getName(), method.getName(), propertyName, annotation.getSimpleName(), attribute));
	}

}
