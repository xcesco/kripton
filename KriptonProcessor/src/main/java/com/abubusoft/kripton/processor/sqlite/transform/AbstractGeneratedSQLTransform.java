/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa (info@abubusoft.com).
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

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLColumnType;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

public class AbstractGeneratedSQLTransform extends AbstractSQLTransform {

	@Override
	public void generateWriteProperty2ContentValues(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property) {
		methodBuilder.addCode("$T.serialize$L($L)", TypeUtility.mergeTypeName(beanClass, "Table"), formatter.convert(property.getName()), getter(beanName, beanClass, property));
	}

	@Override
	public void generateWriteParam2ContentValues(Builder methodBuilder, SQLDaoDefinition sqlDaoDefinition, String paramName, TypeName paramTypeName) {
		String methodName = sqlDaoDefinition.generateJava2ContentSerializer(paramTypeName);
		
		methodBuilder.addCode("$L($L)", methodName, paramName);
	}
	
	@Override
	public void generateReadParamFromCursor(Builder methodBuilder, SQLDaoDefinition daoDefinition, TypeName paramTypeName, String cursorName, String indexName) {
		String methodName = daoDefinition.generateJava2ContentParser(paramTypeName);
		
		methodBuilder.addCode("$L($L.getBlob($L))", methodName, cursorName, indexName);
	}

	@Override
	public void generateReadPropertyFromCursor(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {
		methodBuilder.addCode(setter(beanClass, beanName, property, "$T.parse$L($L.getBlob($L))"), TypeUtility.mergeTypeName(beanClass, "Table"), formatter.convert(property.getName()), cursorName, indexName);
	}

	@Override
	public void generateResetProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {
		methodBuilder.addCode(setter(beanClass, beanName, property, "null"));
	}

	@Override
	public SQLColumnType getColumnType() {
		return SQLColumnType.BLOB;
	}
}
