/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite.kripton49.entities;

import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindTable("Channel")
public class ChannelEntity {	
	
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
