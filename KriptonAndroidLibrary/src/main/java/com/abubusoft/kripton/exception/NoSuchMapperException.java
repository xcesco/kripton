package com.abubusoft.kripton.exception;


/**
 * The exception that will be thrown in the event that LoganSquare.mapperFor() is called with a class that hasn't been declared as a @JsonObject.
 */
public class NoSuchMapperException extends KriptonRuntimeException {

	private static final long serialVersionUID = -2423802393630866667L;

	public NoSuchMapperException(Class cls) {
		super("Class " + cls.getCanonicalName() + " could not be mapped to a JSON object. Perhaps it hasn't been annotated with @JsonObject?");
	}

}