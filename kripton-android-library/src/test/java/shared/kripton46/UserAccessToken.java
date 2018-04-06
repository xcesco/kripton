/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package shared.kripton46;

import com.abubusoft.kripton.annotation.BindType;

// TODO: Auto-generated Javadoc
/**
 * Token di accesso al sistema
 *
 * Created by xcesco on 11/04/2016.
 */
@BindType
public class UserAccessToken {

    /**
     * Gets the uid.
     *
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * Gets the creation date.
     *
     * @return the creation date
     */
    public long getCreationDate() {
        return creationDate;
    }

    /** The uid. */
    private String uid;

    /**
     * Sets the uid.
     *
     * @param uid the new uid
     */
    public void setUid(String uid) {
		this.uid = uid;
	}

	/** The creation date. */
	private long creationDate;

    /**
     * Sets the creation date.
     *
     * @param creationDate the new creation date
     */
    public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Checks if is valid.
	 *
	 * @return true, if is valid
	 */
	public boolean isValid() {
        return uid!=null;
    }
}
