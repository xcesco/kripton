/**
 * 
 */
package com.abubusoft.kripton.processor.exceptions;

import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;

/**
 * @author xcesco
 *
 */
public class CircularRelationshipException extends KriptonProcessorException {

	public CircularRelationshipException(SQLEntity entity)
	{			
		super("Table definition "+entity.getTableName()+" is in a circular dependency");
	}
	
	private static final long serialVersionUID = -7836720045039133374L;

}
