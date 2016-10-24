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
package com.abubusoft.kripton.processor.sqlite.transform;

import java.math.BigInteger;

import com.abubusoft.kripton.binder.transform.Transform;


/**
 * Transformer between a string and a java.math.BigInteger object
 * 
 * @author bulldog
 *
 */
class BigIntegerTransform implements Transform<BigInteger> {

	public BigInteger read(String value) throws Exception {
		return new BigInteger(value);
	}

	public String write(BigInteger value) throws Exception {
		return value.toString();
	}


}
