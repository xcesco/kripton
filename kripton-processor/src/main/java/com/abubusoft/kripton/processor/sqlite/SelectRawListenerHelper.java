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

import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLProjection;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 *
 * @since 17/mag/2016
 */
public class SelectRawListenerHelper extends AbstractSelectCodeGenerator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.sqlite.SQLiteSelectBuilder.SelectCodeGenerator#generate(com.squareup.javapoet.MethodSpec.Builder)
	 */
	@Override
	public void generateSpecializedPart(SQLiteModelMethod method, TypeSpec.Builder classBuilder, MethodSpec.Builder methodBuilder, Set<JQLProjection> fieldList, boolean mapFields) {
		//LiteralType listenerType=LiteralType.of(OnReadCursorListener.class);
		ClassName listenerType=ClassName.get(OnReadCursorListener.class);
		
		int counter = SqlBuilderHelper.countParameterOfType(method, listenerType);
		if (counter == 0) {
			// non listener found
			throw (new InvalidMethodSignException(method, "there is no parameter of type \"ReadCursorListener\""));
		}
		if (counter > 1) {
			// more than one listener found
			throw (new InvalidMethodSignException(method, "there are more than one parameter of type \"ReadCursorListener\""));
		}

		String listenerName = SqlSelectBuilder.getNameParameterOfType(method, listenerType);

		methodBuilder.addCode("\n");
		methodBuilder.beginControlFlow("if (_cursor.moveToFirst())");

		// generate index from columns
		methodBuilder.addCode("\n");

		methodBuilder.beginControlFlow("do\n");
		methodBuilder.addCode("$L.onRead(_cursor);\n", listenerName);
		methodBuilder.endControlFlow("while (_cursor.moveToNext())");

		// close cursor
		methodBuilder.endControlFlow();
		methodBuilder.endControlFlow();

	}

}
