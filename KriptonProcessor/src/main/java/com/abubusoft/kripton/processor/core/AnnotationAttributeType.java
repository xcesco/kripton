/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa.
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
 * Attribute name used in annotations. Introduced to avoid to type name attribute as string
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 *
 * @since 05/mag/2016
 */
public enum AnnotationAttributeType {
	ADAPTER("adapter"),	
	ALL_FIELDS("allFields"),	
	ASYNCTASK("asyncTask"),
	
	COLUMN_TYPE("columnType"),	
	CONFLICT_ALGORITHM_TYPE("conflictAlgorithm"),
	
	CURSOR("cursor"),	
	DAO("dao"),
	DATA_TYPE("dataType"),
	
	DISTINCT("distinct"),
	ENABLED("enabled"),
	
	EXCLUDED_FIELDS("excludedFields"),
	FIELD_TYPE("fieldType"),
	FILENAME("fileName"),
	
	FOREIGN_KEY("foreignKey"),
	
	GROUP_BY("groupBy"),
	
	HAVING("having"),
	INCLUDE_PRIMARY_KEY("includePrimaryKey"),
	INDEXES("indexes"),
	
	LOG("log"),
	
	MAP_ENTRY_TYPE("mapEntryType"),
	MAP_KEY_NAME("mapKeyName"),
	MAP_VALUE_NAME("mapValueName"),
	
	NULLABLE("nullable"),
	
	ORDER("order"),	
	ORDER_BY("orderBy"),
	
	RAW_FIELDS("rawFields"),
	
	VALUE("value"),
	VERSION("version"),
	
	WHERE("where"),
		
	XML_ELEMENT_TAG("elementTag"),
	XML_TYPE("xmlType")
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
