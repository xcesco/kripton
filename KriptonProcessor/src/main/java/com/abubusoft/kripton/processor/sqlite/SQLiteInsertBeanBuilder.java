package com.abubusoft.kripton.processor.sqlite;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.annotation.SQLInsertBean;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidMethodSignException;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec.Builder;

public abstract class SQLiteInsertBeanBuilder {
	
	public static void generate(Elements elementUtils, Builder builder, SQLiteModel model, DaoDefinition daoDefinition, ModelMethod method) {
		Converter<String, String> propertyConverter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);
		
		SQLEntity entity = model.getEntity(daoDefinition.getEntityClassName());
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.getName()).addAnnotation(Override.class).addModifiers(Modifier.PUBLIC);

		methodBuilder.addJavadoc("\n$L\n",method.getAnnotations().get(0).toString());

		// check if there is only one parameter
		if (method.getParameters().size() != 1 && SQLUtility.isIn(TypeName.get(method.getParameters().get(0).value1).toString(), daoDefinition.getEntityClassName())) {
			throw (new InvalidMethodSignException(daoDefinition, method));
		}
		
		ParameterSpec parameterSpec;
		for (Pair<String, TypeMirror> item : method.getParameters()) {
			parameterSpec = ParameterSpec.builder(TypeName.get(item.value1), item.value0).build();
			methodBuilder.addParameter(parameterSpec);
		}
				
		ModelProperty primaryKey = CodeBuilderHelper.populateContentValuesFromEntity(elementUtils, model, daoDefinition, entity, method, SQLInsertBean.class, methodBuilder, null);
		
		methodBuilder.addCode("\n");
		methodBuilder.addCode("long result = database.insert($S, null, contentValues);\n", model.classNameConverter.convert(daoDefinition.getEntitySimplyClassName()));
		
		if (primaryKey!=null)
		{
			if (primaryKey.isPublicField())
			{
				methodBuilder.addCode("$L.$L=result;\n",method.getParameters().get(0).value0,primaryKey.getName());
			} else {
				methodBuilder.addCode("$L.$L(result);\n",method.getParameters().get(0).value0,CodeBuilderHelper.setter(propertyConverter, primaryKey));
			}
		}

		TypeName returnType = TypeName.get(method.getReturnClass());
		methodBuilder.returns(returnType);

		methodBuilder.addCode("\n");
		// define return value
		if (returnType == TypeName.VOID) {

		} else if (SQLUtility.isIn(returnType, String.class)) {
			methodBuilder.addCode("return String.valueOf(result);\n");
		} else if (SQLUtility.isIn(returnType, Boolean.TYPE, Boolean.class)) {
			methodBuilder.addCode("return result!=-1;\n");
		} else if (SQLUtility.isIn(returnType, Long.TYPE, Long.class, Integer.TYPE, Integer.class, Short.TYPE, Short.class)) {
			methodBuilder.addCode("return result;\n");
		} else if (SQLUtility.isIn(returnType, Float.TYPE, Float.class)) {
			methodBuilder.addCode("return result;\n");
		} else if (SQLUtility.isIn(returnType, Double.TYPE, Double.class)) {
			methodBuilder.addCode("return result;\n");
		} else if (SQLUtility.isIn(returnType, Character.TYPE, Character.class)) {
			methodBuilder.addCode("return '';\n");
		} else {
			methodBuilder.addCode("return null;\n");
		}
		MethodSpec methodSpec = methodBuilder.build();

		builder.addMethod(methodSpec);
	}
	
}
