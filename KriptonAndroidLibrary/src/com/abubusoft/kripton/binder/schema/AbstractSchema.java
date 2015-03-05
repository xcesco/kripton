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
	
	protected boolean indexed=false;

	protected String name;

	protected boolean nullable=true;

	protected boolean primaryKey=false;

	protected boolean unique=false;
	
	/**
	 * Get POJO field
	 * 
	 * @return field
	 */
	public Field getField() {
		return field;
	}
	
	public String getName() {
		return name;
	}

	public boolean isIndexed() {
		return indexed;
	}

	public boolean isNullable() {
		return nullable;
	}

	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public boolean isUnique() {
		return unique;
	}

	/**
	 * Set POJO field
	 * 
	 * @param field
	 */
	public void setField(Field field) {
		this.field = field;
	}

	public void setIndexed(boolean indexed) {
		this.indexed = indexed;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	public void setUnique(boolean unique) {
		this.unique = unique;
	}
}
