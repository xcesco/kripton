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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.processor.BaseProcessor;
import com.abubusoft.kripton.processor.core.ImmutableUtility;
import com.abubusoft.kripton.processor.core.ModelClass;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.AnnotationFilter;
import com.abubusoft.kripton.processor.exceptions.PropertyVisibilityException;
import com.squareup.javapoet.TypeName;

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
	 * The listener interface for receiving propertyCreated events. The class
	 * that is interested in processing a propertyCreated event implements this
	 * interface, and the object created with that class is registered with a
	 * component using the component's <code>addPropertyCreatedListener</code>
	 * method. When the propertyCreated event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @param <T>
	 *            the generic type
	 * @param <E>
	 *            the element type
	 */
	public interface PropertyCreatedListener<T extends ModelClass<? extends E>, E extends ModelProperty> {

		/**
		 * If true, the property will be included in the class. If return false,
		 * the property will be ignored
		 *
		 * @param entity
		 *            the entity
		 * @param property
		 *            the property
		 * @return true to include property, false otherwise
		 */
		boolean onProperty(T entity, E property);
	};

	/**
	 * Given a model clazz, define its properties. The listener allow to select
	 * which property include in class definition.
	 *
	 * @param <P>
	 *            the generic type
	 * @param <T>
	 *            the generic type
	 * @param elementUtils
	 *            the element utils
	 * @param entity
	 *            the entity
	 * @param factoryProperty
	 *            the factory property
	 * @param propertyAnnotationFilter
	 *            if null, no filter is applied to annotations
	 * @param listener
	 *            the listener
	 */
	public static <P extends ModelProperty, T extends ModelClass<P>> void buildProperties(Elements elementUtils,
			T entity, PropertyFactory<T, P> factoryProperty, AnnotationFilter propertyAnnotationFilter,
			PropertyCreatedListener<T, P> listener) {
		List<? extends Element> listA = elementUtils.getAllMembers(entity.getElement());
		List<Element> list = new ArrayList<Element>(listA);
		Map<String, P> propertyMap = new HashMap<>();

		if (propertyAnnotationFilter != null) {
			AnnotationUtility.forEachAnnotations(entity.getElement(), propertyAnnotationFilter, null);
		}

		// extract fields directly from class
		extractFields(entity, factoryProperty, list, propertyMap);

		list = null;
		// go up in class hierarchy and extract other fields
		String parentClassName = entity.getElement().getSuperclass().toString();
		while (parentClassName != null && !Object.class.getCanonicalName().equals(parentClassName)) {
			TypeElement parentTypeElement = BaseProcessor.elementUtils.getTypeElement(parentClassName);

			if (parentTypeElement != null) {
				List<? extends Element> parentList = elementUtils.getAllMembers(parentTypeElement);
				list = new ArrayList<>(parentList);
				extractFields(entity, factoryProperty, list, propertyMap);
				parentClassName = parentTypeElement.getSuperclass().toString();
			} else {
				// parentTypeElement is null
				parentClassName = null;
			}
		}

		// Method Analysis
		//
		String methodName;
		String propertyName = "";
		ModelProperty currentKriptonField;

		Converter<String, String> converter = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_CAMEL);
		int status = 0;

		// define property for the entity (must be done before working with
		// methods, due to parameters resolver)
		for (P p : propertyMap.values()) {
			entity.add(p);
		}

		// int i=0;
		// AnnotationProcessorUtilis.printMessage(String.format((i++) + "entity
		// %s ",entity.getName()));
		//
		// restore original methods and fields
		list = new ArrayList<Element>(listA);
		for (Element item : list) {
			methodName = item.getSimpleName().toString();

			// cus
			// AnnotationProcessorUtilis.printMessage(String.format((i++) +
			// "item %s type %s modifiers [%s] ",
			// item.getSimpleName().toString(), item.getKind(),
			// item.getModifiers()));

			if (item.getKind() == ElementKind.METHOD && item.getModifiers().contains(Modifier.PUBLIC)) {
				ExecutableElement method = (ExecutableElement) item;

				// this method can not be usefull
				if (method.getParameters().size() > 1)
					continue;

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
				if (propertyMap.containsKey(propertyName)) {
					currentKriptonField = propertyMap.get(propertyName);

					TypeMirror paramTypeMirror = method.getParameters().size() == 1
							? method.getParameters().get(0).asType() : null;
					TypeMirror returnTypeMirror = method.getReturnType();

					// GET
					if (status == 0 && currentKriptonField
							.isType(entity.resolveTypeVariable(TypeUtility.typeName(returnTypeMirror)))) {
						currentKriptonField.setFieldWithGetter(true);
						// IS
					} else if (status == 1 && currentKriptonField
							.isType(entity.resolveTypeVariable(TypeUtility.typeName(returnTypeMirror)))) {
						currentKriptonField.setFieldWithIs(true);
						// SET
					} else if (status == 2 && currentKriptonField
							.isType(entity.resolveTypeVariable(TypeUtility.typeName(paramTypeMirror)))) {
						currentKriptonField.setFieldWithSetter(true);
					}
				}
			}
		}

		if (listener != null) {
			List<P> listPropertiesToFilter = new ArrayList<P>(entity.getCollection());

			// copy all properties (included the excluded one) into immutable
			// collection
			entity.getImmutableCollection().addAll(entity.getCollection());

			for (P item : listPropertiesToFilter) {
				if (!listener.onProperty(entity, item)) {
					entity.getCollection().remove(item);
				}
			}

		}
	}

	/**
	 * @param entity
	 * @param factoryProperty
	 * @param list
	 * @param propertyMap
	 */
	private static <P extends ModelProperty, T extends ModelClass<P>> void extractFields(T entity,
			PropertyFactory<T, P> factoryProperty, List<Element> list, Map<String, P> propertyMap) {
		P field;
		// fill property map from current class
		for (Element item : list) {
			if (item.getKind() == ElementKind.FIELD && modifierIsAcceptable(item)) {
				// insert only if it does not already exists
				if (!propertyMap.containsKey(item.getSimpleName().toString())) {
					field = factoryProperty.createProperty(entity, item);

					// TreePath treePath = KriptonProcessor.trees.getPath(item);
					// AnnotationVisitor visitor = new AnnotationVisitor();
					// TypeMirror recognizerType = visitor. (treePath, null);

					// put properties in a map
					propertyMap.put(field.getName(), field);
				}
			}
		}
	}

	/**
	 * Modifier is acceptable.
	 *
	 * @param item
	 *            the item
	 * @return true, if successful
	 */
	static boolean modifierIsAcceptable(Element item) {
		// kotlin define properties as final
		Object[] values = { Modifier.NATIVE, Modifier.STATIC,
				/* Modifier.FINAL, */ Modifier.ABSTRACT };

		for (Object i : values) {
			if (item.getModifiers().contains(i))
				return false;
		}

		return true;
	}

	/**
	 * Gets the ter.
	 *
	 * @param beanClass
	 *            the bean class
	 * @param property
	 *            the property
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
			throw new PropertyVisibilityException(String.format("In class '%s' property '%s' can not be read",
					property.getParent().getElement().asType(), property.getName()));
		}
	}

	/**
	 * Gets the ter.
	 *
	 * @param beanName
	 *            the bean name
	 * @param beanClass
	 *            the bean class
	 * @param property
	 *            the property
	 * @return the ter
	 */
	public static String getter(String beanName, TypeName beanClass, ModelProperty property) {
		return beanName + (beanClass != null ? "." + getter(property) : "");
	}

	/**
	 * Setter.
	 *
	 * @param beanClass
	 *            the bean class
	 * @param property
	 *            the property
	 * @return the string
	 */
	public static String setter(TypeName beanClass, String beanName, ModelProperty property) {
		if (property.getParent() != null && ((ModelClass<?>) property.getParent()).isImmutablePojo()) {
			return ImmutableUtility.IMMUTABLE_PREFIX + property.getName();
		} else {
			String prefix = "";
			if (beanName != null) {
				prefix = beanName + ".";
			}
			if (property.isPublicField()) {
				return prefix + property.getName();
			} else if (property.isFieldWithSetter()) {
				return prefix + "set" + converterField2Method.convert(property.getName());
			} else {
				throw new PropertyVisibilityException(String.format("property '%s' of class '%s' can not be modify",
						property.getName(), property.getParent().getElement().asType()));
			}
		}
	}

	/**
	 * Setter.
	 *
	 * @param beanClass
	 *            the bean class
	 * @param beanName
	 *            the bean name
	 * @param property
	 *            the property
	 * @param value
	 *            the value
	 * @return the string
	 */
	public static String setter(TypeName beanClass, String beanName, ModelProperty property, String value) {
		if (property.getParent() != null && ((ModelClass<?>) property.getParent()).isImmutablePojo()) {
			return ImmutableUtility.IMMUTABLE_PREFIX + property.getName() + "=" + value;
		} else {
			return beanName + (beanClass != null ? "." + setter(property, value) : "=" + value);
		}
	}

	/**
	 * Setter.
	 *
	 * @param beanClass
	 *            the bean class
	 * @param property
	 *            the property
	 * @param value
	 *            the value
	 * @return the string
	 */
	private static String setter(ModelProperty property, String value) {
		if (property.getParent() != null && ((ModelClass<?>) property.getParent()).isImmutablePojo()) {
			return ImmutableUtility.IMMUTABLE_PREFIX + property.getName() + "=" + value;
		} else {
			if (property.isPublicField())
				return property.getName() + "=" + value;
			else if (property.isFieldWithSetter()) {
				return "set" + converterField2Method.convert(property.getName()) + "(" + value + ")";
			} else {
				throw new PropertyVisibilityException(String.format("property '%s' of class '%s' can not be modify",
						property.getName(), property.getParent().getElement().asType()));
			}
		}

	}

}
