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

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.util.Set;

import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.common.SQLTypeAdapterUtils;
import com.abubusoft.kripton.processor.core.ImmutableUtility;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLProjection;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * The Class SelectBeanListenerHelper.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 * @since 17/mag/2016
 */
public class SelectBeanListenerHelper extends AbstractSelectCodeGenerator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.sqlite.SQLiteSelectBuilder.SelectCodeGenerator#generate(com.squareup.javapoet.MethodSpec.Builder)
	 */
	@Override
	public void generateSpecializedPart(SQLiteModelMethod method, TypeSpec.Builder classBuilder,
			MethodSpec.Builder methodBuilder, Set<JQLProjection> fieldList, boolean mapFields) {
		SQLiteDaoDefinition daoDefinition = method.getParent();
		SQLiteEntity entity = daoDefinition.getEntity();

		ParameterizedTypeName listenerType = ParameterizedTypeName.get(ClassName.get(OnReadBeanListener.class),
				TypeName.get(entity.getElement().asType()));

		// List<SQLProperty> fields = fieldList.value1;
		TypeName entityClass = typeName(entity.getElement());

		int counter = SqlBuilderHelper.countParameterOfType(method, listenerType);
		if (counter == 0) {
			// non listener found
			throw (new InvalidMethodSignException(method, "there is no parameter of type \"ReadCursorListener\""));
		}
		if (counter > 1) {
			// more than one listener found
			throw (new InvalidMethodSignException(method,
					"there are more than one parameter of type \"ReadCursorListener\""));
		}

		String listenerName = SqlSelectBuilder.getNameParameterOfType(method, listenerType);

		
		// immutable management
		if (entity.isImmutablePojo()) {
			methodBuilder.addStatement("$T resultBean=null", entityClass);
			methodBuilder.addCode("\n");			
			methodBuilder.addComment("initialize temporary variable for immutable POJO");
			ImmutableUtility.generateImmutableVariableInit(entity, methodBuilder);
		} else {
			methodBuilder.addStatement("$T resultBean=new $T()", entityClass, entityClass);
		}

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
		methodBuilder.addCode("int rowCount=_cursor.getCount();\n");

		methodBuilder.beginControlFlow("do\n");

		// reset mapping
		methodBuilder.addCode("// reset mapping\n");
		{
			int i = 0;
			for (SQLProperty item : entity.getCollection()) {
				if (item.isNullable()) {
					SQLTransformer.resetBean(methodBuilder, entityClass, "resultBean", item, "_cursor",
							"index" + i + "");
					methodBuilder.addCode(";");
					methodBuilder.addCode("\n");
				} else {
					methodBuilder.addCode("// " + item.getName() + " does not need reset (it will be taken from db)\n");
				}
				i++;
			}
		}
		methodBuilder.addCode("\n");

		// generate mapping
		methodBuilder.addCode("// generate mapping\n");
		{
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
		}
		methodBuilder.addCode("\n");
		
		// immutable management
		if (entity.isImmutablePojo()) {
			methodBuilder.addComment("define immutable POJO");
			ImmutableUtility.generateImmutableEntityCreation(entity, methodBuilder, "resultBean", false);
		} 

		methodBuilder.addCode("$L.onRead(resultBean, _cursor.getPosition(), rowCount);\n", listenerName);
		methodBuilder.endControlFlow("while (_cursor.moveToNext())");

		// close try { open cursor
		methodBuilder.endControlFlow();

		// close method
		methodBuilder.endControlFlow();

	}

}
