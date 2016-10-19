package com.abubusoft.kripton.processor.kripton48.entities;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Bean02 {

	protected long id;


	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	protected String text;

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

}
