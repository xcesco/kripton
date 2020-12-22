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
package sqlite.kripton58.array2;

import com.abubusoft.kripton.annotation.BindType;


/**
 * The Class IntBean.
 */
@BindType
public class IntBean {

	/** The id. */
	private long id;
	
	/** The value. */
	private int[] value;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public int[] getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(int[] value) {
		this.value = value;
	}

	/**
	 * Gets the value 2.
	 *
	 * @return the value 2
	 */
	public Integer[] getValue2() {
		return value2;
	}

	/**
	 * Sets the value 2.
	 *
	 * @param value2 the new value 2
	 */
	public void setValue2(Integer[] value2) {
		this.value2 = value2;
	}

	/** The value 2. */
	private Integer[] value2;
	

}
