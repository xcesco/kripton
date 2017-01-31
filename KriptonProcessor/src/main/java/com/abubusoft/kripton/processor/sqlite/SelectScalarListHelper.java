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

import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransform;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.abubusoft.kripton.processor.utils.LiteralType;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 *
 * @since 17/mag/2016
 */
public class SelectScalarListHelper extends AbstractSelectCodeGenerator {

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

		// return type is already checked
		if (fieldList.value1.size() == 0) {
			// no projection
			throw (new InvalidMethodSignException(method, "no column was selected"));
		} else if (fieldList.value1.size() > 1) {
			// too many values
			throw (new InvalidMethodSignException(method, "only one column can be defined for this kind of method"));
		}

		ParameterizedTypeName returnListName = (ParameterizedTypeName) returnTypeName;

		TypeName collectionClass;
		ClassName listClazzName = returnListName.rawType;
		TypeName elementName = returnListName.typeArguments.get(0);

		collectionClass = SelectBeanListHelper.defineCollection(listClazzName);

		methodBuilder.addCode("\n");
		methodBuilder.addCode("$T<$T> resultList=new $T<$T>();\n", collectionClass, elementName, collectionClass, elementName);
		methodBuilder.addCode("\n");

		LiteralType literalReturn = LiteralType.of(returnType.toString());

		SQLTransform t;
		if (!literalReturn.isList())
			t = SQLTransformer.lookup(returnType);
		else {
			t = SQLTransformer.lookup(typeName(literalReturn.getComposedValue()));
		}

		//@formatter:off
		methodBuilder.addCode("\n");
			methodBuilder.beginControlFlow("if (cursor.moveToFirst())");
				methodBuilder.addCode("\n");
				methodBuilder.beginControlFlow("do\n");
					methodBuilder.beginControlFlow("if (!cursor.isNull(0))");
						methodBuilder.addCode("resultList.add(");
						t.generateReadParam(methodBuilder, method.getParent(), typeName(returnType), "cursor", "0");
						methodBuilder.addCode(");\n");
					methodBuilder.nextControlFlow("else");
						methodBuilder.addCode("resultList.add(null);\n");		
					methodBuilder.endControlFlow();
				methodBuilder.endControlFlow("while (cursor.moveToNext())");
			methodBuilder.endControlFlow();
			methodBuilder.addCode("return resultList;\n");
		methodBuilder.endControlFlow();
		//@formatter:on

	}

}
