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

import java.util.List;

import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.MethodUtility;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.SqlSelectBuilder.SelectCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.abubusoft.kripton.processor.utils.LiteralType;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 *
 * @since 17/mag/2016
 */
public class SelectBeanListenerHelper implements SelectCodeGenerator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.sqlite.SQLiteSelectBuilder.SelectCodeGenerator#generate(com.squareup.javapoet.MethodSpec.Builder)
	 */
	@Override
	public void generate(Elements elementUtils, PropertyList fieldList, MethodSpec.Builder methodBuilder, boolean mapFields, SQLiteModelMethod method, TypeMirror returnType) {
		SQLDaoDefinition daoDefinition=method.getParent();
		SQLEntity entity=daoDefinition.getEntity();
		
		LiteralType listenerType = LiteralType.of(OnReadBeanListener.class, entity.getElement());
		List<SQLProperty> fields = fieldList.value1;
		TypeName entityClass = typeName(entity.getElement());

		int counter = MethodUtility.countParameterOfType(method, listenerType);
		if (counter == 0) {
			// non listener found
			throw (new InvalidMethodSignException(method, "there is no parameter of type \"ReadCursorListener\""));
		}
		if (counter > 1) {
			// more than one listener found
			throw (new InvalidMethodSignException(method, "there are more than one parameter of type \"ReadCursorListener\""));
		}

		String listenerName = MethodUtility.getNameParameterOfType(method, listenerType);

		methodBuilder.addCode("$T resultBean=new $T();", entityClass, entityClass);
		methodBuilder.addCode("\n");
		methodBuilder.beginControlFlow("try");
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
		methodBuilder.addCode("int rowCount=cursor.getCount();\n");

		methodBuilder.beginControlFlow("do\n");

		// reset mapping
		methodBuilder.addCode("// reset mapping\n");
		{
			int i = 0;
			for (SQLProperty item : entity.getCollection()) {
				if (item.isNullable()) {
					SQLTransformer.resetBean(methodBuilder, entityClass, "resultBean", item,  "cursor", "index" + i + "");
					methodBuilder.addCode(";");
					methodBuilder.addCode("\n");
				} else {
					methodBuilder.addCode("// "+item.getName()+" does not need reset\n");
				}
				i++;
			}
		}
		methodBuilder.addCode("\n");

		// generate mapping
		methodBuilder.addCode("// generate mapping\n");
		{
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
		}
		methodBuilder.addCode("\n");

		methodBuilder.addCode("$L.onRead(resultBean, cursor.getPosition(), rowCount);\n", listenerName);
		methodBuilder.endControlFlow("while (cursor.moveToNext())");

		methodBuilder.endControlFlow();
		methodBuilder.nextControlFlow("finally");
		methodBuilder.beginControlFlow("if (!cursor.isClosed())");
		methodBuilder.addCode("cursor.close();\n");
		methodBuilder.endControlFlow();
		methodBuilder.endControlFlow();
	}

}
