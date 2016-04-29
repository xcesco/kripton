package com.abubusoft.kripton.processor.utils;

public class StringUtility {

	public static String repeatString(String fill, int repeat)
	{
		StringBuilder buffer=new StringBuilder();
		for (int i=0; i<repeat;i++)
		{
			buffer.append(fill);
		}
		
		return buffer.toString();
	}
}
