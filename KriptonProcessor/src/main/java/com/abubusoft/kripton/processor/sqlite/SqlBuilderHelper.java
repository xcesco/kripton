/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.MethodFoundListener;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL.JQLDynamicStatementType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL.JQLType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLKeywords;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLPlaceHolder;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLPlaceHolder.JQLPlaceHolderType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplaceVariableStatementListenerImpl;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListenerImpl;
import com.abubusoft.kripton.processor.sqlite.grammars.uri.ContentUriPlaceHolder;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * <p>
 * Utility class for build methods
 * </p>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public abstract class SqlBuilderHelper {

	/**
	 * <p>
	 * </p>
	 * 
	 * @param elementUtils
	 * @param builder
	 * @param method
	 * @param columnNames
	 * 
	 * @return name of column name set
	 */
	public static String generateColumnCheckSet(Elements elementUtils, TypeSpec.Builder builder, SQLiteModelMethod method, Set<String> columnNames) {
		String columnNameSet = method.contentProviderMethodName + "ColumnSet";
		StringBuilder initBuilder = new StringBuilder();
		String temp = "";

		for (String item : columnNames) {
			initBuilder.append(temp + "\"" + item + "\"");
			temp = ", ";
		}

		FieldSpec.Builder fieldBuilder = FieldSpec.builder(ParameterizedTypeName.get(Set.class, String.class), columnNameSet, Modifier.STATIC, Modifier.PRIVATE, Modifier.FINAL);
		fieldBuilder.initializer("$T.asSet($T.class, $L)", CollectionUtils.class, String.class, initBuilder.toString());

		builder.addField(fieldBuilder.build());

		return columnNameSet;
	}

	/**
	 * <p>
	 * Generate column check
	 * </p>
	 * 
	 * @param method
	 * @param methodBuilder
	 * @param columnSetString
	 * @param generateColumnNameCheck
	 * @param listener
	 */
	static void forEachColumnInContentValue(final SQLiteModelMethod method, MethodSpec.Builder methodBuilder, String columnSetString, boolean generateColumnNameCheck, OnColumnListener listener) {
		SQLDaoDefinition daoDefinition = method.getParent();
		methodBuilder.beginControlFlow("for (String columnName:$L)", columnSetString);
		if (generateColumnNameCheck) {
			methodBuilder.beginControlFlow("if (!$L.contains(columnName))", method.contentProviderMethodName + "ColumnSet");
			methodBuilder.addStatement("throw new $T(String.format(\"For URI '$L', column '%s' does not exists in table '%s' or can not be defined in this $L operation\", columnName, $S ))",
					KriptonRuntimeException.class, method.contentProviderUriTemplate, method.jql.operationType, daoDefinition.getEntity().getTableName());
			methodBuilder.endControlFlow();
		}
		if (listener != null)
			listener.onColumnCheck(methodBuilder, "columnName");
		methodBuilder.endControlFlow();
	}

	static List<JQLPlaceHolder> removeDynamicPlaceHolder(List<JQLPlaceHolder> placeHolders) {
		List<JQLPlaceHolder> result = new ArrayList<>();

		for (JQLPlaceHolder item : placeHolders) {
			if (item.type != JQLPlaceHolderType.DYNAMIC_SQL) {
				result.add(item);
			}
		}

		return result;
	}

	/**
	 * look for variable name in place holders defined through path of content
	 * provider.
	 * 
	 * @param value
	 * @param placeHolders
	 * @return <code>true</code> if we found it path
	 */
	static boolean validate(String value, List<JQLPlaceHolder> placeHolders, int pos) {
		return placeHolders.get(pos).value.equals(value);
	}

	/**
	 * @param method
	 * @param methodBuilder
	 */
	public static void generateJavaDocForContentProvider(final SQLiteModelMethod method, MethodSpec.Builder methodBuilder) {
		// javadoc
		String operation = method.jql.operationType.toString();
		methodBuilder.addJavadoc("<h1>Content provider URI ($L operation):</h1>\n", operation);
		methodBuilder.addJavadoc("<pre>$L</pre>\n\n", method.contentProviderUriTemplate.replace("*", "[*]"));

		methodBuilder.addJavadoc("<h2>JQL $L for Content Provider</h2>\n", operation);
		methodBuilder.addJavadoc("<pre>$L</pre>\n\n", method.jql.value);
		methodBuilder.addJavadoc("<h2>SQL $L for Content Provider</h2>\n", operation);
		String sql = JQLChecker.getInstance().replace(method.jql, new com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListenerImpl() {

			@Override
			public String onTableName(String tableName) {
				return method.getParent().getParent().getEntityBySimpleName(tableName).getTableName();
			}

			@Override
			public String onColumnName(String columnName) {
				SQLProperty tempProperty = method.getParent().getEntity().get(columnName);
				AssertKripton.assertTrueOrUnknownPropertyInJQLException(tempProperty != null, method, columnName);

				return tempProperty.columnName;
			}

		});
		methodBuilder.addJavadoc("<pre>$L</pre>\n\n", sql);

		if (method.contentProviderUriVariables.size() > 0) {
			methodBuilder.addJavadoc("<h3>Path variables defined:</h3>\n<ul>\n");
			for (ContentUriPlaceHolder variable : method.contentProviderUriVariables) {
				methodBuilder.addJavadoc("<li><strong>$${$L}</strong> at path segment $L</li>\n", variable.value, variable.pathSegmentIndex);
			}
			methodBuilder.addJavadoc("</ul>\n\n");
		}

		if (!method.hasDynamicWhereConditions()) {
			methodBuilder.addJavadoc("<p><strong>Dynamic where statement is ignored, due no param with @$L was added.</strong></p>\n\n", BindSqlDynamicWhere.class.getSimpleName());
		}

		methodBuilder.addJavadoc("<p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>\n\n");
	}

	/**
	 * <p>
	 * Generate log for content values
	 * </p>
	 * 
	 * <h2>pre conditions</h2>
	 * <p>
	 * required variable are:
	 * </p>
	 * <ul>
	 * <li>contentValues</li>
	 * </ul>
	 * 
	 * <h2>post conditions</h2>
	 * <p>
	 * created variables are:</li>
	 * 
	 * 
	 * @param method
	 * @param methodBuilder
	 */
	public static void generateLogForContentValues(SQLiteModelMethod method, MethodSpec.Builder methodBuilder) {
		methodBuilder.addCode("\n// log for content values -- BEGIN\n");
		methodBuilder.addStatement("Object _contentValue");
		methodBuilder.beginControlFlow("for (String _contentKey:contentValues.keySet())");
		methodBuilder.addStatement("_contentValue=contentValues.get(_contentKey)");
		methodBuilder.beginControlFlow("if (_contentValue==null)");
		methodBuilder.addStatement("$T.info(\"==> :%s = <null>\", _contentKey)", Logger.class);
		methodBuilder.nextControlFlow("else");
		methodBuilder.addStatement("$T.info(\"==> :%s = '%s' (%s)\", _contentKey, $T.checkSize(_contentValue), _contentValue.getClass().getCanonicalName())", Logger.class, StringUtils.class);
		methodBuilder.endControlFlow();
		methodBuilder.endControlFlow();
		methodBuilder.addCode("// log for content values -- END\n");

	}

	/**
	 * <p>
	 * Generate log for where conditions.
	 * </p>
	 * 
	 * <h2>pre conditions</h2>
	 * <p>
	 * required variable are:
	 * </p>
	 * <ul>
	 * <li>_sqlWhereParams</li>
	 * </ul>
	 * 
	 * <h2>post conditions</h2>
	 * <p>
	 * created variables are:</li>
	 * <ul>
	 * <li>_whereParamCounter</li>
	 * </ul>
	 * 
	 * @param method
	 * @param methodBuilder
	 */
	public static void generateLogForWhereParameters(SQLiteModelMethod method, MethodSpec.Builder methodBuilder) {
		// manage log for where parameters
		if (method.getParent().getParent().generateLog) {
			methodBuilder.addCode("\n// log for where parameters -- BEGIN\n");
			methodBuilder.addStatement("int _whereParamCounter=0");
			methodBuilder.beginControlFlow("for (String _whereParamItem: _sqlWhereParams)");
			methodBuilder.addStatement("$T.info(\"==> param%s: '%s'\",(_whereParamCounter++), $T.checkSize(_whereParamItem))", Logger.class, StringUtils.class);
			methodBuilder.endControlFlow();
			methodBuilder.addCode("// log for where parameters -- END\n");
		}
	}

	/**
	 * <p>
	 * Generate log for where conditions.
	 * </p>
	 * 
	 * <h2>pre conditions</h2>
	 * <p>
	 * required variable are:
	 * </p>
	 * <ul>
	 * <li>_sqlBuilder</li>
	 * <li>_projectionBuffer</li> *
	 * </ul>
	 * 
	 * <h2>post conditions</h2>
	 * <p>
	 * created variables are:</li>
	 * <ul>
	 * <li>_sql</li>
	 * </ul>
	 * 
	 * @param method
	 * @param methodBuilder
	 */
	static void generateLogForSQL(SQLiteModelMethod method, MethodSpec.Builder methodBuilder) {
		// manage log
		if (method.getParent().getParent().generateLog) {
			methodBuilder.addCode("\n// manage log\n");
			methodBuilder.addStatement("String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString())");
			methodBuilder.addStatement("$T.info(_sql)", Logger.class);
		}
	}

	/**
	 * <p>
	 * Generate log info at beginning of method
	 * </p>
	 * 
	 * @param method
	 * @param methodBuilder
	 */
	static void generateLogForContentProviderBeginning(SQLiteModelMethod method, MethodSpec.Builder methodBuilder) {
		if (method.getParent().isLogEnabled()) {
			methodBuilder.addStatement("$T.info($S, uri.toString())", Logger.class, "Execute " + method.jql.operationType + " for URI %s");
		}

	}

	/**
	 * Iterate over methods.
	 * 
	 * @param elementUtils
	 * @param typeElement
	 * @param listener
	 */
	public static void forEachMethods(Elements elementUtils, TypeElement typeElement, MethodFoundListener listener) {
		List<? extends Element> list = elementUtils.getAllMembers(typeElement);

		for (Element item : list) {
			if (item.getKind() == ElementKind.METHOD) {
				listener.onMethod((ExecutableElement) item);
			}
		}
	}

	public static boolean hasParameterOfType(ModelMethod method, TypeName parameter) {
		return SqlBuilderHelper.countParameterOfType(method, parameter) > 0;
	}

	public static int countParameterOfType(ModelMethod method, TypeName parameter) {
		int counter = 0;
		for (Pair<String, TypeName> item : method.getParameters()) {
			if (item.value1.equals(parameter)) {
				counter++;
			}
		}

		return counter;
	}

	/**
	 * <p>
	 * Generate where management code.
	 * </p>
	 * 
	 * <h2>pre condition</h2>
	 * <dl>
	 * <dt>_sqlDynamicWhere</dt>
	 * <dd>dynamic where</dd>
	 * </dl>
	 * 
	 * <h2>post conditions</h2>
	 * <dl>
	 * <dt>_sqlWhereParams</dt>
	 * <dd>ArraList</dd>
	 * <dt>_sqlWhereStatement</dt>
	 * <dd>String</dd>
	 * </dl>
	 * 
	 * @param methodBuilder
	 * @param method
	 * @param jql
	 * @param jqlChecker
	 * @param sqlWhereStatement
	 */
	public static void generateWhereCondition(MethodSpec.Builder methodBuilder, final SQLiteModelMethod method, boolean sqlWhereParamsAlreadyDefined) {
		final JQL jql = method.jql;
		final JQLChecker jqlChecker = JQLChecker.getInstance();
		final SQLDaoDefinition daoDefinition = method.getParent();
		final SQLEntity entity = daoDefinition.getEntity();
		final SQLiteDatabaseSchema schema = daoDefinition.getParent();

		// we need always this
		if (!sqlWhereParamsAlreadyDefined) {
			methodBuilder.addStatement("$T<String> _sqlWhereParams=getWhereParamsArray()", ArrayList.class);
		}

		if (jql.isWhereConditions()) {
			// parameters extracted from query
			final One<String> whereStatement = new One<>();
			final One<Boolean> alreadyFoundWhereStatement = new One<>(false);

			// put in whereStatement value of where statement.
			jqlChecker.replaceVariableStatements(method.jql.value, new JQLReplaceVariableStatementListenerImpl() {

				@Override
				public String onWhere(String statement) {
					if (alreadyFoundWhereStatement.value0==false) {
						whereStatement.value0 = statement;
						alreadyFoundWhereStatement.value0=true;
						return "";
					} else {
						// DO NOTHING
						return null;						
					}
				}

			});

			methodBuilder.addCode("\n// manage WHERE arguments -- BEGIN\n");
			String sqlWhere = jqlChecker.replaceFromVariableStatement(whereStatement.value0, new JQLReplacerListenerImpl() {

				@Override
				public String onColumnName(String columnName) {
					SQLProperty tempProperty = entity.get(columnName);
					AssertKripton.assertTrueOrUnknownPropertyInJQLException(tempProperty != null, method, columnName);

					return tempProperty.columnName;
				}

				@Override
				public String onTableName(String tableName) {
					return schema.getEntityBySimpleName(tableName).getTableName();
				}

				@Override
				public String onDynamicSQL(JQLDynamicStatementType dynamicStatement) {
					return null;
				}

				@Override
				public String onBindParameter(String bindParameterName) {
					return "?";
				}
			});

			methodBuilder.addCode("\n// manage WHERE statement\n");
			String value = sqlWhere;
			String valueToReplace = jql.dynamicReplace.get(JQLDynamicStatementType.DYNAMIC_WHERE);

			if (method.jql.operationType == JQLType.SELECT) {
				// we have to include WHERE keywords
				if (jql.isStaticWhereConditions() && !jql.isDynamicWhereConditions()) {
					// case static statement and NO dynamic

					methodBuilder.addStatement("String _sqlWhereStatement=$S", value);
				} else if (jql.isStaticWhereConditions() && jql.isDynamicWhereConditions()) {
					methodBuilder.addStatement("String _sqlWhereStatement=$S+$T.ifNotEmptyAppend($L,\" $L \")", value.replace(valueToReplace, ""), StringUtils.class, "_sqlDynamicWhere",
							method.dynamicWherePrepend);
				} else if (!jql.isStaticWhereConditions() && jql.isDynamicWhereConditions()) {
					methodBuilder.addStatement("String _sqlWhereStatement=$T.ifNotEmptyAppend($L, \" $L \")", StringUtils.class, "_sqlDynamicWhere", JQLKeywords.WHERE_KEYWORD);
				}
			} else {
				// we DON'T have to include WHERE keywords
				value = value.replace(" " + JQLKeywords.WHERE_KEYWORD, "");
				if (jql.isStaticWhereConditions() && !jql.isDynamicWhereConditions()) {
					// case static statement and NO dynamic
					methodBuilder.addStatement("String _sqlWhereStatement=$S", value);
				} else if (jql.isStaticWhereConditions() && jql.isDynamicWhereConditions()) {
					methodBuilder.addStatement("String _sqlWhereStatement=$S+$T.ifNotEmptyAppend($L,\" $L \")", value.replace(valueToReplace, ""), StringUtils.class, "_sqlDynamicWhere",
							method.dynamicWherePrepend);
				} else if (!jql.isStaticWhereConditions() && jql.isDynamicWhereConditions()) {
					methodBuilder.addStatement("String _sqlWhereStatement=$T.ifNotEmptyAppend($L, \" \")", StringUtils.class, "_sqlDynamicWhere");
				}

			}

			methodBuilder.addStatement("_sqlBuilder.append($L)", "_sqlWhereStatement");
			methodBuilder.addCode("\n// manage WHERE arguments -- END\n");
		} else {
			// in every situation we need it
			methodBuilder.addStatement("String _sqlWhereStatement=\"\"");
		}

		// manage where arguments
		if (method.hasDynamicWhereConditions() && method.hasDynamicWhereArgs()) {
			// ASSERT: only with dynamic where conditions
			methodBuilder.beginControlFlow("if ($T.hasText(_sqlDynamicWhere) && _sqlDynamicWhereArgs!=null)", StringUtils.class);

			if (method.hasDynamicWhereConditions()) {
				methodBuilder.beginControlFlow("for (String _arg: _sqlDynamicWhereArgs)");
				methodBuilder.addStatement("_sqlWhereParams.add(_arg)");
				methodBuilder.endControlFlow();
			}

			methodBuilder.endControlFlow();
		}
	}

	/**
	 * <p>
	 * Generate log for INSERT operations
	 * </p>
	 * 
	 * @param method
	 * @param methodBuilder
	 */
	public static void generateLogForInsert(final SQLiteModelMethod method, MethodSpec.Builder methodBuilder) {
		SQLDaoDefinition daoDefinition = method.getParent();
		// log is enabled
		if (daoDefinition.isLogEnabled()) {
			methodBuilder.addCode("// log for insert -- BEGIN \n");
			methodBuilder.addStatement("$T _columnNameBuffer=new $T()", StringBuffer.class, StringBuffer.class);
			methodBuilder.addStatement("$T _columnValueBuffer=new $T()", StringBuffer.class, StringBuffer.class);
			methodBuilder.addStatement("String _columnSeparator=$S", "");

			SqlBuilderHelper.forEachColumnInContentValue(method, methodBuilder, "contentValues.keySet()", false, new OnColumnListener() {

				@Override
				public void onColumnCheck(MethodSpec.Builder methodBuilder, String columNameVariable) {
					methodBuilder.addStatement("_columnNameBuffer.append(_columnSeparator+$L)", columNameVariable);
					methodBuilder.addStatement("_columnValueBuffer.append(_columnSeparator+$S+$L)", ":", columNameVariable);
					methodBuilder.addStatement("_columnSeparator=$S", ", ");

				}
			});

			JQLChecker checker = JQLChecker.getInstance();

			// replace the table name, other pieces will be removed
			String sql = checker.replace(method.jql, new JQLReplacerListenerImpl() {

				@Override
				public String onTableName(String tableName) {

					return method.getParent().getEntity().getTableName();
				}

			});

			sql = checker.replaceVariableStatements(sql, new JQLReplaceVariableStatementListenerImpl() {

				@Override
				public String onColumnNameSet(String statement) {
					return "%s";
				}

				@Override
				public String onColumnValueSet(String statement) {
					return "%s";
				}
			});

			methodBuilder.addStatement("$T.info($S, _columnNameBuffer.toString(), _columnValueBuffer.toString())", Logger.class, sql);
			generateLogForContentValues(method, methodBuilder);

			methodBuilder.addCode("// log for insert -- END \n\n");
		}

	}

}
