package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.isSameType;
import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.isTypeIncludedIn;
import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.annotation.BindUpdate;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.model.AnnotationAttributeType;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec.Builder;

public abstract class SQLiteUpdateBeanBuilder {

	public static void generate(Elements elementUtils, Builder builder, SQLiteDatabaseSchema model, SQLDaoDefinition daoDefinition, ModelMethod method) {
		SQLEntity entity = model.getEntity(daoDefinition.getEntityClassName());
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.getName()).addAnnotation(Override.class).addModifiers(Modifier.PUBLIC);

		methodBuilder.addJavadoc("\n$L\n", method.getAnnotations().get(0).toString());
		boolean foundBean = false;
		ParameterSpec parameterSpec;
		for (Pair<String, TypeMirror> item : method.getParameters()) {
			parameterSpec = ParameterSpec.builder(typeName(item.value1), item.value0).build();
			methodBuilder.addParameter(parameterSpec);

			if (isSameType(typeName(item.value1), daoDefinition.getEntityClassName())) {
				foundBean = true;
			}
		}
		if (!foundBean) {
			throw (new InvalidMethodSignException(daoDefinition, method));
		}

		String whereCondition = AnnotationUtility.extractAsString(elementUtils, method, method.getAnnotation(BindUpdate.class), AnnotationAttributeType.ATTRIBUTE_WHERE);
		SQLAnalyzer analyzer = new SQLAnalyzer();
		analyzer.execute(elementUtils, daoDefinition, entity, method, whereCondition);

		CodeBuilderUtility.populateContentValuesFromEntity(elementUtils, model, daoDefinition, method, BindUpdate.class, methodBuilder, analyzer.getUsedBeanPropertyNames());

		methodBuilder.addCode("\n");

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
		methodBuilder.addCode("int result = database.update($S, contentValues, $S, whereConditions);\n", model.classNameConverter.convert(daoDefinition.getEntitySimplyClassName()), analyzer.getSQLStatement());

		TypeName returnType = TypeName.get(method.getReturnClass());
		methodBuilder.returns(returnType);

		// define return value
		if (returnType == TypeName.VOID) {

		} else if (isTypeIncludedIn(returnType, String.class)) {
			methodBuilder.addCode("return String.valueOf(result);\n");
		} else if (isTypeIncludedIn(returnType, Boolean.TYPE, Boolean.class)) {
			methodBuilder.addCode("return result!=-1;\n");
		} else if (isTypeIncludedIn(returnType, Long.TYPE, Long.class, Integer.TYPE, Integer.class, Short.TYPE, Short.class)) {
			methodBuilder.addCode("return result;\n");
		} else if (isTypeIncludedIn(returnType, Float.TYPE, Float.class)) {
			methodBuilder.addCode("return result;\n");
		} else if (isTypeIncludedIn(returnType, Double.TYPE, Double.class)) {
			methodBuilder.addCode("return result;\n");
		} else if (isTypeIncludedIn(returnType, Character.TYPE, Character.class)) {
			methodBuilder.addCode("return '';\n");
		} else {
			methodBuilder.addCode("return null;\n");
		}
		MethodSpec methodSpec = methodBuilder.build();

		builder.addMethod(methodSpec);
	}

}
