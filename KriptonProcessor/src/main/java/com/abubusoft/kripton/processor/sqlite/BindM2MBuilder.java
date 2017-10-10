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

import java.io.IOException;
import java.util.List;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindDaoGeneratedPart;
import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;
import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.android.sqlite.ForeignKeyAction;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.processor.BaseProcessor;
import com.abubusoft.kripton.processor.BindMany2ManyProcessor;
import com.abubusoft.kripton.processor.bind.model.many2many.M2MEntity;
import com.abubusoft.kripton.processor.bind.model.many2many.M2MModel;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.core.JavadocUtility;
import com.abubusoft.kripton.processor.utils.AnnotationProcessorUtilis;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class BindM2MBuilder extends AbstractBuilder {

	public static final String PREFIX = "Bind";

	public static final String SUFFIX = "Cursor";

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
	}

	private void generateDaoPart(M2MEntity entity) throws IOException {
		String daoClassName = entity.daoName.simpleName();

		PackageElement pkg = elementUtils.getPackageElement(entity.getPackageName());
		String packageName = pkg.getQualifiedName().toString();

		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindDaoMany2Many.class, packageName, daoClassName);
		//@formatter:off
		classBuilder = TypeSpec.interfaceBuilder(daoClassName+"GeneratedPart")
				.addModifiers(Modifier.PUBLIC)				
				.addAnnotation(AnnotationSpec.builder(BindDaoGeneratedPart.class)
						.addMember("dao", "$T.class",TypeUtility.className(packageName, daoClassName))
						.addMember("entity", "$T.class",TypeUtility.className(packageName, entity.name)).build()
						)
						;						

		generateSelects(entity, packageName);
		generateDeletes(entity, packageName);
		generateInsert(entity, packageName);


		TypeSpec typeSpec = classBuilder.build();
		JavaFile.builder(packageName, typeSpec).build().writeTo(filer);

	}

	private void generateSelects(M2MEntity entity, String packageName) {
		String idPart=CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, entity.idName);
		
		{
		//@formatter:off
		MethodSpec methodSpec = MethodSpec.methodBuilder("selectBy"+idPart)
				.addModifiers(Modifier.PUBLIC)
				.addModifiers(Modifier.ABSTRACT)
				.addAnnotation(AnnotationSpec.builder(BindSqlSelect.class).addMember("where", "$S",entity.idName+"=${"+entity.idName+"}").build())
				.addParameter(ParameterSpec.builder(Long.TYPE, entity.idName)
						.addAnnotation(AnnotationSpec.builder(BindSqlParam.class).addMember("value", "$S",entity.idName).build()).build())
				.returns(TypeUtility.className(packageName, entity.name))				
				.build(); 
		//@formatter:on
			classBuilder.addMethod(methodSpec);
		}

		{
			String methodName = entity.entity1Name.simpleName() + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, entity.idName);
			String workId = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, methodName);
			//@formatter:off
			MethodSpec methodSpec = MethodSpec.methodBuilder("selectBy"+methodName)
					.addModifiers(Modifier.PUBLIC)
					.addModifiers(Modifier.ABSTRACT)
					.addAnnotation(AnnotationSpec.builder(BindSqlSelect.class).addMember("where", "$S",workId+"=${"+workId+"}").build())
					.addParameter(ParameterSpec.builder(Long.TYPE, workId)
							.addAnnotation(AnnotationSpec.builder(BindSqlParam.class).addMember("value", "$S",workId).build()).build())
					.returns(TypeUtility.parameterizedTypeName(TypeUtility.className(List.class), TypeUtility.className(packageName, entity.name)))				
					.build();
			//@formatter:on
			classBuilder.addMethod(methodSpec);
		}

		{
			String methodName = entity.entity2Name.simpleName() + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, entity.idName);
			String workId = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, methodName);
			//@formatter:off
			MethodSpec methodSpec = MethodSpec.methodBuilder("selectBy"+methodName)
					.addModifiers(Modifier.PUBLIC)
					.addModifiers(Modifier.ABSTRACT)
					.addAnnotation(AnnotationSpec.builder(BindSqlSelect.class).addMember("where", "$S",workId+"=${"+workId+"}").build())
					.addParameter(ParameterSpec.builder(Long.TYPE, workId)
							.addAnnotation(AnnotationSpec.builder(BindSqlParam.class).addMember("value", "$S",workId).build()).build())
					.returns(TypeUtility.parameterizedTypeName(TypeUtility.className(List.class), TypeUtility.className(packageName, entity.name)))				
					.build();
			//@formatter:on
			classBuilder.addMethod(methodSpec);
		}
	}

	private void generateDeletes(M2MEntity entity, String packageName) {
		String idPart = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, entity.idName);

		{
		//@formatter:off
		MethodSpec methodSpec = MethodSpec.methodBuilder("deleteBy"+idPart)
				.addModifiers(Modifier.PUBLIC)
				.addModifiers(Modifier.ABSTRACT)
				.addAnnotation(AnnotationSpec.builder(BindSqlDelete.class).addMember("where", "$S",entity.idName+"=${"+entity.idName+"}").build())
				.addParameter(ParameterSpec.builder(Long.TYPE, entity.idName)
						.addAnnotation(AnnotationSpec.builder(BindSqlParam.class).addMember("value", "$S",entity.idName).build()).build())
				.returns(Integer.TYPE)				
				.build(); 
		//@formatter:on
			classBuilder.addMethod(methodSpec);
		}

		{
			String methodName = entity.entity1Name.simpleName() + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, entity.idName);
			String workId = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, methodName);
			//@formatter:off
			MethodSpec methodSpec = MethodSpec.methodBuilder("deleteBy"+methodName)
					.addModifiers(Modifier.PUBLIC)
					.addModifiers(Modifier.ABSTRACT)
					.addAnnotation(AnnotationSpec.builder(BindSqlDelete.class).addMember("where", "$S",workId+"=${"+workId+"}").build())
					.addParameter(ParameterSpec.builder(Long.TYPE, workId)
							.addAnnotation(AnnotationSpec.builder(BindSqlParam.class).addMember("value", "$S",workId).build()).build())
					.returns(Integer.TYPE)				
					.build();
			//@formatter:on
			classBuilder.addMethod(methodSpec);
		}

		{
			String methodName = entity.entity2Name.simpleName() + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, entity.idName);
			String workId = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, methodName);
			//@formatter:off
			MethodSpec methodSpec = MethodSpec.methodBuilder("deleteBy"+methodName)
					.addModifiers(Modifier.PUBLIC)
					.addModifiers(Modifier.ABSTRACT)
					.addAnnotation(AnnotationSpec.builder(BindSqlDelete.class).addMember("where", "$S",workId+"=${"+workId+"}").build())
					.addParameter(ParameterSpec.builder(Long.TYPE, workId)
							.addAnnotation(AnnotationSpec.builder(BindSqlParam.class).addMember("value", "$S",workId).build()).build())
					.returns(Integer.TYPE)				
					.build();
			//@formatter:on
			classBuilder.addMethod(methodSpec);
		}
	}

	private void generateInsert(M2MEntity entity, String packageName) {
		{
		//@formatter:off
		MethodSpec methodSpec = MethodSpec.methodBuilder("insert")
				.addModifiers(Modifier.PUBLIC)
				.addModifiers(Modifier.ABSTRACT)
				.addAnnotation(AnnotationSpec.builder(BindSqlInsert.class).build())
				.addParameter(ParameterSpec.builder(TypeUtility.className(packageName, entity.name), "bean")
						.addAnnotation(AnnotationSpec.builder(BindSqlParam.class).addMember("value", "$S","bean").build()).build())
				.returns(Integer.TYPE)				
				.build(); 
		//@formatter:on
			classBuilder.addMethod(methodSpec);
		}
	}

	/**
	 * @param entity
	 * @return
	 * @throws IOException
	 */
	private void generateEntity(M2MEntity entity) throws IOException {
		if(!entity.needToCreate) return;
		
		String entityClassName = entity.name;

		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindDaoMany2Many.class, entity.getPackageName(), entityClassName);
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
		FieldSpec fieldSpec = FieldSpec.builder(Long.TYPE, converterClassName.convert(entity.entity1Name.simpleName()+fkPrefix), Modifier.PUBLIC)
				.addJavadoc("Foreign key to $T model class\n", entity.entity1Name)
				.addAnnotation(AnnotationSpec.builder(BindColumn.class)
						.addMember("foreignKey","$T.class", entity.entity1Name)
						.addMember("onDelete","$T.$L", ForeignKeyAction.class, ForeignKeyAction.CASCADE).build())
				.build();
		//@formatter:on
			classBuilder.addField(fieldSpec);
		}

		{
		//@formatter:off
		FieldSpec fieldSpec = FieldSpec.builder(Long.TYPE, converterClassName.convert(entity.entity2Name.simpleName()+fkPrefix), Modifier.PUBLIC)
				.addJavadoc("Foreign key to $T model class\n", entity.entity2Name)
				.addAnnotation(AnnotationSpec.builder(BindColumn.class)
						.addMember("foreignKey","$T.class", entity.entity2Name)
						.addMember("onDelete","$T.$L", ForeignKeyAction.class, ForeignKeyAction.CASCADE).build())
				.build();
		//@formatter:on
			classBuilder.addField(fieldSpec);
		}

		TypeSpec typeSpec = classBuilder.build();
		JavaFile.builder(entity.getPackageName(), typeSpec).build().writeTo(filer);
	}


}
