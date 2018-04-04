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

@BindType
public class DeviceAccessToken  {
    
    private long creationTime;
    private long lastUsedTime;

    public DeviceAccessToken() {
    }

    public long getCreationTime() {
        return this.creationTime;
    }

    public long getLastUsedTime() {
        return this.lastUsedTime;
    }


    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public void setLastUsedTime(long lastUsedTime) {
        this.lastUsedTime = lastUsedTime;
    }

    public Date getCreationTimeAsDate() {
        return new Date(this.creationTime);
    }

    public Date getLastUsedTimeAsDate() {
        return new Date(this.lastUsedTime);
    }

    public String toString() {
        return "DeviceAccessToken [creationTime=" + this.creationTime + ", lastUsedTime=" + this.lastUsedTime + "]";
    }
}
