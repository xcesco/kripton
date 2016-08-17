//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.abubusoft.kripton.processor.kripton47;

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
