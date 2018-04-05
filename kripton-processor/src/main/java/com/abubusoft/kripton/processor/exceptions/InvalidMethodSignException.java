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
package com.abubusoft.kripton.processor.exceptions;

import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;

// TODO: Auto-generated Javadoc
/**
 * The Class InvalidMethodSignException.
 */
public class InvalidMethodSignException extends KriptonProcessorException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8462705406839489618L;

	/**
	 * Instantiates a new invalid method sign exception.
	 *
	 * @param method the method
	 */
	public InvalidMethodSignException(SQLiteModelMethod method)
	{		
		super(String.format("In class '%s', method '%s' has an invalid signature", method.getParent().getName(), method.getName()));
	}
	
	/**
	 * Instantiates a new invalid method sign exception.
	 *
	 * @param method the method
	 * @param msg the msg
	 */
	public InvalidMethodSignException(SQLiteModelMethod method, String msg)
	{
		super(String.format("In class '%s', method '%s' has an invalid signature: %s", method.getParent().getName(), method.getName(), msg));		
	}
	
}
