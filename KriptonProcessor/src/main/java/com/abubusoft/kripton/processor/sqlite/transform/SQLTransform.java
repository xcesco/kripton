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
package com.abubusoft.kripton.processor.sqlite.transform;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLColumnType;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * 
 * Class implementing this interface can be used to generate code to read and
 * write the property
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public interface SQLTransform {

	/**
	 * Generate code to put into cursor, the bean property value
	 * 
	 * @param beanClass
	 */
	void generateReadPropertyFromCursor(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName);

	/**
	 * Used when you need to use a cursor column as select's result value. 
	 * 
	 * @param methodBuilder
	 * @param daoDefinition
	 * @param paramTypeName
	 * @param cursorName
	 * @param indexName
	 */
	void generateReadValueFromCursor(Builder methodBuilder, SQLDaoDefinition daoDefinition, TypeName paramTypeName, String cursorName, String indexName);

	/**
	 * Generate default value, null or 0 or ''
	 * 
	 * @param methodBuilder
	 */
	void generateDefaultValue(Builder methodBuilder);

	/**
	 * Write a bean property to a content writer
	 * 
	 * 
	 * @param methodBuilder
	 * @param property
	 *            property to write
	 * @param beanName
	 */
	void generateWriteProperty2ContentValues(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property);

	/**
	 * <p>
	 * Generate code to write parameter to content value element
	 * </p>
	 * 
	 * @param methodBuilder
	 * @param daoDefinition
	 *            TODO
	 * @param objectName
	 */
	void generateWriteParam2ContentValues(Builder methodBuilder, SQLDaoDefinition daoDefinition, String paramName, TypeName paramTypeName);

	/**
	 * Generate code to set property to null value or default value
	 * 
	 * @param methodBuilder
	 * @param property
	 * @param beanName
	 * @param cursorName
	 * @param indexName
	 */
	void generateResetProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName);

	/**
	 * Associated column type
	 * 
	 * @return column type as string
	 * 
	 */
	String getColumnTypeAsString();

	/**
	 * 
	 * @return column type
	 */
	SQLColumnType getColumnType();
	
	/**
	 * if true, transform can be used as convertion type in a type adapter.
	 * 
	 * @return
	 */
	boolean isTypeAdapterAware();

}
