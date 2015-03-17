package com.abubusoft.kripton.binder.xml;

import com.abubusoft.kripton.binder.schema.SchemaArray;
import com.abubusoft.kripton.common.FastStack;
import com.abubusoft.kripton.common.Pair;

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
