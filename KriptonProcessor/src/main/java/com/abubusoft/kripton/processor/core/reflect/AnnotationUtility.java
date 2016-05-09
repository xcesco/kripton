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

import com.abubusoft.kripton.android.annotation.SQLDatabaseSchema;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.ModelWithAnnotation;
import com.abubusoft.kripton.processor.sqlite.model.AnnotationAttributeType;

public class AnnotationUtility {

	private static final Pattern classPattern = Pattern.compile("([\\w.]*).class");

	private static final Pattern arrayPattern = Pattern.compile("\"(.*)\"");

	static List<String> extractAsArrayOfClassName(String value) {
		Matcher matcher = classPattern.matcher(value);

		List<String> result = new ArrayList<String>();

		while (matcher.find()) {
			result.add(matcher.group(1));
		}

		return result;
	}

	static List<String> extractAsArrayOfString(String value) {
		Matcher matcher = arrayPattern.matcher(value);

		List<String> result = new ArrayList<String>();

		while (matcher.find()) {
			result.add(matcher.group(1));
		}

		return result;
	}

	public interface AnnotationFoundListener {
		void onAnnotation(Element executableMethod, final String annotationClassName, final Map<String, String> attributes);
	}

	public interface MethodFoundListener {
		void onMethod(ExecutableElement executableMethod);
	}

	/**
	 * Iterate over annotations of currentElement.
	 * 
	 * @param elementUtils
	 * @param currentElement
	 * @param filter
	 * @param listener
	 */
	public static void forEachAnnotations(Elements elementUtils, Element currentElement, AnnotationFilter filter, AnnotationFoundListener listener) {
		List<? extends AnnotationMirror> annotationList = elementUtils.getAllAnnotationMirrors(currentElement);
		String annotationClassName;
		Map<String, String> values = new HashMap<String, String>();
		for (AnnotationMirror annotation : annotationList) {
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
			listener.onAnnotation(currentElement, annotationClassName, values);
		}
	}

	/**
	 * Iterate over annotations of currentElement.
	 * 
	 * @param elementUtils
	 * @param currentElement
	 * @param listener
	 */
	public static void forEachAnnotations(Elements elementUtils, Element currentElement, AnnotationFoundListener listener) {
		forEachAnnotations(elementUtils, currentElement, null, listener);
	}

	/**
	 * Extract from an annotation of a method the attribute value specified.
	 * 
	 * @param elementUtils
	 * @param item
	 * @param annotationClass
	 * @param attributeName
	 * @return
	 * 		attribute value extracted as array of class name
	 */
	public static List<String> extractAsClassNameArray(Elements elementUtils, Element item, Class<? extends Annotation> annotationClass, AnnotationAttributeType attributeName) {
		final Result<List<String>> result=new Result<List<String>>(); 
		
		extractString(elementUtils, item, annotationClass, attributeName, new OnAttributeFoundListener() {
			
			@Override
			public void onFound(String value) {
				result.value=AnnotationUtility.extractAsArrayOfClassName(value);				
			}
		});
		
		return result.value;			
	}

	/**
	 * Extract from an annotation of a method the attribute value specified.
	 * 
	 * @param elementUtils
	 * @param item
	 * @param annotationClass
	 * @param attributeName
	 * @return attribute value extracted as class name
	 */
	public static String extractAsClassName(Elements elementUtils, Element item, Class<? extends Annotation> annotationClass, AnnotationAttributeType attributeName) {
		final Result<String> result=new Result<String>(); 
		
		extractString(elementUtils, item, annotationClass, attributeName, new OnAttributeFoundListener() {
			
			@Override
			public void onFound(String value) {
				result.value=AnnotationUtility.extractAsArrayOfClassName(value).get(0);				
			}
		});
		
		return result.value;				
	}
	
	/**
	 * Extract from an annotation of a method the attribute value specified.
	 * 
	 * @param elementUtils
	 * @param item
	 * @param annotationClass
	 * @param attributeName
	 * @return attribute value extracted as class name
	 */
	public static String extractAsString(Elements elementUtils, Element item, Class<? extends Annotation> annotationClass, AnnotationAttributeType attributeName) {
		final Result<String> result=new Result<String>(); 
		
		extractString(elementUtils, item, annotationClass, attributeName, new OnAttributeFoundListener() {
			
			@Override
			public void onFound(String value) {
				result.value=AnnotationUtility.extractAsArrayOfString(value).get(0);				
			}
		});
		
		return result.value;				
	}
	
	static class Result<T>
	{
		T value;
	}
	
	
	interface OnAttributeFoundListener
	{
		void onFound(String value);
	}
	
	static void extractString(Elements elementUtils, Element item, Class<? extends Annotation> annotationClass, AnnotationAttributeType attributeName, OnAttributeFoundListener listener) {
		extractAttributeValue(elementUtils, item, annotationClass.getCanonicalName(), attributeName, listener);
	}
	
	/**
	 * Extract from an annotation of a method the attribute value specified.
	 * 
	 */
	static void extractAttributeValue(Elements elementUtils, Element item, String annotationName, AnnotationAttributeType attributeName, OnAttributeFoundListener listener) {
		List<? extends AnnotationMirror> annotationList = elementUtils.getAllAnnotationMirrors(item);
		for (AnnotationMirror annotation : annotationList) {

			if (annotationName.equals(annotation.getAnnotationType().asElement().toString())) {
				// found annotation
				for (Entry<? extends ExecutableElement, ? extends AnnotationValue> annotationItem : elementUtils.getElementValuesWithDefaults(annotation).entrySet()) {
					if (attributeName.isEquals(annotationItem.getKey())) {
						
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
	 * @param elementUtils
	 * @param method
	 *            method to analyze
	 * @param annotationClass
	 *            annotation to analyze
	 * @param attributeName
	 *            attribute name to analyze
	 * @return attribute value as list of string
	 */
	public static List<String> extractAsStringArray(Elements elementUtils, ModelMethod method, ModelAnnotation annotationClass, AnnotationAttributeType attributeName) {
		final Result<List<String>> result=new Result<List<String>>();
		extractAttributeValue(elementUtils, method.getElement(), annotationClass.getName(), attributeName, new OnAttributeFoundListener() {
			
			@Override
			public void onFound(String value) {
				result.value=AnnotationUtility.extractAsArrayOfString(value);
			}
		});

		return result.value;				
	}
	
	/**
	 * Estract from an annotation of a method the attribute value specified
	 * 
	 * @param elementUtils
	 * @param method
	 *            method to analyze
	 * @param annotationClass
	 *            annotation to analyze
	 * @param attributeName
	 *            attribute name to analyze
	 * @return attribute value as list of string
	 */
	public static String extractAsString(Elements elementUtils, ModelMethod method, ModelAnnotation annotationClass, AnnotationAttributeType attributeName) {
		final Result<String> result=new Result<String>(); 
		
		extractAttributeValue(elementUtils, method.getElement(), annotationClass.getName(), attributeName, new OnAttributeFoundListener() {
			
			@Override
			public void onFound(String value) {
				result.value=AnnotationUtility.extractAsArrayOfString(value).get(0);				
			}
		});
		
		return result.value;		
	}

	/**
	 * Estract from an annotation of a property the attribute value specified
	 * 
	 * @param elementUtils
	 * @param property
	 *            property to analyze
	 * @param annotationClass
	 *            annotation to analyze
	 * @param attributeName
	 *            attribute name to analyze
	 * @return attribute value as list of string
	 */
	public static String extractAsEnumerationValue(Elements elementUtils, ModelProperty property, ModelAnnotation annotationClass, AnnotationAttributeType attributeName) {
		final Result<String> result=new Result<String>(); 
		
		extractAttributeValue(elementUtils, property.getElement(), annotationClass.getName(), attributeName, new OnAttributeFoundListener() {
			
			@Override
			public void onFound(String value) {
							
				result.value=value.substring(value.lastIndexOf(".")+1);							
			}
		});
		
		return result.value;		
	}

	/**
	 * Puts in model class all annotation found for modelClass
	 * 
	 * @param elementUtils
	 * @param modelWithAnnotation
	 */
	public static void buildAnnotations(Elements elementUtils, final ModelWithAnnotation modelWithAnnotation) {
		buildAnnotations(elementUtils, modelWithAnnotation, null);
	}

	/**
	 * Puts in model class all annotation found for modelClass
	 * 
	 * @param elementUtils
	 * @param modelWithAnnotation
	 */
	public static void buildAnnotations(Elements elementUtils, final ModelWithAnnotation modelWithAnnotation, final AnnotationFilter filter) {
		forEachAnnotations(elementUtils, modelWithAnnotation.getElement(), filter, new AnnotationFoundListener() {

			@Override
			public void onAnnotation(Element executableMethod, String annotationClassName, Map<String, String> attributes) {
				ModelAnnotation annotation = new ModelAnnotation(annotationClassName, attributes);

				modelWithAnnotation.addAnnotation(annotation);
			}
		});

	}

	public static class AnnotationFilter {
		AnnotationFilter(Set<String> annotations) {
			annotationNames = annotations;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("AnnotationFilter [");
			if (annotationNames != null) {
				builder.append("annotationNames=");
				builder.append(annotationNames);
			}
			builder.append("]");
			return builder.toString();
		}

		Set<String> annotationNames;

		public boolean isAccepted(String annotationName) {
			return annotationNames.contains(annotationName);
		}

		public static AnnotationFilterBuilder builder() {
			return new AnnotationFilterBuilder();
		}

	}

	public static class AnnotationFilterBuilder {
		AnnotationFilterBuilder() {
			set = new HashSet<String>();
		}

		public AnnotationFilterBuilder add(Class<? extends Annotation> annotation) {
			set.add(annotation.getCanonicalName());
			return this;
		}

		Set<String> set;

		public AnnotationFilter build() {
			return new AnnotationFilter(set);
		}

	}

	public static int extractAsInt(Elements elementUtils, Element item, Class<? extends Annotation> annotationClass, AnnotationAttributeType attributeName) {
		final Result<Integer> result=new Result<Integer>(); 
		
		extractString(elementUtils, item, annotationClass, attributeName, new OnAttributeFoundListener() {
			
			@Override
			public void onFound(String value) {
				result.value=Integer.parseInt(value);				
			}
		});
		
		return result.value;			
	}

}
