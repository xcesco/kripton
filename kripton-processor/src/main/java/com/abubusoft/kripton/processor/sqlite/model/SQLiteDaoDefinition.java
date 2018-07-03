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

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelBucket;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeVariableResolver;
import com.squareup.javapoet.TypeName;

/**
 * The Class SQLiteDaoDefinition.
 */
public class SQLiteDaoDefinition extends ModelBucket<SQLiteModelMethod, TypeElement> implements SQLiteModelElement {

	/** The Constant PARAM_PARSER_PREFIX. */
	public static final String PARAM_PARSER_PREFIX = "parser";

	/** The Constant PARAM_SERIALIZER_PREFIX. */
	public static final String PARAM_SERIALIZER_PREFIX = "serializer";

	/** The parent. */
	private WeakReference<SQLiteDatabaseSchema> parent;

	/** The type variable resolver. */
	private TypeVariableResolver typeVariableResolver;

	/**
	 * Gets the parent.
	 *
	 * @return the parent
	 */
	public SQLiteDatabaseSchema getParent() {
		return parent.get();
	}

	/**
	 * Next counter.
	 *
	 * @return the long
	 */
	public long nextCounter() {
		return getParent().nextCounter();
	}

	/**
	 * Convert type variable in correct type. This must be done before work on
	 * SQLMethod
	 *
	 * @param value
	 *            the value
	 */
	void resolveTypeVariable(SQLiteModelMethod value) {
		// before proceed, we need to resolve typeVariables
		for (Pair<String, TypeName> item : value.getParameters()) {
			item.value1 = typeVariableResolver.resolve(item.value1);
		}

		value.setReturnClass(typeVariableResolver.resolve(value.getReturnClass()));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.core.ModelBucket#add(com.abubusoft.
	 * kripton.processor.core.ModelEntity)
	 */
	@Override
	public void add(SQLiteModelMethod value) {
		super.add(value);
	}

	/** The entity class name. */
	private String entityClassName;

	/** The entity simply class name. */
	private String entitySimplyClassName;

	/**
	 * Checks if is generated.
	 *
	 * @return true, if is generated
	 */
	public boolean isGenerated() {
		return generated;
	}

	/** The generated. */
	private boolean generated;

	/**
	 * Gets the entity simply class name.
	 *
	 * @return the entitySimplyClassName
	 */
	public String getEntitySimplyClassName() {
		return entitySimplyClassName;
	}

	/**
	 * Gets the entity class name.
	 *
	 * @return the entityClassName
	 */
	public String getEntityClassName() {
		return entityClassName;
	}

	/**
	 * Gets the simple entity class name.
	 *
	 * @return the simple entity class name
	 */
	public String getSimpleEntityClassName() {
		return entitySimplyClassName;
	}

	/**
	 * Instantiates a new SQ lite dao definition.
	 *
	 * @param databaseSchema
	 *            the database schema
	 * @param name
	 *            the name
	 * @param element
	 *            the element
	 * @param entityClassName
	 *            the entity class name
	 * @param generated
	 *            the generated
	 */
	public SQLiteDaoDefinition(SQLiteDatabaseSchema databaseSchema, String name, TypeElement element, String entityClassName, boolean generated) {
		super(element.getSimpleName().toString(), element);
		this.generated = generated;
		this.parent = new WeakReference<SQLiteDatabaseSchema>(databaseSchema);
		this.entityClassName = entityClassName;

		int i = 0;
		i = entityClassName.indexOf(".");

		if (i > 0) {
			entitySimplyClassName = entityClassName.substring(entityClassName.lastIndexOf(".") + 1);
		} else {
			entitySimplyClassName = entityClassName;
		}

		typeVariableResolver = TypeVariableResolver.build(element);
		implementedInterface = new HashSet<>();

		i = name.indexOf(".");
		if (i > 0) {
			this.name = name.substring(name.lastIndexOf(".") + 1);
		} else {
			this.name = name;
		}

	}

	/**
	 * Resolve type variable.
	 *
	 * @param inputTypeName
	 *            the input type name
	 * @return the type name
	 */
	public TypeName resolveTypeVariable(TypeName inputTypeName) {
		return typeVariableResolver.resolve(inputTypeName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.processor.sqlite.model.SQLiteModelElement#accept(
	 * com.abubusoft.kripton.processor.sqlite.model.SQLiteModelElementVisitor)
	 */
	@Override
	public void accept(SQLiteModelElementVisitor visitor) throws Exception {
		visitor.visit(this);
	}

	/**
	 * Gets the entity.
	 *
	 * @return the entity
	 */
	public SQLiteEntity getEntity() {
		return getParent().getEntity(getEntityClassName());
	}

	/**
	 * Return true if log must be generated.
	 * 
	 * @return Return true if log must be generated.
	 */
	public boolean isLogEnabled() {
		return getParent().generateLog;
	}

	/** map of params for which generate a java2Content method converter. */
	public Map<TypeName, String> managedParams = new HashMap<TypeName, String>();

	/**
	 * <p>
	 * if <code>true</code> indicates that content provider generation is
	 * enabled.
	 */
	public boolean contentProviderEnabled;

	/** Base path associated to content provider. */
	public String contentProviderPath;

	/** type name exposed by content provider. */
	public String contentProviderTypeName;

	/** Collections of prepared statements. */
	public List<String> preparedStatementNames = new ArrayList<String>();

	/**
	 * Build and register prepared statement name.
	 *
	 * @param methodName
	 *            the method name
	 * @return the string
	 */
	String buildPreparedStatementName(String methodName) {
		String name = methodName + "PreparedStatement" + preparedStatementNames.size();

		preparedStatementNames.add(name);
		return name;
	}

	/** number of element generated for content provider. */
	public long contentProviderCounter;

	/** The implemented interface. */
	public Set<TypeName> implementedInterface;

	/** Example: DAO_PERSON_UID. */
	public String daoUidName;

	/** Example: 0 to n. */
	public int daoUidValue;

	/**
	 * Generate java 2 content serializer.
	 *
	 * @param paramTypeName
	 *            the param type name
	 * @return the string
	 */
	public String generateJava2ContentSerializer(TypeName paramTypeName) {
		if (!managedParams.containsKey(paramTypeName)) {
			managedParams.put(paramTypeName, "" + (managedParams.size() + 1));
		}

		return PARAM_SERIALIZER_PREFIX + managedParams.get(paramTypeName);
	}

	/**
	 * Generate java 2 content parser.
	 *
	 * @param paramTypeName
	 *            the param type name
	 * @return the string
	 */
	public String generateJava2ContentParser(TypeName paramTypeName) {
		if (!managedParams.containsKey(paramTypeName)) {
			managedParams.put(paramTypeName, "" + (managedParams.size() + 1));
		}

		return PARAM_PARSER_PREFIX + managedParams.get(paramTypeName);
	}

	/**
	 * Content provider uri.
	 *
	 * @return the string
	 */
	public String contentProviderUri() {
		if (!contentProviderEnabled)
			return "";

		return this.getParent().contentProviderUri() + "/" + contentProviderPath;
	}

	/**
	 * Content provider path.
	 *
	 * @return the string
	 */
	public String contentProviderPath() {
		if (!contentProviderEnabled)
			return "";

		return contentProviderPath;
	}

	/**
	 * Adds the implemented interface.
	 *
	 * @param className
	 *            the class name
	 */
	public void addImplementedInterface(TypeName className) {
		this.implementedInterface.add(className);

	}

	/**
	 * return type name of object. Note that this method support DaoGenerated
	 * case
	 *
	 * @return the type name
	 */
	public TypeName getTypeName() {
		return TypeUtility.typeName(TypeUtility.extractPackageName(this.element), name);
	}

	/**
	 * Checks for live data.
	 *
	 * @return true, if successful
	 */
	public boolean hasLiveData() {
		for (SQLiteModelMethod item : collection) {
			if (item.hasLiveData())
				return true;
		}

		return false;
	}

	/**
	 * Returns true if dao and schema stay in same package
	 * 
	 * @return
	 */
	public boolean hasSamePackageOfSchema() {
		String packageName = getPackageName();
		String schemaPackageName = getParent().getPackageName();

		return packageName.equals(schemaPackageName);
	}

	public boolean hasRelations() {
		for (SQLiteModelMethod method : this.collection) {
			if (method.hasChildrenSelects()) {
				return true;
			}
		}
		return false;
	}

}
