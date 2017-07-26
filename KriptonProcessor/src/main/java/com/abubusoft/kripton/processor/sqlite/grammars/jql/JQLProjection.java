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

import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;

public class JQLProjection  {

	public enum ProjectionType {
		COMPLEX,
		COLUMN,
		STAR
	}
	
	public static class ProjectionBuilder {
		
		ProjectionType type;
		
		String alias;
		
		String column;
		
		String table;
		
		String expression;
		
		SQLProperty property;
		
		public static ProjectionBuilder create() {
			return new ProjectionBuilder();
		}
		
		public ProjectionBuilder type(ProjectionType value) {
			type=value;
			return this;
		}
		
		public ProjectionBuilder alias(String value) {
			alias=value;
			return this;
		}
		
		public ProjectionBuilder table(String value) {
			table=value;
			return this;
		}
		
		public ProjectionBuilder column(String value) {
			column=value;
			return this;
		}
		
		public ProjectionBuilder expression(String value) {
			this.expression=value;
			
			return this;			
		}
		
		public ProjectionBuilder property(SQLProperty property) {
			this.property=property;	
			return this;			
		}
		
		public JQLProjection build()
		{
			return new JQLProjection(type, table, column, alias, expression, property);
		}

		
	}

	public SQLProperty property;
	
	public JQLProjection(ProjectionType type,  String table, String column, String alias, String expression, SQLProperty property) {
		this.type=type;
		this.table=table;
		this.column=column;
		this.alias=alias;
		this.expression=expression;
		this.property=property;
	}
	
	public ProjectionType type;
	
	public String alias;
	
	public String column;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		result = prime * result + ((column == null) ? 0 : column.hashCode());
		result = prime * result + ((expression == null) ? 0 : expression.hashCode());
		result = prime * result + ((property == null) ? 0 : property.hashCode());
		result = prime * result + ((table == null) ? 0 : table.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JQLProjection other = (JQLProjection) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		if (column == null) {
			if (other.column != null)
				return false;
		} else if (!column.equals(other.column))
			return false;
		if (expression == null) {
			if (other.expression != null)
				return false;
		} else if (!expression.equals(other.expression))
			return false;
		if (property == null) {
			if (other.property != null)
				return false;
		} else if (!property.equals(other.property))
			return false;
		if (table == null) {
			if (other.table != null)
				return false;
		} else if (!table.equals(other.table))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	public String table;
	
	public String expression;


}
