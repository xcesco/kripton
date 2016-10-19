package com.abubusoft.kripton.processor.core;

import java.util.Map;

import com.abubusoft.kripton.processor.sqlite.model.AnnotationAttributeType;

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

	public String getAttribute(AnnotationAttributeType attribute) {
		return attributes.get(attribute.getValue());
	}

	public String getSimpleName() {
		return name.substring(name.lastIndexOf(".")+1);
	}

}
