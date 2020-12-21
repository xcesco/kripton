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

	/** The values. */
	protected List<String> values;
	
	/**
	 * Gets the values.
	 *
	 * @return the values
	 */
	public List<String> getValues() {
		return values;
	}

	/**
	 * Sets the values.
	 *
	 * @param values the values to set
	 */
	public void setValues(List<String> values) {
		this.values = values;
	}
	
    /**
     * Gets the uid.
     *
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * Sets the uid.
     *
     * @param uid the new uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
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

    /**
     * Gets the token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the token.
     *
     * @param token the new token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /** The uid. */
    public String uid;

    /** The name. */
    public String name;

    /** The email. */
    public String email;

    /** The token. */
    public String token;
    
    /** The prova. */
    public int prova;
    
    /* \((.*)\)(.*) */
    
    /**
     * Saluto magico.
     *
     * @param varie the varie
     * @param log the log
     * @param prova the prova
     * @return the int
     */
    public int salutoMagico(List<Set<String>> varie, String log, Map<String, Object> prova)
    {
    	return 0;
    }
    
    /**
     * Saluto magico 2.
     *
     * @param varie the varie
     * @param log the log
     * @param prova the prova
     */
    public void salutoMagico2(List<Set<String>> varie, String log, Map<String, Object> prova)
    {
    	return;
    }
    
    /**
     * Saluto magico 3.
     *
     * @param varie the varie
     * @param log the log
     * @param prova the prova
     * @return the map
     */
    public Map<String, String> salutoMagico3(List<Set<String>> varie, String log, Map<String, Object> prova)
    {
    	return null;
    }
    
    /**
     * Saluto magico 4.
     *
     * @param varie the varie
     * @param log the log
     * @param prova the prova
     * @return the map
     */
    public Map<String, List<Map<String,String>>> salutoMagico4(List<Set<String>> varie, String log, Map<String, Object> prova)
    {
    	return null;
    }
    
    /**
     * Saluto magico 5.
     *
     * @return the map
     */
    public Map<String, List<Map<String,String>>> salutoMagico5()
    {
    	return null;
    }
}
