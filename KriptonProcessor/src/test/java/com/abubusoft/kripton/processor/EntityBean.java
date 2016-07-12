package com.abubusoft.kripton.processor;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class EntityBean {



	protected String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
