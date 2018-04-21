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
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.ForeignKeyNotFoundException;
import com.abubusoft.kripton.processor.exceptions.IncompatibleAttributesInAnnotationException;
import com.abubusoft.kripton.processor.exceptions.InvalidDefinition;
import com.abubusoft.kripton.processor.exceptions.InvalidKindForAnnotationException;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.exceptions.InvalidPropertyToColumnConversion;
import com.abubusoft.kripton.processor.exceptions.InvalidTypeForAnnotationException;
import com.abubusoft.kripton.processor.exceptions.KriptonProcessorException;
import com.abubusoft.kripton.processor.exceptions.MethodWithoutSupportedAnnotationException;
import com.abubusoft.kripton.processor.exceptions.MissedAnnotationOnClass;
import com.abubusoft.kripton.processor.exceptions.UnknownClassInJQLException;
import com.abubusoft.kripton.processor.exceptions.UnknownParamUsedInJQLException;
import com.abubusoft.kripton.processor.exceptions.UnknownPropertyInJQLException;
import com.abubusoft.kripton.processor.exceptions.UnsupportedFieldTypeException;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

/**
 * The Class AssertKripton.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public abstract class AssertKripton {

	/**
	 * Assertion which generate an exception if expression is not true.
	 *
	 * @param expression the expression
	 * @param messageFormat the message format
	 * @param args the args
	 */
	public static void assertTrue(boolean expression, String messageFormat, Object... args) {
		if (!expression)
			throw (new KriptonProcessorException(String.format(messageFormat, args)));

	}

	/**
	 * Assert true or invalid method sign exception.
	 *
	 * @param expression the expression
	 * @param method the method
	 * @param messageFormat the message format
	 * @param args the args
	 */
	public static void assertTrueOrInvalidMethodSignException(boolean expression, SQLiteModelMethod method, String messageFormat, Object... args) {
		if (!expression)
			throw (new InvalidMethodSignException(method, String.format(messageFormat, args)));
	}

	/**
	 * Assert true or invalid method sign exception.
	 *
	 * @param expression the expression
	 * @param method the method
	 */
	public static void assertTrueOrInvalidMethodSignException(boolean expression, SQLiteModelMethod method) {
		if (!expression)
			throw (new InvalidMethodSignException(method));
	}

	/**
	 * if expression is true, it fails. It is the opposite of assert
	 *
	 * @param expression the expression
	 * @param method the method
	 */
	public static void failWithInvalidMethodSignException(boolean expression, SQLiteModelMethod method) {
		assertTrueOrInvalidMethodSignException(!expression, method);
	}

	/**
	 * if expression is true, it fails. It is the opposite of assert
	 *
	 * @param expression the expression
	 * @param method the method
	 * @param messageFormat the message format
	 * @param args the args
	 */
	public static void failWithInvalidMethodSignException(boolean expression, SQLiteModelMethod method, String messageFormat, Object... args) {
		assertTrueOrInvalidMethodSignException(!expression, method, messageFormat, args);
	}

	/**
	 * Fail with method without supported annotation exception.
	 *
	 * @param value the value
	 */
	public static void failWithMethodWithoutSupportedAnnotationException(SQLiteModelMethod value) {
		throw (new MethodWithoutSupportedAnnotationException(value.getParent(), value));
	}

	/**
	 * Fail.
	 *
	 * @param messageFormat the message format
	 * @param args the args
	 */
	public static void fail(String messageFormat, Object... args) {
		assertTrue(false, messageFormat, args);
	}

	/**
	 * Fail incompatible attributes in annotation exception.
	 *
	 * @param messageFormat the message format
	 * @param args the args
	 */
	public static void failIncompatibleAttributesInAnnotationException(String messageFormat, Object... args) {
		throw (new IncompatibleAttributesInAnnotationException(String.format(messageFormat, args)));
	}

	/**
	 * Fails if expression is true.
	 *
	 * @param expression the expression
	 * @param messageFormat the message format
	 * @param args the args
	 */
	public static void fail(boolean expression, String messageFormat, Object... args) {
		assertTrue(!expression, messageFormat, args);
	}

	/**
	 * Assert true or unsupported field type exception.
	 *
	 * @param expression the expression
	 * @param typeName the type name
	 */
	public static void assertTrueOrUnsupportedFieldTypeException(boolean expression, TypeName typeName) {
		if (!expression)
			throw (new UnsupportedFieldTypeException(typeName));
	}

	/**
	 * Assert true or invalid kind for annotation exception.
	 *
	 * @param expression the expression
	 * @param element the element
	 * @param annotationClazz the annotation clazz
	 */
	public static void assertTrueOrInvalidKindForAnnotationException(boolean expression, Element element, Class<? extends Annotation> annotationClazz) {
		if (!expression) {
			String msg = String.format("%s %s, only class can be annotated with @%s annotation", element.getKind(), element, annotationClazz.getSimpleName());
			throw (new InvalidKindForAnnotationException(msg));
		}
	}

	/**
	 * In case a method's parameters is of a type incompatible with specific
	 * annotation.
	 *
	 * @param expression the expression
	 * @param classElement the class element
	 * @param methodElement the method element
	 * @param parameterElement the parameter element
	 * @param annotationClazz the annotation clazz
	 */
	public static void assertTrueOrInvalidTypeForAnnotationMethodParameterException(boolean expression, Element classElement, ExecutableElement methodElement, VariableElement parameterElement,
			Class<? extends Annotation> annotationClazz) {
		if (!expression) {
			String msg = String.format("In method '%s.%s', parameter '%s' has an invalid type '%s' for @%s annotation", classElement.getSimpleName().toString(),
					methodElement.getSimpleName().toString(), parameterElement.getSimpleName().toString(), parameterElement.asType(), annotationClazz.getSimpleName());
			throw (new InvalidTypeForAnnotationException(msg));
		}
	}

	/**
	 * Assert not null.
	 *
	 * @param value the value
	 * @param exception the exception
	 */
	public static void assertNotNull(Object value, KriptonProcessorException exception) {
		if (value == null) {
			throw (exception);
		}

	}

	/**
	 * Assert true or unknown property in JQL exception.
	 *
	 * @param expression the expression
	 * @param method the method
	 * @param columnName the column name
	 */
	public static void assertTrueOrUnknownPropertyInJQLException(boolean expression, SQLiteModelMethod method, String columnName) {
		if (!expression) {
			throw (new UnknownPropertyInJQLException(method, columnName));
		}

	}

	/**
	 * Assert true or unknown class in JQL exception.
	 *
	 * @param expression the expression
	 * @param method the method
	 * @param className the class name
	 */
	public static void assertTrueOrUnknownClassInJQLException(boolean expression, SQLiteModelMethod method, String className) {
		if (!expression) {
			throw (new UnknownClassInJQLException(method, className));
		}
	}

	/**
	 * Assert true or unknown param in JQL exception.
	 *
	 * @param expression the expression
	 * @param method the method
	 * @param paramName the param name
	 */
	public static void assertTrueOrUnknownParamInJQLException(boolean expression, SQLiteModelMethod method, String paramName) {
		if (!expression) {
			throw (new UnknownParamUsedInJQLException(method, paramName));
		}
	}

	/**
	 * Fail unknown property in JQL exception.
	 *
	 * @param method the method
	 * @param annotationClazz the annotation clazz
	 * @param attribute the attribute
	 * @param fieldName the field name
	 */
	public static void failUnknownPropertyInJQLException(SQLiteModelMethod method, Class<? extends Annotation> annotationClazz, AnnotationAttributeType attribute, String fieldName) {
		throw (new UnknownPropertyInJQLException(method, annotationClazz, attribute, fieldName));

	}

	/**
	 * Assert true or invalid property name.
	 *
	 * @param expression the expression
	 * @param item1 the item 1
	 * @param item2 the item 2
	 */
	public static void assertTrueOrInvalidPropertyName(boolean expression, SQLProperty item1, SQLProperty item2) {
		if (!expression) {
			String msg = String.format("Properties '%s#%s' and '%s#%s' must have same column name", item1.getParent().name, item1.name, item2.getParent().name, item2.name);
			throw (new InvalidPropertyToColumnConversion(msg));
		}

	}

	/**
	 * Assert true or missed annotation on class.
	 *
	 * @param expression the expression
	 * @param element the element
	 * @param beanName the bean name
	 * @param annotationClazz the annotation clazz
	 */
	public static void assertTrueOrMissedAnnotationOnClass(boolean expression, Element element, String beanName, Class<? extends Annotation> annotationClazz) {
		if (!expression) {
			String msg = String.format("In dao definition '%s' is referred a bean definition '%s' without @%s annotation", element.getSimpleName(), beanName, annotationClazz.getSimpleName());
			throw (new MissedAnnotationOnClass(msg));
		}
	}

	/**
	 * Asser true or foreign key not found.
	 *
	 * @param expression the expression
	 * @param currentEntity the current entity
	 * @param entity the entity
	 */
	public static void asserTrueOrForeignKeyNotFound(boolean expression, SQLiteEntity currentEntity, ClassName entity) {
		if (!expression) {

			throw (new ForeignKeyNotFoundException(currentEntity, entity));
		}

	}

	/**
	 * Asser true or missed annotation on class exception.
	 *
	 * @param expression the expression
	 * @param entity the entity
	 * @param foreignClassName the foreign class name
	 */
	public static void asserTrueOrMissedAnnotationOnClassException(boolean expression, SQLiteEntity entity, String foreignClassName) {
		if (!expression) {
			String msg = String.format("Entity '%s' refers a bean '%s' without @%s annotation", entity.getSimpleName(), foreignClassName, BindType.class.getSimpleName());
			throw (new MissedAnnotationOnClass(msg));
		}
	}

	/**
	 * Asser true or missed annotation on class exception.
	 *
	 * @param expression the expression
	 * @param daoElement the dao element
	 * @param entityName the entity name
	 */
	public static void asserTrueOrMissedAnnotationOnClassException(boolean expression, TypeElement daoElement, String entityName) {
		if (!expression) {
			String msg = String.format("Dao '%s' referes a bean '%s' without @%s or @%s annotation", daoElement.getQualifiedName(), TypeUtility.className(entityName), BindType.class.getSimpleName(),
					BindTable.class.getSimpleName());
			throw (new MissedAnnotationOnClass(msg));
		}

	}

	/**
	 * Asser true or unspecified bean exception.
	 *
	 * @param expression the expression
	 * @param schema the schema
	 * @param entity the entity
	 * @param foreignClassName the foreign class name
	 */
	public static void asserTrueOrUnspecifiedBeanException(boolean expression, SQLiteDatabaseSchema schema, SQLiteEntity entity, String foreignClassName) {
		if (!expression) {
			String msg = String.format("In dao definition '%s' is referred a bean definition '%s' that is not defined in '%s' schema", entity.getSimpleName(), foreignClassName,
					schema.getElement().getQualifiedName().toString());
			throw (new InvalidDefinition(msg));
		}

	}

	/**
	 * Assert true of invalid definition.
	 *
	 * @param expression the expression
	 * @param property the property
	 * @param message the message
	 */
	public static void assertTrueOfInvalidDefinition(boolean expression, BindProperty property, String message) {
		if (!expression) {
			String msg = String.format("In class '%s', property '%s' has invalid definition: %s", property.getParent().getElement().asType().toString(), property.getName(), message);
			throw (new InvalidDefinition(msg));
		}		
	}
	
	
	/**
	 * Assert true of invalid definition.
	 *
	 * @param expression the expression
	 * @param property the property
	 * @param message the message
	 */
	public static void assertTrueOfInvalidDefinition(boolean expression, SQLProperty property, String message) {
		if (!expression) {
			String msg = String.format("In class '%s', property '%s' has invalid definition: %s", property.getParent().getElement().asType().toString(), property.getName(), message);
			throw (new InvalidDefinition(msg));
		}		
	}


	/**
	 * Assert true or invalid global type apdater exception.
	 *
	 * @param expression the expression
	 * @param sqLiteDatabaseSchema the sq lite database schema
	 * @param typeAdapter the type adapter
	 * @param typeAdapter2 the type adapter 2
	 */
	public static void assertTrueOrInvalidGlobalTypeApdaterException(boolean expression, SQLiteDatabaseSchema sqLiteDatabaseSchema, String typeAdapter, String typeAdapter2) {
		if (!expression) {
			String msg = String.format("In data source '%s', there are two or more global type adapter that cover type '%s': '%s' and '%s'", sqLiteDatabaseSchema.getElement().getQualifiedName(), TypeAdapterHelper.detectSourceType(typeAdapter),  typeAdapter, typeAdapter2);
			throw (new InvalidDefinition(msg));
		}
	}

}
