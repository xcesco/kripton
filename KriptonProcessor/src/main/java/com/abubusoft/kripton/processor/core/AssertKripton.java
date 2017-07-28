/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.processor.core;

import java.lang.annotation.Annotation;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.IncompatibleAttributesInAnnotationException;
import com.abubusoft.kripton.processor.exceptions.InvalidKindForAnnotationException;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.exceptions.InvalidTableNameInJQLException;
import com.abubusoft.kripton.processor.exceptions.InvalidTypeForAnnotationException;
import com.abubusoft.kripton.processor.exceptions.KriptonProcessorException;
import com.abubusoft.kripton.processor.exceptions.MethodWithoutSupportedAnnotationException;
import com.abubusoft.kripton.processor.exceptions.UnknownClassInJQLException;
import com.abubusoft.kripton.processor.exceptions.UnknownParamUsedInJQLException;
import com.abubusoft.kripton.processor.exceptions.UnknownPropertyInJQLException;
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

	/**
	 * Assertion which generate an exception if expression is not true
	 * 
	 * @param <T>
	 * 
	 * @param <E>
	 * 
	 * @param expression
	 * @param message
	 */
//	public static <T extends KriptonProcessorException> void assertTrue(Class<T> exceptionClazz, boolean expression, String messageFormat, Object... args) {
//		if (!expression)
//			try {
//				throw (exceptionClazz.getConstructor(String.class).newInstance(String.format(messageFormat, args)));
//			} catch (Throwable e) {
//				throw (new KriptonRuntimeException("Unknwon exception!"));
//			}
//
//	}

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

	public static void fail(String messageFormat, Object... args) {
		assertTrue(false, messageFormat, args);
	}

	public static void failIncompatibleAttributesInAnnotationException(String messageFormat, Object... args) {
		throw (new IncompatibleAttributesInAnnotationException(String.format(messageFormat, args)));
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

	/**
	 * In case a method's parameters is of a type incompatible with specific
	 * annotation
	 * 
	 * @param expression
	 * @param element
	 * @param annotationClazz
	 */
	public static void assertTrueOrInvalidTypeForAnnotationMethodParameterException(boolean expression, Element classElement, ExecutableElement methodElement, VariableElement parameterElement,
			Class<? extends Annotation> annotationClazz) {
		if (!expression) {
			String msg = String.format("In method '%s.%s', parameter '%s' has an invalid type '%s' for @%s annotation", classElement.getSimpleName().toString(),
					methodElement.getSimpleName().toString(), parameterElement.getSimpleName().toString(), parameterElement.asType(), annotationClazz.getSimpleName());
			throw (new InvalidTypeForAnnotationException(msg));
		}
	}

	public static void assertNotNull(Object value, KriptonProcessorException exception) {
		if (value == null) {
			throw (exception);
		}

	}

	public static void assertTrueOrUnknownPropertyInJQLException(boolean expression, SQLiteModelMethod method, String columnName) {
		if (!expression) {
			throw (new UnknownPropertyInJQLException(method, columnName));
		}

	}

	public static void assertTrueOrUnknownClassInJQLException(boolean expression, SQLiteModelMethod method, String className) {
		if (!expression) {
			throw (new UnknownClassInJQLException(method, className));
		}
	}

	public static void assertTrueOrUnknownParamInJQLException(boolean expression, SQLiteModelMethod method, String paramName) {
		if (!expression) {
			throw (new UnknownParamUsedInJQLException(method, paramName));
		}
	}

	public static void failUnknownPropertyInJQLException(SQLiteModelMethod method, Class<? extends Annotation> annotationClazz,
			AnnotationAttributeType attribute, String fieldName) {
		throw(new UnknownPropertyInJQLException(method,annotationClazz, attribute, fieldName));
		
	}

}
