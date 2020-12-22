/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
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
package bind.feature.generichierarchy.case1.model;

import com.abubusoft.kripton.annotation.BindType;

import bind.feature.generichierarchy.UIDObject;


/**
 * The Class ChannelUser.
 */
@BindType
public class ChannelUser extends UIDObject {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8393678197733870803L;

	/** The administrator. */
	protected boolean administrator;
	
	/** The name. */
	protected String name;

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

	/**
	 * Checks if is administrator.
	 *
	 * @return true, if is administrator
	 */
	public boolean isAdministrator() {
		return administrator;
	}

	/**
	 * Sets the administrator.
	 *
	 * @param administrator the new administrator
	 */
	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}
}
