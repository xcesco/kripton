package com.abubusoft.kripton.processor.exceptions;

public class KriptonInstantiationException extends KriptonProcessorException {

	public KriptonInstantiationException(ReflectiveOperationException e) {
		super(e);
	}

	private static final long serialVersionUID = 6090989918789339818L;

}
