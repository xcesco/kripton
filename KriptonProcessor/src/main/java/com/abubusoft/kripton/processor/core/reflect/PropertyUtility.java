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
import com.abubusoft.kripton.processor.exceptions.PropertyVisibilityException;
import com.squareup.javapoet.TypeName;

public class PropertyUtility {
	
	static Converter<String, String> converterField2Method=CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

	static Logger logger = Logger.getGlobal();

	private static final String SET_PREFIX = "set";
	private static final String IS_PREFIX = "is";
	private static final String GET_PREFIX = "get";

	public interface PropertyCreatedListener<E extends ModelProperty> {
		/**
		 * If true, the property will be included in the class. If return false, the property will be ignored
		 * 
		 * @param property
		 * @return true to include property, false otherwise
		 */
		boolean onProperty(E property);
	};

	/**
	 * Given a model clazz, define its properties. No annotation is analyzed for properties
	 * @param elementUtils
	 * @param entity
	 * @param factoryProperty
	 */
	public static <P extends ModelProperty, T extends ModelClass<P>> void buildProperties(Elements elementUtils,  T entity, PropertyFactory<P> factoryProperty) {
		buildProperties(elementUtils, entity, factoryProperty, null, null);
	}

	/**
	 * Given a model clazz, define its properties.
	 * 
	 * @param elementUtils
	 * @param entity
	 * @param factoryProperty
	 * @param propertyAnnotationFilter
	 *            if null, no filter is applied to annotations
	 */
	public static <P extends ModelProperty, T extends ModelClass<P>> void buildProperties(Elements elementUtils,  T entity, PropertyFactory<P> factoryProperty,  AnnotationFilter propertyAnnotationFilter) {
		buildProperties(elementUtils, entity, factoryProperty, propertyAnnotationFilter, null);
	}

	/**
	 * Given a model clazz, define its properties. The listener allow to select which property include in class definition.
	 * 
	 * @param elementUtils
	 * @param entity
	 * @param factoryProperty
	 * @param propertyAnnotationFilter
	 *            if null, no filter is applied to annotations
	 * @param listener
	 */
	public static <P extends ModelProperty, T extends ModelClass<P>> void buildProperties(Elements elementUtils, T entity, PropertyFactory<P> factoryProperty,  AnnotationFilter propertyAnnotationFilter, PropertyCreatedListener<P> listener) {
		List<? extends Element> list = elementUtils.getAllMembers((TypeElement) entity.getElement());

		if (propertyAnnotationFilter != null) {
			AnnotationUtility.forEachAnnotations(elementUtils, entity.getElement(), propertyAnnotationFilter, new AnnotationFoundListener() {

				@Override
				public void onAcceptAnnotation(Element executableMethod, String annotationClassName, Map<String, String> attributes) {
					logger.info("Annotation... " + annotationClassName);					
				}
			});
		}

		P field;
		for (Element item : list) {
			if (item.getKind() == ElementKind.FIELD && modifierIsAcceptable(item)) {
				field = factoryProperty.createProperty(item);				
				AnnotationUtility.buildAnnotations(elementUtils, field, propertyAnnotationFilter);
								
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
					Pair<String, String> result = MethodUtility.extractResultAndArguments(item.asType().toString());

					if (currentKriptonField.isType(result.value1) && status==0) {
						currentKriptonField.setFieldWithGetter(true);
					} else if (currentKriptonField.isType(result.value1) && status==1) {
						currentKriptonField.setFieldWithIs(true);
					} else if (currentKriptonField.isType(result.value0) && status==2) {
						currentKriptonField.setFieldWithSetter(true);
					}
					
				}
			}
		}

		if (listener != null) {
			List<P> listPropertiesToFilter = new ArrayList<P>(entity.getCollection());

			for (P item : listPropertiesToFilter) {
				if (!listener.onProperty(item)) {
					entity.getCollection().remove(item);
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
	
	public static String getter(TypeName beanClass, ModelProperty property) {
		if (property.isPublicField())
			return property.getName();

		if (property.isFieldWithGetter()) {
			return "get" + converterField2Method.convert(property.getName()) + "()";
		} else if (property.isFieldWithIs()) {
			return "is" + converterField2Method.convert(property.getName()) + "()";
		} else {
			throw new PropertyVisibilityException("Property "+property.getName()+" of class "+beanClass+" can not be read");
		}

	}

	public static String setter(TypeName beanClass, ModelProperty property) {
		if (property.isPublicField())
		{
			return property.getName();
		} else if (property.isFieldWithSetter()) {
			return "set" + converterField2Method.convert(property.getName());
		} else {
			throw new PropertyVisibilityException("Property "+property.getName()+" of class "+beanClass+" can not be modify");
		}
	}
	
	public static String setter(TypeName beanClass, ModelProperty property, String value) {
		if (property.isPublicField())
			return property.getName()+"="+value;
		else if (property.isFieldWithSetter()) {
			return "set" + converterField2Method.convert(property.getName())+"("+value+")";
		} else {
			throw new PropertyVisibilityException("Property "+property.getName()+" of class "+beanClass+" can not be modify");
		}
	}

}
