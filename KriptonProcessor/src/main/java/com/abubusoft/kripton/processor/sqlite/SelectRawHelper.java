/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
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

import javax.lang.model.util.Elements;

import com.abubusoft.kripton.processor.sqlite.AbstractSelectCodeGenerator.GenerationType;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 *
 * @since 17/mag/2016
 */
public class SelectRawHelper extends AbstractSelectCodeGenerator {

	public void generateCommonPart(Elements elementUtils, SQLiteModelMethod method, MethodSpec.Builder methodBuilder, PropertyList fieldList, boolean mapFields) {
		generateCommonPart(elementUtils, method, methodBuilder, fieldList, mapFields, GenerationType.NO_CLOSE_CURSOR);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.sqlite.SQLiteSelectBuilder.SelectCodeGenerator#generate(com.squareup.javapoet.MethodSpec.Builder)
	 */
	@Override
	public void generateSpecializedPart(Elements elementUtils, SQLiteModelMethod method, Builder methodBuilder, PropertyList fieldList, boolean mapFields) {		
		methodBuilder.addCode("return cursor;\n");
	}


}
