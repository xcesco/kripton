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
package com.abubusoft.kripton.common;

import java.util.Currency;


/**
 * The Class CurrencyUtils.
 */
public class CurrencyUtils {

	/**
	 * Write.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String write(Currency value) {
		if (value==null) return null;
		return value.toString();		
	}
	
	/**
	 * Read.
	 *
	 * @param value the value
	 * @return the currency
	 */
	public static Currency read(String value) {
		if (value==null) return null;
		return Currency.getInstance(value);
	}
}
