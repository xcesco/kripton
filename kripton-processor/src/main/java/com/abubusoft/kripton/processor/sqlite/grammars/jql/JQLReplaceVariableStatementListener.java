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

// TODO: Auto-generated Javadoc
/**
 * Listener to replace variable parts of a sql statement. 
 * 
 * If event return null, replacement will no executed.
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public interface JQLReplaceVariableStatementListener {

	/**
	 * If event return null, replacement will no executed.
	 *
	 * @param statement the statement
	 * @return 		<code>null</code> to avoid replacement.
	 */
	String onWhere(String statement);
	
	/**
	 * If event return null, replacement will no executed.
	 *
	 * @param statement the statement
	 * @return 		<code>null</code> to avoid replacement.
	 */
	String onOrderBy(String statement);

	/**
	 * If event return null, replacement will no executed.
	 *
	 * @param statement the statement
	 * @return 		<code>null</code> to avoid replacement.
	 */
	String onLimit(String statement);

	/**
	 * If event return null, replacement will no executed.
	 *
	 * @param statement the statement
	 * @return 		<code>null</code> to avoid replacement.
	 */
	String onOffset(String statement);

	/**
	 * If event return null, replacement will no executed.
	 *
	 * @param statement the statement
	 * @return 		<code>null</code> to avoid replacement.
	 */
	String onGroup(String statement);

	/**
	 * If event return null, replacement will no executed.
	 *
	 * @param statement the statement
	 * @return 		<code>null</code> to avoid replacement.
	 */
	String onHaving(String statement);

	/**
	 * If event return null, replacement will no executed.
	 *
	 * @param statement the statement
	 * @return 		<code>null</code> to avoid replacement.
	 */
	String onProjectedColumns(String statement);

	/**
	 * If event return null, replacement will no executed.
	 *
	 * @param statement the statement
	 * @return 		<code>null</code> to avoid replacement.
	 */
	String onColumnNameSet(String statement);
	
	/**
	 * If event return null, replacement will no executed.
	 *
	 * @param statement the statement
	 * @return 		<code>null</code> to avoid replacement.
	 */
	String onColumnValueSet(String statement);

}
