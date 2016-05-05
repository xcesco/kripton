package com.abubusoft.kripton.processor.sqlite;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.annotation.SQLDeleteBean;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidMethodSignException;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec.Builder;

public abstract class SQLiteDeleteBeanBuilder {
	
	public static void generate(Elements elementUtils, Builder builder, SQLiteModel model, DaoDefinition daoDefinition, ModelMethod method) {
		SQLEntity entity = model.getEntity(daoDefinition.getEntityClassName());
		com.squareup.javapoet.MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.getName()).addAnnotation(Override.class).addModifiers(Modifier.PUBLIC);

		methodBuilder.addJavadoc("\n$L\n",method.getAnnotations().get(0).toString());
		boolean foundBean=false;
		ParameterSpec parameterSpec;
		for (Pair<String, TypeMirror> item : method.getParameters()) {
			parameterSpec = ParameterSpec.builder(TypeName.get(item.value1), item.value0).build();
			methodBuilder.addParameter(parameterSpec);
			
			if (SQLUtility.isIn(item.value1, daoDefinition.getEntityClassName())) {
				foundBean=true;
			}
		}
		if (!foundBean)
		{
			throw (new InvalidMethodSignException(daoDefinition, method));
		}
		
		CodeBuilderHelper.populateContentValuesFromEntity(elementUtils, model, daoDefinition, entity, method, SQLDeleteBean.class, methodBuilder);		

		methodBuilder.addCode("\n");
		String whereCondition=AnnotationUtility.extractAsString(elementUtils, method, method.getAnnotation(SQLDeleteBean.class), "where");
		methodBuilder.addCode("String[] whereConditions={");
		
		methodBuilder.addCode("};");
		
		methodBuilder.addCode("\n");
		methodBuilder.addCode("int result = database.delete($S, $S, whereConditions);\n", model.classNameConverter.convert(daoDefinition.getEntitySimplyClassName()), whereCondition);

		TypeName returnType = TypeName.get(method.getReturnClass());
		methodBuilder.returns(returnType);

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
