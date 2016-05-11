package com.abubusoft.kripton.processor.sqlite;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.annotation.BindInsertBean;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec.Builder;

public abstract class SQLiteInsertBeanBuilder {
	
	public static void generate(Elements elementUtils, Builder builder, SQLiteDatabaseSchema model, SQLDaoDefinition daoDefinition, ModelMethod method) {
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.getName()).addAnnotation(Override.class).addModifiers(Modifier.PUBLIC);

		methodBuilder.addJavadoc("\n$L\n",method.getAnnotations().get(0).toString());

		// check if there is only one parameter
		if (method.getParameters().size() != 1 && TypeUtility.isSameType(TypeUtility.typeName(method.getParameters().get(0).value1), daoDefinition.getEntityClassName())) {
			throw (new InvalidMethodSignException(daoDefinition, method));
		}
		
		ParameterSpec parameterSpec;
		for (Pair<String, TypeMirror> item : method.getParameters()) {
			parameterSpec = ParameterSpec.builder(TypeName.get(item.value1), item.value0).build();
			methodBuilder.addParameter(parameterSpec);
		}
				
		ModelProperty primaryKey = CodeBuilderHelper.populateContentValuesFromEntity(elementUtils, model, daoDefinition, method, BindInsertBean.class, methodBuilder, null);
		
		methodBuilder.addCode("\n");
		methodBuilder.addCode("long result = database.insert($S, null, contentValues);\n", model.classNameConverter.convert(daoDefinition.getEntitySimplyClassName()));
		
		if (primaryKey!=null)
		{
			if (primaryKey.isPublicField())
			{
				methodBuilder.addCode("$L.$L=result;\n",method.getParameters().get(0).value0,primaryKey.getName());
			} else {
				methodBuilder.addCode("$L.$L(result);\n",method.getParameters().get(0).value0,PropertyUtility.setter(primaryKey));
			}
		}

		TypeName returnType = TypeName.get(method.getReturnClass());
		methodBuilder.returns(returnType);

		methodBuilder.addCode("\n");
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
