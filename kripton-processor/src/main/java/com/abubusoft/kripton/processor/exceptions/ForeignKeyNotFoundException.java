/**
 * 
 */
package com.abubusoft.kripton.processor.exceptions;

import com.abubusoft.kripton.processor.sqlite.model.SQLiteEntity;
import com.squareup.javapoet.ClassName;


/**
 * The Class ForeignKeyNotFoundException.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class ForeignKeyNotFoundException extends KriptonProcessorException {

	/**
	 * Instantiates a new foreign key not found exception.
	 *
	 * @param currentEntity the current entity
	 * @param entity the entity
	 */
	public ForeignKeyNotFoundException(SQLiteEntity currentEntity, ClassName entity) {		
		super(String.format("Entity '%s' is used to rapresents a many-to-many relationship and it need a foreign key to '%s'", currentEntity.getName(), entity.toString()));
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2584075607665935566L;

}
