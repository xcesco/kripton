package com.abubusoft.kripton.processor.sqlite.model;

import javax.lang.model.element.Element;

/**
 * Attribute name used in annotations. Introduced to avoid to type name attribute as string
 * 
 * @author xcesco
 *
 *
 * @since 05/mag/2016
 */
public enum AnnotationAttributeType {
	ATTRIBUTE_ALL_FIELDS("allFields"),
	
	ATTRIBUTE_NAME("name"),
	
	ATTRIBUTE_RAW_FIELDS("rawFields"),
	
	ATTRIBUTE_VALUE("value"),
	ATTRIBUTE_EXCLUDED_FIELDS("excludedFields"),	
	ATTRIBUTE_DISTINCT("distinct"),
	
	ATTRIBUTE_WHERE("where"),	
	ATTRIBUTE_HAVING("having"),
	ATTRIBUTE_GROUP_BY("groupBy"),
	ATTRIBUTE_ORDER_BY("orderBy"),
	ATTRIBUTE_NULLABLE("nullable"),
	ATTRIBUTE_FILENAME("fileName"),
	ATTRIBUTE_VERSION("version"),
	ATTRIBUTE_LOG("log"), 
	ATTRIBUTE_ASYNCTASK("asyncTask"), 
	ATTRIBUTE_CURSOR("cursor") 
	
	
	;
	
	
	private String value;
	
	public String getValue()
	{
		return value;
	}

	private AnnotationAttributeType(String value)
	{
		this.value=value;
	}
	
	public boolean isEquals(Element element)
	{
		return value.equals(element.getSimpleName().toString());
	}
}
