package com.abubusoft.kripton.processor;

import java.util.List;

import com.abubusoft.kripton.annotation.BindTypeBundle;

@BindTypeBundle
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
