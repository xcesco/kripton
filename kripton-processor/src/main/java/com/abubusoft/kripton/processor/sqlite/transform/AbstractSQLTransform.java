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

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.exceptions.KriptonProcessorException;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public abstract class AbstractSQLTransform implements SQLTransform {

	protected static final String PRE_TYPE_ADAPTER_TO_JAVA = "$LAdapter.toJava(";

	protected static final String PRE_TYPE_ADAPTER_TO_DATA = "$T.toData($T.class, ";

	protected static final String PRE_TYPE_ADAPTER_TO_STRING = "$T.toString($T.class, ";

	protected static final String POST_TYPE_ADAPTER = ")";

	protected static Converter<String, String> formatter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

	@Override
	public void generateWriteProperty2WhereCondition(Builder methodBuilder, String beanName, TypeName beanClass, ModelProperty property) {
		generateWriteProperty2ContentValues(methodBuilder, beanName, beanClass, property);
	}

	@Override
	public void generateWriteParam2ContentValues(Builder methodBuilder, SQLiteModelMethod method, String paramName, TypeName paramTypeName, ModelProperty property) {
		methodBuilder.addCode("$L", paramName);
	}

	@Override
	public String getColumnTypeAsString() {
		return getColumnType().toString();
	}

	@Override
	public void generateReadValueFromCursor(Builder methodBuilder, SQLiteDaoDefinition daoDefinition, TypeName paramTypeName, String cursorName, String indexName) {
		// except for supported result type, each transform does not need to
		// implements this method
		throw new KriptonProcessorException("Something went wrong!");
	}

	@Override
	public void generateDefaultValue(Builder methodBuilder) {
		methodBuilder.addCode("null");
	}

	@Override
	public boolean isTypeAdapterAware() {
		return false;
	}
}
