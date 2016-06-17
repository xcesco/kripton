package com.abubusoft.kripton.processor.kripton33;

import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindAllFields
public class Channel {	
	
	protected String ownerUid;
	
	protected long updateTime;

	/**
	 * @return the updateTime
	 */
	public long getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the ownerUid
	 */
	public String getOwnerUid() {
		return ownerUid;
	}

	/**
	 * @param ownerUid the ownerUid to set
	 */
	public void setOwnerUid(String ownerUid) {
		this.ownerUid = ownerUid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	protected String name;
	
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
}
