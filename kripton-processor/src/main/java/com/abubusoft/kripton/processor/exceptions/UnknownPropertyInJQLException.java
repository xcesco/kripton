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

import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLContext;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;


/**
 * The Class UnknownPropertyInJQLException.
 */
public class UnknownPropertyInJQLException extends KriptonProcessorException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8462705406839489618L;

	/**
	 * Instantiates a new unknown property in JQL exception.
	 *
	 * @param method the method
	 * @param propertyName the property name
	 */
	public UnknownPropertyInJQLException(JQLContext method, String propertyName) {
		this(method, null, propertyName);
	}
	
	/**
	 * Instantiates a new unknown property in JQL exception.
	 *
	 * @param method the method
	 * @param className the class name
	 * @param propertyName the property name
	 */
	public UnknownPropertyInJQLException(JQLContext method, String className, String propertyName) {		
		super(String.format("In DAO '%s' in method '%s', unknown property '%s' is used", method.getParentName(), method.getName(), (StringUtils.hasText(className) ? className + "." : "")+propertyName));		
	}
	
	/**
	 * Instantiates a new unknown property in JQL exception.
	 *
	 * @param method the method
	 * @param annotation the annotation
	 * @param attribute the attribute
	 * @param propertyName the property name
	 */
	public UnknownPropertyInJQLException(SQLiteModelMethod method, Class<? extends Annotation> annotation, AnnotationAttributeType attribute, String propertyName) {
		super(String.format("In DAO '%s' in method '%s', unknown property '%s' is used in annotation '%s' attribute '%s'", method.getParent().getName(), method.getName(), propertyName, annotation.getSimpleName(), attribute));
	}

}
