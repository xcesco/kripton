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
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.core.JavadocUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.utils.AnnotationProcessorUtilis;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * Generates database class
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class BindDaoFactoryBuilder extends AbstractBuilder  {

	public static final String PREFIX = "Bind";
	
	public static final String SUFFIX = "DaoFactory";

	public BindDaoFactoryBuilder(Elements elementUtils, Filer filer, SQLiteDatabaseSchema model) {
		super(elementUtils, filer, model);
	}
	
	public static String generateDaoFactoryName(SQLiteDatabaseSchema schema)
	{
		String schemaName = schema.getName();
		schemaName=PREFIX+schemaName;		
		
		schemaName=schemaName.replace(BindDataSourceBuilder.SUFFIX, SUFFIX);
		
		return schemaName;
	}

	/**
	 * Build dao factory interface
	 * 
	 * @param elementUtils
	 * @param filer
	 * @param schema
	 * 
	 * @return
	 * 		schema typeName
	 * 
	 * @throws Exception
	 */
	public String buildDaoFactoryInterface(Elements elementUtils, Filer filer, SQLiteDatabaseSchema schema) throws Exception {
		String schemaName =  generateDaoFactoryName(schema);
		
		PackageElement pkg = elementUtils.getPackageOf(schema.getElement());
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();
		
		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindDataSource.class, packageName, schemaName);
		classBuilder=buildDaoFactoryInterfaceInternal(elementUtils, filer, schema);
		TypeSpec typeSpec = classBuilder.build();
		JavaFile.builder(packageName, typeSpec).build().writeTo(filer);
		
		return schemaName;
	}
	
	/**
	 * Build dao factory interface
	 * 
	 * @param elementUtils
	 * @param filer
	 * @param schema
	 * 
	 * @return
	 * 		schema typeName
	 * 
	 * @throws Exception
	 */
	public TypeSpec.Builder buildDaoFactoryInterfaceInternal(Elements elementUtils, Filer filer, SQLiteDatabaseSchema schema) throws Exception {
		String schemaName =  schema.getName();
		schemaName=PREFIX+schemaName;		
		
		schemaName=schemaName.replace(BindDataSourceBuilder.SUFFIX, SUFFIX);
		
		classBuilder = TypeSpec.interfaceBuilder(schemaName).addModifiers(Modifier.PUBLIC).addSuperinterface(BindDaoFactory.class);
		
		classBuilder.addJavadoc("<p>\n");
		classBuilder.addJavadoc("Represents dao factory interface for $L.\n",schema.getName());
		classBuilder.addJavadoc("This class expose database interface through Dao attribute.\n",schema.getName());
		classBuilder.addJavadoc("</p>\n\n");
		
		JavadocUtility.generateJavadocGeneratedBy(classBuilder);
		classBuilder.addJavadoc("@see $T\n", TypeUtility.typeName(schema.getElement()));
		for (SQLDaoDefinition dao : schema.getCollection()) {
			TypeName daoName = BindDaoBuilder.daoInterfaceTypeName(dao);
			TypeName daoImplName = BindDaoBuilder.daoTypeName(dao); 
			classBuilder.addJavadoc("@see $T\n", daoName);
			classBuilder.addJavadoc("@see $T\n", daoImplName);
			classBuilder.addJavadoc("@see $T\n", TypeUtility.typeName(dao.getEntity().getElement()));
		}
						
		for (SQLDaoDefinition dao : schema.getCollection()) {
			TypeName daoImplName =  BindDaoBuilder.daoTypeName(dao);
			
			// dao with external connections
			{
				MethodSpec.Builder methodBuilder=MethodSpec.methodBuilder("get"+dao.getName())
						.addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
						.addJavadoc("\nretrieve dao $L\n", dao.getName())
						.returns(daoImplName);
				classBuilder.addMethod(methodBuilder.build());
			}
		}		
		
		return classBuilder;
	}

}
