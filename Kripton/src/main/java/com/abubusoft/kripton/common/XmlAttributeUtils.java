package com.abubusoft.kripton.common;

import com.abubusoft.kripton.binder.xml.XmlParser;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class XmlAttributeUtils {
	
	public static boolean getAttributeAsBoolean(XmlParser parser, String attributeName, boolean defaultValue) throws Exception
	{
		int index=parser.getAttributeIndex(null, attributeName);
		
		if (index==-1) return defaultValue;
		
		return parser.getAttributeAsBoolean(index);		
	}

}
