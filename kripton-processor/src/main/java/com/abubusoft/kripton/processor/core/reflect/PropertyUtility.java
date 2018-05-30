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
package com.abubusoft.kripton.processor.core.reflect;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.processor.core.ModelClass;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.AnnotationFilter;
import com.abubusoft.kripton.processor.exceptions.PropertyVisibilityException;
import com.squareup.javapoet.TypeName;

// TODO: Auto-generated Javadoc
/**
 * The Class PropertyUtility.
 */
public abstract class PropertyUtility {

	/** The converter field 2 method. */
	static Converter<String, String> converterField2Method = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

	/** The logger. */
	static Logger logger = Logger.getGlobal();

	/** The Constant SET_PREFIX. */
	private static final String SET_PREFIX = "set";
	
	/** The Constant IS_PREFIX. */
	private static final String IS_PREFIX = "is";
	
	/** The Constant GET_PREFIX. */
	private static final String GET_PREFIX = "get";

	/**
	 * The listener interface for receiving propertyCreated events.
	 * The class that is interested in processing a propertyCreated
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addPropertyCreatedListener</code> method. When
	 * the propertyCreated event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @param <T> the generic type
	 * @param <E> the element type
	 */
	public interface PropertyCreatedListener<T extends ModelClass<? extends E>, E extends ModelProperty> {
		
		/**
		 * If true, the property will be included in the class. If return false,
		 * the property will be ignored
		 *
		 * @param entity the entity
		 * @param property the property
		 * @return true to include property, false otherwise
		 */
		boolean onProperty(T entity, E property);
	};

	/**
	 * Given a model clazz, define its properties. The listener allow to select
	 * which property include in class definition.
	 *
	 * @param <P> the generic type
	 * @param <T> the generic type
	 * @param elementUtils the element utils
	 * @param entity the entity
	 * @param factoryProperty the factory property
	 * @param propertyAnnotationFilter            if null, no filter is applied to annotations
	 * @param listener the listener
	 */
	public static <P extends ModelProperty, T extends ModelClass<P>> void buildProperties(Elements elementUtils, T entity, PropertyFactory<T, P> factoryProperty,
			AnnotationFilter propertyAnnotationFilter, PropertyCreatedListener<T, P> listener) {
		List<? extends Element> list = elementUtils.getAllMembers(entity.getElement());

		if (propertyAnnotationFilter != null) {
			AnnotationUtility.forEachAnnotations(entity.getElement(), propertyAnnotationFilter, null);
		}

		P field;
		for (Element item : list) {
			if (item.getKind() == ElementKind.FIELD && modifierIsAcceptable(item)) {
				field = factoryProperty.createProperty(entity, item);
				//AnnotationUtility.buildAnnotations(elementUtils, field, propertyAnnotationFilter);
				entity.add(field);
			}
		}

		// Analizziamo i metodi
		//
		String methodName;
		String propertyName = "";
		ModelProperty currentKriptonField;

		Converter<String, String> converter = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_CAMEL);
		int status = 0;

		for (Element item : list) {
			methodName = item.getSimpleName().toString();
			if (item.getKind() == ElementKind.METHOD && item.getModifiers().contains(Modifier.PUBLIC)) {
				ExecutableElement  method=(ExecutableElement)item;
				
				// this method can not be usefull
				if (method.getParameters().size()>1) continue;
				
				status = -1;
				if (methodName.startsWith(GET_PREFIX)) {
					status = 0;
					propertyName = methodName.substring(GET_PREFIX.length());
				} else if (methodName.startsWith(IS_PREFIX)) {
					status = 1;
					propertyName = methodName.substring(IS_PREFIX.length());
				} else if (methodName.startsWith(SET_PREFIX)) {
					status = 2;
					propertyName = methodName.substring(SET_PREFIX.length());
				}

				propertyName = converter.convert(propertyName);
				if (entity.contains(propertyName)) {
					currentKriptonField = entity.get(propertyName);
					
					TypeMirror paramTypeMirror=method.getParameters().size()==1 ? method.getParameters().get(0).asType(): null;
					TypeMirror returnTypeMirror=method.getReturnType();

					// GET
					if (status == 0 && currentKriptonField.isType(entity.resolveTypeVariable(TypeUtility.typeName(returnTypeMirror)))) {
						currentKriptonField.setFieldWithGetter(true);
					// IS
					} else if (status == 1 && currentKriptonField.isType(entity.resolveTypeVariable(TypeUtility.typeName(returnTypeMirror)))) {
						currentKriptonField.setFieldWithIs(true);
					// SET
					} else if (status == 2 && currentKriptonField.isType(entity.resolveTypeVariable(TypeUtility.typeName(paramTypeMirror)))) {
						currentKriptonField.setFieldWithSetter(true);
					}

				}
			}
		}

		if (listener != null) {
			List<P> listPropertiesToFilter = new ArrayList<P>(entity.getCollection());

			for (P item : listPropertiesToFilter) {
				if (!listener.onProperty(entity, item)) {
					entity.getCollection().remove(item);
				}
			}

		}
	}

	/**
	 * Modifier is acceptable.
	 *
	 * @param item the item
	 * @return true, if successful
	 */
	static boolean modifierIsAcceptable(Element item) {
		Object[] values = { Modifier.NATIVE, Modifier.STATIC, Modifier.FINAL, Modifier.ABSTRACT };

		for (Object i : values) {
			if (item.getModifiers().contains(i))
				return false;
		}

		return true;
	}

	/**
	 * Gets the ter.
	 *
	 * @param beanClass the bean class
	 * @param property the property
	 * @return the ter
	 */
	public static String getter(ModelProperty property) {
		if (property.isPublicField())
			return property.getName();

		if (property.isFieldWithGetter()) {
			return "get" + converterField2Method.convert(property.getName()) + "()";
		} else if (property.isFieldWithIs()) {
			return "is" + converterField2Method.convert(property.getName()) + "()";
		} else {			
			throw new PropertyVisibilityException(String.format("In class '%s' property '%s' can not be read", property.getParent().getElement().asType(), property.getName()));
		}
	}

	/**
	 * Gets the ter.
	 *
	 * @param beanName the bean name
	 * @param beanClass the bean class
	 * @param property the property
	 * @return the ter
	 */
	public static String getter(String beanName, TypeName beanClass, ModelProperty property) {
		return beanName + (beanClass != null ? "." + getter(property) : "");
	}

	/**
	 * Setter.
	 *
	 * @param beanClass the bean class
	 * @param property the property
	 * @return the string
	 */
	public static String setter(TypeName beanClass, ModelProperty property) {
		if (property.isPublicField()) {
			return property.getName();
		} else if (property.isFieldWithSetter()) {
			return "set" + converterField2Method.convert(property.getName());
		} else {			
			throw new PropertyVisibilityException(String.format("property '%s' of class '%s' can not be modify", property.getName(), property.getParent().getElement().asType()));
		}
	}

	/**
	 * Setter.
	 *
	 * @param beanClass the bean class
	 * @param beanName the bean name
	 * @param property the property
	 * @param value the value
	 * @return the string
	 */
	public static String setter(TypeName beanClass, String beanName, ModelProperty property, String value) {
		return beanName + (beanClass != null ? "." + setter(property, value) : "=" + value);
	}

	/**
	 * Setter.
	 *
	 * @param beanClass the bean class
	 * @param property the property
	 * @param value the value
	 * @return the string
	 */
	private static String setter(ModelProperty property, String value) {
		if (property.isPublicField())
			return property.getName() + "=" + value;
		else if (property.isFieldWithSetter()) {
			return "set" + converterField2Method.convert(property.getName()) + "(" + value + ")";
		} else {
			throw new PropertyVisibilityException(String.format("property '%s' of class '%s' can not be modify", property.getName(), property.getParent().getElement().asType()));
		}
	}

}
