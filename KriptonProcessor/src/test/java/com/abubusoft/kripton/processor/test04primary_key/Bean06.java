package com.abubusoft.kripton.processor.test04primary_key;

import com.abubusoft.kripton.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Bean06 {

	protected long pk;

	/**
	 * @return the pk
	 */
	public long getPk() {
		return pk;
	}

	/**
	 * @param pk the pk to set
	 */
	public void setPk(long pk) {
		this.pk = pk;
	}

	@BindColumn
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
