/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
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

import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL.JQLDynamicStatementType;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Column_name_setContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Column_value_setContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Where_stmtContext;

/**
 * The listener interface for receiving JQLReplacer events.
 * The class that is interested in processing a JQLReplacer
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addJQLReplacerListener</code> method. When
 * the JQLReplacer event occurs, that object's appropriate
 * method is invoked.
 *
 */
public interface JQLReplacerListener {
	
	/**
	 * If event return null, replacement will no executed.
	 *
	 * @param bindParameterName the bind parameter name
	 * @return 		<code>null</code> to avoid replacement.
	 */
	String onBindParameter(String bindParameterName);

	/**
	 * If event return null, replacement will no executed.
	 *
	 * @param dynamicStatement the dynamic statement
	 * @return 		<code>null</code> to avoid replacement.
	 */
	String onDynamicSQL(JQLDynamicStatementType dynamicStatement);
	

	/**
	 * If event return null, replacement will no executed.
	 *
	 * @param tableName the table name
	 * @return 		<code>null</code> to avoid replacement.
	 */
	String onTableName(String tableName);

	/**
	 * On simple column name.
	 * If event return null, replacement will no executed.
	 *
	 * @param columnName the column name
	 * @return 		<code>null</code> to avoid replacement.
	 */
	String onColumnName(String columnName);
	
	/**
	 * On full qualified column name.
	 *
	 * @param tableName the table name
	 * @param columnName the column name
	 * @return the string
	 */
	String onColumnFullyQualifiedName(String tableName, String columnName);
	
	/**
	 * If event return null, replacement will no executed.
	 *
	 * @param columnName the column name
	 * @return 		<code>null</code> to avoid replacement.
	 */
	String onColumnNameToUpdate(String columnName);
	
	/**
	 * On where statement begin.
	 *
	 * @param ctx the ctx
	 */
	void onWhereStatementBegin(Where_stmtContext ctx);

	/**
	 * On where statement end.
	 *
	 * @param ctx the ctx
	 */
	void onWhereStatementEnd(Where_stmtContext ctx);

	/**
	 * On column name set begin.
	 *
	 * @param ctx the ctx
	 */
	void onColumnNameSetBegin(Column_name_setContext ctx);

	/**
	 * On column name set end.
	 *
	 * @param ctx the ctx
	 */
	void onColumnNameSetEnd(Column_name_setContext ctx);

	/**
	 * On column value set begin.
	 *
	 * @param ctx the ctx
	 */
	void onColumnValueSetBegin(Column_value_setContext ctx);

	/**
	 * On column value set end.
	 *
	 * @param ctx the ctx
	 */
	void onColumnValueSetEnd(Column_value_setContext ctx);
}
