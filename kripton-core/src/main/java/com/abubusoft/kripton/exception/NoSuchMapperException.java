/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.abubusoft.kripton.exception;

import java.lang.reflect.Type;



/**
 * The Class NoSuchMapperException.
 */
public class NoSuchMapperException extends KriptonRuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2423802393630866667L;

	/**
	 * Instantiates a new no such mapper exception.
	 *
	 * @param type the type
	 */
	public NoSuchMapperException(Type type) {
		super("Class " + type.toString() + " could not be mapped. Is it marked with @BindType?");
	}

}
