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

import com.abubusoft.kripton.common.SQLTypeAdapterUtils;
import com.abubusoft.kripton.processor.core.ImmutableUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLProjection;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * The Class SelectBeanListHelper.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 * @param <ElementUtils>
 *            the generic type
 * @since 17/mag/2016
 */
public class SelectBeanListHelper<ElementUtils> extends AbstractSelectCodeGenerator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.sqlite.SQLiteSelectBuilder. SelectCodeGenerator#generate(com.squareup.javapoet.MethodSpec.Builder)
	 */
	@Override
	public void generateSpecializedPart(SQLiteModelMethod method, TypeSpec.Builder classBuilder,
			MethodSpec.Builder methodBuilder, Set<JQLProjection> fieldList, boolean mapFields) {		
		SQLiteEntity entity = method.getEntity();
		TypeName returnTypeName = method.getReturnClass();

		ParameterizedTypeName returnListName = (ParameterizedTypeName) returnTypeName;

		ClassName collectionClass;
		TypeName entityClass = typeName(entity.getElement());
		ClassName returnRawListClazzName = returnListName.rawType;

		collectionClass = defineCollection(returnRawListClazzName);

		methodBuilder.addCode("\n");
		if (TypeUtility.isTypeEquals(collectionClass, TypeUtility.typeName(ArrayList.class))) {
			methodBuilder.addCode("$T<$T> resultList=new $T<$T>(_cursor.getCount());\n", collectionClass, entityClass,
					collectionClass, entityClass);
		} else {
			methodBuilder.addCode("$T<$T> resultList=new $T<$T>();\n", collectionClass, entityClass, collectionClass,
					entityClass);
		}
		methodBuilder.addStatement("$T resultBean=null", entityClass);
		// immutable management
		if (entity.isImmutablePojo()) {
			methodBuilder.addCode("\n");
			methodBuilder.addComment("initialize temporary variable for immutable POJO");
			ImmutableUtility.generateImmutableVariableInit(entity, methodBuilder);
		}

		methodBuilder.addCode("\n");
		methodBuilder.beginControlFlow("if (_cursor.moveToFirst())");

		// generate index from columns
		methodBuilder.addCode("\n");
		{
			int i = 0;
			for (JQLProjection a : fieldList) {
				SQLProperty item = a.property;

				methodBuilder.addStatement("int index$L=_cursor.getColumnIndex($S)", (i++), item.columnName);
				if (item.hasTypeAdapter()) {
					methodBuilder.addStatement("$T $LAdapter=$T.getAdapter($T.class)",
							item.typeAdapter.getAdapterTypeName(), item.getName(), SQLTypeAdapterUtils.class,
							item.typeAdapter.getAdapterTypeName());
				}
			}
		}
		methodBuilder.addCode("\n");

		methodBuilder.beginControlFlow("do\n");

		// immutable management
		if (entity.isImmutablePojo()) {
			methodBuilder.addComment("reset temporary variable for immutable POJO");
			ImmutableUtility.generateImmutableVariableReset(entity, methodBuilder);
		} else {
			methodBuilder.addCode("resultBean=new $T();\n\n", entityClass);
		}

		// generate mapping
		int i = 0;
		for (JQLProjection a : fieldList) {
			SQLProperty item = a.property;
			if (item.isNullable()) {
				methodBuilder.addCode("if (!_cursor.isNull(index$L)) { ", i);
			}
			SQLTransformer.cursor2Java(methodBuilder, typeName(entity.getElement()), item, "resultBean", "_cursor",
					"index" + i + "");
			methodBuilder.addCode(";");
			if (item.isNullable()) {
				methodBuilder.addCode(" }");
			}
			methodBuilder.addCode("\n");

			i++;
		}

		generateSubQueries(methodBuilder, method);

		methodBuilder.addCode("\n");

		// immutable management
		if (entity.isImmutablePojo()) {
			methodBuilder.addComment("define immutable POJO");
			ImmutableUtility.generateImmutableEntityCreation(entity, methodBuilder, "resultBean", false);
		} 

		methodBuilder.addCode("resultList.add(resultBean);\n");
		methodBuilder.endControlFlow("while (_cursor.moveToNext())");

		methodBuilder.endControlFlow();

		methodBuilder.addCode("\n");
		
		// return list or immutable list
		if (entity.isImmutablePojo()) {
			methodBuilder.addCode("return ");
			ImmutableUtility.generateImmutableCollectionIfPossible(entity,  methodBuilder, "resultList", ParameterizedTypeName.get(returnRawListClazzName, entityClass));	
			methodBuilder.addCode(";\n");
		} else {
			methodBuilder.addCode("return resultList;\n");
		}
		

		// close try { open cursor
		methodBuilder.endControlFlow();
	}

	/**
	 * Define collection.
	 *
	 * @param listClazzName
	 *            the list clazz name
	 * @return the type name
	 */
	static ClassName defineCollection(ClassName listClazzName) {
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

			return ClassName.get(clazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;

	}

}
