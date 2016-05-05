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
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.core.ModelWithAnnotation;
import com.squareup.javapoet.TypeName;
import com.sun.tools.javac.code.Attribute;

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
		void onMethod(Element executableMethod);
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
			for (Entry<? extends ExecutableElement, ? extends AnnotationValue> annotationItem : annotation.getElementValues().entrySet()) {
				String value=annotationItem.getValue().toString();
				if (value.startsWith("\"") && value.endsWith("\""))
				{														
					value=value.substring(1);
					value=value.substring(0, value.length()-1);					
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
	 * Iterate over methods.
	 * 
	 * @param elementUtils
	 * @param typeElement
	 * @param listener
	 */
	public static void forEachMethods(Elements elementUtils, TypeElement typeElement, MethodFoundListener listener) {
		List<? extends Element> list = elementUtils.getAllMembers(typeElement);

		for (Element item : list) {
			if (item.getKind() == ElementKind.METHOD) {
				listener.onMethod((ExecutableElement) item);
			}
		}
	}

	public static List<String> extractAsClassNameArray(Elements elementUtils, Element item, Class<? extends Annotation> annotationClass, String attributeName) {
		// SQLDatabaseSchema schema = item.getAnnotation(SQLDatabaseSchema.class);
		List<? extends AnnotationMirror> annotationList = elementUtils.getAllAnnotationMirrors(item);
		for (AnnotationMirror annotation : annotationList) {
			if (annotationClass.getCanonicalName().equals(annotation.getAnnotationType().asElement().toString())) {
				for (Entry<? extends ExecutableElement, ? extends AnnotationValue> annotationItem : annotation.getElementValues().entrySet()) {
					if (annotationItem.getKey().getSimpleName().toString().equals(attributeName)) {
						return AnnotationUtility.extractAsArrayOfClassName(annotationItem.getValue().toString());

					}
				}
			}
		}

		return null;
	}

	public static String extractAsClassName(Elements elementUtils, Element item, Class<? extends Annotation> annotationClass, String attributeName) {
		List<? extends AnnotationMirror> annotationList = elementUtils.getAllAnnotationMirrors(item);
		for (AnnotationMirror annotation : annotationList) {
			if (annotationClass.getCanonicalName().equals(annotation.getAnnotationType().asElement().toString())) {
				for (Entry<? extends ExecutableElement, ? extends AnnotationValue> annotationItem : annotation.getElementValues().entrySet()) {
					if (annotationItem.getKey().getSimpleName().toString().equals(attributeName)) {
						return AnnotationUtility.extractAsArrayOfClassName(annotationItem.getValue().toString()).get(0);

					}
				}
			}
		}

		return null;
	}

	public static List<String> extractAsStringArray(Elements elementUtils, ModelMethod method, ModelAnnotation annotationClass, String attributeName) {
		List<? extends AnnotationMirror> annotationList = elementUtils.getAllAnnotationMirrors(method.getElement());
		for (AnnotationMirror item : annotationList) {
			if (annotationClass.getName().equals(item.getAnnotationType().asElement().toString())) {
				for (Entry<? extends ExecutableElement, ? extends AnnotationValue> annotationItem : item.getElementValues().entrySet()) {
					if (annotationItem.getKey().getSimpleName().toString().equals(attributeName)) {
						return AnnotationUtility.extractAsArrayOfString(annotationItem.getValue().toString());

					}
				}
			}
		}

		return new ArrayList<>();
	}
	
	public static String extractAsString(Elements elementUtils, ModelMethod method, ModelAnnotation annotationClass, String attributeName) {
		List<? extends AnnotationMirror> annotationList = elementUtils.getAllAnnotationMirrors(method.getElement());
		for (AnnotationMirror item : annotationList) {
			if (annotationClass.getName().equals(item.getAnnotationType().asElement().toString())) {
				for (Entry<? extends ExecutableElement, ? extends AnnotationValue> annotationItem : item.getElementValues().entrySet()) {
					if (annotationItem.getKey().getSimpleName().toString().equals(attributeName)) {
						return AnnotationUtility.extractAsArrayOfString(annotationItem.getValue().toString()).get(0);

					}
				}
			}
		}

		return null;
		
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

		/* (non-Javadoc)
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



}
