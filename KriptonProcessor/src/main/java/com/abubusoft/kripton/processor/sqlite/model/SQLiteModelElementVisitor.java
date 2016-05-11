package com.abubusoft.kripton.processor.sqlite.model;



public interface SQLiteModelElementVisitor {
	void visit(SQLDaoDefinition value) throws Exception;
	
	void visit(SQLiteModelMethod value) throws Exception;

}