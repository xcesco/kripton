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
package com.abubusoft.kripton.android.sqlite;

import com.abubusoft.kripton.exception.KriptonRuntimeException;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public abstract class AssertKripton {

	/**
	 * Assertion which generate an exception if expression is not true
	 * 
	 * @param <E>
	 * 
	 * @param expression
	 * @param message
	 */
	public static void assertTrue(boolean expression, String messageFormat, Object... args) {
		if (!expression)
			throw (new KriptonRuntimeException(String.format(messageFormat, args)));

	}


	public static void fail(String messageFormat, Object... args) {
		assertTrue(false, messageFormat, args);
	}

	/**
	 * Fails if expression is true
	 * 
	 * @param expression
	 * @param messageFormat
	 * @param args
	 */
	public static void fail(boolean expression, String messageFormat, Object... args) {
		assertTrue(!expression, messageFormat, args);
	}



}
