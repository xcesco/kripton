package com.abubusoft.kripton.processor.kripton47;

import java.util.Date;

import com.abubusoft.kripton.annotation.BindType;

/**
 * Token di accesso al sistema
 *
 * Created by xcesco on 11/04/2016.
 */
@BindType
public class UserAccessToken {
	
	public UserAccessToken()
	{
		
	}
	
	public UserAccessToken(String value)
	{
		uid=value;
		creationDate=(new Date()).getTime();
	}

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
