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
 * 
 */
package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.abubusoft.kripton.android.sqlite.SQLTypeAdapterUtils;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLProjection;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 * @param <ElementUtils>
 * 
 *
 * @since 17/mag/2016
 */
public class SelectBeanListHelper<ElementUtils> extends AbstractSelectCodeGenerator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.sqlite.SQLiteSelectBuilder.
	 * SelectCodeGenerator#generate(com.squareup.javapoet.MethodSpec.Builder)
	 */
	@Override
	public void generateSpecializedPart(SQLiteModelMethod method, TypeSpec.Builder classBuilder, MethodSpec.Builder methodBuilder, Set<JQLProjection> fieldList, boolean mapFields) {
		SQLDaoDefinition daoDefinition = method.getParent();
		SQLEntity entity = daoDefinition.getEntity();
		TypeName returnTypeName = method.getReturnClass();

		ParameterizedTypeName returnListName = (ParameterizedTypeName) returnTypeName;

		TypeName collectionClass;
		TypeName entityClass = typeName(entity.getElement());
		ClassName listClazzName = returnListName.rawType;

		collectionClass = defineCollection(listClazzName);

		methodBuilder.addCode("\n");
		if (TypeUtility.isTypeEquals(collectionClass, TypeUtility.typeName(ArrayList.class))) {
			methodBuilder.addCode("$T<$T> resultList=new $T<$T>(cursor.getCount());\n", collectionClass, entityClass, collectionClass, entityClass);
		} else {
			methodBuilder.addCode("$T<$T> resultList=new $T<$T>();\n", collectionClass, entityClass, collectionClass, entityClass);
		}
		methodBuilder.addCode("$T resultBean=null;\n", entityClass);
		methodBuilder.addCode("\n");
		methodBuilder.beginControlFlow("if (cursor.moveToFirst())");

		// generate index from columns
		methodBuilder.addCode("\n");
		{
			int i = 0;
			for (JQLProjection a : fieldList) {
				SQLProperty item = a.property;

				methodBuilder.addStatement("int index$L=cursor.getColumnIndex($S)", (i++), item.columnName);
				if (item.hasTypeAdapter()) {
					methodBuilder.addStatement("$T $LAdapter=$T.getAdapter($T.class)", item.typeAdapter.getAdapterTypeName(), item.getName(), SQLTypeAdapterUtils.class,
							item.typeAdapter.getAdapterTypeName());
				}
			}
		}
		methodBuilder.addCode("\n");

		methodBuilder.beginControlFlow("do\n");
		methodBuilder.addCode("resultBean=new $T();\n\n", entityClass);

		// generate mapping
		int i = 0;
		for (JQLProjection a : fieldList) {
			SQLProperty item = a.property;
			if (item.isNullable()) {
				methodBuilder.addCode("if (!cursor.isNull(index$L)) { ", i);
			}
			SQLTransformer.cursor2Java(methodBuilder, typeName(entity.getElement()), item, "resultBean", "cursor", "index" + i + "");
			methodBuilder.addCode(";");
			if (item.isNullable()) {
				methodBuilder.addCode(" }");
			}
			methodBuilder.addCode("\n");

			i++;
		}
		methodBuilder.addCode("\n");

		methodBuilder.addCode("resultList.add(resultBean);\n");
		methodBuilder.endControlFlow("while (cursor.moveToNext())");

		methodBuilder.endControlFlow();

		methodBuilder.addCode("\n");
		methodBuilder.addCode("return resultList;\n");

		// close try { open cursor
		methodBuilder.endControlFlow();
	}

	static TypeName defineCollection(ClassName listClazzName) {
		try {
			Class<?> clazz = Class.forName(listClazzName.toString());

			if (clazz.isAssignableFrom(Collection.class)) {
				clazz = ArrayList.class;
			} else if (clazz.isAssignableFrom(List.class)) {
				clazz = ArrayList.class;
			}
			if (clazz.isAssignableFrom(Set.class)) {
				clazz = LinkedHashSet.class;
			}

			return typeName(clazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;

	}

}
