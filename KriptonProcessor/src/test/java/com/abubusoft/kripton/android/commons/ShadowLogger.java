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
package com.abubusoft.kripton.android.commons;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

import com.abubusoft.kripton.android.Logger;

@Implements(Logger.class)
public class ShadowLogger {
	
	
	/**
	 * debug
	 * 
	 * @param msg
	 */
	@Implementation
	public static void debug(String msg, Object... args) {
		System.out.print(String.format(msg, args));		
	}

	/**
	 * error
	 * 
	 * @param msg
	 */
	@Implementation
	public static void error(String msg, Object... args) {
		System.out.print(String.format(msg, args));
	}

	/**
	 * verbose
	 * 
	 * @param msg
	 */
	@Implementation
	public static void verbose(String msg, Object... args) {
		System.out.print(String.format(msg, args));
	}

	/**
	 * warn
	 * 
	 * @param msg
	 */
	@Implementation
	public static void warn(String msg, Object... args) {
		System.out.print(String.format(msg, args));
	}

	/**
	 * fatal
	 * 
	 * @param msg
	 */
	@Implementation
	public static void fatal(String msg, Object... args) {
		System.out.print(String.format(msg, args));
	}

	/**
	 * info
	 * 
	 * @param msg
	 */
	@Implementation
	public static void info(String msg, Object... args) {
		System.out.print(String.format(msg, args));
	}

}
