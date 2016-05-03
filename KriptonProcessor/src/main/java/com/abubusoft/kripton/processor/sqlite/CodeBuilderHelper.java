package com.abubusoft.kripton.processor.sqlite;

import java.util.List;

import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.annotation.SQLInsertBean;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.sqlite.exceptions.IncompatibleAttributesInAnnotationException;
import com.abubusoft.kripton.processor.sqlite.exceptions.PropertyInAnnotationNotFoundException;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;

public class CodeBuilderHelper {

	/**
	 * Generate code necessary to put bean properties in content values map. Return primary key
	 * 
	 * @param elementUtils
	 * @param model
	 * @param daoDefinition
	 * @param entity
	 * @param method
	 * @param methodBuilder
	 * @return
	 * 		primary key.
	 */
	public static ModelProperty populateContentValuesFromEntity(Elements elementUtils, SQLiteModel model, DaoDefinition daoDefinition, SQLEntity entity, ModelMethod method, Builder methodBuilder) {
		Converter<String, String> propertyConverter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);
		// check included and excluded fields
		ModelAnnotation annotation=method.getAnnotation(SQLInsertBean.class);
		List<String> includedFields=AnnotationUtility.extractAsStringArray(elementUtils, method, annotation, "value");
		List<String> excludedFields=AnnotationUtility.extractAsStringArray(elementUtils, method, annotation, "excludedFields");
		if (includedFields.size()>0 && excludedFields.size()>0)
		{
			throw (new IncompatibleAttributesInAnnotationException(daoDefinition, method, annotation, "value", "excludedFields"));	
		}
		// check included
		for (String item: includedFields)
		{
			if (!entity.contains(item))
			{
				throw (new PropertyInAnnotationNotFoundException(daoDefinition, method, item));
			}
		}
		// check excluded
		for (String item: excludedFields)
		{
			if (!entity.contains(item))
			{
				throw (new PropertyInAnnotationNotFoundException(daoDefinition, method, item));
			}
		}
		
		// build method signature
		ParameterSpec parameterSpec;
		for (Pair<String, TypeMirror> item : method.getParameters()) {
			parameterSpec = ParameterSpec.builder(TypeName.get(item.value1), item.value0).build();
			methodBuilder.addParameter(parameterSpec);
		}
		
		// initialize contentValues
		ModelProperty primaryKey = entity.getPrimaryKey();
		methodBuilder.addCode("contentValues.clear();\n\n");
		for (ModelProperty item : entity.getCollection()) {					
			if (item.equals(primaryKey) || excludedFields.contains(item.getName())) continue;
			if (includedFields.size()>0 && !includedFields.contains(item.getName())) continue;
			if (excludedFields.size()>0 && excludedFields.contains(item.getName())) continue;
			
			methodBuilder.addCode("contentValues.put($S, $L.$L);\n", model.columnNameConverter.convert(item.getName()), method.getParameters().get(0).value0, getter(propertyConverter, item));
		}
		
		return primaryKey;
		
	}
	
	public static String getter(Converter<String, String> converter, ModelProperty property)
	{
		if (property.isPublicField()) return property.getName();
		
		if (property.isFieldWithGetter()) {
			return "get"+converter.convert(property.getName())+"()";
		}
		
		if (property.isFieldWithIs()) {
			return "is"+converter.convert(property.getName())+"()";
		}
		
		return null;
	}
	
	public static String setter(Converter<String, String> converter, ModelProperty property)
	{
		if (property.isPublicField()) return property.getName();
		
		if (property.isFieldWithGetter() || property.isFieldWithIs()) {
			return "set"+converter.convert(property.getName());
		}
		
		return null;
	}

}
