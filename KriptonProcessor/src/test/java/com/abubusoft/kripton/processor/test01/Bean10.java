package com.abubusoft.kripton.processor.test01;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindAllFields
public class Bean10 {
	
	protected Long id;
	@BindColumn(value=ColumnType.PRIMARY_KEY)
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

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
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
