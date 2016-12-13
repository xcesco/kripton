package com.abubusoft.kripton.binder.persistence;

import org.codehaus.stax2.XMLStreamWriter2;

import com.abubusoft.kripton.binder.BinderType;
import com.abubusoft.kripton.binder.xml.XmlSerializer;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

public class XmlWrapperSerializer implements SerializerWrapper {

	public XmlSerializer xmlSerializer;

	public XmlWrapperSerializer(XMLStreamWriter2 xmlSerializer, BinderType supportedFormat) {
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
