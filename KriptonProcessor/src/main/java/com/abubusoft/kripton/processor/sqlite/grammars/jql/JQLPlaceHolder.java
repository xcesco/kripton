package com.abubusoft.kripton.processor.sqlite.grammars.jql;

/**
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class JQLPlaceHolder {
	
	public JQLPlaceHolderType type;
	
	public String value;
	
	/**
	 * if <code>true</code>, means parameter used an attribute of parameter. If <code>false</code>, parameter is used directly
	 *  
	 */
	public boolean composed;
	
	public JQLPlaceHolder(JQLPlaceHolderType type, String value) {
		this.value=value;
		this.composed=value.indexOf(".")>=0;
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

	public enum JQLPlaceHolderType {
		PARAMETER,		
		DYNAMIC_SQL				 
	}

}
