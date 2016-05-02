package com.abubusoft.kripton.processor.sqlite;

import com.abubusoft.kripton.processor.core.ModelMethod;

public interface SQLiteModelElementVisitor {
	void visit(DaoDefinition value) throws Exception;
	
	void visit(ModelMethod value) throws Exception;

}