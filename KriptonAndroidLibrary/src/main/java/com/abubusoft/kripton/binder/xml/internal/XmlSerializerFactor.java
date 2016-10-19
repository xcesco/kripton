/**
 * 
 */
package com.abubusoft.kripton.binder.xml.internal;

/**
 * @author xcesco
 *
 */
public class XmlSerializerFactor {
	
	public static XmlSerializer createXmlSerializer()
	{
		return new MXSerializer();
	}
}
