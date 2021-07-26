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

import java.util.ArrayList;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.processor.bind.JavaWriterHelper;
import com.abubusoft.kripton.processor.core.ModelElementVisitor;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.core.JavadocUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteEntity;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.abubusoft.kripton.processor.utils.AnnotationProcessorUtilis;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;

import android.database.Cursor;


/**
 * The Class BindCursorBuilder.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class BindCursorBuilder extends AbstractBuilder implements ModelElementVisitor<SQLiteEntity, SQLProperty> {

	/** The Constant PREFIX. */
	public static final String PREFIX = "Bind";
	
	/** The Constant SUFFIX. */
	public static final String SUFFIX = "Cursor";
	
	/** The counter. */
	private int counter;

	/**
	 * Instantiates a new bind cursor builder.
	 *
	 * @param elementUtils the element utils
	 * @param filer the filer
	 * @param model the model
	 */
	public BindCursorBuilder(Elements elementUtils, Filer filer, SQLiteDatabaseSchema model) {
		super(elementUtils, filer, model);
	}

	/**
	 * Generate.
	 *
	 * @param elementUtils the element utils
	 * @param filer the filer
	 * @param schema the schema
	 * @throws Exception the exception
	 */
	public static void generate(Elements elementUtils, Filer filer, SQLiteDatabaseSchema schema) throws Exception {
		BindCursorBuilder visitor = new BindCursorBuilder(elementUtils, filer, schema);

		for (SQLiteEntity item : schema.getEntities()) {
			visitor.visit(schema, item);
		}

	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.core.ModelElementVisitor#visit(com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema, com.abubusoft.kripton.processor.core.ModelClass)
	 */
	@Override
	public void visit(SQLiteDatabaseSchema schema, SQLiteEntity entity) throws Exception {
		String classCursorName = PREFIX+entity.getSimpleName()+SUFFIX;		

		PackageElement pkg = elementUtils.getPackageOf(entity.getElement());
		String packageName = pkg.isUnnamed() ? "" : pkg.getQualifiedName().toString();
		
		ClassName className=TypeUtility.className(packageName,classCursorName);

		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindDataSource.class, packageName, classCursorName);
		classBuilder = TypeSpec.classBuilder(classCursorName).addModifiers(Modifier.PUBLIC);

		// javadoc for class
		classBuilder.addJavadoc("<p>");
		classBuilder.addJavadoc("\nCursor implementation for entity <code>$L</code>\n", entity.getSimpleName());
		classBuilder.addJavadoc("</p>\n");
		JavadocUtility.generateJavadocGeneratedBy(classBuilder);
		classBuilder.addJavadoc(" @see $T\n", TypeUtility.className(entity.getElement().getQualifiedName().toString()));

		//@formatter:off
		FieldSpec fieldSpec = FieldSpec.builder(Cursor.class, "cursor", Modifier.PROTECTED)
				.addJavadoc("Cursor used to read database\n")
				.build();
		//@formatter:on
		classBuilder.addField(fieldSpec);
		
		// add constructor
		//@formatter:off
		classBuilder.addMethod(MethodSpec.constructorBuilder()
				.addJavadoc("<p>Constructor</p>\n\n")
				.addJavadoc("@param cursor cursor used to read from database\n")
				.addParameter(Cursor.class, "cursor")
				.addCode("wrap(cursor);\n")
				.build());
		//@formatter:on

		// add wrap method
		//@formatter:off
		MethodSpec.Builder wrapMethodBuilder = MethodSpec.methodBuilder("wrap")
				.addJavadoc("<p>Wrap cursor with this class</p>\n\n")
				.addJavadoc("@param cursor cursor to include\n")
				.addModifiers(Modifier.PUBLIC)
				.addParameter(Cursor.class, "cursor")
				.returns(className)
				.addCode("this.cursor=cursor;\n");
		//@formatter:on
		counter=0;
		wrapMethodBuilder.addCode("\n");
		for (SQLProperty item : entity.getCollection()) {
			wrapMethodBuilder.addCode("index$L=cursor.getColumnIndex($S);\n", counter, item.columnName);
			counter++;
		}
		wrapMethodBuilder.addCode("\n");
		wrapMethodBuilder.addCode("return this;\n");
		
		classBuilder.addMethod(wrapMethodBuilder.build());
		
		// add execute method		
		classBuilder.addMethod(generateExecuteMethod(packageName, entity).build());
		
		// add execute listener method
		classBuilder.addMethod(generateExecuteListener(packageName, entity).build());

		// add create
		//@formatter:off
		classBuilder.addMethod(
				MethodSpec.methodBuilder("create")
				.addModifiers(Modifier.STATIC, Modifier.PUBLIC)
				.addParameter(Cursor.class, "cursor")
				.returns(className(packageName, classCursorName))
				.addJavadoc("<p>Create a binded cursor starting from a cursor</p>\n\n")
				.addJavadoc("@param cursor to wrap\n")
				.addCode("return new "+classCursorName+"(cursor);\n")				
				.build());
		//@formatter:on

		// define column typeName set
		counter=0;
		for (ModelProperty item : entity.getCollection()) {
			item.accept(this);
		}
		
		TypeSpec typeSpec = classBuilder.build();			
		JavaWriterHelper.writeJava2File(filer, packageName, typeSpec);

	}



	/**
	 * Generate execute method.
	 *
	 * @param packageName the package name
	 * @param entity the entity
	 * @return the method spec. builder
	 */
	private MethodSpec.Builder generateExecuteMethod(String packageName, SQLiteEntity entity) {		
		ParameterizedTypeName parameterizedReturnTypeName = ParameterizedTypeName.get(className(ArrayList.class), className(packageName,entity.getSimpleName()));
		
		//@formatter:off
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("execute")
				.addJavadoc("<p>Execute the cursor and read all rows from database.</p>\n\n")
				.addJavadoc("@return list of beans\n")
				.addModifiers(Modifier.PUBLIC)
				.returns(parameterizedReturnTypeName);
		//@formatter:on

		TypeName entityClass= typeName(entity.getElement());
		
		methodBuilder.addCode("\n");
		
		methodBuilder.addCode("$T resultList=new $T(cursor.getCount());\n",parameterizedReturnTypeName, parameterizedReturnTypeName);
		methodBuilder.addCode("$T resultBean=new $T();\n",entityClass, entityClass);
		methodBuilder.addCode("\n");
		methodBuilder.beginControlFlow("if (cursor.moveToFirst())");

		methodBuilder.beginControlFlow("do\n");			
		methodBuilder.addCode("resultBean=new $T();\n\n",entityClass);
		
		// generate mapping
		int i=0;
		for (ModelProperty item : entity.getCollection()) {			
			methodBuilder.addCode("if (index$L>=0 && !cursor.isNull(index$L)) { ",i,i);
			SQLTransformer.cursor2Java(entity, methodBuilder, entityClass, item, "resultBean", "cursor","index"+i+"");		
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
	
	/**
	 * Generate execute listener.
	 *
	 * @param packageName the package name
	 * @param entity the entity
	 * @return the method spec. builder
	 */
	private MethodSpec.Builder generateExecuteListener(String packageName, SQLiteEntity entity) {		
		String interfaceName="On" + entity.getSimpleName() + "Listener";
		
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
		
		ClassName interfaceType=TypeUtility.className(interfaceName);
		
		
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

		TypeName entityClass= typeName(entity.getElement());
		
		methodBuilder.addCode("$T resultBean=new $T();\n",entityClass, entityClass);
		methodBuilder.addCode("\n");
		methodBuilder.beginControlFlow("if (cursor.moveToFirst())");

		methodBuilder.beginControlFlow("do\n");					
		
		// reset mapping
		{
			int i=0;
			for (ModelProperty item : entity.getCollection()) {			
				methodBuilder.addCode("if (index$L>=0) { ",i);
				SQLTransformer.resetBean(methodBuilder, entityClass, "resultBean", item,  "cursor","index"+i+"");
				methodBuilder.addCode(";");
				methodBuilder.addCode("}\n");
				
				i++;
			}
		}
		methodBuilder.addCode("\n");
		
		// generate mapping
		{
			int i=0;
			for (ModelProperty item : entity.getCollection()) {			
				methodBuilder.addCode("if (index$L>=0 && !cursor.isNull(index$L)) { ",i,i);
				SQLTransformer.cursor2Java(entity, methodBuilder, entityClass, item, "resultBean", "cursor","index"+i+"");
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

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.core.ModelElementVisitor#visit(com.abubusoft.kripton.processor.core.ModelProperty)
	 */
	@Override
	public void visit(SQLProperty property) throws Exception {
		// add property index
		classBuilder.addField(FieldSpec.builder(Integer.TYPE, "index"+(counter++), Modifier.PROTECTED).addJavadoc("Index for column $S\n", property.getName()).build());

	}

	

}
