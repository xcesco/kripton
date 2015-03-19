package com.abubusoft.kripton.examples;

import com.abubusoft.kripton.annotation.BindAllFields;

@BindAllFields
public class SimpleBean {

	private long age;

	private String name;

	public long getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public void setAge(long age) {
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}
}
