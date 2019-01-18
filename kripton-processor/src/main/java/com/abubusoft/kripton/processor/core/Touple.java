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
package com.abubusoft.kripton.processor.core;

import com.abubusoft.kripton.common.Triple;
 
/**
 * The Class Touple.
 *
 * @param <V0>
 * @param <V1>
 * @param <V2>
 * @param <V3>
 */
public class Touple<V0, V1, V2, V3> extends Triple<V0, V1, V2> {
	
	/**
	 * Instantiates a new triple.
	 *
	 * @param v0 the v 0
	 * @param v1 the v 1
	 * @param v2 the v 2
	 * @param v3 the v 3
	 * 
	 */
	public Touple(V0 v0, V1 v1, V2 v2, V3 v3) {
		super(v0, v1, v2);
		this.value3 = v3;
	}

	/** The value 3. */
	public V3 value3;
}