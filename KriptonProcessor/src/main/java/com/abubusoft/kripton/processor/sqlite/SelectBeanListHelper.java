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
 * 
 */
package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.sqlite.SqlSelectBuilder.SelectCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

/**
 * @author xcesco
 * @param <ElementUtils>
 * 
 *
 * @since 17/mag/2016
 */
public class SelectBeanListHelper<ElementUtils> implements SelectCodeGenerator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.sqlite.SQLiteSelectBuilder.SelectCodeGenerator#generate(com.squareup.javapoet.MethodSpec.Builder)
	 */
	@Override
	public void generate(Elements elementUtils, PropertyList fieldList, MethodSpec.Builder methodBuilder, boolean mapFields,  SQLiteModelMethod method, TypeMirror returnType) {
		TypeName returnTypeName=typeName(returnType);
		SQLDaoDefinition daoDefinition=method.getParent();
		SQLEntity entity=daoDefinition.getEntity();
		
		ParameterizedTypeName returnListName = (ParameterizedTypeName) returnTypeName;
		//String fieldStatement = fieldList.value0;
		List<SQLProperty> fields = fieldList.value1;

		TypeName collectionClass;
		TypeName entityClass = typeName(entity.getElement());
		ClassName listClazzName = returnListName.rawType;

		collectionClass=defineCollection(listClazzName);

		methodBuilder.addCode("\n");
		methodBuilder.addCode("$T<$T> resultList=new $T<$T>();\n", collectionClass, entityClass, collectionClass, entityClass);
		methodBuilder.addCode("$T resultBean=null;\n", entityClass);
		methodBuilder.addCode("\n");
		methodBuilder.beginControlFlow("if (cursor.moveToFirst())");

		// generate index from columns
		methodBuilder.addCode("\n");
		{
			int i = 0;
			for (ModelProperty item : fields) {
				methodBuilder.addCode("int index" + (i++) + "=");
				methodBuilder.addCode("cursor.getColumnIndex($S)", SqlUtility.getColumnName(item));
				methodBuilder.addCode(";\n");
			}
		}
		methodBuilder.addCode("\n");

		methodBuilder.beginControlFlow("do\n");
		methodBuilder.addCode("resultBean=new $T();\n\n", entityClass);

		// generate mapping
		int i = 0;
		for (SQLProperty item : fields) {
			
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
		methodBuilder.addCode("cursor.close();\n");

		methodBuilder.addCode("\n");
		methodBuilder.addCode("return resultList;\n");
	}

	static TypeName defineCollection(ClassName listClazzName) {
		try {
			Class<?> clazz = Class.forName(listClazzName.toString());
			
			if (clazz.isAssignableFrom(Collection.class))
			{
				clazz=LinkedList.class;
			} else if (clazz.isAssignableFrom(List.class))
			{
				clazz=LinkedList.class;
			} if (clazz.isAssignableFrom(Set.class))
			{
				clazz=HashSet.class;
			}
			
			return typeName(clazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;

	}

}
