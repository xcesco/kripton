/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.processor.sqlite.grammars.jql;

import java.util.Map;

import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;

public class JQL {

	public enum JQLType {
		SELECT, INSERT, UPDATE, DELETE
	}

	public enum JQLDynamicStatementType {
		DYNAMIC_WHERE, DYNAMIC_ORDER_BY, DYNAMIC_PAGE_SIZE, DYNAMIC_PAGE_OFFSET
	}
	
	/**
	 * Specificies how jql is defined. 
	 */
	public enum JQLDeclarationType {
		/**
		 * jql is defined with jql attribute
		 */
		JQL_EXPLICIT,
		
		/**
		 * jql is not defined explicity, it's gained from attributes
		 */
		JQL_COMPACT;
	}
	
	public JQLDeclarationType declarationType=JQLDeclarationType.JQL_COMPACT;

	/**
	 * jql type
	 */
	public JQLType operationType;

	/**
	 * JQL statement
	 */
	public String value;

	/**
	 * <code>true</code> if method's annotation contains <code>where</code>
	 * attribute
	 */
	public boolean annotatedWhere;

	/**
	 * <code>true</code> if method's annotation contains <code>where</code>
	 * attribute
	 */
	public boolean annotatedGroupBy;

	/**
	 * <code>true</code> if method's annotation contains <code>having</code>
	 * attribute
	 */
	public boolean annotatedHaving;

	/**
	 * <code>true</code> if method's annotation contains <code>where</code>
	 * attribute
	 */
	public boolean annotatedOrderBy;

	/**
	 * <code>true</code> if method's annotation contains <code>page</code>
	 * attribute
	 */
	public boolean annotatedPageSize;

	public String paramReadBeanListener;

	public String paramReadBeanCursor;

	/**
	 * parameter's name of managed type of the dao
	 */
	public String paramBean;

	public String paramPageSize;

	public String paramOrderBy;

	public Map<JQLDynamicStatementType, String> dynamicReplace;

	boolean staticOrderBy;

	public boolean isStaticOrderBy() {
		return staticOrderBy;
	}

	boolean staticWhereConditions;

	public boolean annotatedOffset;

	public boolean annotatedLimit;

	/**
	 * States that operation is of type INSERT ... SELECT (..) or UPDATE-SELECT-IN. It's a
	 * particular type of INSERT that need to be managed in a specific way. This
	 * kind of INSERT can not be used for content provider methods.
	 */
	public boolean containsSelectOperation = false;
		
	/**
	 * used only for INSERT and UPDATE operation
	 */
	public ConflictAlgorithmType conflictAlgorithmType=ConflictAlgorithmType.NONE;
	
	/**
	 * counter of binded parameter on column value. Typically it incremented by bind parameter used on column values (INSERT SQL).
	 */
	public int bindParameterAsColumnValueCounter=0;
	
	/**
	 * counter of binded parameter in where condition (INSERT-SELECT, SELECT, DELETE, UPDATE).
	 */
	public int bindParameterOnWhereStatementCounter=0;

	/**
	 * if <code>true</code> states that JQL has a static WHERE statement.
	 * 
	 * @return if <code>true</code> states that JQL has a static WHERE
	 *         statement.
	 */
	public boolean isStaticWhereConditions() {
		return staticWhereConditions;
	}

	public boolean isDynamicOrderBy() {
		return dynamicReplace.containsKey(JQLDynamicStatementType.DYNAMIC_ORDER_BY);
	}
	
	public boolean hasDynamicParts() {
		return dynamicReplace.size()>0;
	}

	public boolean isOrderBy() {
		return isStaticOrderBy() || isDynamicOrderBy();
	}

	/**
	 * if <code>true</code> states that JQL has a dynamic WHERE statement.
	 * 
	 * @return if <code>true</code> states that JQL has a dynamic WHERE
	 *         statement.
	 */
	public boolean isDynamicWhereConditions() {
		return dynamicReplace.containsKey(JQLDynamicStatementType.DYNAMIC_WHERE);
	}

	/**
	 * if <code>true</code> states that JQL has a WHERE statement, static or
	 * dynamic.
	 * 
	 * @return if <code>true</code> states that JQL has a WHERE statement,
	 *         static or dynamic.
	 */
	public boolean isWhereConditions() {
		return isStaticWhereConditions() || isDynamicWhereConditions();
	}

	public boolean hasParamReadBeanListener() {
		return paramReadBeanListener != null;
	}

	public boolean hasParamReadBeanCursor() {
		return paramReadBeanCursor != null;
	}

	public boolean hasParamBean() {
		return paramBean != null;
	}

	public boolean hasParamPageSize() {
		return paramPageSize != null;
	}

	public boolean hasParamOrderBy() {
		return paramOrderBy != null;
	}

}
