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
package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.util.Map.Entry;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.processor.BindDataSourceSubProcessor;
import com.abubusoft.kripton.processor.bind.BindTypeContext;
import com.abubusoft.kripton.processor.bind.JavaWriterHelper;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ManagedPropertyPersistenceHelper;
import com.abubusoft.kripton.processor.core.ManagedPropertyPersistenceHelper.PersistType;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.core.JavadocUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelElementVisitor;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.utils.AnnotationProcessorUtilis;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;

/**
 * Dao generator
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class BindDaoBuilder implements SQLiteModelElementVisitor {

	/**
	 * Suffix to add to DAO interface to define DAO implementation typeName.
	 */
	public static final String SUFFIX = "Impl";

	protected Elements elementUtils;
	protected Filer filer;
	private Builder builder;
	private SQLDaoDefinition currentDaoDefinition;

	public BindDaoBuilder(Elements elementUtils, Filer filer) {
		this.elementUtils = elementUtils;
		this.filer = filer;
	}

	public static void generate(Elements elementUtils, Filer filer, SQLiteDatabaseSchema schema) throws Exception {
		BindDaoBuilder visitor = new BindDaoBuilder(elementUtils, filer);

		for (SQLDaoDefinition item : schema.getCollection()) {
			item.accept(visitor);
		}
	}

	public static void generateSecondRound(Elements elementUtils, Filer filer, SQLiteDatabaseSchema schema) throws Exception {
		BindDaoBuilder visitor = new BindDaoBuilder(elementUtils, filer);

		for (SQLDaoDefinition item : schema.getCollection()) {
			if (item.isGenerated()) {
				item.accept(visitor);
			}
		}
	}

	@Override
	public void visit(SQLDaoDefinition value) throws Exception {
		currentDaoDefinition = value;

		String classTableName = daoName(value);

		PackageElement pkg = elementUtils.getPackageOf(value.getElement());
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();

		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindDao.class, packageName, classTableName);

		builder = TypeSpec.classBuilder(classTableName).superclass(AbstractDao.class)
				.addSuperinterface(typeName(value.getElement()))
				.addModifiers(Modifier.PUBLIC);
		
		for (TypeName item: value.implementedInterface) {
			builder.addSuperinterface(item);
		}

		BindTypeContext context = new BindTypeContext(builder, TypeUtility.typeName(packageName, classTableName), Modifier.PRIVATE);
		String entityName=BindDataSourceSubProcessor.generateEntityName(value, value.getEntity());
				
		// javadoc for class
		builder.addJavadoc("<p>");
		builder.addJavadoc("\nDAO implementation for entity <code>$L</code>, based on interface <code>$L</code>\n", entityName, value.getElement().getSimpleName().toString());
		builder.addJavadoc("</p>\n\n");
		JavadocUtility.generateJavadocGeneratedBy(builder);
		builder.addJavadoc(" @see $T\n", TypeUtility.className(value.getEntityClassName()));
		builder.addJavadoc(" @see $T\n", TypeUtility.className(value.getElement().getQualifiedName().toString()));
		builder.addJavadoc(" @see $T\n", BindTableGenerator.tableClassName(value, value.getEntity()));

		{
			// constructor
			MethodSpec.Builder methodBuilder = MethodSpec.constructorBuilder().addModifiers(Modifier.PUBLIC).addParameter(value.getParent().getGeneratedClass(), "dataSet");
			methodBuilder.addCode("super(dataSet);\n");
			builder.addMethod(methodBuilder.build());
		}

		// define column typeName set
		for (SQLiteModelMethod item : value.getCollection()) {
			item.accept(this);
		}

		// generate serializer params
		for (Entry<TypeName, String> item : currentDaoDefinition.managedParams.entrySet()) {
			ManagedPropertyPersistenceHelper.generateParamSerializer(context, item.getValue(), item.getKey(), PersistType.BYTE);
			ManagedPropertyPersistenceHelper.generateParamParser(context, item.getValue(), item.getKey(), PersistType.BYTE);
		}

		TypeSpec typeSpec = builder.build();

		JavaWriterHelper.writeJava2File(filer, packageName, typeSpec);		
	}

	/**
	 * @param value
	 * @return typeName of dao
	 */
	public static String daoName(SQLDaoDefinition value) {
		String classTableName = value.getName();
		classTableName = classTableName + SUFFIX;
		return classTableName;
	}

	public static TypeName daoTypeName(SQLDaoDefinition value) {				
		return TypeUtility.mergeTypeNameWithSuffix(value.getTypeName(), SUFFIX);
	}

	public static TypeName daoInterfaceTypeName(SQLDaoDefinition value) {
		return value.getTypeName();
	}

	@Override
	public void visit(SQLiteModelMethod value) throws Exception {
		if (value.getAnnotation(BindSqlInsert.class) != null) {
			SqlInsertBuilder.generate(elementUtils, builder, value);
		} else if (value.getAnnotation(BindSqlUpdate.class) != null) {
			SqlModifyBuilder.generate(elementUtils, builder, value);
		} else if (value.getAnnotation(BindSqlDelete.class) != null) {
			SqlModifyBuilder.generate(elementUtils, builder, value);
		} else if (value.getAnnotation(BindSqlSelect.class) != null) {
			SqlSelectBuilder.generateSelect(elementUtils, builder, value);
		} else {
			// method without supported annotation
			AssertKripton.failWithMethodWithoutSupportedAnnotationException(value);
		}

	}

}
