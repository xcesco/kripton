package com.abubusoft.kripton.exception;

import java.lang.reflect.Type;


public class NoSuchMapperException extends KriptonRuntimeException {

	private static final long serialVersionUID = -2423802393630866667L;

	public NoSuchMapperException(Type type) {
		super("Class " + type.toString() + " could not be mapped. Is it marked with @BindType?");
	}

}