package com.abubusoft.kripton.processor.core;

import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.exceptions.KriptonProcessorException;
import com.abubusoft.kripton.processor.exceptions.MethodWithoutSupportedAnnotationException;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public abstract class AssertKripton {


	/**
	 * Assertion which generate an exception if expression is not true
	 * 
	 * @param <E>
	 * 
	 * @param expression
	 * @param message
	 */
	public static void assertTrue(boolean expression, String messageFormat, Object... args) {
		if (!expression)
			throw (new KriptonProcessorException(String.format(messageFormat, args)));

	}

	public static void assertTrueOrInvalidMethodSignException(boolean expression, SQLiteModelMethod method, String messageFormat, Object... args) {
		if (!expression)
			throw (new InvalidMethodSignException(method, String.format(messageFormat, args)));
	}
	
	public static void assertTrueOrInvalidMethodSignException(boolean expression, SQLiteModelMethod method) {
		if (!expression)
			throw (new InvalidMethodSignException(method));
	}
	
	/**
	 * if expression is true, it fails. It is the opposite of assert
	 * @param method
	 */
	public static void failWithInvalidMethodSignException(boolean expression, SQLiteModelMethod method) {
		assertTrueOrInvalidMethodSignException(!expression, method);			
	}
	
	/**
	 * if expression is true, it fails. It is the opposite of assert
	 * @param method
	 */
	public static void failWithInvalidMethodSignException(boolean expression, SQLiteModelMethod method, String messageFormat, Object... args) {
		assertTrueOrInvalidMethodSignException(!expression, method, messageFormat, args);			
	}

	public static void failWithMethodWithoutSupportedAnnotationException(SQLiteModelMethod value) {
		throw (new MethodWithoutSupportedAnnotationException(value.getParent(), value));		
	}
	
	/**
	 * Fails if expression is true
	 * 
	 * @param expression
	 * @param messageFormat
	 * @param args
	 */
	public static void fail(boolean expression, String messageFormat, Object... args) {
		assertTrue(!expression, messageFormat, args);
	}
	

}
