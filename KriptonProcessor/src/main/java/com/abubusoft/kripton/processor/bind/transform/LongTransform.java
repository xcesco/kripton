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
package com.abubusoft.kripton.processor.bind.transform;


/**
 *
 */
class LongTransform extends AbstractPrimitiveBindTransform {

	public LongTransform(boolean nullable) {
		super(nullable);
		XML_TYPE = "Long";
		PRIMITIVE_UTILITY_TYPE="Long";
		
		if (!nullable)
		{
			DEFAULT_VALUE="0L";
		}
		
		JSON_TYPE = "Number";
		JSON_PARSER_METHOD="getLongValue";
		
		XML_ATTRIBUTE_METHOD_PRE="Integer";
		XML_ATTRIBUTE_METHOD_POST="long";
		
	}
}
