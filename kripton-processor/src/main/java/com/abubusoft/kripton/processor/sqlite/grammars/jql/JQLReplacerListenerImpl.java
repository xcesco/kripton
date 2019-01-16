/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.abubusoft.kripton.processor.sqlite.grammars.jql;

import java.util.Set;

import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.Finder;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL.JQLDynamicStatementType;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Column_name_setContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Column_value_setContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Where_stmtContext;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;

/**
 * The Class JQLReplacerListenerImpl.
 */
public class JQLReplacerListenerImpl implements JQLReplacerListener {

	/** The current method. */
	protected SQLiteModelMethod currentMethod;

	/** The current dao definition. */
	protected SQLiteDaoDefinition currentDaoDefinition;

	/** The current schema. */
	protected SQLiteDatabaseSchema currentSchema;

	/** The current entity. */
	protected SQLiteEntity currentEntity;

	private boolean onlyBindParameter = false;

	public JQLReplacerListenerImpl(SQLiteModelMethod method) {
		this(method, false);
	}

	/**
	 * Instantiates a new JQL replacer listener impl.
	 *
	 * @param method
	 *            the method
	 */
	public JQLReplacerListenerImpl(SQLiteModelMethod method, boolean skipTest) {
		this.onlyBindParameter = skipTest;
		this.currentMethod = method;
		if (method != null) {
			this.currentDaoDefinition = method.getParent();
			this.currentSchema = this.currentDaoDefinition.getParent();
			this.currentEntity = method.getEntity();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListener#
	 * onBindParameter(java.lang.String)
	 */
	@Override
	public String onBindParameter(String bindParameterName, boolean inStatement) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListener#
	 * onDynamicSQL(com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL.
	 * JQLDynamicStatementType)
	 */
	@Override
	public String onDynamicSQL(JQLDynamicStatementType dynamicStatement) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListener#
	 * onTableName(java.lang.String)
	 */
	@Override
	public String onTableName(String tableName) {
		if (onlyBindParameter)
			return null;

		if (currentSchema != null) {
			Finder<SQLProperty> finder = currentSchema.getEntityBySimpleName(tableName);
			AssertKripton.assertTrueOrUnknownClassInJQLException(finder != null, currentMethod, tableName);
			return finder.getTableName();
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListener#
	 * onColumnName(java.lang.String)
	 */
	@Override
	public String onColumnName(String columnName) {
		if (onlyBindParameter)
			return null;

		if (currentSchema != null) {
			Set<SQLProperty> props = currentSchema.getPropertyBySimpleName(columnName);
			AssertKripton.assertTrueOrUnknownPropertyInJQLException(props != null && props.size() > 0, currentMethod, columnName);
			return props.iterator().next().columnName;
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListener#
	 * onColumnNameToUpdate(java.lang.String)
	 */
	@Override
	public String onColumnNameToUpdate(String columnName) {
		if (onlyBindParameter)
			return null;

		if (currentSchema != null) {
			Set<SQLProperty> props = currentSchema.getPropertyBySimpleName(columnName);
			AssertKripton.assertTrueOrUnknownPropertyInJQLException(props != null && props.size() > 0, currentMethod, columnName);
			return props.iterator().next().columnName;
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListener#
	 * onWhereStatementBegin(com.abubusoft.kripton.processor.sqlite.grammars.
	 * jsql.JqlParser.Where_stmtContext)
	 */
	@Override
	public void onWhereStatementBegin(Where_stmtContext ctx) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListener#
	 * onWhereStatementEnd(com.abubusoft.kripton.processor.sqlite.grammars.jsql.
	 * JqlParser.Where_stmtContext)
	 */
	@Override
	public void onWhereStatementEnd(Where_stmtContext ctx) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListener#
	 * onColumnNameSetBegin(com.abubusoft.kripton.processor.sqlite.grammars.jsql
	 * .JqlParser.Column_name_setContext)
	 */
	@Override
	public void onColumnNameSetBegin(Column_name_setContext ctx) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListener#
	 * onColumnNameSetEnd(com.abubusoft.kripton.processor.sqlite.grammars.jsql.
	 * JqlParser.Column_name_setContext)
	 */
	@Override
	public void onColumnNameSetEnd(Column_name_setContext ctx) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListener#
	 * onColumnValueSetBegin(com.abubusoft.kripton.processor.sqlite.grammars.
	 * jsql.JqlParser.Column_value_setContext)
	 */
	@Override
	public void onColumnValueSetBegin(Column_value_setContext ctx) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListener#
	 * onColumnValueSetEnd(com.abubusoft.kripton.processor.sqlite.grammars.jsql.
	 * JqlParser.Column_value_setContext)
	 */
	@Override
	public void onColumnValueSetEnd(Column_value_setContext ctx) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListener#
	 * onColumnFullyQualifiedName(java.lang.String, java.lang.String)
	 */
	@Override
	public String onColumnFullyQualifiedName(String tableName, String columnName) {
		if (onlyBindParameter)
			return null;

		return JQLReplacerListenerImpl.resolveFullyQualifiedColumnName(currentSchema, currentMethod, tableName, columnName);
	}

	/**
	 * given a fully qualified property name, it will be transformed in
	 * associated column name. If class or property does not exist, an exception
	 * will be thrown
	 *
	 * @param schema
	 *            the schema
	 * @param method
	 *            the method
	 * @param className
	 *            the class name
	 * @param columnName
	 *            the property name
	 * @return resolved name ex: "person.birth_date"
	 */
	public static String resolveFullyQualifiedColumnName(SQLiteDatabaseSchema schema, SQLiteModelMethod method, String className, String columnName) {
		Finder<SQLProperty> currentEntity = method.getEntity();
		if (StringUtils.hasText(className)) {
			currentEntity = schema.getEntityBySimpleName(className);
			AssertKripton.assertTrueOrUnknownClassInJQLException(currentEntity != null, method, className);
		}

		SQLProperty currentProperty = currentEntity.findPropertyByName(columnName);
		AssertKripton.assertTrueOrUnknownPropertyInJQLException(currentProperty != null, method, columnName);

		return (StringUtils.hasText(className) ? currentEntity.getTableName() + "." : "") + currentProperty.columnName;
	}

	@Override
	public String onColumnAlias(String text) {
		return null;
	}

}
