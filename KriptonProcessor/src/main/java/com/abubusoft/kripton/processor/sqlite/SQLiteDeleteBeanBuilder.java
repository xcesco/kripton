package com.abubusoft.kripton.processor.sqlite;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.model.AnnotationAttributeType;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec.Builder;

public abstract class SQLiteDeleteBeanBuilder {

	public static void generate(Elements elementUtils, Builder builder, SQLiteDatabaseSchema model, SQLDaoDefinition daoDefinition, ModelMethod method) {
		SQLEntity entity = model.getEntity(daoDefinition.getEntityClassName());
		com.squareup.javapoet.MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.getName()).addAnnotation(Override.class).addModifiers(Modifier.PUBLIC);

		methodBuilder.addJavadoc("\n$L\n", method.getAnnotations().get(0).toString());
		boolean foundBean = false;
		ParameterSpec parameterSpec;
		for (Pair<String, TypeMirror> item : method.getParameters()) {
			parameterSpec = ParameterSpec.builder(TypeName.get(item.value1), item.value0).build();
			methodBuilder.addParameter(parameterSpec);

			if (TypeUtility.isSameType(TypeUtility.typeName(item.value1), daoDefinition.getEntityClassName())) {
				foundBean = true;
			}
		}
		if (!foundBean) {
			throw (new InvalidMethodSignException(daoDefinition, method));
		}

		methodBuilder.addCode("\n");

		String whereCondition = AnnotationUtility.extractAsString(elementUtils, method, method.getAnnotation(BindDelete.class), AnnotationAttributeType.ATTRIBUTE_WHERE);
		SQLAnalyzer analyzer = new SQLAnalyzer();
		analyzer.execute(elementUtils, daoDefinition, entity, method, whereCondition);

		// build where condition
		methodBuilder.addCode("String[] whereConditions={");
		String separator = "";
		for (String item : analyzer.getParamGetters()) {
			methodBuilder.addCode(separator);
			methodBuilder.addCode("String.valueOf($L)", item);

			separator = ", ";
		}
		methodBuilder.addCode("};");

		methodBuilder.addCode("\n");
		methodBuilder.addCode("int result = database.delete($S, $S, whereConditions);\n", model.classNameConverter.convert(daoDefinition.getEntitySimplyClassName()), analyzer.getSQLStatement());

		TypeName returnType = TypeName.get(method.getReturnClass());
		methodBuilder.returns(returnType);

		// define return value
		if (returnType == TypeName.VOID) {

		} else if (TypeUtility.isTypeIncludedIn(returnType, String.class)) {
			methodBuilder.addCode("return String.valueOf(result);\n");
		} else if (TypeUtility.isTypeIncludedIn(returnType, Boolean.TYPE, Boolean.class)) {
			methodBuilder.addCode("return result!=-1;\n");
		} else if (TypeUtility.isTypeIncludedIn(returnType, Long.TYPE, Long.class, Integer.TYPE, Integer.class, Short.TYPE, Short.class)) {
			methodBuilder.addCode("return result;\n");
		} else if (TypeUtility.isTypeIncludedIn(returnType, Float.TYPE, Float.class)) {
			methodBuilder.addCode("return result;\n");
		} else if (TypeUtility.isTypeIncludedIn(returnType, Double.TYPE, Double.class)) {
			methodBuilder.addCode("return result;\n");
		} else if (TypeUtility.isTypeIncludedIn(returnType, Character.TYPE, Character.class)) {
			methodBuilder.addCode("return '';\n");
		} else {
			methodBuilder.addCode("return null;\n");
		}
		MethodSpec methodSpec = methodBuilder.build();

		builder.addMethod(methodSpec);
	}

}
