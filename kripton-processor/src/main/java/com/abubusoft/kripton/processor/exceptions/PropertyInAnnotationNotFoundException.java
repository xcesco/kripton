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

import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;


/**
 * The Class PropertyInAnnotationNotFoundException.
 */
public class PropertyInAnnotationNotFoundException extends KriptonProcessorException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8462705406839489618L;

	/**
	 * Instantiates a new property in annotation not found exception.
	 *
	 * @param method the method
	 * @param fieldName the field name
	 */
	public PropertyInAnnotationNotFoundException(SQLiteModelMethod method, String fieldName) {
		super(String.format("In class '%s', annotation of method '%s' uses field '%s' that does not exists in bean '%s'", method.getParent().getName(), method.getName(), fieldName,
				method.getParent().getEntityClassName()));
	}

}
