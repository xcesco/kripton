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
import com.squareup.javapoet.TypeName;


/**
 * The Class PropertyNotFoundException.
 */
public class PropertyNotFoundException extends KriptonProcessorException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8462705406839489618L;

	/**
	 * Instantiates a new property not found exception.
	 *
	 * @param msg the msg
	 */
	public PropertyNotFoundException(String msg) {
		super(msg);
	}

	/**
	 * Instantiates a new property not found exception.
	 *
	 * @param method the method
	 * @param fieldName the field name
	 * @param typeName the type name
	 */
	public PropertyNotFoundException(SQLiteModelMethod method, String fieldName, TypeName typeName) {
		super(String.format("In dao '%s' method '%s' uses parameter '%s' (of type '%s') that does not exists in bean '%s'", method.getParent().getName(), method.getName(), fieldName,
				typeName, method.getParent().getEntitySimplyClassName()));
	}

}
