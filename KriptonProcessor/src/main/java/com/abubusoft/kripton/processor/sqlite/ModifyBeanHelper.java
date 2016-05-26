package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.isTypeIncludedIn;
import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.util.List;

import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindUpdate;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.SQLiteModifyBuilder.ModifyCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.model.AnnotationAttributeType;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.Transformer;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

public class ModifyBeanHelper implements ModifyCodeGenerator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.sqlite.SQLiteUpdateBuilder.UpdateCodeGenerator#generate(javax.lang.model.util.Elements, com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema,
	 * com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition, com.abubusoft.kripton.processor.sqlite.model.SQLEntity, com.squareup.javapoet.MethodSpec.Builder, boolean, com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod,
	 * com.squareup.javapoet.TypeName)
	 */
	public void generate(Elements elementUtils, SQLiteDatabaseSchema model, SQLDaoDefinition daoDefinition, SQLEntity entity, MethodSpec.Builder methodBuilder, boolean updateMode, SQLiteModelMethod method, TypeName returnType) {
		String beanNameParameter = method.getParameters().get(0).value0;
		SQLAnalyzer analyzer = new SQLAnalyzer();

		String whereCondition;

		if (updateMode) {
			whereCondition = method.getAnnotation(BindUpdate.class).getAttribute(AnnotationAttributeType.ATTRIBUTE_WHERE);
		} else {
			whereCondition = method.getAnnotation(BindDelete.class).getAttribute(AnnotationAttributeType.ATTRIBUTE_WHERE);
		}

		analyzer.execute(elementUtils, daoDefinition, entity, method, whereCondition);

		List<SQLProperty> listUsedProperty;
		if (updateMode) {
			listUsedProperty = CodeBuilderUtility.populateContentValuesFromEntity(elementUtils, model, daoDefinition, method, BindUpdate.class, methodBuilder, analyzer.getUsedBeanPropertyNames());
			CodeBuilderUtility.generateContentValuesFromEntity(elementUtils, model, daoDefinition, method, BindUpdate.class, methodBuilder, analyzer.getUsedBeanPropertyNames());
		} else {
			listUsedProperty = CodeBuilderUtility.populateContentValuesFromEntity(elementUtils, model, daoDefinition, method, BindDelete.class, methodBuilder, analyzer.getUsedBeanPropertyNames());
		}
		methodBuilder.addCode("\n");

		// build where condition
		generateWhereCondition(entity, methodBuilder, method, analyzer);

		if (updateMode) {
			methodBuilder.addCode("int result = database.update($S, contentValues, $S, whereConditions);\n", model.classNameConverter.convert(daoDefinition.getEntitySimplyClassName()), analyzer.getSQLStatement());
		} else {
			methodBuilder.addCode("int result = database.delete($S, $S, whereConditions);\n", model.classNameConverter.convert(daoDefinition.getEntitySimplyClassName()), analyzer.getSQLStatement());
		}

		// build javadoc
		buildJavadoc(model, daoDefinition, methodBuilder, updateMode, method, beanNameParameter, whereCondition, listUsedProperty);

		// define return value
		buildReturnCode(daoDefinition, methodBuilder, updateMode, method, returnType);

	}

	/**
	 * @param entity
	 * @param methodBuilder
	 * @param method
	 * @param analyzer
	 */
	public void generateWhereCondition(SQLEntity entity, MethodSpec.Builder methodBuilder, SQLiteModelMethod method, SQLAnalyzer analyzer) {
		String beanParamName = method.getParameters().get(0).value0;
		SQLProperty property;
		boolean nullable;
		TypeName beanClass = typeName(entity.getElement());

		methodBuilder.addCode("String[] whereConditions={");
		String separator = "";
		for (String item : analyzer.getUsedBeanPropertyNames()) {
			property=entity.findByName(item);
			methodBuilder.addCode(separator);					
			nullable=TypeUtility.isNullable(property);
			
			if (nullable)
			{
				methodBuilder.addCode("($L."+getter(beanClass, property)+"==null?null:", beanParamName);
			}
			methodBuilder.addCode("String.valueOf(");
			Transformer.java2ContentValues(methodBuilder, beanClass, beanParamName, property);
			if (nullable)
			{
				methodBuilder.addCode(")");
			}
			
			methodBuilder.addCode(")");

			separator = ", ";
		}
		methodBuilder.addCode("};");

		methodBuilder.addCode("\n");
	}

	/**
	 * @param daoDefinition
	 * @param methodBuilder
	 * @param updateMode
	 * @param method
	 * @param returnType
	 */
	public void buildReturnCode(SQLDaoDefinition daoDefinition, MethodSpec.Builder methodBuilder, boolean updateMode, SQLiteModelMethod method, TypeName returnType) {
		if (returnType == TypeName.VOID) {

		} else if (isTypeIncludedIn(returnType, Boolean.TYPE, Boolean.class)) {
			if (updateMode)
				methodBuilder.addJavadoc("\n@return true if record is updated\n");
			else
				methodBuilder.addJavadoc("\n@return true if record is deleted\n");
			methodBuilder.addCode("return result!=0;\n");
		} else if (isTypeIncludedIn(returnType, Long.TYPE, Long.class, Integer.TYPE, Integer.class, Short.TYPE, Short.class)) {
			if (updateMode) {
				methodBuilder.addJavadoc("\n@return number of updated records\n");
			} else {
				methodBuilder.addJavadoc("\n@return number of deleted records\n");
			}
			methodBuilder.addCode("return result;\n");
		} else {
			// more than one listener found
			throw (new InvalidMethodSignException(daoDefinition, method, "invalid return type"));
		}
	}

	/**
	 * @param model
	 * @param daoDefinition
	 * @param methodBuilder
	 * @param updateMode
	 * @param method
	 * @param beanNameParameter
	 * @param whereCondition
	 * @param listUsedProperty
	 */
	public void buildJavadoc(SQLiteDatabaseSchema model, SQLDaoDefinition daoDefinition, MethodSpec.Builder methodBuilder, boolean updateMode, SQLiteModelMethod method, String beanNameParameter, String whereCondition,
			List<SQLProperty> listUsedProperty) {
		// generate javadoc
		{
			StringBuilder buffer = new StringBuilder();
			String separator = "";
			for (SQLProperty property : listUsedProperty) {
				buffer.append(separator + property.getName() + "=${" + beanNameParameter + "." + property.getName() + "}");
				separator = ", ";
			}

			if (updateMode) {
				methodBuilder.addJavadoc("<p>Update query:</p>\n");
				methodBuilder.addJavadoc("<pre>UPDATE $L SET $L WHERE $L</pre>\n", model.classNameConverter.convert(daoDefinition.getEntitySimplyClassName()), buffer.toString(), whereCondition);
			} else {
				methodBuilder.addJavadoc("<p>Delete query:</p>\n");
				methodBuilder.addJavadoc("<pre>DELETE $L WHERE $L</pre>\n", model.classNameConverter.convert(daoDefinition.getEntitySimplyClassName()), whereCondition);
			}
			methodBuilder.addJavadoc("\n");

			// update bean have only one parameter: the bean to update
			for (Pair<String, TypeMirror> param : method.getParameters()) {
				methodBuilder.addJavadoc("@param $L", param.value0);
				methodBuilder.addJavadoc("\n\tused as updated field and in where condition\n");
			}
		}
	}

}
