package com.abubusoft.kripton.processor.core;

import java.util.Map;

public class ModelAnnotation {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModelAnnotation [");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (attributes != null) {
			builder.append("attributes=");
			builder.append(attributes);
		}
		builder.append("]");
		return builder.toString();
	}

	protected String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	protected Map<String, String> attributes;

	/**
	 * @return the attributes
	 */
	public Map<String, String> getAttributes() {
		return attributes;
	}

	public ModelAnnotation(String name, Map<String, String> attributes) {
		this.name = name;
		this.attributes = attributes;
	}

	public String getAttribute(String key) {
		return attributes.get(key);
	}

}
