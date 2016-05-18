package com.abubusoft.kripton.processor.core;


public interface ModelElement {
	void accept(ModelElementVisitor visitor) throws Exception;
}