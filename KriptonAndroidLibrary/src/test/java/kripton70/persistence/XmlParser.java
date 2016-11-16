package kripton70.persistence;

import javax.xml.stream.XMLStreamException;

import org.codehaus.stax2.XMLStreamReader2;

import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonToken;

import kripton70.contexts.BinderContext;
import kripton70.core.BinderType;

public class XmlParser implements Visitable {

	protected BinderContext context;

	protected XMLStreamReader2 xmlParser;

	public XmlParser(BinderContext context, XMLStreamReader2 xmlStreamReader, BinderType supportedFormat) {
		this.context = context;
		this.xmlParser = xmlStreamReader;
	}

	public Boolean getBooleanValue() {
		try {
			return xmlParser.getElementAsBoolean();
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public Byte getByteValue() {
		try {
			return (byte) xmlParser.getElementAsInt();
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public String getCurrentName() {
		return xmlParser.getName().toString();
	}

	public JsonToken getCurrentToken() {
		throw new KriptonRuntimeException("not implemented!");
	}

	public Double getDoubleValue() {
		try {
			return xmlParser.getElementAsDouble();
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public Float getFloatValue() {
		try {
			return xmlParser.getElementAsFloat();
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public Integer getIntValue() {
		try {
			return xmlParser.getElementAsInt();
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public Long getLongValue() {
		try {
			return xmlParser.getElementAsLong();
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public Short getShortValue() {
		try {
			return (short) xmlParser.getElementAsInt();
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public String getText() {
		return xmlParser.getText();
	}

	public boolean isOnlyText() {
		return false;
	}

	public JsonToken nextToken() {
		throw new KriptonRuntimeException("not implemented!");
	}

	public void skipChildren() {
		try {
			xmlParser.skipElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public String getString() {
		return xmlParser.getText();
	}
	
	@Override
	public <E> void accept(BinderContext context, E bean, Visitor<E> visitor, boolean writeStartAndEnd) {
			visitor.visit(context, bean, this, writeStartAndEnd, context.getSupportedFormat().onlyText);
	}
}
