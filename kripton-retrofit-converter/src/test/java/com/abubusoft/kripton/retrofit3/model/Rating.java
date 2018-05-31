package com.abubusoft.kripton.retrofit3.model;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Rating {
	private String Source;

	public String getSource() {
		return this.Source;
	}

	public void setSource(String Source) {
		this.Source = Source;
	}

	private String Value;

	public String getValue() {
		return this.Value;
	}

	public void setValue(String Value) {
		this.Value = Value;
	}
}
