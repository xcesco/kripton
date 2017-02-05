package com.abubusoft.kripton.processor.core;

import java.lang.annotation.Annotation;

import javax.lang.model.element.Element;

import com.abubusoft.kripton.processor.exceptions.InvalidKindForAnnotationException;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.exceptions.KriptonProcessorException;
import com.abubusoft.kripton.processor.exceptions.MethodWithoutSupportedAnnotationException;
import com.abubusoft.kripton.processor.exceptions.UnsupportedFieldTypeException;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.TypeName;

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
	 * 
	 * @param method
	 */
	public static void failWithInvalidMethodSignException(boolean expression, SQLiteModelMethod method) {
		assertTrueOrInvalidMethodSignException(!expression, method);
	}

	/**
	 * if expression is true, it fails. It is the opposite of assert
	 * 
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

	public static void assertTrueOrUnsupportedFieldTypeException(boolean expression, TypeName typeName) {
		if (!expression)
			throw (new UnsupportedFieldTypeException(typeName));
	}

	public static void assertTrueOrInvalidKindForAnnotationException(boolean expression, Element element, Class<? extends Annotation> annotationClazz) {
		if (!expression) {
			String msg = String.format("%s %s, only class can be annotated with @%s annotation", element.getKind(), element, annotationClazz.getSimpleName());
			throw (new InvalidKindForAnnotationException(msg));
		}
	}

}
