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
package com.abubusoft.kripton.processor.sqlite.grammars.jql;

/**
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class JQLPlaceHolder {
	
	public JQLPlaceHolderType type;
	
	public String value;
	
	/**
	 * if <code>true</code>, means parameter used an attribute of parameter. If <code>false</code>, parameter is used directly
	 *  
	 */
	public boolean composed;
	
	public JQLPlaceHolder(JQLPlaceHolderType type, String value) {
		this.value=value;
		this.composed=value.indexOf(".")>=0;
		this.type=type;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (composed ? 1231 : 1237);
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JQLPlaceHolder other = (JQLPlaceHolder) obj;
		if (composed != other.composed)
			return false;
		if (type != other.type)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}


	public enum JQLPlaceHolderType {
		PARAMETER,		
		DYNAMIC_SQL				 
	}

}
