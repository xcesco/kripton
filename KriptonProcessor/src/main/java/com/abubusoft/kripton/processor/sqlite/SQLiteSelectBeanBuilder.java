package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import android.database.Cursor;

import com.abubusoft.kripton.android.annotation.SQLSelect;
import com.abubusoft.kripton.android.annotation.SQLSelectBean;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.reflect.JavaDocUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidReturnTypeException;
import com.abubusoft.kripton.processor.sqlite.exceptions.MethodParameterNotFoundException;
import com.abubusoft.kripton.processor.sqlite.exceptions.PropertyNotFoundException;
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
public abstract class SQLiteSelectBeanBuilder {

	/**
	 * 
	 * @param elementUtils
	 * @param builder
	 * @param model
	 * @param daoDefinition
	 * @param method
	 */
	public static void generate(Elements elementUtils, Builder builder, SQLiteModel model, DaoDefinition daoDefinition, SQLiteModelMethod method) {
SQLEntity entity = model.getEntity(daoDefinition.getEntityClassName());
		
		// separate params used for update bean and params used in whereCondition
		// analyze whereCondition
		ModelAnnotation annotation = method.getAnnotation(SQLSelect.class);
		boolean distinctClause = Boolean.valueOf(annotation.getAttribute(AnnotationAttributeType.ATTRIBUTE_DISTINCT));
		// String fieldStatement=method.getAnnotation(SQLSelect.class).getAttribute(AnnotationAttributeType.ATTRIBUTE_VALUE);
		String fieldStatement = CodeBuilderHelper.generatePropertyList(elementUtils, model, daoDefinition, method, SQLSelect.class, null).value0;
		String tableStatement = model.classNameConverter.convert(daoDefinition.getEntitySimplyClassName());

		SQLAnalyzer analyzer=new SQLAnalyzer();
		
		String whereSQL = annotation.getAttribute(AnnotationAttributeType.ATTRIBUTE_WHERE);	
		analyzer.execute(elementUtils, daoDefinition, entity, method, whereSQL);
		String whereStatement = analyzer.getSQLStatement();
		List<String> whereParamGetters=analyzer.getParamGetters();
		List<String> whereParamNames=analyzer.getParamNames();		
		//Pair<String, List<String>> whereStatement = SQLUtility.extractParametersFromString(whereSQL, model.columnNameConverter, entity);

		String havingSQL = annotation.getAttribute(AnnotationAttributeType.ATTRIBUTE_HAVING);
		analyzer.execute(elementUtils, daoDefinition, entity, method, havingSQL);
		String havingStatement = analyzer.getSQLStatement();
		List<String> havingParamGetters=analyzer.getParamGetters();
		List<String> havingParamNames=analyzer.getParamNames();
		//Pair<String, List<String>> havingStatement = SQLUtility.extractParametersFromString(havingSQL, model.columnNameConverter, entity);

		String groupBySQL= annotation.getAttribute(AnnotationAttributeType.ATTRIBUTE_GROUP_BY);
		analyzer.execute(elementUtils, daoDefinition, entity, method, groupBySQL);
		String groupByStatement = analyzer.getSQLStatement();
		List<String> groupByParamGetters=analyzer.getParamGetters();
		List<String> groupByParamNames=analyzer.getParamNames();
		//Pair<String, List<String>> groupByStatement = SQLUtility.extractParametersFromString(groupBySQL, model.columnNameConverter, entity);
		
		String orderBySQL= annotation.getAttribute(AnnotationAttributeType.ATTRIBUTE_ORDER_BY);
		analyzer.execute(elementUtils, daoDefinition, entity, method, orderBySQL);
		String orderByStatement = analyzer.getSQLStatement();
		List<String> orderByParamGetters=analyzer.getParamGetters();
		List<String> orderByParamNames=analyzer.getParamNames();
		//Pair<String, List<String>> orderByStatement = SQLUtility.extractParametersFromString(orderBySQL, model.columnNameConverter, entity);

		// select statement
		String sqlWithParameters = SelectStatementBuilder.create().distinct(distinctClause).fields(fieldStatement).table(tableStatement).where(whereSQL).having(havingSQL).groupBy(groupBySQL).orderBy(orderBySQL)
				.build();
		String sql = SelectStatementBuilder.create().distinct(distinctClause).fields(fieldStatement).table(tableStatement).where(whereStatement).having(havingStatement).groupBy(groupByStatement).orderBy(orderByStatement)
				.build();
		
		// select parameters
		List<String> paramNames = new ArrayList<String>();
		paramNames.addAll(whereParamNames);
		paramNames.addAll(havingParamNames);
		paramNames.addAll(groupByParamNames);
		paramNames.addAll(orderByParamNames);
		
		// select getter
		List<String> paramGetters = new ArrayList<String>();
		paramGetters.addAll(whereParamGetters);
		paramGetters.addAll(havingParamGetters);
		paramGetters.addAll(groupByParamGetters);
		paramGetters.addAll(orderByParamGetters);
		
		// generate method code
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.getName()).addAnnotation(Override.class).addModifiers(Modifier.PUBLIC);

		JavaDocUtility.generateJavaDocForSelect(methodBuilder, sqlWithParameters, paramNames, method, annotation, fieldStatement);

		ParameterSpec parameterSpec;
		for (Pair<String, TypeMirror> item : method.getParameters()) {
			parameterSpec = ParameterSpec.builder(TypeName.get(item.value1), item.value0).build();
			methodBuilder.addParameter(parameterSpec);
		}
		
		// build where condition
		methodBuilder.addCode("String[] args={");
		String separator = "";

		for (String item : paramGetters) {
			methodBuilder.addCode(separator);
			methodBuilder.addCode("String.valueOf($L)", item);

			separator = ", ";
		}
		methodBuilder.addCode("};");

		/*
		for (String item : paramNames) {
			if (!entity.contains(item))
			{
				throw (new PropertyNotFoundException(daoDefinition, method, item));
			}
			
			if (!method.hasParameter(item))
			{
				throw (new MethodParameterNotFoundException(daoDefinition, method, item));
			}
		}*/

		methodBuilder.addCode("\n");
		methodBuilder.addCode("Cursor result = database.rawQuery(\"$L\", args);\n", sql);

		TypeName returnType = TypeName.get(method.getReturnClass());
		methodBuilder.returns(returnType);

		if (!TypeUtility.isTypeIncludedIn(TypeUtility.typeName(method.getReturnClass()), Cursor.class, Void.class)) {
			throw (new InvalidReturnTypeException(daoDefinition, method, typeName(method.getReturnClass()), typeName(Cursor.class)));
		}

		// define return value
		if (TypeUtility.isTypeIncludedIn(TypeUtility.typeName(method.getReturnClass()), Cursor.class)) {
			methodBuilder.addCode("return result;\n");
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
