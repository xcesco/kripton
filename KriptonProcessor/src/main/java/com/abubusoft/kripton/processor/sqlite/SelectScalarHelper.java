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

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.SqlSelectBuilder.SelectCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransform;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

/**
 * Manage query with only one value
 * 
 * @author xcesco
 *
 *
 * @since 17/mag/2016
 */
public class SelectScalarHelper implements SelectCodeGenerator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.sqlite.SQLiteSelectBuilder.SelectCodeGenerator#generate(com.squareup.javapoet.MethodSpec.Builder)
	 */
	@Override
	public void generate(Elements elementUtils, PropertyList fieldList, MethodSpec.Builder methodBuilder, boolean mapFields, SQLiteModelMethod method, TypeMirror returnType) {
		TypeName returnTypeName=typeName(returnType);
		if (TypeUtility.isTypePrimitive(returnTypeName) || TypeUtility.isTypeWrappedPrimitive(returnTypeName) || TypeUtility.isTypeIncludedIn(returnTypeName, String.class) || TypeUtility.isByteArray(returnTypeName)) {

		} else {
			// error return type
			throw (new InvalidMethodSignException(method, "Invalid return type"));
		}
		
		if (fieldList.value1.size() == 0) {
			// no projection
			throw (new InvalidMethodSignException(method, "No column was selected"));
		} else if (fieldList.value1.size() > 1) {
			// too many values
			if (fieldList.explicitDefinition)
				throw (new InvalidMethodSignException(method, "Only one column can be defined for this kind of method"));
			else
				throw (new InvalidMethodSignException(method, "No column was selected for query defined with method"));
		}
		
		SQLTransform t = SQLTransformer.lookup(returnType);

		methodBuilder.addCode("$T result=",returnType);
		t.generateDefaultValue(methodBuilder);
		methodBuilder.addCode(";\n");
		
		methodBuilder.addCode("\n");
		methodBuilder.beginControlFlow("try");
		methodBuilder.beginControlFlow("if (cursor.moveToFirst())");

		// generate index from columns
		methodBuilder.addCode("\n");
		//methodBuilder.beginControlFlow("do\n");
		
		//methodBuilder.addCode("if (cursor.getString(0);\n");
		if (TypeUtility.isNullable(returnTypeName)) {
			methodBuilder.addCode("if (cursor.isNull(0)) { return null; }\n");
		} else  {
			methodBuilder.addCode("if (cursor.isNull(0)) { return ");
			t.generateDefaultValue(methodBuilder);			
			methodBuilder.addCode("; }\n", t);
		}
		methodBuilder.addCode("result=");
		t.generateReadParam(methodBuilder, method.getParent(), typeName(returnType), "cursor", "0");
		methodBuilder.addCode(";\n");

		//methodBuilder.endControlFlow("while (cursor.moveToNext())");

		methodBuilder.endControlFlow();
		methodBuilder.nextControlFlow("finally");		
		methodBuilder.beginControlFlow("if (!cursor.isClosed())");
		methodBuilder.addCode("cursor.close();\n");
		methodBuilder.endControlFlow();
		methodBuilder.endControlFlow();
		
		methodBuilder.addCode("return result;\n");
	}

}
