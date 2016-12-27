package com.abubusoft.kripton.persistence;

import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.xml.XMLSerializer;

public class XmlWrapperSerializer implements SerializerWrapper {

	public XMLSerializer xmlSerializer;
	
	public XmlWrapperSerializer(XMLSerializer xmlSerializer) {
		this.xmlSerializer = xmlSerializer;
	}

	@Override
	public void close() {
		try {
			xmlSerializer.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}

	}

}
