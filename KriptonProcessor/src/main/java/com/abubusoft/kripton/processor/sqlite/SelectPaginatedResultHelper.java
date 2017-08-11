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
import java.util.List;
import java.util.Set;

import javax.lang.model.element.Modifier;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.sqlite.PaginatedResult;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLProjection;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 * @param <ElementUtils>
 * 
 *
 * @since 29/01/2017
 */
public class SelectPaginatedResultHelper<ElementUtils> extends AbstractSelectCodeGenerator {

	@Override
	public void generate(Elements elementUtils, TypeSpec.Builder builder, boolean mapFields, SQLiteModelMethod method, TypeName returnType) {
		SQLDaoDefinition daoDefinition = method.getParent();
		String pagedResultName = buildSpecializedPagedResultClass(builder, method);

		
		Set<JQLProjection> fieldList=JQLChecker.getInstance().extractProjections(method.jql.value, daoDefinition.getEntity());
		
		{
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.getName()).addAnnotation(Override.class).addModifiers(Modifier.PUBLIC);
			// create PaginatedResult

			String separator = "";
			methodBuilder.addCode("$L paginatedResult=new $L(", pagedResultName, pagedResultName);
			for (Pair<String, TypeName> item : method.getParameters()) {
				// field
				methodBuilder.addCode(separator + "$L", item.value0);
				separator = ", ";
			}
			methodBuilder.addCode(");\n");

			generateCommonPart(elementUtils, method, methodBuilder, fieldList, selectType.isMapFields(), GenerationType.NO_CONTENT);
			methodBuilder.addStatement("return paginatedResult");

			builder.addMethod(methodBuilder.build());
		}

		// generate paged result method
		{
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.getName()).addModifiers(Modifier.PRIVATE);
			generateMethodSignature(method, methodBuilder, TypeUtility.parameterizedTypeName(TypeUtility.className(List.class), TypeUtility.typeName(daoDefinition.getEntityClassName())),
					ParameterSpec.builder(TypeUtility.typeName(pagedResultName), "paginatedResult").build());

			generateCommonPart(elementUtils, method, methodBuilder, fieldList, selectType.isMapFields(), GenerationType.NO_METHOD_SIGN,
					JavadocPart.build(JavadocPartType.ADD_PARAMETER, "paginatedResult", "handler of paginated result"), JavadocPart.build(JavadocPartType.RETURN, "", "result list"));
			generateSpecializedPart(elementUtils, method, methodBuilder, fieldList, selectType.isMapFields());
			builder.addMethod(methodBuilder.build());
		}

	}

	/**
	 * Used to generate specialized Paged Result classes;
	 */
	static int pagedResultCounter;

	@Override
	public void generateSpecializedPart(Elements elementUtils, SQLiteModelMethod method, Builder methodBuilder, Set<JQLProjection> fieldList, boolean mapFields) {
		SQLDaoDefinition daoDefinition = method.getParent();
		SQLEntity entity = daoDefinition.getEntity();
		//List<SQLProperty> fields = fieldList.value1;

		TypeName entityClass = typeName(entity.getElement());

		methodBuilder.addCode("\n");
		methodBuilder.addCode("$T<$T> resultList=new $T<$T>();\n", List.class, entityClass, ArrayList.class, entityClass);
		methodBuilder.addCode("$T resultBean=null;\n", entityClass);
		methodBuilder.addCode("\n");
		methodBuilder.beginControlFlow("if (cursor.moveToFirst())");

		// generate index from columns
		methodBuilder.addCode("\n");
		{
			int i = 0;
			for (JQLProjection a : fieldList) {
				SQLProperty item=a.property;
				methodBuilder.addCode("int index" + (i++) + "=");
				methodBuilder.addCode("cursor.getColumnIndex($S)", item.columnName);
				methodBuilder.addCode(";\n");
			}
		}
		methodBuilder.addCode("\n");

		methodBuilder.beginControlFlow("do\n");
		methodBuilder.addCode("resultBean=new $T();\n\n", entityClass);

		// generate mapping
		int i = 0;
		for (JQLProjection a : fieldList) {
			SQLProperty item=a.property;

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
		methodBuilder.endControlFlow();
	}

	private static String buildSpecializedPagedResultClass(TypeSpec.Builder builder, SQLiteModelMethod method) {
		TypeName entityTypeName = TypeUtility.typeName(method.getParent().getEntityClassName());

		String pagedResultName = "PaginatedResult" + (pagedResultCounter++);

		TypeSpec.Builder typeBuilder = TypeSpec.classBuilder(pagedResultName).addModifiers(Modifier.PUBLIC)
				.superclass(TypeUtility.parameterizedTypeName(TypeUtility.className(PaginatedResult.class), entityTypeName));

		// add fields and define constructor
		MethodSpec.Builder setupBuilder = MethodSpec.constructorBuilder();

		MethodSpec.Builder executeBuilder = MethodSpec.methodBuilder("execute").addModifiers(Modifier.PUBLIC)
				.returns(TypeUtility.parameterizedTypeName(TypeUtility.className(List.class), entityTypeName));
		executeBuilder.addCode("list=$T.this.$L(", TypeUtility.typeName(method.getParent().getElement(), BindDaoBuilder.SUFFIX), method.getName());

		// we have alway a first parameter
		String separator = "";
		ParameterSpec parameterSpec;
		for (Pair<String, TypeName> item : method.getParameters()) {
			if (method.hasDynamicPageSizeConditions() && method.dynamicPageSizeName.equals(item.value0)) {
				setupBuilder.addStatement("this.pageSize=$L", item.value0);
			} else {
				// field
				typeBuilder.addField(item.value1, item.value0);
				setupBuilder.addStatement("this.$L=$L", item.value0, item.value0);
			}

			// construtor
			parameterSpec = ParameterSpec.builder(item.value1, item.value0).build();
			setupBuilder.addParameter(parameterSpec);

			// execute
			executeBuilder.addCode(separator + item.value0);
			separator = ", ";
		}

		if (!method.hasDynamicPageSizeConditions()) {
			ModelAnnotation annotation = method.getAnnotation(BindSqlSelect.class);
			int pageSize = annotation.getAttributeAsInt(AnnotationAttributeType.PAGE_SIZE);
			setupBuilder.addStatement("this.pageSize=$L", pageSize);
		}

		typeBuilder.addMethod(setupBuilder.build());

		executeBuilder.addCode(separator + "this);\n");
		executeBuilder.addStatement("return list");

		typeBuilder.addMethod(executeBuilder.build());

		builder.addType(typeBuilder.build());

		return pagedResultName;
	}

}
