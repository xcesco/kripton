package com.abubusoft.kripton.persistence;

import org.codehaus.stax2.XMLStreamReader2;

import com.abubusoft.kripton.BinderContext;
import com.abubusoft.kripton.BinderType;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

public class XmlWrapperParser implements ParserWrapper {

	public XmlParser xmlParser;

	public XmlWrapperParser(BinderContext context, XMLStreamReader2 xmlStreamReader, BinderType supportedFormat) {
		this.xmlParser = new XmlParser(xmlStreamReader);
	}

	@Override
	public void close() {
		try {
			xmlParser.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e));
		}

	}

}
