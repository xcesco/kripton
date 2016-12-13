package com.abubusoft.kripton.exception;

import java.lang.reflect.Type;

import com.abubusoft.kripton.binder.BinderType;


public class NoSuchMapperException extends KriptonRuntimeException {

	private static final long serialVersionUID = -2423802393630866667L;

	public NoSuchMapperException(Type type, BinderType binderType) {
		super("Class " + type.toString() + " could not be mapped in "+binderType+" format. Check dependency jar");
	}

}