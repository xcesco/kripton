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

	public String getAttribute(String attributeName) {
		return attributes.get(attributeName);
	}
	
	public String getAttribute(AnnotationAttributeType attribute) {
		return attributes.get(attribute.getValue());
	}


	public String getSimpleName() {
		return name.substring(name.lastIndexOf(".")+1);
	}

}
