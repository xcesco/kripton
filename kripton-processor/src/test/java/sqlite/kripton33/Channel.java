/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite.kripton33;

import com.abubusoft.kripton.annotation.BindType;


/**
 * The Class Channel.
 */
@BindType
public class Channel {	
	
	/** The owner uid. */
	protected String ownerUid;
	
	/** The update time. */
	protected long updateTime;

	/**
	 * Gets the update time.
	 *
	 * @return the updateTime
	 */
	public long getUpdateTime() {
		return updateTime;
	}

	/**
	 * Sets the update time.
	 *
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * Gets the owner uid.
	 *
	 * @return the ownerUid
	 */
	public String getOwnerUid() {
		return ownerUid;
	}

	/**
	 * Sets the owner uid.
	 *
	 * @param ownerUid the ownerUid to set
	 */
	public void setOwnerUid(String ownerUid) {
		this.ownerUid = ownerUid;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** The name. */
	protected String name;
	
	/** The id. */
	protected long id;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
}
