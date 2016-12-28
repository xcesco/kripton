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
package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.className;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.CircularRelationshipException;
import com.abubusoft.kripton.processor.sqlite.core.EntityUtility;
import com.abubusoft.kripton.processor.sqlite.core.JavadocUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.utils.AnnotationProcessorUtilis;
import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Generates database class
 * 
 * @author xcesco
 *
 */
public class BindDataSourceBuilder extends AbstractBuilder {

	public static final String PREFIX = "Bind";

	public static final String SUFFIX = "DataSource";

	public BindDataSourceBuilder(Elements elementUtils, Filer filer, SQLiteDatabaseSchema model) {
		super(elementUtils, filer, model);
	}

	/**
	 * Generate database
	 * 
	 * @param elementUtils
	 * @param filer
	 * @param schema
	 * @throws Exception
	 */
	public static void generate(Elements elementUtils, Filer filer, SQLiteDatabaseSchema schema) throws Exception {
		BindDaoFactoryBuilder visitor = new BindDaoFactoryBuilder(elementUtils, filer, schema);
		visitor.buildDaoFactoryInterface(elementUtils, filer, schema);
		String daoFactoryName = BindDaoFactoryBuilder.generateDaoFactoryName(schema);

		BindDataSourceBuilder visitorDao = new BindDataSourceBuilder(elementUtils, filer, schema);
		visitorDao.buildDataSource(elementUtils, filer, schema, daoFactoryName);
	}

	public void buildDataSource(Elements elementUtils, Filer filer, SQLiteDatabaseSchema schema, String daoFactoryName) throws Exception {
		ClassName daoFactoryClazz = className(daoFactoryName);
		Converter<String, String> convert = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_CAMEL);

		String schemaName = schema.getName();
		schemaName = PREFIX + schemaName;

		PackageElement pkg = elementUtils.getPackageOf(schema.getElement());
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();

		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindDataSource.class, packageName, schemaName);
		builder = TypeSpec.classBuilder(schemaName).addModifiers(Modifier.PUBLIC).superclass(AbstractDataSource.class).addSuperinterface(daoFactoryClazz)
				.addSuperinterface(TypeUtility.typeName(schema.getElement().asType()));

		builder.addJavadoc("<p>\n");
		builder.addJavadoc("Represents implementation of datasource $L.\n", schema.getName());
		builder.addJavadoc("This class expose database interface through Dao attribute.\n", schema.getName());
		builder.addJavadoc("</p>\n\n");

		JavadocUtility.generateJavadocGeneratedBy(builder);
		builder.addJavadoc("@see $T\n", className(schema.getName()));
		builder.addJavadoc("@see $T\n", daoFactoryClazz);
		for (SQLDaoDefinition dao : schema.getCollection()) {
			TypeName daoImplName = BindDaoBuilder.daoTypeName(dao);
			builder.addJavadoc("@see $T\n", dao.getElement());
			builder.addJavadoc("@see $T\n", daoImplName);
			builder.addJavadoc("@see $T\n", TypeUtility.typeName(dao.getEntity().getElement()));
		}

		// define static fields
		builder.addField(FieldSpec.builder(className(schemaName), "instance", Modifier.PRIVATE, Modifier.STATIC).addJavadoc("<p><singleton of datasource,/p>\n").build());
		builder.addField(FieldSpec.builder(String.class, "name", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL).addJavadoc("<p><file name used to save database,/p>\n")
				.initializer("$S", schema.fileName).build());
		builder.addField(
				FieldSpec.builder(Integer.TYPE, "version", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL).addJavadoc("<p>database version</p>\n").initializer("$L", schema.version).build());

		for (SQLDaoDefinition dao : schema.getCollection()) {
			// TypeName daoInterfaceName =
			// BindDaoBuilder.daoInterfaceTypeName(dao);
			TypeName daoImplName = BindDaoBuilder.daoTypeName(dao);
			builder.addField(FieldSpec.builder(daoImplName, convert.convert(dao.getName()), Modifier.PROTECTED).addJavadoc("<p>dao instance</p>\n").initializer("new $T(this)", daoImplName).build());

			// dao with connections
			{
				MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("get" + dao.getName()).addAnnotation(Override.class).addModifiers(Modifier.PUBLIC).returns(BindDaoBuilder.daoTypeName(dao));
				methodBuilder.addCode("return $L;\n", convert.convert(dao.getName()));
				builder.addMethod(methodBuilder.build());
			}
		}

		// interface
		// public interface DummyExecutor extends DatabaseExecutor
		generateMethodExecute(daoFactoryName);

		{
			// instance
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("instance").addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.SYNCHRONIZED).returns(className(schemaName));

			methodBuilder.addJavadoc("instance\n");
			methodBuilder.beginControlFlow("if (instance==null)");
			methodBuilder.addCode("instance=new $L($T.context());\n", className(schemaName), KriptonLibrary.class);
			methodBuilder.endControlFlow();
			methodBuilder.addCode("return instance;\n");

			builder.addMethod(methodBuilder.build());
		}

		{
			// constructor
			MethodSpec.Builder methodBuilder = MethodSpec.constructorBuilder().addModifiers(Modifier.PROTECTED).addParameter(Context.class, "context");
			methodBuilder.addCode("super(context, name, null, version);\n");
			builder.addMethod(methodBuilder.build());
		}

		List<SQLEntity> orderedEntities;
		boolean useForeignKey = false;

		{
			// onCreate
			// before use entities, order them with dependencies respect
			orderedEntities = generateOrderedEntitiesList(schema);

			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("onCreate").addAnnotation(Override.class).addModifiers(Modifier.PUBLIC);
			methodBuilder.addParameter(SQLiteDatabase.class, "database");
			methodBuilder.addJavadoc("onCreate\n");
			methodBuilder.addCode("// generate tables\n");
			for (SQLEntity item : orderedEntities) {
				if (schema.isLogEnabled()) {
					methodBuilder.addCode("$T.info(\"DDL: %s\",$T.CREATE_TABLE_SQL);\n", Logger.class, BindTableGenerator.tableClassName(item));
				}
				methodBuilder.addCode("database.execSQL($T.CREATE_TABLE_SQL);\n", BindTableGenerator.tableClassName(item));

				if (item.referedEntities.size() > 0) {
					useForeignKey = true;
				}
			}

			builder.addMethod(methodBuilder.build());
		}

		{
			// onUpgrade
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("onUpgrade").addAnnotation(Override.class).addModifiers(Modifier.PUBLIC);
			methodBuilder.addParameter(SQLiteDatabase.class, "database");
			methodBuilder.addParameter(Integer.TYPE, "oldVersion");
			methodBuilder.addParameter(Integer.TYPE, "newVersion");
			methodBuilder.addJavadoc("onUpgrade\n");

			Collections.reverse(orderedEntities);

			methodBuilder.addCode("// drop tables\n");
			for (SQLEntity item : orderedEntities) {
				if (schema.isLogEnabled()) {
					methodBuilder.addCode("$T.info(\"DDL: %s\",$T.DROP_TABLE_SQL);\n", Logger.class, BindTableGenerator.tableClassName(item));
				}
				methodBuilder.addCode("database.execSQL($T.DROP_TABLE_SQL);\n", BindTableGenerator.tableClassName(item));
			}

			// reorder entities
			Collections.reverse(orderedEntities);

			methodBuilder.addCode("\n");
			methodBuilder.addCode("// generate tables\n");

			for (SQLEntity item : orderedEntities) {
				if (schema.isLogEnabled()) {
					methodBuilder.addCode("$T.info(\"DDL: %s\",$T.CREATE_TABLE_SQL);\n", Logger.class, BindTableGenerator.tableClassName(item));
				}
				methodBuilder.addCode("database.execSQL($T.CREATE_TABLE_SQL);\n", BindTableGenerator.tableClassName(item));
			}

			builder.addMethod(methodBuilder.build());
		}

		// onConfigure
		if (useForeignKey) {
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("onConfigure").addAnnotation(Override.class).addModifiers(Modifier.PUBLIC);
			methodBuilder.addParameter(SQLiteDatabase.class, "database");
			methodBuilder.addJavadoc("onConfigure\n");
			methodBuilder.addCode("// configure database\n");

			// methodBuilder.addStatement("//database.setLocale");
			methodBuilder.addStatement("database.setForeignKeyConstraintsEnabled(true)");

			builder.addMethod(methodBuilder.build());
		}

		TypeSpec typeSpec = builder.build();
		JavaFile.builder(packageName, typeSpec).build().writeTo(filer);
	}

	private List<SQLEntity> generateOrderedEntitiesList(SQLiteDatabaseSchema schema) {
		List<SQLEntity> entities = schema.getEntitiesAsList();
		Collections.sort(entities, new Comparator<SQLEntity>() {

			@Override
			public int compare(SQLEntity lhs, SQLEntity rhs) {
				return lhs.getTableName().compareTo(rhs.getTableName());
			}
		});

		List<SQLEntity> list = schema.getEntitiesAsList();
		
		EntityUtility<SQLEntity> sorder = new EntityUtility<SQLEntity>(list) {

			@Override
			public Collection<SQLEntity> getDependencies(SQLEntity item) {
				return item.referedEntities;
			}

			@Override
			public void generateError(SQLEntity item) {
				throw new CircularRelationshipException(item);
			}
		};

		return sorder.order();
	}

	/**
	 * <p>
	 * Generate execute method
	 * </p>
	 * 
	 * @param daoFactoryName
	 */
	public void generateMethodExecute(String daoFactoryName) {
		String transationExecutorName = "Transaction";
		ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName.get(className("AbstractTransaction"), className(daoFactoryName));
		builder.addType(
				TypeSpec.interfaceBuilder(transationExecutorName).addModifiers(Modifier.PUBLIC).addSuperinterface(parameterizedTypeName).addJavadoc("interface to define transactions\n").build());

		MethodSpec.Builder executeMethod = MethodSpec.methodBuilder("execute").addModifiers(Modifier.PUBLIC, Modifier.SYNCHRONIZED).addParameter(className(transationExecutorName), "transaction");

		executeMethod.addCode("$T connection=openDatabase();\n", SQLiteDatabase.class);

		executeMethod.beginControlFlow("try");
		executeMethod.addCode("connection.beginTransaction();\n");

		executeMethod.beginControlFlow("if (transaction!=null && transaction.onExecute(this))");
		executeMethod.addCode("connection.setTransactionSuccessful();\n");
		executeMethod.endControlFlow();

		executeMethod.nextControlFlow("finally");
		executeMethod.addCode("connection.endTransaction();\n");
		executeMethod.addCode("close();\n");
		executeMethod.endControlFlow();

		// generate javadoc
		executeMethod.addJavadoc("<p>executes a transaction. This method is synchronized to avoid concurrent problems. The database will be open in write mode.</p>\n");
		executeMethod.addJavadoc("\n");
		executeMethod.addJavadoc("@param transaction transaction to execute\n");

		builder.addMethod(executeMethod.build());
	}

}
