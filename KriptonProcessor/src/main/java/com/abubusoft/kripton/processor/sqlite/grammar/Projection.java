package com.abubusoft.kripton.processor.sqlite.grammar;

public class Projection  {

	public enum ProjectionType {
		COMPLEX,
		COLUMN
	}
	
	public static class ProjectionBuilder {
		
		ProjectionType type;
		
		String alias;
		
		String column;
		
		String table;
		
		String expression;
		
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
		
		public Projection build()
		{
			return new Projection(type, table, column, alias, expression);
		}

		
	}
	
	public Projection(ProjectionType type,  String table, String column, String alias, String expression) {
		this.type=type;
		this.table=table;
		this.column=column;
		this.alias=alias;
		this.expression=expression;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Projection [");
		if (type != null) {
			builder.append("type=");
			builder.append(type);
			builder.append(", ");
		}
		if (alias != null) {
			builder.append("alias=");
			builder.append(alias);
			builder.append(", ");
		}
		if (column != null) {
			builder.append("column=");
			builder.append(column);
			builder.append(", ");
		}
		if (table != null) {
			builder.append("table=");
			builder.append(table);
			builder.append(", ");
		}
		if (expression != null) {
			builder.append("expression=");
			builder.append(expression);
		}
		builder.append("]");
		return builder.toString();
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
		Projection other = (Projection) obj;
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
