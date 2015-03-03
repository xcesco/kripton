package com.abubusoft.kripton.android.xml;

import com.abubusoft.kripton.binder.annotation.schema.SchemaArray;
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
	
	/**
	 * stack for array (Object[]) 
	 */
	public FastStack<SchemaArray> arrayStack = new FastStack<SchemaArray>(5);
	
	public boolean isRoot() {
		return valueStack.size() == 1 && depth == 1;
	}
	
	public void clearTextBuffer() {
		int length = textBuilder.length();
		this.textBuilder.delete(0, length);
	}
}
