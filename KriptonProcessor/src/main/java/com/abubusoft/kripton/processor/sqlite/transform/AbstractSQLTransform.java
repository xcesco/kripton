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
/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.exceptions.KriptonProcessorException;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public abstract class AbstractSQLTransform implements SQLTransform {

	protected static Converter<String, String> formatter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

	@Override
	public void generateWriteProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property) {
		methodBuilder.addCode("$L", getter(beanName, beanClass, property));
	}

	@Override
	public String getColumnTypeAsString() {
		return getColumnType().toString();
	}

	@Override
	public void generateWriteParam(Builder methodBuilder, SQLDaoDefinition sqlDaoDefinition, String paramName, TypeName paramTypeName) {
		methodBuilder.addCode("$L", paramName);
	}

	@Override
	public void generateReadParam(Builder methodBuilder, SQLDaoDefinition daoDefinition, TypeName paramTypeName, String cursorName, String indexName) {
		// except for supported result type, each transform does not need to
		// implements this method
		throw new KriptonProcessorException("Something went wrong!");
	}

	@Override
	public void generateDefaultValue(Builder methodBuilder) {
		methodBuilder.addCode("null");
	}

}
