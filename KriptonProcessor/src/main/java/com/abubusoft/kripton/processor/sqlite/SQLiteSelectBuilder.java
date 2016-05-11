package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import android.database.Cursor;

import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.JavaDocUtility;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidReturnTypeException;
import com.abubusoft.kripton.processor.sqlite.model.AnnotationAttributeType;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.Transformer;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec.Builder;


/**
 * @author xcesco
 *
 *
 * @since 05/mag/2016
 */
public abstract class SQLiteSelectBuilder {

	/**
	 * 
	 * @param elementUtils
	 * @param builder
	 * @param model
	 * @param daoDefinition
	 * @param method
	 */
	@SuppressWarnings("serial")
	public static void generate(Elements elementUtils, Builder builder, SQLiteDatabaseSchema model, SQLDaoDefinition daoDefinition, SQLiteModelMethod method) {
		SQLEntity entity = model.getEntity(daoDefinition.getEntityClassName());
		
		// separate params used for update bean and params used in whereCondition
		// analyze whereCondition
		ModelAnnotation annotation = method.getAnnotation(BindSelect.class);
		boolean distinctClause = Boolean.valueOf(annotation.getAttribute(AnnotationAttributeType.ATTRIBUTE_DISTINCT));

		Pair<String, List<ModelProperty>> fieldList = CodeBuilderHelper.generatePropertyList(elementUtils, model, daoDefinition, method, BindSelect.class, null);
		String fieldStatement = fieldList.value0;
		List<ModelProperty> fields=fieldList.value1;
		String tableStatement = model.classNameConverter.convert(daoDefinition.getEntitySimplyClassName());

		SQLAnalyzer analyzer=new SQLAnalyzer();
		
		String whereSQL = annotation.getAttribute(AnnotationAttributeType.ATTRIBUTE_WHERE);	
		analyzer.execute(elementUtils, daoDefinition, entity, method, whereSQL);
		String whereStatement = analyzer.getSQLStatement();
		List<String> whereParamGetters=analyzer.getParamGetters();
		List<String> whereParamNames=analyzer.getParamNames();		

		String havingSQL = annotation.getAttribute(AnnotationAttributeType.ATTRIBUTE_HAVING);
		analyzer.execute(elementUtils, daoDefinition, entity, method, havingSQL);
		String havingStatement = analyzer.getSQLStatement();
		List<String> havingParamGetters=analyzer.getParamGetters();
		List<String> havingParamNames=analyzer.getParamNames();

		String groupBySQL= annotation.getAttribute(AnnotationAttributeType.ATTRIBUTE_GROUP_BY);
		analyzer.execute(elementUtils, daoDefinition, entity, method, groupBySQL);
		String groupByStatement = analyzer.getSQLStatement();
		List<String> groupByParamGetters=analyzer.getParamGetters();
		List<String> groupByParamNames=analyzer.getParamNames();
		
		String orderBySQL= annotation.getAttribute(AnnotationAttributeType.ATTRIBUTE_ORDER_BY);
		analyzer.execute(elementUtils, daoDefinition, entity, method, orderBySQL);
		String orderByStatement = analyzer.getSQLStatement();
		List<String> orderByParamGetters=analyzer.getParamGetters();
		List<String> orderByParamNames=analyzer.getParamNames();

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
		{
		String separator = "";

		for (String item : paramGetters) {
			methodBuilder.addCode(separator);
			methodBuilder.addCode("String.valueOf($L)", item);

			separator = ", ";
		}
		methodBuilder.addCode("};");
		}

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
		methodBuilder.addCode("$T cursor = database.rawQuery(\"$L\", args);\n", Cursor.class, sql);

		TypeName returnType = TypeName.get(method.getReturnClass());
		methodBuilder.returns(returnType);
		
		if ((returnType instanceof ParameterizedTypeName))
		{
			// return Collection<Entity>
			ParameterizedTypeName returnListName = (ParameterizedTypeName) returnType;
			
			TypeName collectionClass;			
			TypeName entityClass= typeName(entity.getElement());
			ClassName listClazzName=returnListName.rawType;
			
			if (TypeUtility.isTypeIncludedIn(listClazzName.toString(), List.class, Collection.class, Iterable.class))
			{
				// there is an interface as result
				collectionClass=typeName(LinkedList.class);
			} else {
				collectionClass=listClazzName;
			}
			
			methodBuilder.addCode("\n");
			methodBuilder.addCode("$T<$T> resultList=new $T<$T>();\n",collectionClass,entityClass, collectionClass,entityClass);
			methodBuilder.addCode("$T resultBean=new $T();\n",entityClass, entityClass);
			methodBuilder.addCode("\n");
			methodBuilder.beginControlFlow("if (cursor.moveToFirst())");
			
			// generate index from columns
			
			methodBuilder.addCode("\n");
			{
				int i=0;	
				for (ModelProperty item : fields) {					
					methodBuilder.addCode("int index"+(i++)+"=");
					methodBuilder.addCode("cursor.getColumnIndex($S)", SQLUtility.getColumnName(item));
					methodBuilder.addCode(";\n");
				}
			}
			methodBuilder.addCode("\n\n");			
			
			methodBuilder.beginControlFlow("do\n");			
			methodBuilder.addCode("resultBean=new $T();\n",entityClass);
			
			// generate mapping
			int i=0;
			for (ModelProperty item : fields) {			
				Transformer.cursor2Bean(methodBuilder, item, "resultBean", "cursor","index"+(i++)+"");
				methodBuilder.addCode("\n");
			}
			methodBuilder.addCode("\n");
			
			methodBuilder.addCode("resultList.add(resultBean);\n");			
			methodBuilder.endControlFlow("while (cursor.moveToNext())");						
			
			methodBuilder.endControlFlow();
			methodBuilder.addCode("cursor.close();\n");
			
			methodBuilder.addCode("\n");
			methodBuilder.addCode("return resultList;\n");
			
		} else if (TypeUtility.isTypeIncludedIn(TypeUtility.typeName(method.getReturnClass()), Cursor.class, Void.class)) {			
			// return cursor
			// define return value
			if (TypeUtility.isTypeIncludedIn(TypeUtility.typeName(method.getReturnClass()), Cursor.class)) {
				methodBuilder.addCode("return cursor;\n");
			}			
		} else {
			throw (new InvalidReturnTypeException(daoDefinition, method, typeName(method.getReturnClass()), typeName(Cursor.class)));
		}
		
		methodBuilder.addJavadoc("caio2\n");

		
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
