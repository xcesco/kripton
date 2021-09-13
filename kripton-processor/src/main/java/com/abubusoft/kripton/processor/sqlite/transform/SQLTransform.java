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

import com.abubusoft.kripton.android.ColumnAffinityType;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;


/**
 * Class implementing this interface can be used to generate code to read and
 * write the property.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public interface SQLTransform {

	/**
	 * Generate code to put into cursor, the bean property value.
	 *
	 * @param tableEntity entity with table to target
	 * @param methodBuilder the method builder
	 * @param beanClass the bean class
	 * @param beanName the bean name
	 * @param property the property
	 * @param cursorName the cursor name
	 * @param indexName the index name
	 */
	void generateReadPropertyFromCursor(SQLiteEntity tableEntity, Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName);

	/**
	 * Used when you need to use a cursor column as select's result value. 
	 *
	 * @param methodBuilder the method builder
	 * @param daoDefinition the dao definition
	 * @param paramTypeName the param type name
	 * @param cursorName the cursor name
	 * @param indexName the index name
	 */
	void generateReadValueFromCursor(Builder methodBuilder, SQLiteDaoDefinition daoDefinition, TypeName paramTypeName, String cursorName, String indexName);

	/**
	 * Generate default value, null or 0 or ''.
	 *
	 * @param methodBuilder the method builder
	 */
	void generateDefaultValue(Builder methodBuilder);

	/**
	 * Write a bean property to a content writer.
	 *
	 * @param methodBuilder the method builder
	 * @param beanName the bean name
	 * @param beanClass the bean class
	 * @param property            property to write
	 */
	void generateWriteProperty2ContentValues(Builder methodBuilder, String beanName, TypeName beanClass, ModelProperty property);
	
	/**
	 * Write a bean property into a where condition.
	 *
	 * @param methodBuilder the method builder
	 * @param beanName the bean name
	 * @param beanClass the bean class
	 * @param property the property
	 */
	void generateWriteProperty2WhereCondition(Builder methodBuilder, String beanName, TypeName beanClass, ModelProperty property);

	/**
	 * <p>
	 * Generate code to write parameter to where condition
	 * </p>.
	 *
	 * @param methodBuilder the method builder
	 * @param method the method
	 * @param paramName the param name
	 * @param paramTypeName the param type name
	 */
	void generateWriteParam2WhereCondition(Builder methodBuilder, SQLiteModelMethod method, String paramName, TypeName paramTypeName);
	
	/**
	 * <p>Generate code to write parameter to where statement</p>.
	 *
	 * @param methodBuilder the method builder
	 * @param method the method
	 * @param paramName the param name
	 * @param paramType the param type
	 * @param property the property
	 */
	void generateWriteParam2ContentValues(Builder methodBuilder,  SQLiteModelMethod method, String paramName, TypeName paramType, ModelProperty property);

	/**
	 * Generate code to set property to null value or default value.
	 *
	 * @param methodBuilder the method builder
	 * @param beanClass the bean class
	 * @param beanName the bean name
	 * @param property the property
	 * @param cursorName the cursor name
	 * @param indexName the index name
	 */
	void generateResetProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName);

	/**
	 * Associated column type.
	 *
	 * @return column type as string
	 */
	String getColumnTypeAsString();

	/**
	 * Gets the column type.
	 *
	 * @return column type
	 */
	ColumnAffinityType getColumnType();
	
	/**
	 * if true, transform can be used as convertion type in a type adapter.
	 *
	 * @return true, if is type adapter aware
	 */
	boolean isTypeAdapterAware();

}
