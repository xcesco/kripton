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

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;


/**
 * The Class JQL.
 */
public class JQL {

	/**
	 * The Enum JQLType.
	 */
	public enum JQLType {

		/** The select. */
		SELECT,
		/** The insert. */
		INSERT,
		/** The update. */
		UPDATE,
		/** The delete. */
		DELETE
	}

	/**
	 * The Enum JQLDynamicStatementType.
	 */
	public enum JQLDynamicStatementType {

		/** The dynamic where. */
		DYNAMIC_WHERE,
		/** The dynamic order by. */
		DYNAMIC_ORDER_BY,
		/** The dynamic page size. */
		DYNAMIC_PAGE_SIZE,
		/** The dynamic page offset. */
		DYNAMIC_PAGE_OFFSET,
		/**
		 * States for query parameters that are used as arrays
		 */
		DYNAMIC_SPREAD
	}

	/**
	 * Specificies how jql is defined.
	 */
	public enum JQLDeclarationType {

		/** jql is defined with jql attribute. */
		JQL_EXPLICIT,

		/** jql is not defined explicity, it's gained from attributes. */
		JQL_COMPACT;
	}

	/** The declaration type. */
	public JQLDeclarationType declarationType = JQLDeclarationType.JQL_COMPACT;

	/** jql type. */
	public JQLType operationType;

	/** JQL statement. */
	public String value;

	/**
	 * <code>true</code> if method's annotation contains <code>where</code>
	 * attribute.
	 */
	public boolean annotatedWhere;

	/**
	 * <code>true</code> if method's annotation contains <code>where</code>
	 * attribute.
	 */
	public boolean annotatedGroupBy;

	/**
	 * <code>true</code> if method's annotation contains <code>having</code>
	 * attribute.
	 */
	public boolean annotatedHaving;

	/**
	 * <code>true</code> if method's annotation contains <code>where</code>
	 * attribute.
	 */
	public boolean annotatedOrderBy;

	/**
	 * <code>true</code> if method's annotation contains <code>page</code>
	 * attribute.
	 */
	public boolean annotatedPageSize;

	/** The param read bean listener. */
	public String paramReadBeanListener;

	/** The param read bean cursor. */
	public String paramReadBeanCursor;

	/** parameter's name of managed type of the dao. */
	public String paramBean;

	/** The param page size. */
	public String paramPageSize;

	/** The param order by. */
	public String paramOrderBy;

	/** The dynamic replace. */
	public Map<JQLDynamicStatementType, String> dynamicReplace;
	
	public Set<String> spreadParams=new HashSet<String>();

	/** The static order by. */
	boolean staticOrderBy;

	/**
	 * Checks if is static order by.
	 *
	 * @return true, if is static order by
	 */
	public boolean isStaticOrderBy() {
		return staticOrderBy;
	}

	/** The static where conditions. */
	boolean staticWhereConditions;

	/** The annotated offset. */
	public boolean annotatedOffset;

	/** The annotated limit. */
	public boolean annotatedLimit;

	/**
	 * States that operation is of type INSERT ... SELECT (..) or
	 * UPDATE-SELECT-IN. It's a particular type of INSERT that need to be
	 * managed in a specific way. This kind of INSERT can not be used for
	 * content provider methods.
	 */
	public boolean containsSelectOperation = false;

	/** used only for INSERT and UPDATE operation. */
	public ConflictAlgorithmType conflictAlgorithmType = ConflictAlgorithmType.NONE;

	/**
	 * counter of binded parameter on column value. Typically it incremented by
	 * bind parameter used on column values (INSERT SQL).
	 */
	public int bindParameterAsColumnValueCounter = 0;

	/**
	 * counter of binded parameter in where condition (INSERT-SELECT, SELECT,
	 * DELETE, UPDATE).
	 */
	public int bindParameterOnWhereStatementCounter = 0;

	/**
	 * if <code>true</code> states that JQL has a static WHERE statement.
	 * 
	 * @return if <code>true</code> states that JQL has a static WHERE
	 *         statement.
	 */
	public boolean isStaticWhereConditions() {
		return staticWhereConditions;
	}

	/**
	 * Checks if is dynamic order by.
	 *
	 * @return true, if is dynamic order by
	 */
	public boolean isDynamicOrderBy() {
		return dynamicReplace.containsKey(JQLDynamicStatementType.DYNAMIC_ORDER_BY);
	}

	/**
	 * Checks for dynamic parts.
	 *
	 * @return true, if successful
	 */
	public boolean hasDynamicParts() {
		return dynamicReplace.size() > 0;
	}

	/**
	 * Checks if is order by.
	 *
	 * @return true, if is order by
	 */
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
	 * if <code>true</code> states that JQL has dynamic arguments managed with spread operator.
	 * 
	 * @return if <code>true</code> states that JQL has a dynamic parameter managed with spread operator
	 *         
	 */
	public boolean isDynamicSpreadConditions() {
		return dynamicReplace.containsKey(JQLDynamicStatementType.DYNAMIC_SPREAD);
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

	/**
	 * Checks for param read bean listener.
	 *
	 * @return true, if successful
	 */
	public boolean hasParamReadBeanListener() {
		return paramReadBeanListener != null;
	}

	/**
	 * Checks for param read bean cursor.
	 *
	 * @return true, if successful
	 */
	public boolean hasParamReadBeanCursor() {
		return paramReadBeanCursor != null;
	}

	/**
	 * Checks for param bean.
	 *
	 * @return true, if successful
	 */
	public boolean hasParamBean() {
		return paramBean != null;
	}

	/**
	 * Checks for param page size.
	 *
	 * @return true, if successful
	 */
	public boolean hasParamPageSize() {
		return paramPageSize != null;
	}

	/**
	 * Checks for param order by.
	 *
	 * @return true, if successful
	 */
	public boolean hasParamOrderBy() {
		return paramOrderBy != null;
	}

}
