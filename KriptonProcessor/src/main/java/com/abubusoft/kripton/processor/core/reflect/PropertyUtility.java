package com.abubusoft.kripton.processor.core.reflect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelClass;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.AnnotationFilter;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.AnnotationFoundListener;

public class PropertyUtility {
	
	static Converter<String, String> converterField2Method=CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

	static Logger logger = Logger.getGlobal();

	private static final String SET_PREFIX = "set";
	private static final String IS_PREFIX = "is";
	private static final String GET_PREFIX = "get";

	public interface PropertyCreatedListener {
		/**
		 * If true, the property will be included in the class. If return false, the property will be ignored
		 * 
		 * @param property
		 * @return true to include property, false otherwise
		 */
		boolean onProperty(ModelProperty property);
	};

	/**
	 * Given a model clazz, define its properties. No annotation is analyzed for properties
	 * 
	 * @param elementUtils
	 * @param clazz
	 */
	public static void buildProperties(Elements elementUtils, ModelClass clazz) {
		buildProperties(elementUtils, clazz, null, null);
	}

	/**
	 * Given a model clazz, define its properties.
	 * 
	 * @param elementUtils
	 * @param clazz
	 * @param propertyAnnotationFilter
	 *            if null, no filter is applied to annotations
	 */
	public static void buildProperties(Elements elementUtils, ModelClass clazz, AnnotationFilter propertyAnnotationFilter) {
		buildProperties(elementUtils, clazz, propertyAnnotationFilter, null);
	}

	/**
	 * Given a model clazz, define its properties. The listener allow to select which property include in class definition.
	 * 
	 * @param elementUtils
	 * @param clazz
	 * @param propertyAnnotationFilter
	 *            if null, no filter is applied to annotations
	 */
	public static void buildProperties(Elements elementUtils, ModelClass clazz, AnnotationFilter propertyAnnotationFilter, PropertyCreatedListener listener) {
		List<? extends Element> list = elementUtils.getAllMembers((TypeElement) clazz.getElement());

		if (propertyAnnotationFilter != null) {
			AnnotationUtility.forEachAnnotations(elementUtils, clazz.getElement(), propertyAnnotationFilter, new AnnotationFoundListener() {

				@Override
				public void onAcceptAnnotation(Element executableMethod, String annotationClassName, Map<String, String> attributes) {
					logger.info("Annotation... " + annotationClassName);					
				}
			});
		}

		ModelProperty field;
		for (Element item : list) {
			if (item.getKind() == ElementKind.FIELD && modifierIsAcceptable(item)) {
				field = new ModelProperty(item);
				AnnotationUtility.buildAnnotations(elementUtils, field, propertyAnnotationFilter);

				clazz.add(field);
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
				if (clazz.contains(propertyName)) {
					currentKriptonField = clazz.get(propertyName);
					Pair<String, String> result = MethodUtility.extractResultAndArguments(item.asType().toString());

					if (currentKriptonField.isType(result.value1)) {
						switch (status) {
						case 0:
							currentKriptonField.setFieldWithGetter(true);
							break;
						case 1:
							currentKriptonField.setFieldWithIs(true);
							break;
						case 2:
							currentKriptonField.setFieldWithSetter(true);
							break;
						default:
							break;
						}
					}
				}
			}
		}

		if (listener != null) {
			List<ModelProperty> listPropertiesToFilter = new ArrayList<ModelProperty>(clazz.getCollection());

			for (ModelProperty item : listPropertiesToFilter) {
				if (!listener.onProperty(item)) {
					clazz.getCollection().remove(item);
				}
			}

		}
	}

	static boolean modifierIsAcceptable(Element item) {
		Object[] values = { Modifier.NATIVE, Modifier.STATIC, Modifier.FINAL, Modifier.ABSTRACT };

		for (Object i : values) {
			if (item.getModifiers().contains(i))
				return false;
		}

		return true;
	}
	
	public static String getter(ModelProperty property) {
		if (property.isPublicField())
			return property.getName();

		if (property.isFieldWithGetter()) {
			return "get" + converterField2Method.convert(property.getName()) + "()";
		}

		if (property.isFieldWithIs()) {
			return "is" + converterField2Method.convert(property.getName()) + "()";
		}

		return null;
	}

	public static String setter(ModelProperty property) {
		if (property.isPublicField())
			return property.getName();

		if (property.isFieldWithGetter() || property.isFieldWithIs()) {
			return "set" + converterField2Method.convert(property.getName());
		}

		return null;
	}
	
	public static String setter(ModelProperty property, String value) {
		if (property.isPublicField())
			return property.getName()+"="+value;

		if (property.isFieldWithGetter() || property.isFieldWithIs()) {
			return "set" + converterField2Method.convert(property.getName())+"("+value+")";
		}

		return null;
	}

}
