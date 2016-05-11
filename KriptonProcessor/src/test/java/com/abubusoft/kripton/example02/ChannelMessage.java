package com.abubusoft.kripton.example02;

import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.binder.database.ColumnType;

@BindType
@BindAllFields
public class ChannelMessage extends UidEntity {

	private static final long serialVersionUID = -2411765210163916759L;
	
	@BindColumn(ColumnType.PRIMARY_KEY)
	protected long id;
	
	protected String text;

	protected long number;

	public String getValore() {
		return valore;
	}

	public void setValore(String valore) {
		this.valore = valore;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	protected String valore;




}
