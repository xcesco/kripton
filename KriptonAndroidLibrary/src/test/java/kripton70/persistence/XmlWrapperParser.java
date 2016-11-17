package kripton70.persistence;

import org.codehaus.stax2.XMLStreamReader2;

import kripton70.contexts.BinderContext;
import kripton70.core.BinderType;

public class XmlWrapperParser implements ParserWrapper {

	public XMLStreamReader2 xmlParser;

	public XmlWrapperParser(BinderContext<XmlWrapperSerializer, XmlWrapperParser> context, XMLStreamReader2 xmlStreamReader, BinderType supportedFormat) {
		this.xmlParser = xmlStreamReader;
	}

}
