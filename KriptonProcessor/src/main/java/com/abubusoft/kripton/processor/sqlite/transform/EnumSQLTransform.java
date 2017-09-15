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

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.common.TypeAdapterUtils;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLColumnType;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.MethodSpec.Builder;

/**
 * Transformer between a string and a Java5 Enum object
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 * 
 */
public class EnumSQLTransform extends AbstractSQLTransform {

	public EnumSQLTransform(TypeName typeName) {
		
	}
	
	@Override
	public void generateWriteProperty2ContentValues(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property) {
		// 	methodBuilder.addCode("$L.toString()", getter(beanName, beanClass, property));
		if (property.hasTypeAdapter()) {			
			methodBuilder.addCode(PRE_TYPE_ADAPTER_TO_DATA + "$L.toString()" + POST_TYPE_ADAPTER,TypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
		} else {
			methodBuilder.addCode("$L.toString()", getter(beanName, beanClass, property));
		}		
	}
	
	@Override
	public void generateWriteParam2ContentValues(Builder methodBuilder, SQLDaoDefinition sqlDaoDefinition, String paramName, TypeName paramTypeName) {
		methodBuilder.addCode("$L.toString()", paramName);		
	}

	
	@Override
	public void generateReadPropertyFromCursor(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {			
		methodBuilder.addCode(setter(beanClass, beanName, property, "$T.valueOf($L.getString($L))"), property.getPropertyType().getTypeName(),cursorName, indexName);
	}
	
	@Override
	public void generateReadValueFromCursor(Builder methodBuilder, SQLDaoDefinition daoDefinition, TypeName paramTypeName, String cursorName, String indexName) {
		methodBuilder.addCode("$L.getString($L)", cursorName, indexName);		
	}

	@Override
	public void generateDefaultValue(Builder methodBuilder)
	{
		methodBuilder.addCode("null");		
	}
	
	@Override
	public void generateResetProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property,  String cursorName, String indexName) {
		methodBuilder.addCode(setter(beanClass, beanName, property, "null"));
	}
	
	@Override
	public SQLColumnType getColumnType() {
		return SQLColumnType.TEXT;
	}
}