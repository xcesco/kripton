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
package com.abubusoft.kripton.processor.sqlite.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ModelBucket;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.squareup.javapoet.ClassName;

public class SQLiteDatabaseSchema extends ModelBucket<SQLDaoDefinition, TypeElement> {

	public Converter<String, String> classNameConverter = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);

	public Converter<String, String> columnNameConverter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);

	protected Map<String, SQLEntity> entities = new HashMap<String, SQLEntity>();

	protected Map<String, SQLEntity> entitiesBySimpleName = new HashMap<String, SQLEntity>();

	public List<String> sqlForCreate = new ArrayList<String>();

	public List<String> sqlForDrop = new ArrayList<String>();

	public String fileName;

	public String generatedClassName;

	/**
	 * @return the generatedClassName
	 */
	public String getGeneratedClassName() {
		return generatedClassName;
	}

	public int version;

	public boolean generateLog;

	public boolean generateAsyncTask;

	public boolean generateCursor;

	public boolean generateSchema;

	/**
	 * if <code>true</code>, content provider is generated.
	 */
	public boolean generateContentProvider;

	public SQLiteModelContentProvider contentProvider;

	private Map<String, Set<SQLProperty>> propertyBySimpleName = new HashMap<>();

	public SQLiteDatabaseSchema(TypeElement item, String schemaFileName, int schemaVersion, boolean schema, boolean log, boolean asyncTask, boolean generateCursor) {
		super(item.getSimpleName().toString(), item);

		this.fileName = schemaFileName;
		this.version = schemaVersion;
		this.generateLog = log;
		this.generateAsyncTask = asyncTask;
		this.generateCursor = generateCursor;
		this.generateSchema = schema;
		this.generatedClassName = "Bind" + getName();
		this.generateContentProvider = false;
		this.contentProvider = null;

	}

	public void clear() {
		entities.clear();
		entitiesBySimpleName.clear();
	}

	public void addEntity(SQLEntity value) {
		entities.put(value.getName(), value);
		entitiesBySimpleName.put(value.getSimpleName().toString().toLowerCase(), value);
		Set<SQLProperty> listEntity = null;

		// update map property name -> property collection with same name
		for (SQLProperty p : value.getCollection()) {
			listEntity = propertyBySimpleName.get(p.getName());
			if (listEntity == null) {
				listEntity = new HashSet<>();
			}
			checkName(listEntity, p);
			listEntity.add(p);
			propertyBySimpleName.put(p.getName().toLowerCase(), listEntity);

		}
	}

	/**
	 * property in different class, but same name, must have same column name.
	 * 
	 * @param listEntity
	 * @param p
	 */
	private void checkName(Set<SQLProperty> listEntity, SQLProperty p) {
		for (SQLProperty item: listEntity) {
			AssertKripton.assertTrueOrInvalidPropertyName(item.columnName.equals(p.columnName), item, p);
		}
		
	}

	public Collection<SQLEntity> getEntities() {
		return entities.values();
	}

	public List<SQLEntity> getEntitiesAsList() {
		return new ArrayList<>(entities.values());
	}

	public SQLEntity getEntity(String entityClassName) {
		return entities.get(entityClassName);
	}

	public SQLEntity getEntityBySimpleName(String entityName) {
		if (entityName == null)
			return null;

		return entitiesBySimpleName.get(entityName.toLowerCase());
	}

	public Set<SQLProperty> getPropertyBySimpleName(String propertyName) {
		if (propertyName == null)
			return null;

		return this.propertyBySimpleName.get(propertyName.toLowerCase());
	}

	/**
	 * get a
	 * 
	 * @param propertyName
	 * @return
	 */
	public String getExactPropertyBySimpleName(SQLiteModelMethod method, String propertyName) {
		Set<SQLProperty> propertiesSet = getPropertyBySimpleName(propertyName);
		Set<String> set = new HashSet<String>();
		String result = null;

		for (SQLProperty item : propertiesSet) {
			result = item.columnName;
			set.add(item.columnName);
		}

		AssertKripton.assertTrueOrInvalidMethodSignException(result != null && set.size() == 1, method, "in JQL attribute can not translate property %s", propertyName);

		return result;
	}

	public boolean isLogEnabled() {
		return generateLog;
	}

	public String contentProviderUri() {
		if (!generateContentProvider)
			return "";

		return contentProvider.getUri();

	}

	public ClassName getGeneratedClass() {
		String packageName=getElement().asType().toString();
		return TypeUtility.className(packageName.substring(0, packageName.lastIndexOf("."))+"."+getGeneratedClassName());
	}

}
