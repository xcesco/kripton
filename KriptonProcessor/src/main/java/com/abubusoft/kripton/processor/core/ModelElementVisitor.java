package com.abubusoft.kripton.processor.core;


public interface ModelElementVisitor<T extends ModelClass<P>, P extends ModelProperty> {
	void visit(T entity) throws Exception;

	void visit(P property) throws Exception;	
}