/**
 * 
 */
package com.abubusoft.kripton.persistence;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.abubusoft.kripton.persistence.xml.internal.MXSerializer;

/**
 * @author xcesco
 *
 */
public class XmlSerializer {

	private MXSerializer xmlStreamWriter2;

	public XmlSerializer(MXSerializer xmlStreamWriter2) {
		this.xmlStreamWriter2 = xmlStreamWriter2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamWriter#close()
	 */
	public void close() throws Exception {
		xmlStreamWriter2.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamWriter#flush()
	 */
	public void flush() throws Exception {
		xmlStreamWriter2.flush();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamWriter#getProperty(java.lang.String)
	 */
	public Object getProperty(String name) throws IllegalArgumentException {
		return xmlStreamWriter2.getProperty(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamWriter#setPrefix(java.lang.String,
	 * java.lang.String)
	 */
	public void setPrefix(String prefix, String uri) throws Exception {
		xmlStreamWriter2.setPrefix(prefix, uri);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamWriter#writeAttribute(java.lang.String,
	 * java.lang.String)
	 */
	public void writeAttribute(String localName, String value) throws Exception {
		xmlStreamWriter2.writeAttribute(localName, value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamWriter#writeAttribute(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public void writeAttribute(String namespaceURI, String localName, String value) throws Exception {
		xmlStreamWriter2.writeAttribute(namespaceURI, localName, value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamWriter#writeAttribute(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public void writeAttribute(String prefix, String namespaceURI, String localName, String value) throws Exception {
		xmlStreamWriter2.writeAttribute(namespaceURI, localName, value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.typed.TypedXMLStreamWriter#writeBinary(byte[],
	 * int, int)
	 */
	public void writeBinary(byte[] value, int from, int length) throws Exception {
		xmlStreamWriter2.writeBinary(value, from, length);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.typed.TypedXMLStreamWriter#writeBinary(byte[],
	 * int, int)
	 */
	public void writeBinary(byte[] value) throws Exception {
		xmlStreamWriter2.writeBinary(value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.typed.TypedXMLStreamWriter#writeBoolean(boolean)
	 */
	public void writeBoolean(boolean value) throws Exception {
		xmlStreamWriter2.writeBoolean(value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamWriter#writeBooleanAttribute(java.
	 * lang.String, java.lang.String, java.lang.String, boolean)
	 */
	public void writeBooleanAttribute(String prefix, String namespaceURI, String localName, boolean value) throws Exception {
		xmlStreamWriter2.writeBooleanAttribute(prefix, namespaceURI, localName, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamWriter#writeCData(java.lang.String)
	 */
	public void writeCData(String data) throws Exception {
		xmlStreamWriter2.writeCData(data);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamWriter#writeCharacters(java.lang.String)
	 */
	public void writeCharacters(String text) throws Exception {
		xmlStreamWriter2.writeCharacters(text);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamWriter#writeComment(java.lang.String)
	 */
	public void writeComment(String data) throws Exception {
		xmlStreamWriter2.writeComment(data);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamWriter#writeDecimal(java.math.
	 * BigDecimal)
	 */
	public void writeDecimal(BigDecimal value) throws Exception {
		xmlStreamWriter2.writeDecimal(value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamWriter#writeDecimalAttribute(java.
	 * lang.String, java.lang.String, java.lang.String, java.math.BigDecimal)
	 */
	public void writeDecimalAttribute(String prefix, String namespaceURI, String localName, BigDecimal value) throws Exception {
		xmlStreamWriter2.writeDecimalAttribute(prefix, namespaceURI, localName, value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.typed.TypedXMLStreamWriter#writeDouble(double)
	 */
	public void writeDouble(double value) throws Exception {
		xmlStreamWriter2.writeDouble(value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamWriter#writeDoubleAttribute(java.
	 * lang.String, java.lang.String, java.lang.String, double)
	 */
	public void writeDoubleAttribute(String prefix, String namespaceURI, String localName, double value) throws Exception {
		xmlStreamWriter2.writeDoubleAttribute(prefix, namespaceURI, localName, value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamWriter#writeEmptyElement(java.lang.String)
	 */
	public void writeEmptyElement(String localName) throws Exception {
		xmlStreamWriter2.writeEmptyElement(localName);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamWriter#writeEmptyElement(java.lang.String,
	 * java.lang.String)
	 */
	public void writeEmptyElement(String namespaceURI, String localName) throws Exception {
		xmlStreamWriter2.writeEmptyElement(namespaceURI, localName);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamWriter#writeEndDocument()
	 */
	public void writeEndDocument() throws Exception {
		xmlStreamWriter2.writeEndDocument();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamWriter#writeEndElement()
	 */
	public void writeEndElement() throws Exception {
		xmlStreamWriter2.writeEndElement();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.typed.TypedXMLStreamWriter#writeFloat(float)
	 */
	public void writeFloat(float value) throws Exception {
		xmlStreamWriter2.writeFloat(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.typed.TypedXMLStreamWriter#writeInt(int)
	 */
	public void writeInt(int value) throws Exception {
		xmlStreamWriter2.writeInt(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamWriter#writeIntegerAttribute(java.
	 * lang.String, java.lang.String, java.lang.String, java.math.BigInteger)
	 */
	public void writeIntegerAttribute(String prefix, String namespaceURI, String localName, BigInteger value) throws Exception {
		xmlStreamWriter2.writeIntegerAttribute(prefix, namespaceURI, localName, value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.typed.TypedXMLStreamWriter#writeLong(long)
	 */
	public void writeLong(long value) throws Exception {
		xmlStreamWriter2.writeLong(value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamWriter#writeStartDocument()
	 */
	public void writeStartDocument() throws Exception {
		xmlStreamWriter2.writeStartDocument();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamWriter#writeStartElement(java.lang.String)
	 */
	public void writeStartElement(String localName) throws Exception {
		xmlStreamWriter2.writeStartElement(localName);

	}

}
