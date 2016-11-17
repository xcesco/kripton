package kripton70.persistence;

import javax.xml.stream.XMLStreamException;

import kripton70.core.BinderType;

import org.codehaus.stax2.XMLStreamWriter2;

import com.abubusoft.kripton.exception.KriptonRuntimeException;

public class XmlSerializer implements BinderSerializer {
	protected XMLStreamWriter2 xmlSerializer;

	public XmlSerializer(XMLStreamWriter2 xmlSerializer, BinderType supportedFormat) {
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

	public boolean isOnlyText() {
		return false;
	}

	public void writeAttribute(String name, boolean writeName, long value) {
		try {
			xmlSerializer.writeAttribute(name, String.valueOf(value));
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}

	}

	public void writeAttribute(String fieldName, String value) {
		try {
			xmlSerializer.writeAttribute(fieldName, value);
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public void writeBoolean(Boolean value) {
		try {
			xmlSerializer.writeBoolean(value);
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public void writeEndArray() {
		try {
			xmlSerializer.writeEndElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}

	}

	public void writeEndObject() {
		try {
			xmlSerializer.writeEndElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}

	}

	public void writeStartElement(String fieldName) throws XMLStreamException {
		xmlSerializer.writeStartElement(fieldName);
	}
	
	public void writeEndElement() throws XMLStreamException {
		xmlSerializer.writeEndElement();
	}

	public void writeNull() {
		throw new KriptonRuntimeException("Not supported");
	}

	public void writeNull(String fieldName) {
		try {
			xmlSerializer.writeEmptyElement(fieldName);
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public void writeNumber(Byte value) {
		try {
			xmlSerializer.writeInt(value);
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public void writeNumber(Double value) {
		try {
			xmlSerializer.writeDouble(value);
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}

	}

	public void writeNumber(Float value) {
		try {
			xmlSerializer.writeFloat(value);
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}

	}

	public void writeNumber(Integer value) {
		try {
			xmlSerializer.writeInt(value);
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}

	}

	public void writeNumber(Long value) {
		try {
			xmlSerializer.writeLong(value);
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}

	}

	public void writeNumber(Short value) {
		try {
			xmlSerializer.writeInt(value);
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public void writeStartArray() {
		throw new KriptonRuntimeException("Not supported");
	}

	public void writeStartArray(String fieldName) {
		try {
			xmlSerializer.writeStartElement(fieldName);
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}

	}

	public void writeStartObject() {
		throw new KriptonRuntimeException("Not supported");
	}

	public void writeStartObject(String fieldName) {
		try {
			xmlSerializer.writeStartElement(fieldName);
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}

	}

	public void writeString(String value) {
		try {
			xmlSerializer.writeCharacters(value);
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public void writeStartDocument() throws XMLStreamException {
		xmlSerializer.writeStartDocument();
	}

	public void writeEndDocument() throws XMLStreamException {
		xmlSerializer.writeEndDocument();

	}
}
