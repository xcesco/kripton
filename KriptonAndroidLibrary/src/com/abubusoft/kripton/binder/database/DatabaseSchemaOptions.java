/**
 * 
 */
package com.abubusoft.kripton.binder.database;

import java.util.HashSet;

import com.abubusoft.kripton.binder.schema.MappingSchema;
import com.abubusoft.kripton.exception.MappingException;

/**
 * 
 * Options for database creation.
 * 
 * @author xcesco
 *
 */
public class DatabaseSchemaOptions {

	protected String tablePrefix="";

	public String getTablePrefix() {
		return tablePrefix;
	}

	public NameConverterType getNameConverter() {
		return nameConverter;
	}

	public boolean isCompiled() {
		return compiled;
	}

	public HashSet<MappingSchema> getMappingSchemaSet() {
		return mappingSchemaSet;
	}

	protected NameConverterType nameConverter = NameConverterType.UPPER_UNDERSCORE;

	protected boolean compiled;

	protected HashSet<MappingSchema> mappingSchemaSet=new HashSet<MappingSchema>();

	public static DatabaseSchemaOptions build() {
		DatabaseSchemaOptions value = new DatabaseSchemaOptions();

		return value;
	}

	public DatabaseSchemaOptions add(Class<?> clazz) throws MappingException {
		mappingSchemaSet.add(MappingSchema.fromClass(clazz));
		return this;
	}

	public DatabaseSchemaOptions compile() {
		compiled = true;

		return this;
	}

	public DatabaseSchemaOptions nameConverter(NameConverterType value) {
		nameConverter = value;
		return this;
	}

	public DatabaseSchemaOptions tablePrefix(String value) {
		tablePrefix=value;
		return this;
		
	}
}
