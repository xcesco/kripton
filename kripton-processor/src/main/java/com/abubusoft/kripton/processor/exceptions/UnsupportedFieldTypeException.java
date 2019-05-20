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

import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.TypeName;

// TODO: Auto-generated Javadoc
/**
 * The Class UnsupportedFieldTypeException.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class UnsupportedFieldTypeException extends KriptonProcessorException {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8165503181899906671L;

	/**
	 * Instantiates a new unsupported field type exception.
	 *
	 * @param method the method
	 * @param typeName the type name
	 */
	public UnsupportedFieldTypeException(SQLiteModelMethod method, TypeName typeName) {
		super(String.format("In DAO '%s' method '%s' unsupported type '%s' is used", method.getParent().getName(), method.getName(), typeName));
	}
	
	/**
	 * Instantiates a new unsupported field type exception.
	 *
	 * @param typeName the type name
	 */
	public UnsupportedFieldTypeException(TypeName typeName) {
		super(String.format("Unsupported type '%s' is used", typeName));
	}
	
	public UnsupportedFieldTypeException(String description) {
		super(description);
	}
	
	/*public UnsupportedFieldTypeException(PropertyTypeName typeName) {
		super(String.format("Unsupported type '%s' is used", typeName));
	}*/

	public static UnsupportedFieldTypeException merge(UnsupportedFieldTypeException e, BindProperty property) {
		return new UnsupportedFieldTypeException(String.format("In POJO '%s' field '%s' is defined as unsupported type '%s'", property.getParent().getElement().asType(), property.getName(), property.getPropertyType().getTypeName()));
	}
}
