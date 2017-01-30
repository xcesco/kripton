package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelType;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.SqlSelectBuilder.SelectCodeGenerator;
import com.abubusoft.kripton.processor.sqlite.SqlSelectBuilder.SelectResultType;
import com.abubusoft.kripton.processor.sqlite.core.JavadocUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.abubusoft.kripton.processor.utils.LiteralType;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec.Builder;

import android.database.Cursor;

public abstract class AbstractSelectCodeGenerator implements SelectCodeGenerator {

	public SelectResultType selectResultType;
	
	@Override
	public void generate(Elements elementUtils, Builder builder, boolean mapFields, SQLiteModelMethod method, TypeMirror returnType) {
		SQLDaoDefinition daoDefinition=method.getParent();
		PropertyList fieldList = CodeBuilderUtility.generatePropertyList(elementUtils, daoDefinition, method, BindSqlSelect.class, selectResultType.isMapFields(), null);
		// generate method code
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.getName()).addAnnotation(Override.class).addModifiers(Modifier.PUBLIC);
		
		generatePartOne(elementUtils, fieldList, selectResultType.isMapFields(), method, methodBuilder);
		generatePartTwo(elementUtils, fieldList, selectResultType.isMapFields(), method, methodBuilder);
		
		builder.addMethod(methodBuilder.build());
		
	}

	public void generatePartOne(Elements elementUtils, PropertyList fieldList, boolean mapFields, SQLiteModelMethod method, MethodSpec.Builder methodBuilder) {
		SQLDaoDefinition daoDefinition=method.getParent();
		SQLEntity entity = daoDefinition.getEntity();

		// if true, field must be associate to ben attributes
		TypeMirror returnType = method.getReturnClass();
		TypeName returnTypeName = typeName(returnType);
		
		ModelAnnotation annotation = method.getAnnotation(BindSqlSelect.class);
		int pageSize = annotation.getAttributeAsInt(AnnotationAttributeType.PAGE_SIZE);
		
		// take field list		
		String fieldStatement = fieldList.value0;
		// List<SQLProperty> fields = fieldList.value1;
		String tableStatement = daoDefinition.getEntity().getTableName();

		// separate params used for update bean and params used in
		// whereCondition
		// analyze whereCondition

		boolean distinctClause = Boolean.valueOf(annotation.getAttribute(AnnotationAttributeType.DISTINCT));

		// parameters
		List<String> paramNames = new ArrayList<String>();
		List<String> paramGetters = new ArrayList<String>();
		List<TypeMirror> paramTypeNames = new ArrayList<TypeMirror>();

		// used method parameters
		Set<String> usedMethodParameters = new HashSet<String>();

		SqlAnalyzer analyzer = new SqlAnalyzer();

		String whereSQL = annotation.getAttribute(AnnotationAttributeType.WHERE);
		analyzer.execute(elementUtils, method, whereSQL);
		String whereStatement = analyzer.getSQLStatement();
		paramGetters.addAll(analyzer.getParamGetters());
		paramNames.addAll(analyzer.getParamNames());
		paramTypeNames.addAll(analyzer.getParamTypeNames());
		usedMethodParameters.addAll(analyzer.getUsedMethodParameters());

		String havingSQL = annotation.getAttribute(AnnotationAttributeType.HAVING);
		analyzer.execute(elementUtils, method, havingSQL);
		String havingStatement = analyzer.getSQLStatement();
		paramGetters.addAll(analyzer.getParamGetters());
		paramNames.addAll(analyzer.getParamNames());
		paramTypeNames.addAll(analyzer.getParamTypeNames());
		usedMethodParameters.addAll(analyzer.getUsedMethodParameters());

		String groupBySQL = annotation.getAttribute(AnnotationAttributeType.GROUP_BY);
		analyzer.execute(elementUtils, method, groupBySQL);
		String groupByStatement = analyzer.getSQLStatement();
		paramGetters.addAll(analyzer.getParamGetters());
		paramNames.addAll(analyzer.getParamNames());
		paramTypeNames.addAll(analyzer.getParamTypeNames());
		usedMethodParameters.addAll(analyzer.getUsedMethodParameters());

		String orderBySQL = annotation.getAttribute(AnnotationAttributeType.ORDER_BY);
		analyzer.execute(elementUtils, method, orderBySQL);
		String orderByStatement = analyzer.getSQLStatement();
		paramGetters.addAll(analyzer.getParamGetters());
		paramNames.addAll(analyzer.getParamNames());
		paramTypeNames.addAll(analyzer.getParamTypeNames());
		usedMethodParameters.addAll(analyzer.getUsedMethodParameters());

		// add as used parameter dynamic components too
		if (method.hasDynamicWhereConditions()) {
			AssertKripton.assertTrueOrInvalidMethodSignException(!usedMethodParameters.contains(method.dynamicWhereParameterName), method,
					" parameter %s is used like SQL parameter and dynamic WHERE condition.", method.dynamicOrderByParameterName);
			usedMethodParameters.add(method.dynamicWhereParameterName);
		}

		if (method.hasDynamicOrderByConditions()) {
			AssertKripton.assertTrueOrInvalidMethodSignException(!usedMethodParameters.contains(method.dynamicOrderByParameterName), method,
					" parameter %s is used like SQL parameter and dynamic ORDER BY condition.", method.dynamicOrderByParameterName);
			usedMethodParameters.add(method.dynamicOrderByParameterName);
		}

		if (method.hasDynamicPageSizeConditions()) {
			AssertKripton.assertTrueOrInvalidMethodSignException(!usedMethodParameters.contains(method.dynamicPageSize), method,
					" parameter %s is used like SQL parameter and dynamic page size of LIMIT condition.", method.dynamicPageSize);
			usedMethodParameters.add(method.dynamicPageSize);
		}

		// select statement
		String sqlWithParameters = SelectStatementBuilder.create().distinct(distinctClause).fields(fieldStatement).table(tableStatement).where(whereSQL).having(havingSQL).groupBy(groupBySQL)
				.orderBy(orderBySQL).limit(pageSize, selectResultType == SelectResultType.PAGED_RESULT).build(method);
		String sql = SelectStatementBuilder.create().distinct(distinctClause).fields(fieldStatement).table(tableStatement).where(whereStatement).having(havingStatement).groupBy(groupByStatement)
				.orderBy(orderByStatement).limit(pageSize, selectResultType == SelectResultType.PAGED_RESULT).build(method);
		String sqlForLog = SelectStatementBuilder.create().distinct(distinctClause).fields(fieldStatement).table(tableStatement).where(whereStatement).having(havingStatement).groupBy(groupByStatement)
				.orderBy(orderByStatement).limit(pageSize, selectResultType == SelectResultType.PAGED_RESULT).buildForLog(method);
		
		// generate javadoc
		JavadocUtility.generateJavaDocForSelect(methodBuilder, sqlWithParameters, paramNames, method, annotation, fieldList, selectResultType);

		// add parameter for method
		ParameterSpec parameterSpec;
		for (Pair<String, TypeMirror> item : method.getParameters()) {
			parameterSpec = ParameterSpec.builder(TypeName.get(item.value1), item.value0).build();
			methodBuilder.addParameter(parameterSpec);
		}

		// add return type
		methodBuilder.returns(returnTypeName);

		// build where condition (common for every type of select)
		StringBuilder logArgsBuffer = new StringBuilder();
		methodBuilder.addCode("// build where condition\n");
		methodBuilder.addCode("String[] args={");
		{
			String separator = "";

			TypeMirror paramTypeName;
			TypeName paramName;

			boolean nullable;
			int i = 0;
			for (String item : paramGetters) {
				methodBuilder.addCode(separator);
				logArgsBuffer.append(separator + "%s");

				paramTypeName = paramTypeNames.get(i);

				if (paramTypeName instanceof ModelType) {
					paramName = ((ModelType) paramTypeName).getName();
				} else {
					paramName = typeName(paramTypeName);
				}

				// code for query arguments
				nullable = TypeUtility.isNullable(paramName);
				if (nullable) {
					methodBuilder.addCode("($L==null?\"\":", item);
				}

				// check for string conversion
				TypeUtility.beginStringConversion(methodBuilder, paramTypeName);

				SQLTransformer.java2ContentValues(methodBuilder, daoDefinition, TypeUtility.typeName(paramTypeName), item);

				// check for string conversion
				TypeUtility.endStringConversion(methodBuilder, paramTypeName);

				if (nullable) {
					methodBuilder.addCode(")");
				}

				separator = ", ";
				i++;
			}
			methodBuilder.addCode("};\n");
		}

		methodBuilder.addCode("\n");
		// this comment is added to include in all situation StringUtil.class
		methodBuilder.addCode("//$T will be used in case of dynamic parts of SQL\n", StringUtils.class);

		if (daoDefinition.isLogEnabled()) {
			methodBuilder.addStatement("$T.info($T.formatSQL(\"$L\",(Object[])args))", Logger.class, StringUtils.class, formatSqlForLog(method, sqlForLog));
		}
		methodBuilder.addStatement("$T cursor = database().rawQuery(\"$L\", args)", Cursor.class, formatSql(method, sql));

		if (daoDefinition.isLogEnabled()) {
			methodBuilder.addCode("$T.info(\"Rows found: %s\",cursor.getCount());\n", Logger.class);
		}

		{
			switch (selectResultType) {
			case LISTENER_CURSOR: {
				LiteralType readCursorListenerToExclude = LiteralType.of(OnReadCursorListener.class);
				checkUnusedParameters(method, usedMethodParameters, readCursorListenerToExclude);
			}
				break;
			case LISTENER_BEAN: {
				LiteralType readBeanListenerToExclude = LiteralType.of(OnReadBeanListener.class.getCanonicalName(), entity.getName());
				checkUnusedParameters(method, usedMethodParameters, readBeanListenerToExclude);
			}
				break;
			default:
				checkUnusedParameters(method, usedMethodParameters, null);
				break;
			}
		}
		
	}
	
	public abstract void generatePartTwo(Elements elementUtils, PropertyList fieldList, boolean mapFields, SQLiteModelMethod method, MethodSpec.Builder methodBuilder);
	

	@Override
	public void setSelectResultTye(SelectResultType value) {
		this.selectResultType=value;
	}
	

	public static String formatSqlForLog(SQLiteModelMethod method, String sql) {
		sql = sql.replaceAll("\\%[^s]", "\\%\\%").replaceAll("\\?", "\'%s\'");

		return formatSqlInternal(method, sql, "appendForLog");
	}

	public static String formatSql(SQLiteModelMethod method, String sql) {
		return formatSqlInternal(method, sql, "appendForSQL");
	}

	public static String formatSqlInternal(SQLiteModelMethod method, String sql, String appendMethod) {
		if (method.hasDynamicWhereConditions()) {
			sql = sql.replace("#{" + method.dynamicWhereParameterName + "}", "\"+StringUtils." + appendMethod + "(" + method.dynamicWhereParameterName + ")+\"");
		}

		if (method.hasDynamicOrderByConditions()) {
			sql = sql.replace("#{" + method.dynamicOrderByParameterName + "}", "\"+StringUtils." + appendMethod + "(" + method.dynamicOrderByParameterName + ")+\"");
		}

		return sql;
	}
	
	/**
	 * Check if there are unused method parameters. In this case an exception
	 * was throws.
	 * 
	 * @param method
	 * @param usedMethodParameters
	 */
	public static void checkUnusedParameters(SQLiteModelMethod method, Set<String> usedMethodParameters, LiteralType excludedClasses) {
		int paramsCount = method.getParameters().size();
		int usedCount = usedMethodParameters.size();

		if (paramsCount > usedCount) {
			StringBuilder sb = new StringBuilder();
			String separator = "";
			for (Pair<String, TypeMirror> item : method.getParameters()) {
				if (excludedClasses != null && typeName(item.value1).toString().equals(excludedClasses.getValue())) {
					usedCount++;
				} else {
					if (!usedMethodParameters.contains(item.value0)) {
						sb.append(separator + "'" + item.value0 + "'");
						separator = ", ";
					}
				}
			}

			if (paramsCount > usedCount) {
				throw (new InvalidMethodSignException(method, "unused parameter(s) " + sb.toString()));
			}
		}
	}


}
