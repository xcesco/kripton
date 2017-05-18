package com.abubusoft.kripton.processor.sqlite.grammar;

/**
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class Parameter {
	
	public ParameterType type;
	
	public String value;
	
	public Parameter(ParameterType type, String value) {
		this.value=value;
		this.type=type;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Parameter [");
		if (type != null) {
			builder.append("type=");
			builder.append(type);
			builder.append(", ");
		}
		if (value != null) {
			builder.append("value=");
			builder.append(value);
		}
		builder.append("]");
		return builder.toString();
	}

	public enum ParameterType {
		PARAMETER_SIMPLE, PARAMETER_INNER_FIELD,
		DYNAMIC_SQL_SIMPLE, DYNAMIC_SQL_INNER_FIELD
		 
	}

}
