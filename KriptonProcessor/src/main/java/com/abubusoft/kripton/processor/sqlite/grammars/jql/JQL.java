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

import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;

public class JQL {

	public enum JQLType {
		SELECT, INSERT, UPDATE, DELETE
	}

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
	 * <code>true</code> if method's annotation contains <code>where</code>
	 * attribute
	 */
	public boolean annotatedHaving;

	/**
	 * <code>true</code> if method's annotation contains <code>where</code>
	 * attribute
	 */
	public boolean annotatedOrderBy;

	/**
	 * <code>true</code> if method's annotation contains <code>where</code>
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
