package com.abubusoft.kripton.common;

import com.abubusoft.kripton.xml.XmlPullParser;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class XmlAttributeUtils {
	
	public static boolean getAttributeAsBoolean(XmlPullParser parser, String attributeName, boolean defaultValue) throws Exception
	{
		//parser.getText()
		String value=parser.getAttributeValue(null, attributeName);
		
		if (!StringUtils.hasText(value))
		{
			return defaultValue;
		}	
		
		return Boolean.parseBoolean(value);
	}

}
