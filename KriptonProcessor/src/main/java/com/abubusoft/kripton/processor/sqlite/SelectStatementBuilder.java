/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.processor.sqlite;

import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;

public class SelectStatementBuilder {

	public static SelectStatementBuilder create() {
		return new SelectStatementBuilder();
	}

	protected boolean distinct;

	protected String where;

	protected String fields;

	protected String table;

	protected String having;

	protected String groupBy;

	protected String orderBy;

	public SelectStatementBuilder distinct(boolean value) {
		distinct = value;
		return this;
	}

	public SelectStatementBuilder table(String value) {
		table = value;
		return this;
	}

	public SelectStatementBuilder where(String value) {
		where = value;
		return this;
	}

	public SelectStatementBuilder fields(String value) {
		fields = value;
		return this;
	}

	public SelectStatementBuilder having(String value) {
		having = value;
		return this;
	}

	public SelectStatementBuilder groupBy(String value) {
		groupBy = value;
		return this;
	}

	public SelectStatementBuilder orderBy(String value) {
		orderBy = value;
		return this;
	}

	public String build(SQLiteModelMethod method) {
		return buildInternal(method, "format");
	}
	
	public String buildForLog(SQLiteModelMethod method) {
		return buildInternal(method, "formatForLog");
	}

	private String buildInternal(SQLiteModelMethod method, String nvlFunction) {
		StringBuilder buffer = new StringBuilder();

		buffer.append("SELECT ");
		if (distinct)
			buffer.append("DISTINCT ");
		buffer.append(fields);
		buffer.append(" FROM " + table);

		if (StringUtils.hasText(where) || method.hasDynamicWhereConditions()) {
			buffer.append(" WHERE");
			if (StringUtils.hasText(where))
				buffer.append(" "+where.trim());
			if (method.hasDynamicWhereConditions()) {
				buffer.append(" \"+StringUtils."+nvlFunction+"(" + method.dynamicWhereParameterName + ")+\"");
			}
		}

		if (StringUtils.hasText(having))
			buffer.append(" HAVING " + having.trim());
		if (StringUtils.hasText(groupBy))
			buffer.append(" GROUP BY " + groupBy.trim());
		if (StringUtils.hasText(orderBy))
			buffer.append(" ORDER BY " + orderBy.trim());
		if (method.hasDynamicOrderByConditions()) {
			buffer.append("\"" + method.dynamicOrderByParameterName + "\"");
		}

		return buffer.toString();
	}
}
