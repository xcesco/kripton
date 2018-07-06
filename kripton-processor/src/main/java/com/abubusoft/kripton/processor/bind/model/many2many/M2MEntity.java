/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.abubusoft.kripton.processor.bind.model.many2many;

import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.BaseProcessor;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

// TODO: Auto-generated Javadoc
/**
 * The Class M2MEntity.
 */
public class M2MEntity extends M2MBase {
	
	public TypeName propertyPrimaryKey;

	public TypeName propertyKey1;

	public TypeName propertyKey2;

	/** The package name. */
	private String packageName;

	/** The entity 1 name. */
	public ClassName entity1Name;

	/** The entity 2 name. */
	public ClassName entity2Name;

	/** The id name. */
	public String idName;

	/**
	 * Gets the package name.
	 *
	 * @return the package name
	 */
	public String getPackageName() {
		return packageName;
	}

	/** The name. */
	public String name;

	/** The table name. */
	public String tableName;

	/** The dao name. */
	public ClassName daoName;

	/** The need to create. */
	public boolean needToCreate;
	
	/** The dao element. */
	public TypeElement daoElement;

	/** The generate methods. */
	public boolean generateMethods;

	/**
	 * Instantiates a new m 2 M entity.
	 *
	 * @param daoElement the dao element
	 * @param packageName the package name
	 * @param entityName the entity name
	 * @param daoClazzName the dao clazz name
	 * @param entity1ClazzName the entity 1 clazz name
	 * @param entity2ClazzName the entity 2 clazz name
	 * @param idName the id name
	 * @param tableName the table name
	 * @param needToCreate the need to create
	 * @param generatedMethods the generated methods
	 */
	public M2MEntity(TypeElement daoElement, String packageName, String entityName, ClassName daoClazzName, ClassName entity1ClazzName, ClassName entity2ClazzName, String idName, String tableName, boolean needToCreate, boolean generatedMethods) {
		this.packageName = packageName;
		this.entity1Name = entity1ClazzName;
		this.entity2Name = entity2ClazzName;
		this.daoName = daoClazzName;
		this.idName = idName;
		this.name = entityName;
		this.tableName = StringUtils.hasText(tableName) ? tableName : (CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.name));
		this.needToCreate = needToCreate;
		this.daoElement=daoElement;
		this.generateMethods=generatedMethods;
	}

	/**
	 * Extract class name.
	 *
	 * @param fullName the full name
	 * @return the string
	 */
	public static String extractClassName(String fullName) {
		int l = fullName.lastIndexOf(".");

		return fullName.substring(l + 1);
	}

	/**
	 * Gets the class name.
	 *
	 * @return the class name
	 */
	public ClassName getClassName() {
		return TypeUtility.className(this.packageName, this.name);
	}

	/**
	 * Works with @BindDaoMany2Many and @BindDao to extract entity name.
	 * @param schema 
	 *
	 * @param daoElement the dao element
	 * @return the m 2 M entity
	 */
	public static M2MEntity extractEntityManagedByDAO(TypeElement daoElement) {
		ClassName entity1 = null;
		ClassName entity2 = null;
		String prefixId = null;
		String tableName = null;
		String entityName = null;
		PackageElement pkg = null;
		String packageName = null;
		boolean needToCreate = true;
		boolean generatedMethods=true;
		
		
		if (daoElement.getAnnotation(BindDaoMany2Many.class) != null) {
			entity1 = TypeUtility.className(AnnotationUtility.extractAsClassName(daoElement, BindDaoMany2Many.class, AnnotationAttributeType.ENTITY_1));
			entity2 = TypeUtility.className(AnnotationUtility.extractAsClassName(daoElement, BindDaoMany2Many.class, AnnotationAttributeType.ENTITY_2));
			prefixId = AnnotationUtility.extractAsString(daoElement, BindDaoMany2Many.class, AnnotationAttributeType.ID_NAME);
			tableName = AnnotationUtility.extractAsString(daoElement, BindDaoMany2Many.class, AnnotationAttributeType.TABLE_NAME);
			
			generatedMethods=AnnotationUtility.extractAsBoolean(daoElement, BindDaoMany2Many.class, AnnotationAttributeType.METHODS);
			
			entityName = entity1.simpleName() + entity2.simpleName();
			pkg = BaseProcessor.elementUtils.getPackageOf(daoElement);
			packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();
		}

		if (daoElement.getAnnotation(BindDao.class) != null) {
			// we have @BindDao
			String derived = AnnotationUtility.extractAsClassName(daoElement, BindDao.class, AnnotationAttributeType.VALUE);
			ClassName clazz = TypeUtility.className(derived);

			packageName = clazz.packageName();
			entityName = clazz.simpleName();

			String tableTemp = AnnotationUtility.extractAsClassName(daoElement, BindDao.class, AnnotationAttributeType.TABLE_NAME);
			if (StringUtils.hasText(tableTemp)) {
				tableName = tableTemp;
			}

			needToCreate = false;
		}

		M2MEntity entity = new M2MEntity(daoElement, packageName, entityName, TypeUtility.className(daoElement.asType().toString()), entity1, entity2, prefixId, tableName, needToCreate, generatedMethods);

		return entity;
	}

	/**
	 * Gets the qualified name.
	 *
	 * @return the qualified name
	 */
	public String getQualifiedName() {
		if (StringUtils.hasText(packageName)) {
			return packageName+"."+name;
		}
		return name;
	}

	/**
	 * Gets the simple name.
	 *
	 * @return the simple name
	 */
	public String getSimpleName() {
		return name;
	}
	
	/**
	 * Gets the dao qualified name.
	 *
	 * @return the dao qualified name
	 */
	public String getDaoQualifiedName() {
		return daoElement.getQualifiedName().toString();
	}

}
