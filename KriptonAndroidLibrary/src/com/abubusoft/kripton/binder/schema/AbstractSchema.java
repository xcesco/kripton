package com.abubusoft.kripton.binder.schema;

import java.lang.reflect.Field;

/**
 * Base schema for all schemas
 * 
 * @author xcesco
 *
 */
public class AbstractSchema {

	protected Field field;

	/**
	 * resolved field type
	 */
	protected Class<?> fieldType;

	protected String name;

	/**
	 * Get POJO field
	 * 
	 * @return field
	 */
	public Field getField() {
		return field;
	}

	/**
	 * resolved field type
	 */
	public Class<?> getFieldType() {
		return fieldType;
	}

	public String getName() {
		return name;
	}

	/**
	 * Set POJO field
	 * 
	 * @param field
	 */
	public void setField(Field field) {
		this.field = field;
	}

	/**
	 * resolved field type
	 */
	public void setFieldType(Class<?> fieldType) {
		this.fieldType = fieldType;
	}


	public void setName(String name) {
		this.name = name;
	}


}
