/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.common;


/**
 * The Class Pair.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 * @param <V0> the generic type
 * @param <V1> the generic type
 */
public class Pair<V0, V1> extends One<V0> {
	
	/**
	 * Instantiates a new pair.
	 */
	public Pair()
	{
		super();
	}
	
	/**
	 * Instantiates a new pair.
	 *
	 * @param value0 the value 0
	 * @param value1 the value 1
	 */
	public Pair(V0 value0, V1 value1)
	{
		super(value0);		
		this.value1=value1;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((value1 == null) ? 0 : value1.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair other = (Pair) obj;
		if (value1 == null) {
			if (other.value1 != null)
				return false;
		} else if (!value1.equals(other.value1))
			return false;
		return true;
	}
	
	/** The value 1. */
	public V1 value1;
}
