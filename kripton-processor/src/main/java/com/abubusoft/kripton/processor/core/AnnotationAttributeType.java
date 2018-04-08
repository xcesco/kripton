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
package com.abubusoft.kripton.processor.core;

import javax.lang.model.element.ExecutableElement;

// TODO: Auto-generated Javadoc
/**
 * Attribute typeName used in annotations. Introduced to avoid to type typeName
 * attribute as string
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 *
 * @since 05/mag/2016
 */
public enum AnnotationAttributeType {

	/** The adapter. */
	ADAPTER("adapter"),
	/** The all fields. */
	ALL_FIELDS("allFields"),

	/** The column type. */
	COLUMN_TYPE("columnType"),
	/** The conflict algorithm type. */
	CONFLICT_ALGORITHM_TYPE("conflictAlgorithm"),

	/** The dao set. */
	DAO_SET("daoSet"),
	/** The distinct. */
	DISTINCT("distinct"),

	/** The enabled. */
	ENABLED("enabled"),
	/** The excluded fields. */
	EXCLUDED_FIELDS("excludedFields"),

	/** The fields. */
	FIELDS("fields"),
	/** The filename. */
	FILENAME("fileName"),
	/** The foreign key. */
	FOREIGN_KEY("foreignKey"),

	/** The generate async task. */
	GENERATE_ASYNC_TASK("asyncTask"),
	/** The generate cursor wrapper. */
	GENERATE_CURSOR_WRAPPER("cursorWrapper"),
	/** The generate log. */
	GENERATE_LOG("log"),
	/** The generate schema. */
	GENERATE_SCHEMA("schema"),
	/** The group by. */
	GROUP_BY("groupBy"),

	/** The having. */
	HAVING("having"),

	/** The include primary key. */
	INCLUDE_PRIMARY_KEY("includePrimaryKey"),
	/** The indexes. */
	INDEXES("indexes"),

	/** The map entry type. */
	MAP_ENTRY_TYPE("mapEntryType"),
	/** The map key name. */
	MAP_KEY_NAME("mapKeyName"),
	/** The map value name. */
	MAP_VALUE_NAME("mapValueName"),

	/** The nullable. */
	NULLABLE("nullable"),

	/** The order. */
	ORDER("order"),
	/** The order by. */
	ORDER_BY("orderBy"),

	/** The page size. */
	PAGE_SIZE("pageSize"),
	/** The path. */
	PATH("path"),

	/** The prepend. */
	PREPEND("prepend"),

	/** The raw fields. */
	RAW_FIELDS("rawFields"),
	/** The result type. */
	RESULT_TYPE("resultType"),

	/** The name. */
	NAME("name"),

	/** The type parameters. */
	TYPE_PARAMETERS("typeParameters"),
	/** The type variables. */
	TYPE_VARIABLES("typeVariables"),

	/** The value. */
	VALUE("value"),
	/** The version. */
	VERSION("version"),

	/** The where. */
	WHERE("where"),

	/** The unique indexes. */
	UNIQUE_INDEXES("uniqueIndexes"),

	/** The xml element tag. */
	XML_ELEMENT_TAG("elementTag"),
	/** The xml type. */
	XML_TYPE("xmlType"),

	/** The on delete. */
	ON_DELETE("onDelete"),

	/** The on update. */
	ON_UPDATE("onUpdate"),

	/** The multiplicity result. */
	MULTIPLICITY_RESULT("multiplicityResult"),
	/** The jql. */
	JQL("jql"),
	/** The id name. */
	ID_NAME("idName"),

	/** The relationship. */
	RELATIONSHIP("relationship"),

	/** The in memory. */
	IN_MEMORY("inMemory"),

	/** The entity 1. */
	ENTITY_1("entity1"),

	/** The entity 2. */
	ENTITY_2("entity2"),

	/** The table name. */
	TABLE_NAME("tableName"),

	/** The dao. */
	DAO("dao"),

	/** The generate rx. */
	GENERATE_RX("rx"),

	/** The methods. */
	METHODS("methods"),
	/** The task. */
	TASK("task"),

	/** The update tasks. */
	UPDATE_TASKS("updateTasks"),

	/** The populator. */
	POPULATOR("populator"),

	/** The log enabled. */
	LOG_ENABLED("logEnabled"),
	/** The cursor factory. */
	CURSOR_FACTORY("cursorFactory"),
	/** The database lifecycle handler. */
	DATABASE_LIFECYCLE_HANDLER("databaseLifecycleHandler"),
	/** The type adapters. */
	TYPE_ADAPTERS("typeAdapters"),
	/**
	 * <p>
	 * column affinity type.
	 * </p>
	 */
	COLUMN_AFFINITY("columnAffinity");

	/** The value. */
	private String value;

	/**
	 * Instantiates a new annotation attribute type.
	 *
	 * @param value
	 *            the value
	 */
	private AnnotationAttributeType(String value) {
		this.value = value;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Checks if is equals.
	 *
	 * @param value
	 *            the value
	 * @return true, if is equals
	 */
	public boolean isEquals(ExecutableElement value) {
		return this.getValue().equals(value.getSimpleName().toString());
	}

}
