package com.abubusoft.kripton.processor.exceptions;

import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;

public class InvalidTableNameInJQLException extends KriptonProcessorException {

	public InvalidTableNameInJQLException(SQLiteModelMethod method, String tableName) {
		super(String.format("In DAO '%s' method '%s' JQL expression uses Java class '%s' that is no possible to translate in a table name", method.getParent().getName(), method.getName(), tableName));
	}

	private static final long serialVersionUID = -6673740993510722701L;

}
