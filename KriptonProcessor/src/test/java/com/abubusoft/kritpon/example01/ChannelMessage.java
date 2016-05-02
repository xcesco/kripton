package com.abubusoft.kritpon.example01;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.binder.database.ColumnType;

@BindType
@BindAllFields
public class ChannelMessage extends UidEntity {

	private static final long serialVersionUID = -2411765210163916759L;
	
	@Bind
	@BindColumn(ColumnType.PRIMARY_KEY)
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

	@Bind
	protected String text;

	protected String type;

	@Bind
	protected String ownerUid;

	@Bind
	protected long updateTime;

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public String getOwnerUid() {
		return ownerUid;
	}

	public void setOwnerUid(String ownerUid) {
		this.ownerUid = ownerUid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "ChannelMessage [text=" + text + ", type=" + type + ", ownerUid=" + ownerUid + ", updateTime=" + updateTime
				+ "]";
	}




}
