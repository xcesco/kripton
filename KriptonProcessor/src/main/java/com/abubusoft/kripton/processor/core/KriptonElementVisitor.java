package com.abubusoft.kripton.processor.core;

public interface KriptonElementVisitor {
	void visit(KriptonClass kriptonClass) throws Exception;

	void visit(KriptonProperty kriptonProperty) throws Exception;

	void visit(KriptonType kriptonType) throws Exception;
	
	void visit(KriptonAnalyzer kriptonAnalyzer) throws Exception;

}