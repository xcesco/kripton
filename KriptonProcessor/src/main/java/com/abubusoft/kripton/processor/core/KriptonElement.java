package com.abubusoft.kripton.processor.core;

public interface KriptonElement {
	void accept(KriptonElementVisitor visitor) throws Exception;
}