package com.abubusoft.kripton.processor.sqlite;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.sqlite.exceptions.PropertyNotFoundException;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec.Builder;

public abstract class SQLiteInsertBuilder {
	
	public static void generate(Elements elementUtils, Builder builder, SQLiteDatabaseSchema model, SQLDaoDefinition daoDefinition, ModelMethod method) {
		SQLEntity entity = model.getEntity(daoDefinition.getEntityClassName());
		com.squareup.javapoet.MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.getName()).addAnnotation(Override.class).addModifiers(Modifier.PUBLIC);

		methodBuilder.addJavadoc("\n$L\n",method.getAnnotations().get(0).toString());

		ParameterSpec parameterSpec;
		for (Pair<String, TypeMirror> item : method.getParameters()) {
			parameterSpec = ParameterSpec.builder(TypeName.get(item.value1), item.value0).build();
			methodBuilder.addParameter(parameterSpec);
		}
		// code
		methodBuilder.addCode("contentValues.clear();\n\n");
		for (Pair<String, TypeMirror> item : method.getParameters()) {
			parameterSpec = ParameterSpec.builder(TypeName.get(item.value1), item.value0).build();

			ModelProperty a = entity.get(item.value0);
			if (a == null)
				throw (new PropertyNotFoundException(daoDefinition, method, item.value0));

			methodBuilder.addCode("contentValues.put($S, $L);\n", model.columnNameConverter.convert(a.getName()), item.value0);
		}

		methodBuilder.addCode("\n");
		methodBuilder.addCode("long result = database.insert($S, null, contentValues);\n", model.classNameConverter.convert(daoDefinition.getEntitySimplyClassName()));

		TypeName returnType = TypeName.get(method.getReturnClass());
		methodBuilder.returns(returnType);

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
}
