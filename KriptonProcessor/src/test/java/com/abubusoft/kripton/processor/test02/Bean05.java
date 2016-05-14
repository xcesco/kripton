package com.abubusoft.kripton.processor.test02;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Bean05 {

	@Bind
	protected long id;
	
	@Bind
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

	/**
	 * @return the value
	 */
	public long getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(long value) {
		this.value = value;
	}

	protected long value;

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
}
