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

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.className;
import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindDaoGeneratedPart;
import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.processor.BaseProcessor;
import com.abubusoft.kripton.processor.bind.model.many2many.M2MEntity;
import com.abubusoft.kripton.processor.bind.model.many2many.M2MModel;
import com.abubusoft.kripton.processor.bind.model.many2many.M2MProperty;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.core.JavadocUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.abubusoft.kripton.processor.utils.AnnotationProcessorUtilis;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class BindM2MBuilder extends AbstractBuilder {

	public static final String PREFIX = "Bind";

	public static final String SUFFIX = "Cursor";

	private int counter;

	public BindM2MBuilder(Filer filer) {
		super(BaseProcessor.elementUtils, filer, null);
	}

	public static void generate(Filer filer, M2MModel model) throws Exception {
		BindM2MBuilder visitor = new BindM2MBuilder(filer);

		for (M2MEntity item : model.getEntities()) {
			visitor.generate(item);
		}

	}

	public void generate(M2MEntity entity) throws Exception {
		generateEntity(entity);
		generateDaoPart(entity);

		// add constructor
		//@formatter:off
//		classBuilder.addMethod(MethodSpec.constructorBuilder()
//				.addJavadoc("<p>Constructor</p>\n\n")
//				.addJavadoc("@param cursor cursor used to read from database\n")
//				.addParameter(Cursor.class, "cursor")
//				.addCode("wrap(cursor);\n")
//				.build());
		//@formatter:on

		// add wrap method
		//@formatter:off
//		MethodSpec.Builder wrapMethodBuilder = MethodSpec.methodBuilder("wrap")
//				.addJavadoc("<p>Wrap cursor with this class</p>\n\n")
//				.addJavadoc("@param cursor cursor to include\n")
//				.addModifiers(Modifier.PUBLIC)
//				.addParameter(Cursor.class, "cursor")
//				.returns(className)
//				.addCode("this.cursor=cursor;\n");
		//@formatter:on
		// counter=0;
		// wrapMethodBuilder.addCode("\n");
		// for (M2MProperty item : entity.getCollection()) {
		// //wrapMethodBuilder.addCode("index$L=cursor.getColumnIndex($S);\n",
		// counter, item.columnName);
		// counter++;
		// }
		// wrapMethodBuilder.addCode("\n");
		// wrapMethodBuilder.addCode("return this;\n");

		// classBuilder.addMethod(wrapMethodBuilder.build());

		// add execute method
		// classBuilder.addMethod(generateExecuteMethod(packageName,
		// entity).build());

		// add execute listener method
		// classBuilder.addMethod(generateExecuteListener(packageName,
		// entity).build());

		// add create
		//@formatter:off
//		classBuilder.addMethod(
//				MethodSpec.methodBuilder("create")
//				.addModifiers(Modifier.STATIC, Modifier.PUBLIC)
//				.addParameter(Cursor.class, "cursor")
//				.returns(className(packageName, entityClassName))
//				.addJavadoc("<p>Create a binded cursor starting from a cursor</p>\n\n")
//				.addJavadoc("@param cursor to wrap\n")
//				.addCode("return new "+entityClassName+"(cursor);\n")				
//				.build());
		//@formatter:on

		// define column typeName set
		counter = 0;
		// for (ModelProperty item : entity.getCollection()) {
		// item.accept(this);
		// }

	}

	private void generateDaoPart(M2MEntity entity) throws IOException {
		String daoClassName = entity.daoName;

		PackageElement pkg = elementUtils.getPackageElement(entity.getPackageName());
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();

		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindDataSource.class, packageName, daoClassName);
		//@formatter:off
		classBuilder = TypeSpec.interfaceBuilder(daoClassName+"GeneratedPart")
				.addModifiers(Modifier.PUBLIC)				
				.addAnnotation(AnnotationSpec.builder(BindDaoGeneratedPart.class)
						.addMember("dao", "$T.class",TypeUtility.className(packageName, daoClassName))
						.addMember("entity", "$T.class",TypeUtility.className(packageName, entity.name)).build()
						)
						;				
		//@formatter:on

		// javadoc for class
		// classBuilder.addJavadoc("<p>");
		// classBuilder.addJavadoc("\nEntity implementation for entity
		// <code>$L</code>\n", entity.name);
		// classBuilder.addJavadoc("</p>\n");
		// JavadocUtility.generateJavadocGeneratedBy(classBuilder);
		// classBuilder.addJavadoc(" @see $T\n",
		// TypeUtility.className(entity.getElement().getQualifiedName().toString()));

		{
		//@formatter:off
		MethodSpec methodSpec = MethodSpec.methodBuilder("selectBy"+entity.idName)
				.addModifiers(Modifier.PUBLIC)
				.addModifiers(Modifier.ABSTRACT)
				.addAnnotation(AnnotationSpec.builder(BindSqlSelect.class).addMember("where", "$S",entity.idName+"=${"+entity.idName+"}").build())
				.addParameter(ParameterSpec.builder(Long.TYPE, "id")
						.addAnnotation(AnnotationSpec.builder(BindSqlParam.class).addMember("value", "$S",entity.idName).build()).build())
				.returns(TypeUtility.className(packageName, entity.name))				
				.build();
		//@formatter:on
			classBuilder.addMethod(methodSpec);
		}

		{
			//@formatter:off
			MethodSpec methodSpec = MethodSpec.methodBuilder("selectBy"+M2MEntity.extractClassName(entity.entityName1)+entity.idName)
					.addModifiers(Modifier.PUBLIC)
					.addModifiers(Modifier.ABSTRACT)
					.addAnnotation(AnnotationSpec.builder(BindSqlSelect.class).addMember("where", "$S","id=${id}").build())
					.addParameter(ParameterSpec.builder(Long.TYPE, "id")
							.addAnnotation(AnnotationSpec.builder(BindSqlParam.class).addMember("value", "$S","id").build()).build())
					.returns(TypeUtility.className(packageName, entity.name))				
					.build();
			//@formatter:on
			classBuilder.addMethod(methodSpec);
		}

		// Converter<String, String> converterFK =
		// CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);
		// Converter<String, String> converterClassName =
		// CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_CAMEL);
		// String fkPrefix = converterFK.convert(entity.idName);
		//
		// {
//		//@formatter:off
//		FieldSpec fieldSpec = FieldSpec.builder(Long.TYPE, converterClassName.convert(TypeUtility.className(entity.entityName1).simpleName()+fkPrefix), Modifier.PUBLIC)
//				.addJavadoc("Foreign key to $T model class\n", TypeUtility.className(entity.entityName1))
//				.addAnnotation(AnnotationSpec.builder(BindColumn.class)
//						.addMember("foreignKey","$T.class", TypeUtility.className(entity.entityName1)).build())
//				.build();
//		//@formatter:on
		// classBuilder.addField(fieldSpec);
		// }
		//
		// {
//		//@formatter:off
//		FieldSpec fieldSpec = FieldSpec.builder(Long.TYPE, converterClassName.convert(TypeUtility.className(entity.entityName2).simpleName()+fkPrefix), Modifier.PUBLIC)
//				.addJavadoc("Foreign key to $T model class\n", TypeUtility.className(entity.entityName2))
//				.addAnnotation(AnnotationSpec.builder(BindColumn.class)
//						.addMember("foreignKey","$T.class", TypeUtility.className(entity.entityName2)).build())
//				.build();
//		//@formatter:on
		// classBuilder.addField(fieldSpec);
		// }

		TypeSpec typeSpec = classBuilder.build();
		JavaFile.builder(packageName, typeSpec).build().writeTo(filer);

	}

	/**
	 * @param entity
	 * @return
	 * @throws IOException
	 */
	private void generateEntity(M2MEntity entity) throws IOException {
		String entityClassName = entity.name;

		PackageElement pkg = elementUtils.getPackageElement(entity.getPackageName());
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();

		ClassName className = TypeUtility.className(packageName, entityClassName);

		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindDataSource.class, packageName, entityClassName);
		//@formatter:off
		classBuilder = TypeSpec.classBuilder(entityClassName)
				.addModifiers(Modifier.PUBLIC)
				.addAnnotation(BindType.class)
				.addAnnotation(AnnotationSpec.builder(BindTable.class).addMember("name", "$S",entity.tableName).build());
		//@formatter:on

		// javadoc for class
		classBuilder.addJavadoc("<p>");
		classBuilder.addJavadoc("\nEntity implementation for entity <code>$L</code>\n", entity.name);
		classBuilder.addJavadoc("</p>\n");
		JavadocUtility.generateJavadocGeneratedBy(classBuilder);
		// classBuilder.addJavadoc(" @see $T\n",
		// TypeUtility.className(entity.getElement().getQualifiedName().toString()));

		{
		//@formatter:off
		FieldSpec fieldSpec = FieldSpec.builder(Long.TYPE, entity.idName, Modifier.PUBLIC)
				.addJavadoc("Primary key\n")
				.addAnnotation(AnnotationSpec.builder(BindColumn.class)
						.addMember("columnType","$T.$L", ColumnType.class, ColumnType.PRIMARY_KEY).build())
				.build();
		//@formatter:on
			classBuilder.addField(fieldSpec);
		}

		Converter<String, String> converterFK = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);
		Converter<String, String> converterClassName = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_CAMEL);
		String fkPrefix = converterFK.convert(entity.idName);

		{
		//@formatter:off
		FieldSpec fieldSpec = FieldSpec.builder(Long.TYPE, converterClassName.convert(TypeUtility.className(entity.entityName1).simpleName()+fkPrefix), Modifier.PUBLIC)
				.addJavadoc("Foreign key to $T model class\n", TypeUtility.className(entity.entityName1))
				.addAnnotation(AnnotationSpec.builder(BindColumn.class)
						.addMember("foreignKey","$T.class", TypeUtility.className(entity.entityName1)).build())
				.build();
		//@formatter:on
			classBuilder.addField(fieldSpec);
		}

		{
		//@formatter:off
		FieldSpec fieldSpec = FieldSpec.builder(Long.TYPE, converterClassName.convert(TypeUtility.className(entity.entityName2).simpleName()+fkPrefix), Modifier.PUBLIC)
				.addJavadoc("Foreign key to $T model class\n", TypeUtility.className(entity.entityName2))
				.addAnnotation(AnnotationSpec.builder(BindColumn.class)
						.addMember("foreignKey","$T.class", TypeUtility.className(entity.entityName2)).build())
				.build();
		//@formatter:on
			classBuilder.addField(fieldSpec);
		}

		TypeSpec typeSpec = classBuilder.build();
		JavaFile.builder(packageName, typeSpec).build().writeTo(filer);
	}

	private MethodSpec.Builder generateExecuteMethod(String packageName, SQLEntity entity) {
		ParameterizedTypeName parameterizedReturnTypeName = ParameterizedTypeName.get(className("java.util.LinkedList"), className(packageName, entity.getSimpleName()));

		//@formatter:off
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("execute")
				.addJavadoc("<p>Execute the cursor and read all rows from database.</p>\n\n")
				.addJavadoc("@return list of beans\n")
				.addModifiers(Modifier.PUBLIC)
				.returns(parameterizedReturnTypeName);
		//@formatter:on

		TypeName entityClass = typeName(entity.getElement());

		methodBuilder.addCode("\n");
		methodBuilder.addCode("$T resultList=new $T();\n", parameterizedReturnTypeName, parameterizedReturnTypeName);
		methodBuilder.addCode("$T resultBean=new $T();\n", entityClass, entityClass);
		methodBuilder.addCode("\n");
		methodBuilder.beginControlFlow("if (cursor.moveToFirst())");

		methodBuilder.beginControlFlow("do\n");
		methodBuilder.addCode("resultBean=new $T();\n\n", entityClass);

		// generate mapping
		int i = 0;
		for (ModelProperty item : entity.getCollection()) {
			methodBuilder.addCode("if (index$L>=0 && !cursor.isNull(index$L)) { ", i, i);
			SQLTransformer.cursor2Java(methodBuilder, entityClass, item, "resultBean", "cursor", "index" + i + "");
			methodBuilder.addCode(";");
			methodBuilder.addCode("}\n");

			i++;
		}
		methodBuilder.addCode("\n");

		methodBuilder.addCode("resultList.add(resultBean);\n");
		methodBuilder.endControlFlow("while (cursor.moveToNext())");

		methodBuilder.endControlFlow();
		methodBuilder.addCode("cursor.close();\n");

		methodBuilder.addCode("\n");
		methodBuilder.addCode("return resultList;\n");

		return methodBuilder;
	}

	private MethodSpec.Builder generateExecuteListener(String packageName, SQLEntity entity) {
		String interfaceName = "On" + entity.getSimpleName() + "Listener";

		//@formatter:off
		Builder listenerInterface = TypeSpec.interfaceBuilder(interfaceName)
				.addModifiers(Modifier.PUBLIC)
				.addMethod(MethodSpec.methodBuilder("onRow")
						.addJavadoc("Method executed for each row extracted from database\n\n")
						.addJavadoc("@param bean loaded from database. Only selected columns/fields are valorized\n")
						.addJavadoc("@param rowPosition position of row\n")
						.addJavadoc("@param rowCount total number of rows\n")
						.addParameter(ParameterSpec.builder(typeName(entity.getElement()), "bean").build())
						.addParameter(ParameterSpec.builder(Integer.TYPE, "rowPosition").build())
						.addParameter(ParameterSpec.builder(Integer.TYPE, "rowCount").build())
				.returns(Void.TYPE).addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT).build());
		//@formatter:on

		ClassName interfaceType = TypeUtility.className(interfaceName);

		// javadoc for interface
		listenerInterface.addJavadoc("<p>Listener for row read from database.</p>\n");
		TypeSpec listenerClass = listenerInterface.build();

		classBuilder.addType(listenerClass);

		//@formatter:off
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("executeListener")
				.addJavadoc("Method executed for each row extracted from database. For each row specified listener will be invoked.\n\n")
				.addJavadoc("@param listener listener to invoke for each row\n")				
				.addModifiers(Modifier.PUBLIC)
				.addParameter(ParameterSpec.builder(interfaceType, "listener").build())
				.returns(TypeName.VOID);
		//@formatter:on

		TypeName entityClass = typeName(entity.getElement());

		methodBuilder.addCode("$T resultBean=new $T();\n", entityClass, entityClass);
		methodBuilder.addCode("\n");
		methodBuilder.beginControlFlow("if (cursor.moveToFirst())");

		methodBuilder.beginControlFlow("do\n");

		// reset mapping
		{
			int i = 0;
			for (ModelProperty item : entity.getCollection()) {
				methodBuilder.addCode("if (index$L>=0) { ", i);
				SQLTransformer.resetBean(methodBuilder, entityClass, "resultBean", item, "cursor", "index" + i + "");
				methodBuilder.addCode(";");
				methodBuilder.addCode("}\n");

				i++;
			}
		}
		methodBuilder.addCode("\n");

		// generate mapping
		{
			int i = 0;
			for (ModelProperty item : entity.getCollection()) {
				methodBuilder.addCode("if (index$L>=0 && !cursor.isNull(index$L)) { ", i, i);
				SQLTransformer.cursor2Java(methodBuilder, entityClass, item, "resultBean", "cursor", "index" + i + "");
				methodBuilder.addCode(";");
				methodBuilder.addCode("}\n");

				i++;
			}
		}

		// send to listener
		methodBuilder.addCode("\n");
		methodBuilder.addCode("listener.onRow(resultBean, cursor.getPosition(),cursor.getCount());\n");

		methodBuilder.endControlFlow("while (cursor.moveToNext())");

		methodBuilder.endControlFlow();
		methodBuilder.addCode("cursor.close();\n");

		return methodBuilder;
	}

	public void visit(M2MProperty property) throws Exception {
		// add property index
		classBuilder.addField(FieldSpec.builder(Integer.TYPE, "index" + (counter++), Modifier.PROTECTED).addJavadoc("Index for column $S\n", property.getName()).build());

	}

}
