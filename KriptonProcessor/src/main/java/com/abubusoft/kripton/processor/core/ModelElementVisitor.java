package com.abubusoft.kripton.processor.core;

public interface ModelElementVisitor {
	void visit(ModelClass kriptonClass) throws Exception;

	void visit(ModelProperty kriptonProperty) throws Exception;

	void visit(ModelType kriptonType) throws Exception;
	
}