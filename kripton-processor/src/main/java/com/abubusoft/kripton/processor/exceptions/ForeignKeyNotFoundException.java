/**
 * 
 */
package com.abubusoft.kripton.processor.exceptions;

import com.abubusoft.kripton.processor.sqlite.model.SQLiteEntity;
import com.squareup.javapoet.ClassName;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class ForeignKeyNotFoundException extends KriptonProcessorException {

	public ForeignKeyNotFoundException(SQLiteEntity currentEntity, ClassName entity) {		
		super(String.format("Entity '%s' is used to rapresents a many-to-many relationship and it need a foreign key to '%s'", currentEntity.getName(), entity.toString()));
	}

	private static final long serialVersionUID = 2584075607665935566L;

}
