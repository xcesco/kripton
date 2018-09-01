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

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.sqlite.BindDaoFactory;
import com.abubusoft.kripton.processor.BindDataSourceSubProcessor;
import com.abubusoft.kripton.processor.bind.JavaWriterHelper;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.core.JavadocUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.utils.AnnotationProcessorUtilis;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * Generates database class.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class BindDaoFactoryBuilder extends AbstractBuilder {

	/** The Constant PREFIX. */
	public static final String PREFIX = "Bind";

	/** The Constant SUFFIX. */
	public static final String SUFFIX = "DaoFactory";

	/**
	 * Instantiates a new bind dao factory builder.
	 *
	 * @param elementUtils the element utils
	 * @param filer the filer
	 * @param model the model
	 */
	public BindDaoFactoryBuilder(Elements elementUtils, Filer filer, SQLiteDatabaseSchema model) {
		super(elementUtils, filer, model);
	}

	/**
	 * Given a schema, generate its daoFactory name.
	 *
	 * @param schema the schema
	 * @return the string
	 */
	public static String generateDaoFactoryName(SQLiteDatabaseSchema schema) {
		String schemaName = buildDaoFactoryName(schema);

		return schemaName;
	}
	
	/**
	 * Given a schema, generate its daoFactory name.
	 *
	 * @param schema the schema
	 * @return the string
	 */
	public static ClassName generateDaoFactoryClassName(SQLiteDatabaseSchema schema) {
		String schemaName = buildDaoFactoryName(schema);

		String packageName=TypeUtility.extractPackageName(schema.getElement());
		return ClassName.get(packageName, schemaName);
	}

	/**
	 * Build dao factory interface.
	 *
	 * @param elementUtils the element utils
	 * @param filer the filer
	 * @param schema the schema
	 * @return schema typeName
	 * @throws Exception the exception
	 */
	public String buildDaoFactoryInterface(Elements elementUtils, Filer filer, SQLiteDatabaseSchema schema) throws Exception {
		String schemaName = generateDaoFactoryName(schema);

		PackageElement pkg = elementUtils.getPackageOf(schema.getElement());
		String packageName = pkg.isUnnamed() ? "" : pkg.getQualifiedName().toString();

		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindDataSource.class, packageName, schemaName);
		classBuilder = buildDaoFactoryInterfaceInternal(elementUtils, filer, schema);
		TypeSpec typeSpec = classBuilder.build();
		JavaWriterHelper.writeJava2File(filer, packageName, typeSpec);

		return schemaName;
	}

	/**
	 * Build dao factory interface.
	 *
	 * @param elementUtils the element utils
	 * @param filer the filer
	 * @param schema the schema
	 * @return schema typeName
	 * @throws Exception the exception
	 */
	public TypeSpec.Builder buildDaoFactoryInterfaceInternal(Elements elementUtils, Filer filer, SQLiteDatabaseSchema schema) throws Exception {
		String schemaName = buildDaoFactoryName(schema);

		classBuilder = TypeSpec.interfaceBuilder(schemaName).addModifiers(Modifier.PUBLIC).addSuperinterface(BindDaoFactory.class);

		classBuilder.addJavadoc("<p>\n");
		classBuilder.addJavadoc("Represents dao factory interface for $L.\n", schema.getName());
		classBuilder.addJavadoc("This class expose database interface through Dao attribute.\n", schema.getName());
		classBuilder.addJavadoc("</p>\n\n");

		JavadocUtility.generateJavadocGeneratedBy(classBuilder);
		classBuilder.addJavadoc("@see $T\n", TypeUtility.typeName(schema.getElement()));

		for (SQLiteDaoDefinition dao : schema.getCollection()) {
			TypeName daoName = BindDaoBuilder.daoInterfaceTypeName(dao);
			TypeName daoImplName = BindDaoBuilder.daoTypeName(dao);

			classBuilder.addJavadoc("@see $T\n", daoName);
			classBuilder.addJavadoc("@see $T\n", daoImplName);

			String entity = BindDataSourceSubProcessor.generateEntityName(dao, dao.getEntity());

			classBuilder.addJavadoc("@see $T\n", TypeUtility.typeName(entity));
		}

		for (SQLiteDaoDefinition dao : schema.getCollection()) {
			TypeName daoImplName = BindDaoBuilder.daoTypeName(dao);

			// dao with external connections
			{
				MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("get" + dao.getName())
						.addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT).addJavadoc("\nretrieve dao $L\n", dao.getName())
						.returns(daoImplName);
				classBuilder.addMethod(methodBuilder.build());
			}
		}

		return classBuilder;
	}

	public static String buildDaoFactoryName(SQLiteDatabaseSchema schema) {
		String schemaName = schema.getName();
		schemaName = PREFIX + schemaName;

		schemaName = schemaName.replace(BindDataSourceBuilder.SUFFIX, SUFFIX);
		return schemaName;
	}

}
