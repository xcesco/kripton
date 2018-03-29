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
package base;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.abubusoft.kripton.annotation.BindType;

/**
 * Created by xcesco on 07/04/2016.
 */
@BindType
public class UserIdentity extends BaseUserIdentity {

	protected List<String> values;
	
	/**
	 * @return the values
	 */
	public List<String> getValues() {
		return values;
	}

	/**
	 * @param values the values to set
	 */
	public void setValues(List<String> values) {
		this.values = values;
	}
	
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String uid;

    public String name;

    public String email;

    public String token;
    
    public int prova;
    
    /* \((.*)\)(.*) */
    
    public int salutoMagico(List<Set<String>> varie, String log, Map<String, Object> prova)
    {
    	return 0;
    }
    
    public void salutoMagico2(List<Set<String>> varie, String log, Map<String, Object> prova)
    {
    	return;
    }
    
    public Map<String, String> salutoMagico3(List<Set<String>> varie, String log, Map<String, Object> prova)
    {
    	return null;
    }
    
    public Map<String, List<Map<String,String>>> salutoMagico4(List<Set<String>> varie, String log, Map<String, Object> prova)
    {
    	return null;
    }
    
    public Map<String, List<Map<String,String>>> salutoMagico5()
    {
    	return null;
    }
}
