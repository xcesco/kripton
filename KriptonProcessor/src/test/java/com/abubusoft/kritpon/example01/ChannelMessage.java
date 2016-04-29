package com.abubusoft.kritpon.example01;

import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindAllFields
public class ChannelMessage extends UidEntity {

	private static final long serialVersionUID = -2411765210163916759L;

	protected String text;

	protected String type;

	protected String ownerUid;

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
