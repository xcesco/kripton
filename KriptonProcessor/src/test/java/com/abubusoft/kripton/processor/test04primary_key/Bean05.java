package com.abubusoft.kripton.processor.test04primary_key;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Bean05 {

	@Bind
	@BindColumn(ColumnType.PRIMARY_KEY)
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
	
	@Bind
	@BindColumn
	protected String text;
	
	@Bind
	@BindColumn
	protected byte[] content;

	/**
	 * @return the content
	 */
	public byte[] getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(byte[] content) {
		this.content = content;
	}

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
