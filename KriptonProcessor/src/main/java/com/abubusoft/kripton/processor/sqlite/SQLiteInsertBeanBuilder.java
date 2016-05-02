package com.abubusoft.kripton.processor.sqlite;

import java.util.List;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import android.content.ContentValues;

import com.abubusoft.kripton.android.annotation.SQLInsertBean;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.exceptions.PropertyInAnnotationNotFoundException;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec.Builder;

public abstract class SQLiteInsertBeanBuilder {
	
	public static void generate(Elements elementUtils, Builder builder, SQLiteModel model, DaoDefinition daoDefinition, ModelMethod method) {
		SQLEntity entity = model.getEntity(daoDefinition.getEntityClassName());
		com.squareup.javapoet.MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.getName()).addAnnotation(Override.class).addModifiers(Modifier.PUBLIC);

		methodBuilder.addJavadoc("\n" + method.getAnnotations().get(0).toString() + "\n");

		if (method.getParameters().size() != 1 && isIn(TypeName.get(method.getParameters().get(0).value1).toString(), daoDefinition.getEntityClassName())) {
			throw (new InvalidMethodSignException(daoDefinition, method));
		}
		
		// check excluded field
		ModelAnnotation annotation=method.getAnnotation(SQLInsertBean.class);
		List<String> excludedFields=AnnotationUtility.extractAsStringArray(elementUtils, method, annotation, "excludedFields");
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
		
		// code
		methodBuilder.addCode("$T contentValues=new $T();\n", ContentValues.class, ContentValues.class);
		for (ModelProperty item : entity.getCollection()) {					
			if (excludedFields.contains(item.getName())) continue;
			methodBuilder.addCode("contentValues.put($S, $L.$L);\n", model.columnNameConverter.convert(item.getName()), method.getParameters().get(0).value0, getter(item));
		}

		methodBuilder.addCode("\n");
		methodBuilder.addCode("long result = database.insert($S, null, contentValues);\n", model.classNameConverter.convert(daoDefinition.getEntitySimplyClassName()));
		
		ModelProperty primaryKey = entity.getPrimaryKey();
		if (primaryKey!=null)
		{
			if (primaryKey.isPublicField())
			{
				methodBuilder.addCode("$L.$L=result;\n",method.getParameters().get(0).value0,primaryKey.getName());
			} else {
				methodBuilder.addCode("$L.$L(result);\n",method.getParameters().get(0).value0,setter(primaryKey));
			}
		}

		TypeName returnType = TypeName.get(method.getReturnClass());
		methodBuilder.returns(returnType);

		methodBuilder.addCode("\n");
		// define return value
		if (returnType == TypeName.VOID) {

		} else if (isIn(returnType, String.class)) {
			methodBuilder.addCode("return String.valueOf(result);\n");
		} else if (isIn(returnType, Boolean.TYPE, Boolean.class)) {
			methodBuilder.addCode("return result!=-1;\n");
		} else if (isIn(returnType, Long.TYPE, Long.class, Integer.TYPE, Integer.class, Short.TYPE, Short.class)) {
			methodBuilder.addCode("return result;\n");
		} else if (isIn(returnType, Float.TYPE, Float.class)) {
			methodBuilder.addCode("return result;\n");
		} else if (isIn(returnType, Double.TYPE, Double.class)) {
			methodBuilder.addCode("return result;\n");
		} else if (isIn(returnType, Character.TYPE, Character.class)) {
			methodBuilder.addCode("return '';\n");
		} else {
			methodBuilder.addCode("return null;\n");
		}
		MethodSpec methodSpec = methodBuilder.build();

		builder.addMethod(methodSpec);
	}

	static boolean isIn(TypeName value, Class<?>... classes) {
		for (Class<?> item : classes) {
			if (value.toString().equals(TypeName.get(item).toString())) {
				return true;
			}
		}

		return false;
	}
	
	static boolean isIn(String value, String ... classes) {
		for (String item : classes) {
			if (value.equals(item)) {
				return true;
			}
		}

		return false;
	}
	
	private static Converter<String, String> propertyNameConverter=CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);
	
	static String getter(ModelProperty property)
	{
		if (property.isPublicField()) return property.getName();
		
		if (property.isFieldWithGetter()) {
			return "get"+propertyNameConverter.convert(property.getName())+"()";
		}
		
		if (property.isFieldWithIs()) {
			return "is"+propertyNameConverter.convert(property.getName())+"()";
		}
		
		return null;
	}
	
	static String setter(ModelProperty property)
	{
		if (property.isPublicField()) return property.getName();
		
		if (property.isFieldWithGetter() || property.isFieldWithIs()) {
			return "set"+propertyNameConverter.convert(property.getName());
		}
		
		return null;
	}
}
