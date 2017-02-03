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

import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransform;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * Manage query with only one value
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 *
 * @since 17/mag/2016
 */
public class SelectScalarHelper extends AbstractSelectCodeGenerator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.sqlite.SQLiteSelectBuilder.
	 * SelectCodeGenerator#generate(com.squareup.javapoet.MethodSpec.Builder)
	 */
	@Override
	public void generateSpecializedPart(Elements elementUtils, SQLiteModelMethod method, Builder methodBuilder, PropertyList fieldList, boolean mapFields) {
		TypeMirror returnType = method.getReturnClass();
		TypeName returnTypeName = typeName(returnType);

		//ASSERT: returnType is a supported type
		
		// no column or too many columns
		AssertKripton.assertTrueOrInvalidMethodSignException(fieldList.value1.size() == 1, method, "only one field can be defined as result for this method");				

		SQLTransform t = SQLTransformer.lookup(returnType);

		methodBuilder.addCode("$T result=", returnType);
		t.generateDefaultValue(methodBuilder);
		methodBuilder.addCode(";\n");

		methodBuilder.addCode("\n");
		// methodBuilder.beginControlFlow("try");
		methodBuilder.beginControlFlow("if (cursor.moveToFirst())");

		// generate index from columns
		methodBuilder.addCode("\n");
		// methodBuilder.beginControlFlow("do\n");

		// methodBuilder.addCode("if (cursor.getString(0);\n");
		if (TypeUtility.isNullable(returnTypeName)) {
			methodBuilder.addCode("if (cursor.isNull(0)) { return null; }\n");
		} else {
			methodBuilder.addCode("if (cursor.isNull(0)) { return ");
			t.generateDefaultValue(methodBuilder);
			methodBuilder.addCode("; }\n", t);
		}
		methodBuilder.addCode("result=");
		t.generateReadParam(methodBuilder, method.getParent(), typeName(returnType), "cursor", "0");
		methodBuilder.addCode(";\n");

		// end cursor
		methodBuilder.endControlFlow();

		methodBuilder.addCode("return result;\n");
		// end method
		methodBuilder.endControlFlow();
	}

}
