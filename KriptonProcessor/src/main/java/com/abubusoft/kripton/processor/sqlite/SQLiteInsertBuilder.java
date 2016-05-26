package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.model.AnnotationAttributeType;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec.Builder;

/**
 * @author xcesco
 *
 *
 * @since 05/mag/2016
 */
public abstract class SQLiteInsertBuilder {

	public enum InsertType {
			INSERT_BEAN(InsertBeanHelper.class, true),
			INSERT_RAW(InsertRawHelper.class, false);

		private InsertCodeGenerator codeGenerator;

		private boolean mapFields;

		/**
		 * if true, map cursor fields to bean attributes.
		 * 
		 * @return the mapFields
		 */
		public boolean isMapFields() {
			return mapFields;
		}

		/**
		 * @return the codeGenerator
		 */
		public InsertCodeGenerator getCodeGenerator() {
			return codeGenerator;
		}

		private InsertType(Class<? extends InsertCodeGenerator> codeGenerator, boolean mapFields) {
			try {
				this.mapFields = mapFields;
				this.codeGenerator = codeGenerator.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		public void generate(Elements elementUtils, SQLiteDatabaseSchema model, SQLDaoDefinition daoDefinition, SQLEntity entity, MethodSpec.Builder methodBuilder, SQLiteModelMethod method,
				TypeName returnType) {
			codeGenerator.generate(elementUtils, model, daoDefinition, entity, methodBuilder, this.isMapFields(), method, returnType);

		}
	}

	public interface InsertCodeGenerator {
		void generate(Elements elementUtils, SQLiteDatabaseSchema model, SQLDaoDefinition daoDefinition, SQLEntity entity, MethodSpec.Builder methodBuilder, boolean mapFields,
				SQLiteModelMethod method, TypeName returnType);
	}

	/**
	 * 
	 * @param elementUtils
	 * @param builder
	 * @param model
	 * @param daoDefinition
	 * @param method
	 */
	public static void generate(Elements elementUtils, Builder builder, SQLiteDatabaseSchema model, SQLDaoDefinition daoDefinition, SQLiteModelMethod method) {
		SQLEntity entity = model.getEntity(daoDefinition.getEntityClassName());
		InsertType insertResultType = null;

		// check type of arguments
		int count = 0;
		for (Pair<String, TypeMirror> param : method.getParameters()) {
			if (TypeUtility.isEquals(typeName(param.value1), typeName(entity.getElement()))) {
				count++;
			}
		}

		if (count == 0) {
			// method to insert raw data: no bean is used
			insertResultType = InsertType.INSERT_RAW;

			ModelAnnotation annotation = method.getAnnotation(BindInsert.class);
			
			if (AnnotationUtility.extractAsStringArray(elementUtils, method, annotation, AnnotationAttributeType.ATTRIBUTE_VALUE).size()>0) {
				throw (new InvalidMethodSignException(daoDefinition, method, " can not use attribute " + AnnotationAttributeType.ATTRIBUTE_VALUE + " in this kind of query definition"));
			}
			
			if (AnnotationUtility.extractAsStringArray(elementUtils, method, annotation, AnnotationAttributeType.ATTRIBUTE_EXCLUDED_FIELDS).size()>0) {
				throw (new InvalidMethodSignException(daoDefinition, method, " can not use attribute " + AnnotationAttributeType.ATTRIBUTE_EXCLUDED_FIELDS + " in this kind of query definition"));
			}
			
			// check if there is only one parameter
			if (method.getParameters().size() != 1 && TypeUtility.isSameType(TypeUtility.typeName(method.getParameters().get(0).value1), daoDefinition.getEntityClassName())) {
				throw (new InvalidMethodSignException(daoDefinition, method));
			}
			

		} else if (count == 1) {
			insertResultType = InsertType.INSERT_BEAN;
			
			if (method.getParameters().size()>1)
			{
				throw (new InvalidMethodSignException(daoDefinition, method, " aspected only one parameter of "+daoDefinition.getEntityClassName()+" type"));
			}
		} else {
			throw (new InvalidMethodSignException(daoDefinition, method, "only one parameter of type " + typeName(entity.getElement()) + " can be used"));
		}

		// if true, field must be associate to ben attributes
		TypeName returnType = typeName(method.getReturnClass());

		if (insertResultType == null) {
			throw (new InvalidMethodSignException(daoDefinition, method));
		}

		// generate method code
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.getName()).addAnnotation(Override.class).addModifiers(Modifier.PUBLIC);

		ParameterSpec parameterSpec;
		for (Pair<String, TypeMirror> item : method.getParameters()) {
			parameterSpec = ParameterSpec.builder(TypeName.get(item.value1), item.value0).build();
			methodBuilder.addParameter(parameterSpec);
		}
		methodBuilder.returns(returnType);

		// generate inner code
		insertResultType.generate(elementUtils, model, daoDefinition, entity, methodBuilder, method, returnType);

		MethodSpec methodSpec = methodBuilder.build();
		builder.addMethod(methodSpec);

	}
	
}
