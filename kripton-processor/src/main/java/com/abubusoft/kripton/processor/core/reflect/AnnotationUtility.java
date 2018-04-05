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

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.util.Elements;

import org.apache.commons.lang3.StringEscapeUtils;

import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.processor.BaseProcessor;
import com.abubusoft.kripton.processor.BindDataSourceSubProcessor;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelEntity;
import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.ModelWithAnnotation;

// TODO: Auto-generated Javadoc
/**
 * The Class AnnotationUtility.
 */
public abstract class AnnotationUtility {

	/** The Constant classPattern. */
	private static final Pattern classPattern = Pattern.compile("([\\w.]*).class");

	/** The Constant arrayPattern. */
	private static final Pattern arrayPattern = Pattern.compile("\"([^\"]*)\"");

	/**
	 * Extract as array of class name.
	 *
	 * @param value the value
	 * @return the list
	 */
	static List<String> extractAsArrayOfClassName(String value) {
		Matcher matcher = classPattern.matcher(value);

		List<String> result = new ArrayList<String>();

		while (matcher.find()) {
			result.add(matcher.group(1));
		}

		return result;
	}

	/**
	 * Extract as array of string.
	 *
	 * @param value the value
	 * @return the list
	 */
	public static List<String> extractAsArrayOfString(String value) {
		Matcher matcher = arrayPattern.matcher(value);

		List<String> result = new ArrayList<String>();

		//	we need to unscape javascript format, otherwise string like " where id like '%' " will become " where id like \'%\' " 
		while (matcher.find()) {
			result.add(StringEscapeUtils.unescapeEcmaScript(matcher.group(1)));
		}

		return result;
	}

	/**
	 * The listener interface for receiving annotationFound events.
	 * The class that is interested in processing a annotationFound
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addAnnotationFoundListener</code> method. When
	 * the annotationFound event occurs, that object's appropriate
	 * method is invoked.
	 *
	 */
	public interface AnnotationFoundListener {
		
		/**
		 * If true, annotation is accepted.
		 *
		 * @param executableMethod the executable method
		 * @param annotationClassName the annotation class name
		 * @param attributes the attributes
		 */
		void onAcceptAnnotation(Element executableMethod, final String annotationClassName, final Map<String, String> attributes);
	}

	/**
	 * The listener interface for receiving methodFound events.
	 * The class that is interested in processing a methodFound
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addMethodFoundListener</code> method. When
	 * the methodFound event occurs, that object's appropriate
	 * method is invoked.
	 *
	 */
	public interface MethodFoundListener {
		
		/**
		 * On method.
		 *
		 * @param executableMethod the executable method
		 */
		void onMethod(ExecutableElement executableMethod);
	}

	/**
	 * Iterate over annotations of currentElement. Accept only annotation in
	 * accepted set.
	 *
	 * @param currentElement the current element
	 * @param filter the filter
	 * @param listener the listener
	 */
	public static void forEachAnnotations(Element currentElement, AnnotationFilter filter, AnnotationFoundListener listener) {
		final Elements elementUtils=BaseProcessor.elementUtils;
		List<? extends AnnotationMirror> annotationList = elementUtils.getAllAnnotationMirrors(currentElement);
		String annotationClassName;
		// boolean valid=true;

		for (AnnotationMirror annotation : annotationList) {
			Map<String, String> values = new HashMap<String, String>();
			annotationClassName = annotation.getAnnotationType().asElement().toString();

			if (filter != null && !filter.isAccepted(annotationClassName)) {
				continue;
			}

			values.clear();
			for (Entry<? extends ExecutableElement, ? extends AnnotationValue> annotationItem : elementUtils.getElementValuesWithDefaults(annotation).entrySet()) {
				String value = annotationItem.getValue().toString();
				if (value.startsWith("\"") && value.endsWith("\"")) {
					value = value.substring(1);
					value = value.substring(0, value.length() - 1);
				}
				values.put(annotationItem.getKey().getSimpleName().toString(), value);
			}

			if (listener != null) {
				listener.onAcceptAnnotation(currentElement, annotationClassName, values);
			}
		}				
	}

	/**
	 * Iterate over annotations of currentElement.
	 *
	 * @param currentElement the current element
	 * @param listener the listener
	 */
	public static void forEachAnnotations(Element currentElement, AnnotationFoundListener listener) {
		forEachAnnotations(currentElement, null, listener);
	}

	/**
	 * Extract from an annotation of a method the attribute value specified.
	 *
	 * @param elementUtils the element utils
	 * @param item the item
	 * @param annotationClass the annotation class
	 * @param attributeName the attribute name
	 * @return attribute value extracted as array of class typeName
	 */
	public static List<String> extractAsClassNameArray(Elements elementUtils, Element item, Class<? extends Annotation> annotationClass, AnnotationAttributeType attributeName) {
		final One<List<String>> result = new One<List<String>>();
		result.value0=new ArrayList<>();

		extractString(elementUtils, item, annotationClass, attributeName, new OnAttributeFoundListener() {

			@Override
			public void onFound(String value) {
				result.value0 = AnnotationUtility.extractAsArrayOfClassName(value);
			}
		});

		return result.value0;
	}

	/**
	 * Extract from an annotation of a method the attribute value specified.
	 *
	 * @param item the item
	 * @param annotationClass the annotation class
	 * @param attributeName the attribute name
	 * @return attribute value extracted as class typeName
	 */
	public static String extractAsClassName(Element item, Class<? extends Annotation> annotationClass, AnnotationAttributeType attributeName) {
		final Elements elementUtils=BindDataSourceSubProcessor.elementUtils;
		final One<String> result = new One<String>();

		extractString(elementUtils, item, annotationClass, attributeName, new OnAttributeFoundListener() {

			@Override
			public void onFound(String value) {
				result.value0 = AnnotationUtility.extractAsArrayOfClassName(value).get(0);
			}
		});

		return result.value0;
	}

	/**
	 * Extract from an annotation of a method the attribute value specified.
	 *
	 * @param item the item
	 * @param annotationClass the annotation class
	 * @param attributeName the attribute name
	 * @return attribute value extracted as class typeName
	 */
	public static String extractAsString(final Element item, final Class<? extends Annotation> annotationClass, final AnnotationAttributeType attributeName) {
		final Elements elementUtils=BaseProcessor.elementUtils;
		
		final One<String> result = new One<String>();

		extractString(elementUtils, item, annotationClass, attributeName, new OnAttributeFoundListener() {

			@Override
			public void onFound(String value) {
				List<String> list = AnnotationUtility.extractAsArrayOfString(value);

				if (list.size() > 0)
					result.value0 = list.get(0);
				else
					result.value0 = value;							
			}
		});

		return result.value0;
	}
	
	/**
	 * Extract from an annotation of a method the attribute value specified. IF NO ELEMENT WAS FOUND, AN EMPTY LIST WILL RETURN.
	 *
	 * @param item the item
	 * @param annotationClass the annotation class
	 * @param attributeName the attribute name
	 * @return attribute value extracted as class typeName
	 */
	public static List<String> extractAsStringArray(Element item, Class<? extends Annotation> annotationClass, AnnotationAttributeType attributeName) {
		final Elements elementUtils=BaseProcessor.elementUtils;
		final One<List<String>> result = new One<List<String>>(new ArrayList<String>());

		extractString(elementUtils, item, annotationClass, attributeName, new OnAttributeFoundListener() {

			@Override
			public void onFound(String value) {
				List<String> list = AnnotationUtility.extractAsArrayOfString(value);

				result.value0 = list;
			}
		});

		return result.value0;
	}

	/**
	 * Estract from an annotation of a property the attribute value specified.
	 *
	 * @param item the item
	 * @param annotationClass            annotation to analyze
	 * @param attribute the attribute
	 * @return attribute value as list of string
	 */
	public static String extractAsEnumerationValue(Element item, Class<? extends Annotation> annotationClass, AnnotationAttributeType attribute) {
		final Elements elementUtils=BindDataSourceSubProcessor.elementUtils;
		final One<String> result = new One<String>();

		extractAttributeValue(elementUtils, item, annotationClass.getName(), attribute, new OnAttributeFoundListener() {

			@Override
			public void onFound(String value) {
				if (value.indexOf(".") >= 0)
					result.value0 = value.substring(value.lastIndexOf(".") + 1);
			}
		});

		return result.value0;
	}

	/**
	 * The listener interface for receiving onAttributeFound events.
	 * The class that is interested in processing a onAttributeFound
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addOnAttributeFoundListener<code> method. When
	 * the onAttributeFound event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see OnAttributeFoundEvent
	 */
	interface OnAttributeFoundListener {
		
		/**
		 * On found.
		 *
		 * @param value the value
		 */
		void onFound(String value);
	}

	/**
	 * Extract string.
	 *
	 * @param elementUtils the element utils
	 * @param item the item
	 * @param annotationClass the annotation class
	 * @param attribute the attribute
	 * @param listener the listener
	 */
	static void extractString(Elements elementUtils, Element item, Class<? extends Annotation> annotationClass, AnnotationAttributeType attribute, OnAttributeFoundListener listener) {
		extractAttributeValue(elementUtils, item, annotationClass.getCanonicalName(), attribute, listener);
	}

	/**
	 * Extract from an annotation of a method the attribute value specified.
	 *
	 * @param elementUtils the element utils
	 * @param item the item
	 * @param annotationName the annotation name
	 * @param attribute the attribute
	 * @param listener the listener
	 */
	static void extractAttributeValue(Elements elementUtils, Element item, String annotationName, AnnotationAttributeType attribute, OnAttributeFoundListener listener) {
		List<? extends AnnotationMirror> annotationList = elementUtils.getAllAnnotationMirrors(item);
		for (AnnotationMirror annotation : annotationList) {			
			if (annotationName.equals(annotation.getAnnotationType().asElement().toString())) {
				// found annotation
				for (Entry<? extends ExecutableElement, ? extends AnnotationValue> annotationItem : elementUtils.getElementValuesWithDefaults(annotation).entrySet()) {
					if (attribute.isEquals(annotationItem.getKey())) {
						listener.onFound(annotationItem.getValue().toString());
						return;
					}
				}
			}
		}
	}
	
	/**
	 * Extract from an annotation of a method the attribute value specified.
	 *
	 * @param method            method to analyze
	 * @param annotationClass            annotation to analyze
	 * @param attribute the attribute
	 * @return attribute value as list of string
	 */
	public static List<String> extractAsStringArray(ModelMethod method, ModelAnnotation annotationClass, AnnotationAttributeType attribute) {
		final Elements elementUtils=BaseProcessor.elementUtils;
		final One<List<String>> result = new One<List<String>>();
		extractAttributeValue(elementUtils, method.getElement(), annotationClass.getName(), attribute, new OnAttributeFoundListener() {

			@Override
			public void onFound(String value) {
				result.value0 = AnnotationUtility.extractAsArrayOfString(value);
			}
		});

		return result.value0;
	}

	/**
	 * Estract from an annotation of a property the attribute value specified.
	 *
	 * @param property            property to analyze
	 * @param annotationClass            annotation to analyze
	 * @param attribute the attribute
	 * @return attribute value as list of string
	 */
	public static String extractAsEnumerationValue(ModelProperty property, ModelAnnotation annotationClass, AnnotationAttributeType attribute) {
		final Elements elementUtils=BaseProcessor.elementUtils;
		final One<String> result = new One<String>();

		extractAttributeValue(elementUtils, property.getElement(), annotationClass.getName(), attribute, new OnAttributeFoundListener() {

			@Override
			public void onFound(String value) {

				result.value0 = value.substring(value.lastIndexOf(".") + 1);
			}
		});

		return result.value0;
	}

	
	/**
	 * Builds the annotation list.
	 *
	 * @param element the element
	 * @param filter the filter
	 * @return the list
	 */
	public static List<ModelAnnotation> buildAnnotationList(final Element element, final AnnotationFilter filter) {
		final List<ModelAnnotation> annotationList=new ArrayList<>();
		
		forEachAnnotations(element, filter, new AnnotationFoundListener() {

			@Override
			public void onAcceptAnnotation(Element executableMethod, String annotationClassName, Map<String, String> attributes) {
				ModelAnnotation annotation = new ModelAnnotation(annotationClassName, attributes);
				
				annotationList.add(annotation);
			}
		});
		
		return annotationList;
	}
	
	/**
	 * Builds the annotation list.
	 *
	 * @param element the element
	 * @return the list
	 */
	public static List<ModelAnnotation> buildAnnotationList(final Element element) {
		return buildAnnotationList(element, null);
	}


	/**
	 * The Class AnnotationFilter.
	 */
	public static class AnnotationFilter {
		
		/**
		 * Instantiates a new annotation filter.
		 *
		 * @param annotations the annotations
		 */
		AnnotationFilter(Set<String> annotations) {
			annotationNames = annotations;
		}

		/** The annotation names. */
		Set<String> annotationNames;

		/**
		 * Checks if is accepted.
		 *
		 * @param annotationName the annotation name
		 * @return true, if is accepted
		 */
		public boolean isAccepted(String annotationName) {
			return annotationNames.contains(annotationName);
		}

		/**
		 * Builder.
		 *
		 * @return the annotation filter builder
		 */
		public static AnnotationFilterBuilder builder() {
			return new AnnotationFilterBuilder();
		}

	}

	/**
	 * The Class AnnotationFilterBuilder.
	 */
	public static class AnnotationFilterBuilder {
		
		/**
		 * Instantiates a new annotation filter builder.
		 */
		AnnotationFilterBuilder() {
			set = new HashSet<String>();
		}

		/**
		 * Adds the.
		 *
		 * @param annotation the annotation
		 * @return the annotation filter builder
		 */
		public AnnotationFilterBuilder add(Class<? extends Annotation> annotation) {
			set.add(annotation.getCanonicalName());
			return this;
		}

		/** The set. */
		Set<String> set;

		/**
		 * Builds the.
		 *
		 * @return the annotation filter
		 */
		public AnnotationFilter build() {
			return new AnnotationFilter(set);
		}

	}

	/**
	 * Extract as int.
	 *
	 * @param item the item
	 * @param annotationClass the annotation class
	 * @param attributeName the attribute name
	 * @return the int
	 */
	public static int extractAsInt(Element item, Class<? extends Annotation> annotationClass, AnnotationAttributeType attributeName) {
		final Elements elementUtils=BaseProcessor.elementUtils;
		final One<Integer> result = new One<Integer>();
		result.value0 = 0;

		extractString(elementUtils, item, annotationClass, attributeName, new OnAttributeFoundListener() {

			@Override
			public void onFound(String value) {
				result.value0 = Integer.parseInt(value);
			}
		});

		return result.value0;
	}

	/**
	 * Extract as boolean.
	 *
	 * @param item the item
	 * @param annotationClass the annotation class
	 * @param attribute the attribute
	 * @return true, if successful
	 */
	public static boolean extractAsBoolean(Element item, Class<? extends Annotation> annotationClass, AnnotationAttributeType attribute) {
		final Elements elementUtils=BaseProcessor.elementUtils;
		final One<Boolean> result = new One<Boolean>(false);

		extractString(elementUtils, item, annotationClass, attribute, new OnAttributeFoundListener() {

			@Override
			public void onFound(String value) {
				result.value0 = Boolean.parseBoolean(value);
			}
		});

		return result.value0;
	}

	/**
	 * Estract from an annotation of a method the attribute value specified.
	 *
	 * @param <E> the element type
	 * @param item            entity to analyze
	 * @param annotation            annotation to analyze
	 * @param attribute the attribute
	 * @return true, if successful
	 */
	public static <E extends ModelEntity<?>> boolean extractAsBoolean(E item, ModelAnnotation annotation, AnnotationAttributeType attribute) {
		final Elements elementUtils=BaseProcessor.elementUtils;
		final One<Boolean> result = new One<Boolean>(false);

		extractAttributeValue(elementUtils, item.getElement(), annotation.getName(), attribute, new OnAttributeFoundListener() {

			@Override
			public void onFound(String value) {
				result.value0 = Boolean.valueOf(value);
			}
		});

		return result.value0;
	}

	/**
	 * Gets the annotation attribute as boolean.
	 *
	 * @param model the model
	 * @param annotation the annotation
	 * @param attribute the attribute
	 * @param defaultValue the default value
	 * @return the annotation attribute as boolean
	 */
	public static Boolean getAnnotationAttributeAsBoolean(ModelWithAnnotation model, Class<? extends Annotation> annotation, AnnotationAttributeType attribute, Boolean defaultValue) {
		return getAnnotationAttribute(model, annotation, attribute, defaultValue, new OnAnnotationAttributeListener<Boolean>() {

			@Override
			public Boolean onFound(String value) {
				return Boolean.valueOf(value);
			}
		});
	}


	/**
	 * Gets the annotation attribute.
	 *
	 * @param <T> the generic type
	 * @param model the model
	 * @param annotation the annotation
	 * @param attribute the attribute
	 * @param defaultValue the default value
	 * @param listener the listener
	 * @return the annotation attribute
	 */
	static <T> T getAnnotationAttribute(ModelWithAnnotation model, Class<? extends Annotation> annotation, AnnotationAttributeType attribute, T defaultValue,
			OnAnnotationAttributeListener<T> listener) {
		String attributeResult;

		ModelAnnotation item = model.getAnnotation(annotation);
		if (item != null) {
			attributeResult = item.getAttribute(attribute);

			return listener.onFound(attributeResult);
		}

		return defaultValue;
	}

	/**
	 * The listener interface for receiving onAnnotationAttribute events.
	 * The class that is interested in processing a onAnnotationAttribute
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addOnAnnotationAttributeListener<code> method. When
	 * the onAnnotationAttribute event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @param <T> the generic type
	 * @see OnAnnotationAttributeEvent
	 */
	interface OnAnnotationAttributeListener<T> {
		
		/**
		 * On found.
		 *
		 * @param value the value
		 * @return the t
		 */
		T onFound(String value);
	}

}
