package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.lang.model.util.Elements;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.sqlite.exceptions.IncompatibleAttributesInAnnotationException;
import com.abubusoft.kripton.processor.sqlite.exceptions.PropertyInAnnotationNotFoundException;
import com.squareup.javapoet.MethodSpec.Builder;

public class CodeBuilderHelper {

	/**
	 * Generate code necessary to put bean properties in content values map. Return primary key
	 * 
	 * @param elementUtils
	 * @param model
	 * @param daoDefinition
	 * @param method
	 *            used to code generation
	 * @param alreadyUsedBeanPropertiesNames
	 *            optional
	 * @return primary key.
	 */
	public static Pair<String, List<ModelProperty>> generatePropertyList(Elements elementUtils, SQLiteModel model, DaoDefinition daoDefinition, ModelMethod method, Class<? extends Annotation> annotationClazz,
			Set<String> alreadyUsedBeanPropertiesNames) {
		Pair<String, List<ModelProperty>> result = new Pair<String, List<ModelProperty>>();
		result.value1 = new ArrayList<ModelProperty>();

		SQLEntity entity = model.getEntity(daoDefinition.getEntityClassName());
	//	Converter<String, String> propertyConverter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);
		// check included and excluded fields
		ModelAnnotation annotation = method.getAnnotation(annotationClazz);
		List<String> includedFields = AnnotationUtility.extractAsStringArray(elementUtils, method, annotation, AnnotationAttributeType.ATTRIBUTE_VALUE);
		if (alreadyUsedBeanPropertiesNames != null) {
			includedFields.removeAll(alreadyUsedBeanPropertiesNames);
		}
		Set<String> excludedFields = new HashSet<String>();
		excludedFields.addAll(AnnotationUtility.extractAsStringArray(elementUtils, method, annotation, AnnotationAttributeType.ATTRIBUTE_EXCLUDED_FIELDS));

		if (includedFields.size() > 0 && excludedFields.size() > 0) {
			throw (new IncompatibleAttributesInAnnotationException(daoDefinition, method, annotation, AnnotationAttributeType.ATTRIBUTE_VALUE, AnnotationAttributeType.ATTRIBUTE_EXCLUDED_FIELDS));
		}
		// check included
		for (String item : includedFields) {
			if (!entity.contains(item)) {
				throw (new PropertyInAnnotationNotFoundException(daoDefinition, method, item));
			}
		}
		// check excluded
		for (String item : excludedFields) {
			if (!entity.contains(item)) {
				throw (new PropertyInAnnotationNotFoundException(daoDefinition, method, item));
			}
		}

		StringBuilder buffer = new StringBuilder();
		String separator = "";
		// methodBuilder.addCode("contentValues.clear();\n\n");
		// for each property in entity except primaryKey and excluded properties
		for (ModelProperty item : entity.getCollection()) {
			if (includedFields.size() > 0 && !includedFields.contains(item.getName()))
				continue;
			if (excludedFields.size() > 0 && excludedFields.contains(item.getName()))
				continue;

			buffer.append(separator + model.columnNameConverter.convert(item.getName()));
			result.value1.add(item);
			separator = ", ";
		}

		result.value0 = buffer.toString();

		return result;

	}

	/**
	 * Generate code necessary to put bean properties in content values map. Return primary key
	 * 
	 * @param elementUtils
	 * @param model
	 * @param daoDefinition
	 * @param method
	 * @param methodBuilder
	 *            used to code generation
	 * @param alreadyUsedBeanPropertiesNames
	 *            optional
	 * @return primary key.
	 */
	public static ModelProperty populateContentValuesFromEntity(Elements elementUtils, SQLiteModel model, DaoDefinition daoDefinition, ModelMethod method, Class<? extends Annotation> annotationClazz, Builder methodBuilder,
			Set<String> alreadyUsedBeanPropertiesNames) {
		SQLEntity entity = model.getEntity(daoDefinition.getEntityClassName());
		// check included and excluded fields
		ModelAnnotation annotation = method.getAnnotation(annotationClazz);
		List<String> includedFields = AnnotationUtility.extractAsStringArray(elementUtils, method, annotation, AnnotationAttributeType.ATTRIBUTE_VALUE);
		if (alreadyUsedBeanPropertiesNames != null) {
			includedFields.removeAll(alreadyUsedBeanPropertiesNames);
		}
		Set<String> excludedFields = new HashSet<String>();
		excludedFields.addAll(AnnotationUtility.extractAsStringArray(elementUtils, method, annotation, AnnotationAttributeType.ATTRIBUTE_EXCLUDED_FIELDS));

		if (includedFields.size() > 0 && excludedFields.size() > 0) {
			throw (new IncompatibleAttributesInAnnotationException(daoDefinition, method, annotation, AnnotationAttributeType.ATTRIBUTE_VALUE, AnnotationAttributeType.ATTRIBUTE_EXCLUDED_FIELDS));
		}
		// check included
		for (String item : includedFields) {
			if (!entity.contains(item)) {
				throw (new PropertyInAnnotationNotFoundException(daoDefinition, method, item));
			}
		}
		// check excluded
		for (String item : excludedFields) {
			if (!entity.contains(item)) {
				throw (new PropertyInAnnotationNotFoundException(daoDefinition, method, item));
			}
		}

		// initialize contentValues
		ModelProperty primaryKey = entity.getPrimaryKey();
		methodBuilder.addCode("contentValues.clear();\n\n");
		// for each property in entity except primaryKey and excluded properties
		for (ModelProperty item : entity.getCollection()) {
			if (item.equals(primaryKey) || excludedFields.contains(item.getName()))
				continue;
			if (includedFields.size() > 0 && !includedFields.contains(item.getName()))
				continue;
			if (excludedFields.size() > 0 && excludedFields.contains(item.getName()))
				continue;

			methodBuilder.addCode("contentValues.put($S, $L.$L);\n", model.columnNameConverter.convert(item.getName()), method.getParameters().get(0).value0, getter(item));
		}

		return primaryKey;

	}

}
