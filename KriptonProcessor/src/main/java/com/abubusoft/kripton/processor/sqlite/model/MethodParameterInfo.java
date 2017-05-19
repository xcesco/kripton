package com.abubusoft.kripton.processor.sqlite.model;

import com.squareup.javapoet.TypeName;

public class MethodParameterInfo {
	
	public enum UsageType {
		UNUSED,
		PARAMETER,
		SUPPORT,
		DYNAMIC_WHERE,
		DYNAMIC_WHERE_ARGS,
		DYNAMIC_ORDER,				
	}

	/**
	 * name of the parameter
	 */
	public String name;
	
	/**
	 * alias defined with {@link Bind
	 */
	public String alias;
	
	public TypeName typeName;
	
	public UsageType usage=UsageType.UNUSED;
}
