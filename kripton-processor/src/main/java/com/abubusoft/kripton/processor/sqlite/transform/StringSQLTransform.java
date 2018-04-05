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

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteColumnType;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDaoDefinition;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

// TODO: Auto-generated Javadoc
/**
 * Transformer between a string and a Java String object.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class StringSQLTransform  extends TypeAdapterAwareSQLTransform {
	
	/** The read from cursor. */
	protected String READ_FROM_CURSOR=null;
	
	
	/**
	 * Instantiates a new string SQL transform.
	 */
	public StringSQLTransform() {
		
		WRITE_COSTANT = "";
		READ_FROM_CURSOR="$L.getString($L)";
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.sqlite.transform.SQLTransform#generateReadPropertyFromCursor(com.squareup.javapoet.MethodSpec.Builder, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.core.ModelProperty, java.lang.String, java.lang.String)
	 */
	@Override
	public void generateReadPropertyFromCursor(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {				
		if (property.hasTypeAdapter()) {			
			methodBuilder.addCode(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA+READ_FROM_CURSOR+POST_TYPE_ADAPTER),property.getName(), cursorName, indexName);
		} else {			
			methodBuilder.addCode(setter(beanClass, beanName, property, READ_FROM_CURSOR), cursorName, indexName);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.sqlite.transform.AbstractSQLTransform#generateReadValueFromCursor(com.squareup.javapoet.MethodSpec.Builder, com.abubusoft.kripton.processor.sqlite.model.SQLiteDaoDefinition, com.squareup.javapoet.TypeName, java.lang.String, java.lang.String)
	 */
	@Override
	public void generateReadValueFromCursor(Builder methodBuilder, SQLiteDaoDefinition daoDefinition, TypeName paramTypeName, String cursorName, String indexName) {
		methodBuilder.addCode(READ_FROM_CURSOR, cursorName, indexName);		
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.sqlite.transform.SQLTransform#generateResetProperty(com.squareup.javapoet.MethodSpec.Builder, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.core.ModelProperty, java.lang.String, java.lang.String)
	 */
	@Override
	public void generateResetProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property,  String cursorName, String indexName) {
		methodBuilder.addCode(setter(beanClass, beanName, property, "null"));
	}
	
	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.sqlite.transform.SQLTransform#getColumnType()
	 */
	@Override
	public SQLiteColumnType getColumnType() {
		return SQLiteColumnType.TEXT;
	}
	
	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.sqlite.transform.AbstractSQLTransform#generateDefaultValue(com.squareup.javapoet.MethodSpec.Builder)
	 */
	@Override
	public void generateDefaultValue(Builder methodBuilder)
	{
		methodBuilder.addCode("null");		
	}
	
	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.sqlite.transform.AbstractSQLTransform#isTypeAdapterAware()
	 */
	@Override
	public boolean isTypeAdapterAware() {		
		return true;
	}

}
