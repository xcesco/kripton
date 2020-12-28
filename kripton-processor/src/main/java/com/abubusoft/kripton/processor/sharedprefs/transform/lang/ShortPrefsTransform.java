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
package com.abubusoft.kripton.processor.sharedprefs.transform.lang;


/**
 * Transformer between a string and a Java Short object.
 *
 * @author xcesco
 */
public class ShortPrefsTransform extends AbstractPrimitivePrefsTransform {

	public ShortPrefsTransform() {
		this(true);
	}

	/**
	 * Instantiates a new short prefs transform.
	 *
	 * @param nullable the nullable
	 */
	public ShortPrefsTransform(boolean nullable)
	{
		super(nullable);
		SIMPLE_TYPE="(short)";
		PREFS_CONVERT="(int)";
		PREFS_TYPE="Int";
		PREFS_DEFAULT_VALUE="(short)0";
	}
}
