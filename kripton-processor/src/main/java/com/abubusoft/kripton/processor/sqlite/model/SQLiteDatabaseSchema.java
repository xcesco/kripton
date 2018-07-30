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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.annotation.BindDataSourceOptions;
import com.abubusoft.kripton.android.sqlite.NoCursorFactory;
import com.abubusoft.kripton.android.sqlite.NoDatabaseErrorHandler;
import com.abubusoft.kripton.android.sqlite.NoDatabaseLifecycleHandler;
import com.abubusoft.kripton.android.sqlite.NoPopulator;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.KriptonOptions;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.Finder;
import com.abubusoft.kripton.processor.core.ModelBucket;
import com.abubusoft.kripton.processor.core.TypeAdapterHelper;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.element.GeneratedTypeElement;
import com.abubusoft.kripton.processor.sqlite.FindSqlTypeAdapterVisitor;
import com.abubusoft.kripton.processor.sqlite.FindTasksVisitor;
import com.squareup.javapoet.ClassName;

/**
 * The Class SQLiteDatabaseSchema.
 */
public class SQLiteDatabaseSchema extends ModelBucket<SQLiteDaoDefinition, TypeElement> {

	/** The config populator clazz. */
	public final String configPopulatorClazz;

	/** The class name converter. */
	public Converter<String, String> classNameConverter = CaseFormat.UPPER_CAMEL
			.converterTo(CaseFormat.LOWER_UNDERSCORE);

	/** The column name converter. */
	public Converter<String, String> columnNameConverter = CaseFormat.LOWER_CAMEL
			.converterTo(CaseFormat.LOWER_UNDERSCORE);

	/** The entities. */
	protected Map<String, SQLiteEntity> entities = new HashMap<String, SQLiteEntity>();

	/** The entities by simple name. */
	protected Map<String, SQLiteEntity> entitiesBySimpleName = new HashMap<String, SQLiteEntity>();

	/** The sql for create. */
	public List<String> sqlForCreate = new ArrayList<String>();

	/** The sql for drop. */
	public List<String> sqlForDrop = new ArrayList<String>();

	/** used to. */
	protected long globalCounter = 0;

	/**
	 * Next counter.
	 *
	 * @return the long
	 */
	public long nextCounter() {
		return ++globalCounter;
	}

	/** The file name. */
	public String fileName;

	/** The generated class name. */
	public String generatedClassName;

	/**
	 * Gets the generated class name.
	 *
	 * @return the generatedClassName
	 */
	public String getGeneratedClassName() {
		return generatedClassName;
	}

	/** The version. */
	public int version;

	/** The generate log. */
	public boolean generateLog;

	/** The generate async task. */
	public boolean generateAsyncTask;

	/** The generate cursor. */
	public boolean generateCursor;

	/** The generate schema. */
	public boolean generateSchema;

	/** The generated entities. */
	public LinkedHashSet<GeneratedTypeElement> generatedEntities;

	/**
	 * if <code>true</code>, content provider is generated.
	 */
	public boolean generateContentProvider;

	/** The content provider. */
	public SQLiteModelContentProvider contentProvider;

	/** The property by simple name. */
	private Map<String, Set<SQLProperty>> propertyBySimpleName = new HashMap<>();

	/** The dao name set. */
	private List<String> daoNameSet;

	/** The generate rx. */
	public boolean generateRx;

	/** The config update tasks. */
	public final ArrayList<Pair<Integer, String>> configUpdateTasks;

	/** The config in memory. */
	public final boolean configInMemory;

	/** The config cursor factory clazz. */
	public final String configCursorFactoryClazz;

	/** The config log enabled. */
	public final boolean configLogEnabled;

	/** The config database error handler clazz. */
	public final String configDatabaseErrorHandlerClazz;

	/** The config database lifecycle handler clazz. */
	public final String configDatabaseLifecycleHandlerClazz;

	/** The global sql type adapter. */
	public final Map<String, String> globalSqlTypeAdapter = new HashMap<String, String>();

	/**
	 * directory used for generate schema
	 */
	public String schemaLocationDirectory;

	/**
	 * Gets the dao name set.
	 *
	 * @return the dao name set
	 */
	public List<String> getDaoNameSet() {
		return daoNameSet;
	}

	/**
	 * Instantiates a new SQ lite database schema.
	 *
	 * @param item
	 *            the item
	 * @param schemaFileName
	 *            the schema file name
	 * @param schemaVersion
	 *            the schema version
	 * @param schema
	 *            the schema
	 * @param log
	 *            the log
	 * @param asyncTask
	 *            the async task
	 * @param generateCursor
	 *            the generate cursor
	 * @param generateRx
	 *            the generate rx
	 * @param daoIntoDataSource
	 *            the dao into data source
	 * @param configCursorFactoryClass
	 *            the config cursor factory class
	 * @param configDatabaseErrorHandlerClass
	 *            the config database error handler class
	 * @param configDatabaseLifecycleHandlerClass
	 *            the config database lifecycle handler class
	 * @param configInMemory
	 *            the config in memory
	 * @param configLogEnabled
	 *            the config log enabled
	 * @param configPopulatorClass
	 *            the config populator class
	 * @param schemaLocationDirectory 
	 */
	public SQLiteDatabaseSchema(TypeElement item, String schemaFileName, int schemaVersion, boolean schema, boolean log,
			boolean asyncTask, boolean generateCursor, boolean generateRx, List<String> daoIntoDataSource,
			String configCursorFactoryClass, String configDatabaseErrorHandlerClass,
			String configDatabaseLifecycleHandlerClass, boolean configInMemory, boolean configLogEnabled,
			String configPopulatorClass) {
		super(item.getSimpleName().toString(), item);

		this.fileName = schemaFileName;
		this.version = schemaVersion;
		this.generateLog = log;
		this.generateAsyncTask = asyncTask;
		this.generateCursor = generateCursor;
		this.generateSchema = schema;
		this.generatedClassName = "Bind" + getName();
		this.generateContentProvider = false;
		this.generateRx = generateRx;
		this.contentProvider = null;
		this.generatedEntities = new LinkedHashSet<GeneratedTypeElement>();
		this.daoNameSet = daoIntoDataSource;
		this.schemaLocationDirectory=KriptonOptions.getSchemaLocation();

		FindTasksVisitor valueVisitor = new FindTasksVisitor();
		FindSqlTypeAdapterVisitor typeAdapterVisitors = new FindSqlTypeAdapterVisitor();
		List<? extends AnnotationMirror> annotationMirrors = item.getAnnotationMirrors();
		for (AnnotationMirror annotationMirror : annotationMirrors) {
			Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues = annotationMirror
					.getElementValues();

			if (BindDataSourceOptions.class.getName().equals(annotationMirror.getAnnotationType().toString())) {
				for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : elementValues
						.entrySet()) {
					// The 'entry.getKey()' here is the annotation attribute
					// name.
					String key = entry.getKey().getSimpleName().toString();
					entry.getValue().accept(valueVisitor, key);
				}
			} else if (BindDataSource.class.getName().equals(annotationMirror.getAnnotationType().toString())) {
				for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : elementValues
						.entrySet()) {
					String key = entry.getKey().getSimpleName().toString();
					entry.getValue().accept(typeAdapterVisitors, key);
				}
			}
		}

		// TODO add supported type and check about types
		{
			List<String> list = typeAdapterVisitors.getAdapters();
			for (String typeAdapter : list) {
				String sourceType = TypeAdapterHelper.detectSourceType(typeAdapter);
				AssertKripton.assertTrueOrInvalidGlobalTypeApdaterException(
						!globalSqlTypeAdapter.containsKey(sourceType), this, typeAdapter,
						globalSqlTypeAdapter.get(sourceType));

				globalSqlTypeAdapter.put(sourceType, typeAdapter);
			}
		}

		this.configLogEnabled = configLogEnabled;
		this.configInMemory = configInMemory;
		this.configUpdateTasks = valueVisitor.getTasks();

		this.configCursorFactoryClazz = fillClazz(configCursorFactoryClass, NoCursorFactory.class);
		this.configDatabaseErrorHandlerClazz = fillClazz(configDatabaseErrorHandlerClass, NoDatabaseErrorHandler.class);
		this.configDatabaseLifecycleHandlerClazz = fillClazz(configDatabaseLifecycleHandlerClass,
				NoDatabaseLifecycleHandler.class);
		this.configPopulatorClazz = fillClazz(configPopulatorClass, NoPopulator.class);

	}

	/**
	 * Fill clazz.
	 *
	 * @param configClazz
	 *            the config clazz
	 * @param clazz
	 *            the clazz
	 * @return the string
	 */
	private String fillClazz(String configClazz, Class<?> clazz) {
		if (!clazz.getName().equals(configClazz)) {
			return configClazz;
		} else {
			return null;
		}
	}

	/**
	 * Clear.
	 */
	public void clear() {
		entities.clear();
		entitiesBySimpleName.clear();
	}

	/**
	 * Adds the entity.
	 *
	 * @param value
	 *            the value
	 */
	public void addEntity(SQLiteEntity value) {
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
	 *            the list entity
	 * @param p
	 *            the p
	 */
	private void checkName(Set<SQLProperty> listEntity, SQLProperty p) {
		for (SQLProperty item : listEntity) {
			AssertKripton.assertTrueOrInvalidPropertyName(item.columnName.equals(p.columnName), item, p);
		}

	}

	/**
	 * Gets the entities.
	 *
	 * @return the entities
	 */
	public Collection<SQLiteEntity> getEntities() {
		return entities.values();
	}

	/**
	 * Gets the entities as list.
	 *
	 * @return the entities as list
	 */
	public List<SQLiteEntity> getEntitiesAsList() {
		return new ArrayList<>(entities.values());
	}

	/**
	 * Gets the entity.
	 *
	 * @param entityClassName
	 *            the entity class name
	 * @return the entity
	 */
	public SQLiteEntity getEntity(String entityClassName) {
		return entities.get(entityClassName);
	}

	/**
	 * Gets the entity by simple name.
	 *
	 * @param entityName
	 *            the entity name
	 * @return the entity by simple name
	 */
	public Finder<SQLProperty> getEntityBySimpleName(String entityName) {
		if (entityName == null)
			return null;

		SQLiteEntity result = entitiesBySimpleName.get(entityName.toLowerCase());
		if (result != null)
			return result;

		for (GeneratedTypeElement item : this.generatedEntities) {
			if (item.typeSpec.name.toLowerCase().equals(entityName.toLowerCase())) {
				return item;
			}
		}

		return null;
	}

	/**
	 * Gets the property by simple name.
	 *
	 * @param propertyName
	 *            the property name
	 * @return the property by simple name
	 */
	public Set<SQLProperty> getPropertyBySimpleName(String propertyName) {
		if (propertyName == null)
			return null;

		return this.propertyBySimpleName.get(propertyName.toLowerCase());
	}

	/**
	 * get a.
	 *
	 * @param method
	 *            the method
	 * @param propertyName
	 *            the property name
	 * @return the string
	 */
	public String findColumnNameByPropertyName(SQLiteModelMethod method, String propertyName) {
		Set<SQLProperty> propertiesSet = getPropertyBySimpleName(propertyName);
		Set<String> set = new HashSet<String>();
		String result = null;

		for (SQLProperty item : propertiesSet) {
			result = item.columnName;
			set.add(item.columnName);
		}

		AssertKripton.assertTrueOrInvalidMethodSignException(result != null && set.size() == 1, method,
				"in JQL attribute can not translate property %s", propertyName);

		return result;
	}

	/**
	 * Checks if is log enabled.
	 *
	 * @return true, if is log enabled
	 */
	public boolean isLogEnabled() {
		return generateLog;
	}

	/**
	 * Content provider uri.
	 *
	 * @return the string
	 */
	public String contentProviderUri() {
		if (!generateContentProvider)
			return "";

		return contentProvider.getUri();

	}

	/**
	 * Gets the generated class.
	 *
	 * @return the generated class
	 */
	public ClassName getGeneratedClass() {
		String packageName = getElement().asType().toString();
		return TypeUtility
				.className(packageName.substring(0, packageName.lastIndexOf(".")) + "." + getGeneratedClassName());
	}

	/**
	 * Returns true if any DAO exposes a method with live data.
	 *
	 * @return true, if successful
	 */
	public boolean hasLiveData() {
		for (SQLiteDaoDefinition dao : getCollection()) {
			if (dao.hasLiveData()) {
				return true;
			}
		}

		return false;
	}

	public SQLiteDaoDefinition findDaoDefinitionForEntity(SQLiteEntity entity) {
		for (SQLiteDaoDefinition item:collection) {
			if (item.getEntity().getName().equals(entity.getName())) {
				return item;
			}
		}
		
		return null;
	}

}
