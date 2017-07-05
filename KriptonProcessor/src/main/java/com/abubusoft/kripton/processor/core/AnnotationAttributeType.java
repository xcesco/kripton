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

/**
 * Attribute typeName used in annotations. Introduced to avoid to type typeName attribute as string
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 *
 * @since 05/mag/2016
 */
public enum AnnotationAttributeType {
	ADAPTER("adapter"),	
	ALL_FIELDS("allFields"),
	
	COLUMN_TYPE("columnType"),	
	CONFLICT_ALGORITHM_TYPE("conflictAlgorithm"),
	
	DAO("dao"),	
	DATA_TYPE("dataType"),	
	DISTINCT("distinct"),
	
	ENABLED("enabled"),	
	EXCLUDED_FIELDS("excludedFields"),
	
	FIELDS("fields"),	
	FILENAME("fileName"),
	FOREIGN_KEY("foreignKey"),
	
	GENERATE_ASYNC_TASK("generateAsyncTask"),
	GENERATE_CURSOR_WRAPPER("generateCursorWrapper"),	
	GENERATE_LOG("generateLog"),
	GENERATE_SCHEMA("generateSchema"),
	GROUP_BY("groupBy"),
	
	
	HAVING("having"),
	
	INCLUDE_PRIMARY_KEY("includePrimaryKey"),	
	INDEXES("indexes"),
	
	MAP_ENTRY_TYPE("mapEntryType"),
	MAP_KEY_NAME("mapKeyName"),
	MAP_VALUE_NAME("mapValueName"),
	
	NULLABLE("nullable"),
	
	ORDER("order"),	
	ORDER_BY("orderBy"),
	
	PAGE_SIZE("pageSize"),	
	PATH("path"),
	
	PREPEND("prepend"),
	
	RAW_FIELDS("rawFields"),	
	RESULT_TYPE("resultType"),
	
	TYPE_PARAMETERS("typeParameters"),		
	TYPE_VARIABLES("typeVariables"),
	
	VALUE("value"), 
	VERSION("version"),
	
	WHERE("where"),
	
	XML_ELEMENT_TAG("elementTag"), 
	XML_TYPE("xmlType"),
	
	MULTIPLICITY_RESULT("multiplicityResult"),
	JQL("jql"),	
	;
	
	
	private String value;
	
	private AnnotationAttributeType(String value)
	{
		this.value=value;
	}

	public String getValue()
	{
		return value;
	}

	public boolean isEquals(ExecutableElement value) {
		return this.getValue().equals(value.getSimpleName().toString());
	}
	
}
