package com.abubusoft.kripton.processor.kripton38;

import java.util.Date;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Bean05 {

	@Bind
	@BindColumn(ColumnType.PRIMARY_KEY)
	protected long pk;
	
	@Bind
	@BindColumn
	protected long number;
	
	/**
	 * @return the number
	 */
	public long getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(long number) {
		this.number = number;
	}

	@Bind
	@BindColumn
	protected BeanType beanType;

	/**
	 * @return the beanType
	 */
	public BeanType getBeanType() {
		return beanType;
	}

	/**
	 * @param beanType the beanType to set
	 */
	public void setBeanType(BeanType beanType) {
		this.beanType = beanType;
	}

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
	
	@Bind
	@BindColumn
	protected Date creationTime;

	/**
	 * @return the creationTime
	 */
	public Date getCreationTime() {
		return creationTime;
	}

	/**
	 * @param creationTime the creationTime to set
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

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
