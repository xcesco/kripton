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

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.core.JavadocUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;
import com.squareup.javapoet.TypeVariableName;

import android.os.AsyncTask;

/**
 * Utility class to generate async task for database operations
 */
public class BindAsyncTaskBuilder {

	public static final String PREFIX = "Bind";

	public static final String SUFFIX = "AsyncTask";

	private static Builder builder;

	/**
	 * Generate async task for database operations
	 * 
	 * @throws IOException
	 */
	public static void generate(Elements elementUtils, Filer filer, SQLiteDatabaseSchema schema) throws IOException {		
		String className = schema.getName();

		String dataSourceName = PREFIX + className;

		className = className.replaceAll(BindDataSourceBuilder.SUFFIX, SUFFIX);
		className = PREFIX + className;

		PackageElement pkg = elementUtils.getPackageOf(schema.getElement());
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();

		//@formatter:off
		builder = TypeSpec.classBuilder(className).addModifiers(Modifier.PUBLIC).addModifiers(Modifier.ABSTRACT)
				.addTypeVariable(TypeVariableName.get("I"))
				.addTypeVariable(TypeVariableName.get("U"))
				.addTypeVariable(TypeVariableName.get("R"));
		//@formatter:on

		// javadoc
		builder.addJavadoc("\n<p>\nSpecialized async task to make async database operation on activity\n</p>\n");
		builder.addJavadoc("\n<p>\nUnlike standard async task, for an instance of this class can be used many time.\n</p>\n");
		builder.addJavadoc("\n<p>\nWhen method <code>execute</code> is invoked, an inner async task is created.\n</p>\n\n");
		JavadocUtility.generateJavadocGeneratedBy(builder);
		builder.addJavadoc("@see $T\n", className(className.replaceAll(SUFFIX, BindDaoFactoryBuilder.SUFFIX)));
		builder.addJavadoc("@see $T\n", className(dataSourceName));

		// build constructors
		builder.addMethod(MethodSpec.constructorBuilder().addModifiers(Modifier.PUBLIC).addCode("this(true);")
				.addJavadoc("<p>\nWith this constructor, a read only database connection will be used\n</p>\n")
				.build());
		
		builder.addMethod(MethodSpec.constructorBuilder().addModifiers(Modifier.PUBLIC)
				.addJavadoc("<p>\nWith this constructor it is possible to specify which type of database use in async task\n</p>\n\n")
				.addJavadoc("@param readOnlyTask if true, force async task to use read only database connection\n")
				.addParameter(Boolean.TYPE, "readOnlyTask").addCode("this.readOnlyTask = readOnlyTask;").build());

		// define fields
		{
			FieldSpec.Builder fieldSpec = FieldSpec.builder(Boolean.TYPE, "readOnlyTask", Modifier.PROTECTED);
			fieldSpec.addJavadoc("If <code>true</code> indicates database operations are only read operations\n\n");
			builder.addField(fieldSpec.build());
		}

		{
			ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName.get(TypeUtility.className(AsyncTask.class), TypeUtility.typeName("I"), TypeUtility.typeName("U"), TypeUtility.typeName("R"));
			FieldSpec.Builder fieldSpec = FieldSpec.builder(parameterizedTypeName, "asyncTask", Modifier.PROTECTED);
			fieldSpec.addJavadoc("Async task wrapped by this class\n\n");
			builder.addField(fieldSpec.build());
		}

		// build methods
		builder.addMethod(MethodSpec.methodBuilder("onPreExecute").addModifiers(Modifier.PUBLIC).addJavadoc("Use this method for operations on UI-thread before start execution\n").build());
		
		builder.addMethod(MethodSpec.methodBuilder("onExecute")
				.returns(TypeUtility.typeName("R"))
				.addParameter(TypeUtility.typeName(className.replaceAll(SUFFIX, BindDaoFactoryBuilder.SUFFIX)), "daoFactory")				
				.addJavadoc("Method used to encapsulate operations on datasource\n\n@param daoFactory\n\tdao factory. Use it to retrieve DAO\n@return\n\tresult of operation (list, bean, etc)\n")
				.addModifiers(Modifier.PUBLIC)
				.addModifiers(Modifier.ABSTRACT).build());
		builder.addMethod(MethodSpec.methodBuilder("onFinish").addParameter(TypeUtility.typeName("R"), "result").addModifiers(Modifier.PUBLIC).addModifiers(Modifier.ABSTRACT).addJavadoc("Use this method for operations on UI-thread after execution\n").build());
		
		builder.addMethod(MethodSpec.methodBuilder("onProgressUpdate")
				.addModifiers(Modifier.PUBLIC)				
				.addParameter(ParameterSpec.builder(ArrayTypeName.of(TypeUtility.typeName("U")), "update").build()).varargs()
				.addJavadoc("Override this method to display operation progress on UI-Thread\n")
				.build());

		MethodSpec.Builder executeBuilder = MethodSpec.methodBuilder("execute")				
				.addParameter(ParameterSpec.builder(ArrayTypeName.of(TypeUtility.className("I")), "params")
						.addAnnotation(AnnotationSpec.builder(SuppressWarnings.class).addMember("value","$S", "unchecked").build())
						.build()).varargs(true)
				.addModifiers(Modifier.PUBLIC)
				.addJavadoc("Method to start operations.\n\n@param\n\tdata input\n");

		//@formatter:off
		TypeSpec.Builder anonymous = TypeSpec.anonymousClassBuilder("").addSuperinterface(ParameterizedTypeName.get(TypeUtility.className(AsyncTask.class), TypeUtility.className("I"), TypeUtility.className("U"), TypeUtility.className("R")));
		anonymous.addMethod(MethodSpec.methodBuilder("onPreExecute").addModifiers(Modifier.PUBLIC).addAnnotation(Override.class).addStatement("$L.this.onPreExecute()",className).build());
		
		anonymous.addMethod(MethodSpec.methodBuilder("doInBackground").addModifiers(Modifier.PUBLIC).returns(TypeUtility.typeName("R")).addAnnotation(Override.class)
				.addParameter(ParameterSpec.builder(ArrayTypeName.of(TypeUtility.className("I")), "params")
						.addAnnotation(AnnotationSpec.builder(SuppressWarnings.class).addMember("value","$S", "unchecked").build())
						.build()).varargs(true)
				.addStatement("$L dataSource=$L.instance()", dataSourceName, dataSourceName)
				.addStatement("R result=null")
				.addStatement("if (readOnlyTask) dataSource.openReadOnlyDatabase(); else dataSource.openWritableDatabase()")
				//.addStatement("$T sqlite=readOnlyTask ? dataSource.getReadableDatabase() : dataSource.getWritableDatabase()", SQLiteDatabase.class)
				.beginControlFlow("try")
				.addStatement("result=onExecute(dataSource)")
				.nextControlFlow("catch(Exception e)")
				.nextControlFlow("finally")
					.beginControlFlow("if (dataSource.isOpen())")
					.addStatement("dataSource.close()")
					.endControlFlow()
				.endControlFlow()
				.addStatement("return result").build());
		anonymous.addMethod(MethodSpec.methodBuilder("onProgressUpdate")
				.addModifiers(Modifier.PUBLIC)
				.addAnnotation(Override.class)
				.addParameter(ParameterSpec.builder(ArrayTypeName.of(TypeUtility.className("U"))
						, "values")
						.addAnnotation(AnnotationSpec.builder(SuppressWarnings.class).addMember("value","$S", "unchecked").build())
						.build())
				.varargs(true)
				.addStatement("$L.this.onProgressUpdate(values)", className)
				.build());
		
		anonymous.addMethod(MethodSpec.methodBuilder("onPostExecute")
				.addModifiers(Modifier.PUBLIC)
				.addAnnotation(Override.class)
				.addParameter(ParameterSpec.builder(TypeUtility.className("R"), "result").build())
				.addStatement("$L.this.onFinish(result)",className)
				.build());
		//@formatter:on

		//@formatter:off
		executeBuilder.addStatement("asyncTask=$L", anonymous.build());
		executeBuilder.addStatement("asyncTask.execute(params)", anonymous.build());
		builder.addMethod(executeBuilder.build());
					
		anonymous.addMethod(MethodSpec.methodBuilder("cancel")
				.addModifiers(Modifier.PUBLIC)
				.addModifiers(Modifier.FINAL)
				.addParameter(Boolean.TYPE, "mayInterruptIfRunning")
				.beginControlFlow("if (asyncTask!=null, args)")
				.addStatement("return asyncTask.cancel(mayInterruptIfRunning)")
				.endControlFlow()
				.addStatement("return false").build());

		
		// build BasicAsyncTask
		builder.addType(TypeSpec.classBuilder("Simple")
				.addJavadoc("Simple implementation of async task. It uses read only database.\n\n")
				.addJavadoc("@see $T\n", TypeUtility.typeName(className.replaceAll(SUFFIX, BindDaoFactoryBuilder.SUFFIX)))
				.addJavadoc("@see $T\n", TypeUtility.typeName(dataSourceName))
				.addModifiers(Modifier.PUBLIC)
				.addModifiers(Modifier.STATIC)
				.addModifiers(Modifier.ABSTRACT)
				.addTypeVariable(TypeVariableName.get("R"))
				.superclass(
						ParameterizedTypeName.get(TypeUtility.className(className),
								TypeUtility.className("Void"), 
								TypeUtility.className("Void"), 
								TypeUtility.className("R")
								)							
				).build());		
		//@formatter:on

		TypeSpec typeSpec = builder.build();
		JavaFile.builder(packageName, typeSpec).build().writeTo(filer);
	}

}
