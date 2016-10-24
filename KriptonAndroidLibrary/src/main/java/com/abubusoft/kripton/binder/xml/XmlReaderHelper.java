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
package com.abubusoft.kripton.binder.xml;

import com.abubusoft.kripton.binder.schema.SchemaArray;
import com.abubusoft.kripton.common.FastStack;

/**
 * Helper class for XmlReader
 * 
 * @author bulldog
 * @author xcesco
 *
 */
class XmlReaderHelper {

	/**
	 * stack for array (Object[])
	 */
	public FastStack<SchemaArray> arrayStack = new FastStack<SchemaArray>(5);

	/**
	 * depth
	 */
	public int depth = 0;

	/**
	 * builder for xml tag content
	 */
	public StringBuilder textBuilder = new StringBuilder();

	/**
	 * stack of values
	 */
	public FastStack<Object> valueStack = new FastStack<Object>(5);

	public void clearTextBuffer() {
		int length = textBuilder.length();
		this.textBuilder.delete(0, length);
	}

	public boolean isRoot() {
		return valueStack.size() == 1 && depth == 1;
	}

	public void reset() {
		depth = 0;
		arrayStack.clear();
		clearTextBuffer();
		valueStack.clear();
	}
}
