package com.abubusoft.kripton.binder2.persistence;

import javax.xml.stream.XMLStreamException;

import org.codehaus.stax2.XMLStreamWriter2;

import com.abubusoft.kripton.binder2.core.BinderType;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

public class XmlWrapperSerializer implements SerializerWrapper {
	public XMLStreamWriter2 xmlSerializer;

	public XmlWrapperSerializer(XMLStreamWriter2 xmlSerializer, BinderType supportedFormat) {
		this.xmlSerializer = xmlSerializer;
	}

	public void close() {
		try {
			xmlSerializer.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}

	}
	
}
