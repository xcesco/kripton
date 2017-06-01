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
package shared.kripton47;

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

    public void setUid(String uid) {
		this.uid = uid;
	}

	private long creationDate;

    public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}

	public boolean isValid() {
        return uid!=null;
    }
}
