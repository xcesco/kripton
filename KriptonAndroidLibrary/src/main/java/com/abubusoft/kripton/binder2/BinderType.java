package com.abubusoft.kripton.binder2;

public enum BinderType {
	JSON(false),
	PROPERTIES(true),
	XML(false),
	YAML(false), 
	CBOR(false);	
	
	private BinderType(boolean value)
	{
		onlyText=value;
	}
	
	public final boolean onlyText; 
}
