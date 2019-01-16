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
package com.abubusoft.kripton.xml;

import com.abubusoft.kripton.common.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class XmlAttributeUtils.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class XmlAttributeUtils {

	/**
	 * Gets the attribute as boolean.
	 *
	 * @param parser the parser
	 * @param attributeName the attribute name
	 * @param defaultValue the default value
	 * @return get attribute as boolean
	 * @throws Exception the exception
	 */
	public static boolean getAttributeAsBoolean(XmlPullParser parser, String attributeName, boolean defaultValue) throws Exception {
		// parser.getText()
		String value = parser.getAttributeValue(null, attributeName);

		if (!StringUtils.hasText(value)) {
			return defaultValue;
		}

		return Boolean.parseBoolean(value);
	}

}
