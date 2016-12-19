/**
 * 
 */
package com.abubusoft.kripton.persistence;

import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;

import org.codehaus.stax2.XMLStreamLocation2;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.XMLStreamWriter2;
import org.codehaus.stax2.typed.Base64Variant;
import org.codehaus.stax2.validation.ValidationProblemHandler;
import org.codehaus.stax2.validation.XMLValidationSchema;
import org.codehaus.stax2.validation.XMLValidator;

import com.abubusoft.kripton.persistence.xml.internal.MXSerializer;

/**
 * @author xcesco
 *
 */
public class XmlSerializer2 {

	private XMLStreamWriter2 xmlStreamWriter2;
	
	//private MXSerializer xmlStreamWriter2;

	public XmlSerializer2(XMLStreamWriter2 xmlStreamWriter2) {
		this.xmlStreamWriter2 = xmlStreamWriter2;
		//xmlStreamWriter2.setOutput(writer);
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
	 * @see org.codehaus.stax2.XMLStreamWriter2#closeCompletely()
	 */
	public void closeCompletely() throws Exception {
		xmlStreamWriter2.closeCompletely();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.XMLStreamWriter2#copyEventFromReader(org.codehaus.
	 * stax2.XMLStreamReader2, boolean)
	 */
	public void copyEventFromReader(XMLStreamReader2 r, boolean preserveEventData) throws Exception {
		xmlStreamWriter2.copyEventFromReader(r, preserveEventData);

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
	 * @see org.codehaus.stax2.XMLStreamWriter2#getEncoding()
	 */
	public String getEncoding() {
		return xmlStreamWriter2.getEncoding();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.XMLStreamWriter2#getLocation()
	 */
	public XMLStreamLocation2 getLocation() {
		return xmlStreamWriter2.getLocation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamWriter#getNamespaceContext()
	 */
	public NamespaceContext getNamespaceContext() {
		return xmlStreamWriter2.getNamespaceContext();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamWriter#getPrefix(java.lang.String)
	 */
	public String getPrefix(String uri) throws Exception {
		return xmlStreamWriter2.getPrefix(uri);
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
	 * @see
	 * org.codehaus.stax2.XMLStreamWriter2#isPropertySupported(java.lang.String)
	 */
	public boolean isPropertySupported(String name) {
		return xmlStreamWriter2.isPropertySupported(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.xml.stream.XMLStreamWriter#setDefaultNamespace(java.lang.String)
	 */
	public void setDefaultNamespace(String uri) throws Exception {
		xmlStreamWriter2.setDefaultNamespace(uri);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.xml.stream.XMLStreamWriter#setNamespaceContext(javax.xml.namespace.
	 * NamespaceContext)
	 */
	public void setNamespaceContext(NamespaceContext context) throws Exception {
		xmlStreamWriter2.setNamespaceContext(context);

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
	 * @see org.codehaus.stax2.XMLStreamWriter2#setProperty(java.lang.String,
	 * java.lang.Object)
	 */
	public boolean setProperty(String name, Object value) {
		return xmlStreamWriter2.setProperty(name, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.validation.Validatable#setValidationProblemHandler(org
	 * .codehaus.stax2.validation.ValidationProblemHandler)
	 */
	public ValidationProblemHandler setValidationProblemHandler(ValidationProblemHandler h) {
		return xmlStreamWriter2.setValidationProblemHandler(h);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.validation.Validatable#stopValidatingAgainst(org.
	 * codehaus.stax2.validation.XMLValidationSchema)
	 */
	public XMLValidator stopValidatingAgainst(XMLValidationSchema schema) throws Exception {
		return xmlStreamWriter2.stopValidatingAgainst(schema);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.validation.Validatable#stopValidatingAgainst(org.
	 * codehaus.stax2.validation.XMLValidator)
	 */
	public XMLValidator stopValidatingAgainst(XMLValidator validator) throws Exception {
		return xmlStreamWriter2.stopValidatingAgainst(validator);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.validation.Validatable#validateAgainst(org.codehaus.
	 * stax2.validation.XMLValidationSchema)
	 */
	public XMLValidator validateAgainst(XMLValidationSchema schema) throws Exception {
		return xmlStreamWriter2.validateAgainst(schema);
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
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamWriter#writeBinary(org.codehaus.
	 * stax2.typed.Base64Variant, byte[], int, int)
	 */
	public void writeBinary(Base64Variant variant, byte[] value, int from, int length) throws Exception {
		xmlStreamWriter2.writeBinary(variant, value, from, length);

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
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamWriter#writeBinaryAttribute(org.
	 * codehaus.stax2.typed.Base64Variant, java.lang.String, java.lang.String,
	 * java.lang.String, byte[])
	 */
	public void writeBinaryAttribute(Base64Variant variant, String prefix, String namespaceURI, String localName,
			byte[] value) throws Exception {
		xmlStreamWriter2.writeBinaryAttribute(variant, prefix, namespaceURI, localName, value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamWriter#writeBinaryAttribute(java.
	 * lang.String, java.lang.String, java.lang.String, byte[])
	 */
	public void writeBinaryAttribute(String prefix, String namespaceURI, String localName, byte[] value)
			throws Exception {
		xmlStreamWriter2.writeBinaryAttribute(prefix, namespaceURI, localName, value);

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
	public void writeBooleanAttribute(String prefix, String namespaceURI, String localName, boolean value)
			throws Exception {
		xmlStreamWriter2.writeBooleanAttribute(prefix, namespaceURI, localName, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.XMLStreamWriter2#writeCData(char[], int, int)
	 */
	public void writeCData(char[] text, int start, int len) throws Exception {
		xmlStreamWriter2.writeCData(text, start, len);

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
	 * @see javax.xml.stream.XMLStreamWriter#writeCharacters(char[], int, int)
	 */
	public void writeCharacters(char[] text, int start, int len) throws Exception {
		xmlStreamWriter2.writeCharacters(text, start, len);

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
	public void writeDecimalAttribute(String prefix, String namespaceURI, String localName, BigDecimal value)
			throws Exception {
		xmlStreamWriter2.writeDecimalAttribute(prefix, namespaceURI, localName, value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.xml.stream.XMLStreamWriter#writeDefaultNamespace(java.lang.String)
	 */
	public void writeDefaultNamespace(String namespaceURI) throws Exception {
		xmlStreamWriter2.writeDefaultNamespace(namespaceURI);

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
	 * org.codehaus.stax2.typed.TypedXMLStreamWriter#writeDoubleArray(double[],
	 * int, int)
	 */
	public void writeDoubleArray(double[] value, int from, int length) throws Exception {
		xmlStreamWriter2.writeDoubleArray(value, from, length);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamWriter#writeDoubleArrayAttribute(
	 * java.lang.String, java.lang.String, java.lang.String, double[])
	 */
	public void writeDoubleArrayAttribute(String prefix, String namespaceURI, String localName, double[] value)
			throws Exception {
		xmlStreamWriter2.writeDoubleArrayAttribute(prefix, namespaceURI, localName, value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamWriter#writeDoubleAttribute(java.
	 * lang.String, java.lang.String, java.lang.String, double)
	 */
	public void writeDoubleAttribute(String prefix, String namespaceURI, String localName, double value)
			throws Exception {
		xmlStreamWriter2.writeDoubleAttribute(prefix, namespaceURI, localName, value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamWriter#writeDTD(java.lang.String)
	 */
	public void writeDTD(String dtd) throws Exception {
		xmlStreamWriter2.writeDTD(dtd);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.XMLStreamWriter2#writeDTD(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public void writeDTD(String rootName, String systemId, String publicId, String internalSubset) throws Exception {
		xmlStreamWriter2.writeDTD(rootName, systemId, publicId, internalSubset);

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
	 * @see javax.xml.stream.XMLStreamWriter#writeEmptyElement(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public void writeEmptyElement(String prefix, String localName, String namespaceURI) throws Exception {
		xmlStreamWriter2.writeEmptyElement(prefix, localName, namespaceURI);

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
	 * @see javax.xml.stream.XMLStreamWriter#writeEntityRef(java.lang.String)
	 */
	public void writeEntityRef(String name) throws Exception {
		xmlStreamWriter2.writeEntityRef(name);

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
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamWriter#writeFloatArray(float[],
	 * int, int)
	 */
	public void writeFloatArray(float[] value, int from, int length) throws Exception {
		xmlStreamWriter2.writeFloatArray(value, from, length);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamWriter#writeFloatArrayAttribute(
	 * java.lang.String, java.lang.String, java.lang.String, float[])
	 */
	public void writeFloatArrayAttribute(String prefix, String namespaceURI, String localName, float[] value)
			throws Exception {
		xmlStreamWriter2.writeFloatArrayAttribute(prefix, namespaceURI, localName, value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamWriter#writeFloatAttribute(java.
	 * lang.String, java.lang.String, java.lang.String, float)
	 */
	public void writeFloatAttribute(String prefix, String namespaceURI, String localName, float value)
			throws Exception {
		xmlStreamWriter2.writeFloatAttribute(prefix, namespaceURI, localName, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.XMLStreamWriter2#writeFullEndElement()
	 */
	public void writeFullEndElement() throws Exception {
		xmlStreamWriter2.writeFullEndElement();
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
	 * @see org.codehaus.stax2.typed.TypedXMLStreamWriter#writeIntArray(int[],
	 * int, int)
	 */
	public void writeIntArray(int[] value, int from, int length) throws Exception {
		xmlStreamWriter2.writeIntArray(value, from, length);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamWriter#writeIntArrayAttribute(java
	 * .lang.String, java.lang.String, java.lang.String, int[])
	 */
	public void writeIntArrayAttribute(String prefix, String namespaceURI, String localName, int[] value)
			throws Exception {
		xmlStreamWriter2.writeIntArrayAttribute(prefix, namespaceURI, localName, value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamWriter#writeIntAttribute(java.lang
	 * .String, java.lang.String, java.lang.String, int)
	 */
	public void writeIntAttribute(String prefix, String namespaceURI, String localName, int value) throws Exception {
		xmlStreamWriter2.writeIntAttribute(prefix, namespaceURI, localName, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamWriter#writeInteger(java.math.
	 * BigInteger)
	 */
	public void writeInteger(BigInteger value) throws Exception {
		xmlStreamWriter2.writeInteger(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamWriter#writeIntegerAttribute(java.
	 * lang.String, java.lang.String, java.lang.String, java.math.BigInteger)
	 */
	public void writeIntegerAttribute(String prefix, String namespaceURI, String localName, BigInteger value)
			throws Exception {
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
	 * @see org.codehaus.stax2.typed.TypedXMLStreamWriter#writeLongArray(long[],
	 * int, int)
	 */
	public void writeLongArray(long[] value, int from, int length) throws Exception {
		xmlStreamWriter2.writeLongArray(value, from, length);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamWriter#writeLongArrayAttribute(
	 * java.lang.String, java.lang.String, java.lang.String, long[])
	 */
	public void writeLongArrayAttribute(String prefix, String namespaceURI, String localName, long[] value)
			throws Exception {
		xmlStreamWriter2.writeLongArrayAttribute(prefix, namespaceURI, localName, value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamWriter#writeLongAttribute(java.
	 * lang.String, java.lang.String, java.lang.String, long)
	 */
	public void writeLongAttribute(String prefix, String namespaceURI, String localName, long value) throws Exception {
		xmlStreamWriter2.writeLongAttribute(prefix, namespaceURI, localName, value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamWriter#writeNamespace(java.lang.String,
	 * java.lang.String)
	 */
	public void writeNamespace(String prefix, String namespaceURI) throws Exception {
		xmlStreamWriter2.writeNamespace(prefix, namespaceURI);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.xml.stream.XMLStreamWriter#writeProcessingInstruction(java.lang.
	 * String)
	 */
	public void writeProcessingInstruction(String target) throws Exception {
		xmlStreamWriter2.writeProcessingInstruction(target);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.xml.stream.XMLStreamWriter#writeProcessingInstruction(java.lang.
	 * String, java.lang.String)
	 */
	public void writeProcessingInstruction(String target, String data) throws Exception {
		xmlStreamWriter2.writeProcessingInstruction(target, data);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.typed.TypedXMLStreamWriter#writeQName(javax.xml.
	 * namespace.QName)
	 */
	public void writeQName(QName value) throws Exception {
		xmlStreamWriter2.writeQName(value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamWriter#writeQNameAttribute(java.
	 * lang.String, java.lang.String, java.lang.String,
	 * javax.xml.namespace.QName)
	 */
	public void writeQNameAttribute(String prefix, String namespaceURI, String localName, QName value)
			throws Exception {
		xmlStreamWriter2.writeQNameAttribute(prefix, namespaceURI, localName, value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.XMLStreamWriter2#writeRaw(char[], int, int)
	 */
	public void writeRaw(char[] text, int offset, int length) throws Exception {
		xmlStreamWriter2.writeRaw(text, offset, length);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.XMLStreamWriter2#writeRaw(java.lang.String)
	 */
	public void writeRaw(String text) throws Exception {
		xmlStreamWriter2.writeRaw(text);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.XMLStreamWriter2#writeRaw(java.lang.String, int,
	 * int)
	 */
	public void writeRaw(String text, int offset, int length) throws Exception {
		xmlStreamWriter2.writeRaw(text, offset, length);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.XMLStreamWriter2#writeSpace(char[], int, int)
	 */
	public void writeSpace(char[] text, int offset, int length) throws Exception {
		xmlStreamWriter2.writeSpace(text, offset, length);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.XMLStreamWriter2#writeSpace(java.lang.String)
	 */
	public void writeSpace(String text) throws Exception {
		xmlStreamWriter2.writeSpace(text);

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
	 * @see
	 * javax.xml.stream.XMLStreamWriter#writeStartDocument(java.lang.String)
	 */
	public void writeStartDocument(String version) throws Exception {
		xmlStreamWriter2.writeStartDocument(version);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.xml.stream.XMLStreamWriter#writeStartDocument(java.lang.String,
	 * java.lang.String)
	 */
	public void writeStartDocument(String encoding, String version) throws Exception {
		xmlStreamWriter2.writeStartDocument(encoding, version);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.XMLStreamWriter2#writeStartDocument(java.lang.String,
	 * java.lang.String, boolean)
	 */
	public void writeStartDocument(String version, String encoding, boolean standAlone) throws Exception {
		xmlStreamWriter2.writeStartDocument(version, encoding, standAlone);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamWriter#writeStartElement(java.lang.String)
	 */
	public void writeStartElement(String localName) throws Exception {
		xmlStreamWriter2.writeStartElement(localName);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamWriter#writeStartElement(java.lang.String,
	 * java.lang.String)
	 */
	public void writeStartElement(String namespaceURI, String localName) throws Exception {
		xmlStreamWriter2.writeStartElement(namespaceURI, localName);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamWriter#writeStartElement(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public void writeStartElement(String prefix, String localName, String namespaceURI) throws Exception {
		xmlStreamWriter2.writeStartElement(prefix, localName, namespaceURI);

	}

}
