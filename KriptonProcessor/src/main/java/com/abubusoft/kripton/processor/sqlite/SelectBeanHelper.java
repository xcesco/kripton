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

import com.abubusoft.kripton.android.sqlite.SQLTypeAdapterUtils;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLProjection;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 *
 * @since 17/mag/2016
 */
public class SelectBeanHelper extends AbstractSelectCodeGenerator {

	@Override
	public void generateSpecializedPart(SQLiteModelMethod method, TypeSpec.Builder classBuilder, MethodSpec.Builder methodBuilder,  Set<JQLProjection> fieldList, boolean mapFields) {
		SQLDaoDefinition daoDefinition=method.getParent();
		SQLEntity entity=daoDefinition.getEntity();
		
		//List<SQLProperty> fields = fieldList.value1;

		//TypeName collectionClass;
		TypeName entityClass = typeName(entity.getElement());
		
		methodBuilder.addCode("\n");		
		methodBuilder.addCode("$T resultBean=null;\n", entityClass);
		methodBuilder.addCode("\n");
						
		methodBuilder.beginControlFlow("if (cursor.moveToFirst())");

		// generate index from columns
		methodBuilder.addCode("\n");
		{
			int i = 0;
			for (JQLProjection a : fieldList) {
				SQLProperty item=a.property;
				methodBuilder.addStatement("int index$L=cursor.getColumnIndex($S)", (i++), item.columnName);				
				if (item.hasTypeAdapter()) {
					methodBuilder.addStatement("$T $LAdapter=$T.getAdapter($T.class)", item.typeAdapter.getAdapterTypeName(), item.getName(), SQLTypeAdapterUtils.class, item.typeAdapter.getAdapterTypeName());
				}
			}
		}
		methodBuilder.addCode("\n");

		methodBuilder.addCode("resultBean=new $T();\n\n", entityClass);

		// generate mapping
		int i = 0;
		for (JQLProjection a : fieldList) {
			SQLProperty item=a.property;
			if (item.isNullable()) {
				methodBuilder.addCode("if (!cursor.isNull(index$L)) { ", i);
			}
			SQLTransformer.cursor2Java(methodBuilder, entityClass, item, "resultBean", "cursor", "index" + i + "");
			methodBuilder.addCode(";");
			if (item.isNullable()) {
				methodBuilder.addCode(" }");
			}
			methodBuilder.addCode("\n");

			i++;
		}
		methodBuilder.addCode("\n");

		methodBuilder.endControlFlow();
		
		methodBuilder.addCode("return resultBean;\n");
		// close try { open cursor 
		methodBuilder.endControlFlow();		
	}



}
