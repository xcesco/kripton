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
package com.abubusoft.kripton.processor.sqlite;

import java.util.Set;

import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLProjection;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;


/**
 * The Class SelectRawHelper.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 * @since 17/mag/2016
 */
public class SelectRawHelper extends AbstractSelectCodeGenerator {

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.sqlite.AbstractSelectCodeGenerator#generateCommonPart(com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod, com.squareup.javapoet.TypeSpec.Builder, com.squareup.javapoet.MethodSpec.Builder, java.util.Set, boolean)
	 */
	public void generateCommonPart(SQLiteModelMethod method, TypeSpec.Builder classBuilder, MethodSpec.Builder methodBuilder, Set<JQLProjection> fieldList, boolean mapFields) {
		generateCommonPart(method, classBuilder, methodBuilder, fieldList, mapFields, GenerationType.NO_CLOSE_CURSOR, null, true);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.sqlite.SQLiteSelectBuilder.SelectCodeGenerator#generate(com.squareup.javapoet.MethodSpec.Builder)
	 */
	@Override
	public void generateSpecializedPart(SQLiteModelMethod method, TypeSpec.Builder classBuilder, MethodSpec.Builder methodBuilder, Set<JQLProjection> fieldList, boolean mapFields) {		
		methodBuilder.addCode("return _cursor;\n");
		methodBuilder.endControlFlow();
	}


}
