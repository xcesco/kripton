package com.abubusoft.kripton.processor.core;

import java.lang.reflect.InvocationTargetException;

import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.exceptions.KriptonProcessorException;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public abstract class AssertKripton {

	/**
	 * Assertion which generate an exception if expression is not true
	 * 
	 * @param expression
	 * @param message
	 */
	public static void assertTrue(boolean expression, String message) {
		if (!expression)
			assertTrue(expression, message);
	}


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

}
