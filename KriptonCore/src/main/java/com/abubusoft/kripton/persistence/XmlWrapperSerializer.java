package com.abubusoft.kripton.persistence;

import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.xml.internal.MXSerializer;

public class XmlWrapperSerializer implements SerializerWrapper {

	public XmlSerializer xmlSerializer;
	
	public XmlWrapperSerializer(MXSerializer xmlSerializer) {
		this.xmlSerializer = new XmlSerializer(xmlSerializer);
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
