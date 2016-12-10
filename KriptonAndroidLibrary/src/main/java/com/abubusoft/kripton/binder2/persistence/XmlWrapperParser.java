package com.abubusoft.kripton.binder2.persistence;

import javax.xml.stream.XMLStreamException;

import org.codehaus.stax2.XMLStreamReader2;

import com.abubusoft.kripton.binder2.BinderType;
import com.abubusoft.kripton.binder2.context.BinderContext;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

public class XmlWrapperParser implements ParserWrapper {

	public XMLStreamReader2 xmlParser;

	public XmlWrapperParser(BinderContext context, XMLStreamReader2 xmlStreamReader, BinderType supportedFormat) {
		this.xmlParser = xmlStreamReader;
	}

	@Override
	public void close() {
		try {			
			xmlParser.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw(new KriptonRuntimeException(e));
		}
		
	}

}
