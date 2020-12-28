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
/**
 * 
 */
package com.abubusoft.kripton.common;


/**
 * The Class Triple.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 * @param <V0> the generic type
 * @param <V1> the generic type
 * @param <V2> the generic type
 */
public class Triple<V0, V1, V2> extends Pair<V0, V1> {

	public static <V0, V1, V2> Triple<V0, V1, V2> of(V0 value0, V1 value1, V2 value2) {
		return new Triple(value0, value1, value2);
	}

	/**
	 * Instantiates a new triple.
	 */
	public Triple() {

	}

	/**
	 * Instantiates a new triple.
	 *
	 * @param v0 the v 0
	 * @param v1 the v 1
	 * @param v2 the v 2
	 */
	public Triple(V0 v0, V1 v1, V2 v2) {
		super(v0, v1);
		this.value2 = v2;
	}

	/** The value 2. */
	public V2 value2;
}