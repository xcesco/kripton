package com.abubusoft.kripton.processor.kripton46;

import com.abubusoft.kripton.annotation.BindType;

/**
 * Token di accesso al sistema
 *
 * Created by xcesco on 11/04/2016.
 */
@BindType
public class UserAccessToken {

    public String getUid() {
        return uid;
    }

    public long getCreationDate() {
        return creationDate;
    }

    private String uid;

    private long creationDate;

    public boolean isValid() {
        return uid!=null;
    }
}
