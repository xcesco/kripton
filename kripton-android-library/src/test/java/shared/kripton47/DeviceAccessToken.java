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
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package shared.kripton47;

import java.util.Date;

import com.abubusoft.kripton.annotation.BindType;


/**
 * The Class DeviceAccessToken.
 */
@BindType
public class DeviceAccessToken  {
    
    /** The creation time. */
    private long creationTime;
    
    /** The last used time. */
    private long lastUsedTime;

    /**
     * Instantiates a new device access token.
     */
    public DeviceAccessToken() {
    }

    /**
     * Gets the creation time.
     *
     * @return the creation time
     */
    public long getCreationTime() {
        return this.creationTime;
    }

    /**
     * Gets the last used time.
     *
     * @return the last used time
     */
    public long getLastUsedTime() {
        return this.lastUsedTime;
    }


    /**
     * Sets the creation time.
     *
     * @param creationTime the new creation time
     */
    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * Sets the last used time.
     *
     * @param lastUsedTime the new last used time
     */
    public void setLastUsedTime(long lastUsedTime) {
        this.lastUsedTime = lastUsedTime;
    }

    /**
     * Gets the creation time as date.
     *
     * @return the creation time as date
     */
    public Date getCreationTimeAsDate() {
        return new Date(this.creationTime);
    }

    /**
     * Gets the last used time as date.
     *
     * @return the last used time as date
     */
    public Date getLastUsedTimeAsDate() {
        return new Date(this.lastUsedTime);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "DeviceAccessToken [creationTime=" + this.creationTime + ", lastUsedTime=" + this.lastUsedTime + "]";
    }
}
