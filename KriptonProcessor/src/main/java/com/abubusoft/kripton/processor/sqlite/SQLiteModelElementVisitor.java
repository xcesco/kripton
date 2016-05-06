package com.abubusoft.kripton.processor.sqlite;


public interface SQLiteModelElementVisitor {
	void visit(DaoDefinition value) throws Exception;
	
	void visit(SQLiteModelMethod value) throws Exception;

}