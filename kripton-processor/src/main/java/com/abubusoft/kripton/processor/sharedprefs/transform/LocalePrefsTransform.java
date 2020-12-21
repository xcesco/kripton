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
package com.abubusoft.kripton.processor.sharedprefs.transform;

import com.abubusoft.kripton.common.LocaleUtils;


/**
 * Transformer between a string and a java.util.Locale object
 * 
 * @author xcesco
 *
 */
public class LocalePrefsTransform extends WrappedPrefsTransform {

	/**
	 * Instantiates a new locale prefs transform.
	 */
	public LocalePrefsTransform() {
		super(LocaleUtils.class);
	}
}
