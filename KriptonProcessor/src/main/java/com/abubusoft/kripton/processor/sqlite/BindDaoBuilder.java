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

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindUpdate;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.processor.core.JavadocUtility;
import com.abubusoft.kripton.processor.core.reflect.MethodUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.MethodWithoutSupportedAnnotationException;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelElementVisitor;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;

/**
 * Dao generator
 * 
 * @author xcesco
 *
 */
public class BindDaoBuilder implements SQLiteModelElementVisitor {

	//public static final String PREFIX = "Bind";
	
	public static final String SUFFIX = "$Impl";
	
	protected Elements elementUtils;
	protected Filer filer;
	private Builder builder;
	private SQLDaoDefinition currentDaoDefinition;

	public BindDaoBuilder(SQLiteDatabaseSchema model, Elements elementUtils, Filer filer) {
		this.elementUtils = elementUtils;
		this.filer = filer;
	}

	public static void execute(Elements elementUtils, Filer filer, SQLiteDatabaseSchema model) throws Exception {
		BindDaoBuilder visitor = new BindDaoBuilder(model, elementUtils, filer);

		for (SQLDaoDefinition item : model.getCollection()) {
			item.accept(visitor);
		}
	}

	@Override
	public void visit(SQLDaoDefinition value) throws Exception {
		currentDaoDefinition = value;

		String classTableName = daoName(value);

		PackageElement pkg = elementUtils.getPackageOf(value.getElement());
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();

		builder = TypeSpec.classBuilder(classTableName).superclass(AbstractDao.class).addSuperinterface(typeName(value.getElement())).addModifiers(Modifier.PUBLIC);

		// javadoc for class
		builder.addJavadoc("<p>");
		builder.addJavadoc("\nDAO implementation for entity <code>$L</code>, based on interface <code>$L</code>\n", value.getEntity().getSimpleName(), value.getElement().getSimpleName().toString());
		builder.addJavadoc("</p>\n");
		JavadocUtility.generateJavadocGeneratedBy(builder);
		builder.addJavadoc(" @see $T\n", TypeUtility.className(value.getEntityClassName()));
		builder.addJavadoc(" @see $T\n", TypeUtility.className(value.getElement().getQualifiedName().toString()));
		builder.addJavadoc(" @see $T\n", BindTableGenerator.tableClassName(value.getEntity()));
		

		{
			// constructor
			MethodSpec.Builder methodBuilder = MethodSpec.constructorBuilder().addModifiers(Modifier.PUBLIC).addParameter(TypeUtility.className(value.getParent().getGeneratedClassName()), "dataSet");
			methodBuilder.addCode("super(dataSet);\n");
			builder.addMethod(methodBuilder.build());
		}

		// define column name set
		for (SQLiteModelMethod item : value.getCollection()) {
			item.accept(this);
		}

		TypeSpec typeSpec = builder.build();

		JavaFile.Builder fileBuilder = JavaFile.builder(packageName, typeSpec);
		fileBuilder.skipJavaLangImports(true);

		fileBuilder.build().writeTo(filer);
	}

	/**
	 * @param value
	 * @return
	 */
	public static String daoName(SQLDaoDefinition value) {
		String classTableName = value.getName();
		classTableName =  classTableName+ SUFFIX;
		return classTableName;
	}
	
	public static TypeName daoTypeName(SQLDaoDefinition value) {
		return TypeUtility.typeName(value.getElement(), SUFFIX);
	}
	
	public static TypeName daoInterfaceTypeName(SQLDaoDefinition value) {
		return TypeUtility.typeName(value.getElement());
	}

	@Override
	public void visit(SQLiteModelMethod value) throws Exception {
		if (value.getAnnotation(BindInsert.class) != null) {
			SQLiteInsertBuilder.generate(elementUtils, builder, value);
		} else if (value.getAnnotation(BindUpdate.class) != null) {
			SQLiteModifyBuilder.generate(elementUtils, builder, value, true);
		} else if (value.getAnnotation(BindDelete.class) != null) {
			SQLiteModifyBuilder.generate(elementUtils, builder, value, false);
		} else if (value.getAnnotation(BindSelect.class) != null) {
			MethodUtility.generateSelect(elementUtils, builder, value);
		} else {
			// method without supported annotation
			throw (new MethodWithoutSupportedAnnotationException(currentDaoDefinition, value));
		}

	}

}
