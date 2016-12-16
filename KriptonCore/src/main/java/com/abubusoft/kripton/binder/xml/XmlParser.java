/**
 * 
 */
package com.abubusoft.kripton.binder.xml;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;

import org.codehaus.stax2.AttributeInfo;
import org.codehaus.stax2.DTDInfo;
import org.codehaus.stax2.LocationInfo;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.typed.Base64Variant;
import org.codehaus.stax2.typed.TypedArrayDecoder;
import org.codehaus.stax2.typed.TypedValueDecoder;
import org.codehaus.stax2.validation.ValidationProblemHandler;
import org.codehaus.stax2.validation.XMLValidationSchema;
import org.codehaus.stax2.validation.XMLValidator;

/**
 * @author xcesco
 *
 */
public class XmlParser {

	private XMLStreamReader2 xmlStreamReader;

	public XmlParser(XMLStreamReader2 xmlStreamReader2) {
		this.xmlStreamReader = xmlStreamReader2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.typed.TypedXMLStreamReader#getElementAsBoolean()
	 */
	public boolean getElementAsBoolean() throws Exception {
		return xmlStreamReader.getElementAsBoolean();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.typed.TypedXMLStreamReader#getElementAsInt()
	 */
	public int getElementAsInt() throws Exception {
		return xmlStreamReader.getElementAsInt();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.typed.TypedXMLStreamReader#getElementAsLong()
	 */
	public long getElementAsLong() throws Exception {
		return xmlStreamReader.getElementAsLong();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.typed.TypedXMLStreamReader#getElementAsFloat()
	 */
	public float getElementAsFloat() throws Exception {
		return xmlStreamReader.getElementAsFloat();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.typed.TypedXMLStreamReader#getElementAsDouble()
	 */
	public double getElementAsDouble() throws Exception {
		return xmlStreamReader.getElementAsDouble();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.typed.TypedXMLStreamReader#getElementAsInteger()
	 */
	public BigInteger getElementAsInteger() throws Exception {
		return xmlStreamReader.getElementAsInteger();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.typed.TypedXMLStreamReader#getElementAsDecimal()
	 */
	public BigDecimal getElementAsDecimal() throws Exception {
		return xmlStreamReader.getElementAsDecimal();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.typed.TypedXMLStreamReader#getElementAsQName()
	 */
	public QName getElementAsQName() throws Exception {
		return xmlStreamReader.getElementAsQName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.typed.TypedXMLStreamReader#getElementAsBinary()
	 */
	public byte[] getElementAsBinary() throws Exception {
		return xmlStreamReader.getElementAsBinary();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamReader#getElementAsBinary(org.
	 * codehaus.stax2.typed.Base64Variant)
	 */
	public byte[] getElementAsBinary(Base64Variant variant) throws Exception {
		return xmlStreamReader.getElementAsBinary(variant);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamReader#getElementAs(org.codehaus.
	 * stax2.typed.TypedValueDecoder)
	 */
	public void getElementAs(TypedValueDecoder tvd) throws Exception {
		xmlStreamReader.getElementAs(tvd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamReader#readElementAsBinary(byte[],
	 * int, int, org.codehaus.stax2.typed.Base64Variant)
	 */
	public int readElementAsBinary(byte[] resultBuffer, int offset, int maxLength, Base64Variant variant) throws Exception {
		return xmlStreamReader.readElementAsBinary(resultBuffer, offset, maxLength, variant);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamReader#readElementAsBinary(byte[],
	 * int, int)
	 */
	public int readElementAsBinary(byte[] resultBuffer, int offset, int maxLength) throws Exception {
		return xmlStreamReader.readElementAsBinary(resultBuffer, offset, maxLength);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamReader#readElementAsIntArray(int[]
	 * , int, int)
	 */
	public int readElementAsIntArray(int[] resultBuffer, int offset, int length) throws Exception {
		return xmlStreamReader.readElementAsIntArray(resultBuffer, offset, length);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamReader#readElementAsLongArray(long
	 * [], int, int)
	 */
	public int readElementAsLongArray(long[] resultBuffer, int offset, int length) throws Exception {
		return xmlStreamReader.readElementAsLongArray(resultBuffer, offset, length);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamReader#readElementAsFloatArray(
	 * float[], int, int)
	 */
	public int readElementAsFloatArray(float[] resultBuffer, int offset, int length) throws Exception {
		return xmlStreamReader.readElementAsFloatArray(resultBuffer, offset, length);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamReader#readElementAsDoubleArray(
	 * double[], int, int)
	 */
	public int readElementAsDoubleArray(double[] resultBuffer, int offset, int length) throws Exception {
		return xmlStreamReader.readElementAsDoubleArray(resultBuffer, offset, length);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamReader#readElementAsArray(org.
	 * codehaus.stax2.typed.TypedArrayDecoder)
	 */
	public int readElementAsArray(TypedArrayDecoder tad) throws Exception {
		return xmlStreamReader.readElementAsArray(tad);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamReader#getAttributeIndex(java.lang
	 * .String, java.lang.String)
	 */
	public int getAttributeIndex(String namespaceURI, String localName) {
		return xmlStreamReader.getAttributeIndex(namespaceURI, localName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamReader#getAttributeAsBoolean(int)
	 */
	public boolean getAttributeAsBoolean(int index) throws Exception {
		return xmlStreamReader.getAttributeAsBoolean(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.typed.TypedXMLStreamReader#getAttributeAsInt(int)
	 */
	public int getAttributeAsInt(int index) throws Exception {
		return xmlStreamReader.getAttributeAsInt(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamReader#getAttributeAsLong(int)
	 */
	public long getAttributeAsLong(int index) throws Exception {
		return xmlStreamReader.getAttributeAsLong(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamReader#getAttributeAsFloat(int)
	 */
	public float getAttributeAsFloat(int index) throws Exception {
		return xmlStreamReader.getAttributeAsFloat(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamReader#getAttributeAsDouble(int)
	 */
	public double getAttributeAsDouble(int index) throws Exception {
		return xmlStreamReader.getAttributeAsDouble(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamReader#getAttributeAsInteger(int)
	 */
	public BigInteger getAttributeAsInteger(int index) throws Exception {
		return xmlStreamReader.getAttributeAsInteger(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamReader#getAttributeAsDecimal(int)
	 */
	public BigDecimal getAttributeAsDecimal(int index) throws Exception {
		return xmlStreamReader.getAttributeAsDecimal(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamReader#getAttributeAsQName(int)
	 */
	public QName getAttributeAsQName(int index) throws Exception {
		return xmlStreamReader.getAttributeAsQName(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.typed.TypedXMLStreamReader#getAttributeAs(int,
	 * org.codehaus.stax2.typed.TypedValueDecoder)
	 */
	public void getAttributeAs(int index, TypedValueDecoder tvd) throws Exception {
		xmlStreamReader.getAttributeAs(index, tvd);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamReader#getAttributeAsBinary(int)
	 */
	public byte[] getAttributeAsBinary(int index) throws Exception {
		return xmlStreamReader.getAttributeAsBinary(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamReader#getAttributeAsBinary(int,
	 * org.codehaus.stax2.typed.Base64Variant)
	 */
	public byte[] getAttributeAsBinary(int index, Base64Variant v) throws Exception {
		return xmlStreamReader.getAttributeAsBinary(index, v);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamReader#getAttributeAsIntArray(int)
	 */
	public int[] getAttributeAsIntArray(int index) throws Exception {
		return xmlStreamReader.getAttributeAsIntArray(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamReader#getAttributeAsLongArray(
	 * int)
	 */
	public long[] getAttributeAsLongArray(int index) throws Exception {
		return xmlStreamReader.getAttributeAsLongArray(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamReader#getAttributeAsFloatArray(
	 * int)
	 */
	public float[] getAttributeAsFloatArray(int index) throws Exception {
		return xmlStreamReader.getAttributeAsFloatArray(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamReader#getAttributeAsDoubleArray(
	 * int)
	 */
	public double[] getAttributeAsDoubleArray(int index) throws Exception {
		return xmlStreamReader.getAttributeAsDoubleArray(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.typed.TypedXMLStreamReader#getAttributeAsArray(int,
	 * org.codehaus.stax2.typed.TypedArrayDecoder)
	 */
	public int getAttributeAsArray(int index, TypedArrayDecoder tad) throws Exception {
		return xmlStreamReader.getAttributeAsArray(index, tad);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getProperty(java.lang.String)
	 */
	public Object getProperty(String name) throws IllegalArgumentException {
		return xmlStreamReader.getProperty(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#next()
	 */
	public int next() throws Exception {
		return xmlStreamReader.next();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#require(int, java.lang.String,
	 * java.lang.String)
	 */
	public void require(int type, String namespaceURI, String localName) throws Exception {
		xmlStreamReader.require(type, namespaceURI, localName);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getElementText()
	 */
	public String getElementText() throws Exception {
		return xmlStreamReader.getElementText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#nextTag()
	 */
	public int nextTag() throws Exception {
		return xmlStreamReader.nextTag();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#hasNext()
	 */
	public boolean hasNext() throws Exception {
		return xmlStreamReader.hasNext();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#close()
	 */
	public void close() throws Exception {
		xmlStreamReader.close();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getNamespaceURI(java.lang.String)
	 */
	public String getNamespaceURI(String prefix) {
		return xmlStreamReader.getNamespaceURI(prefix);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#isStartElement()
	 */
	public boolean isStartElement() {
		return xmlStreamReader.isStartElement();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#isEndElement()
	 */
	public boolean isEndElement() {
		return xmlStreamReader.isEndElement();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#isCharacters()
	 */
	public boolean isCharacters() {
		return xmlStreamReader.isCharacters();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#isWhiteSpace()
	 */
	public boolean isWhiteSpace() {
		return xmlStreamReader.isWhiteSpace();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getAttributeValue(java.lang.String,
	 * java.lang.String)
	 */
	public String getAttributeValue(String namespaceURI, String localName) {
		return xmlStreamReader.getAttributeValue(namespaceURI, localName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getAttributeCount()
	 */
	public int getAttributeCount() {
		return xmlStreamReader.getAttributeCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getAttributeName(int)
	 */
	public QName getAttributeName(int index) {
		return xmlStreamReader.getAttributeName(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getAttributeNamespace(int)
	 */
	public String getAttributeNamespace(int index) {
		return xmlStreamReader.getAttributeNamespace(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getAttributeLocalName(int)
	 */
	public String getAttributeLocalName(int index) {
		return xmlStreamReader.getAttributeLocalName(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getAttributePrefix(int)
	 */
	public String getAttributePrefix(int index) {
		return xmlStreamReader.getAttributePrefix(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getAttributeType(int)
	 */
	public String getAttributeType(int index) {
		return xmlStreamReader.getAttributeType(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getAttributeValue(int)
	 */
	public String getAttributeValue(int index) {
		return xmlStreamReader.getAttributeValue(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#isAttributeSpecified(int)
	 */
	public boolean isAttributeSpecified(int index) {
		return xmlStreamReader.isAttributeSpecified(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getNamespaceCount()
	 */
	public int getNamespaceCount() {
		return xmlStreamReader.getNamespaceCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getNamespacePrefix(int)
	 */
	public String getNamespacePrefix(int index) {
		return xmlStreamReader.getNamespacePrefix(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getNamespaceURI(int)
	 */
	public String getNamespaceURI(int index) {
		return xmlStreamReader.getNamespaceURI(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getNamespaceContext()
	 */
	public NamespaceContext getNamespaceContext() {
		return xmlStreamReader.getNamespaceContext();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getEventType()
	 */
	public int getEventType() {
		return xmlStreamReader.getEventType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getText()
	 */
	public String getText() {
		return xmlStreamReader.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getTextCharacters()
	 */
	public char[] getTextCharacters() {
		return xmlStreamReader.getTextCharacters();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getTextCharacters(int, char[], int,
	 * int)
	 */
	public int getTextCharacters(int sourceStart, char[] target, int targetStart, int length) throws Exception {
		return xmlStreamReader.getTextCharacters(sourceStart, target, targetStart, length);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getTextStart()
	 */
	public int getTextStart() {
		return xmlStreamReader.getTextStart();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getTextLength()
	 */
	public int getTextLength() {
		return xmlStreamReader.getTextLength();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getEncoding()
	 */
	public String getEncoding() {
		return xmlStreamReader.getEncoding();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#hasText()
	 */
	public boolean hasText() {
		return xmlStreamReader.hasText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getLocation()
	 */
	public Location getLocation() {
		return xmlStreamReader.getLocation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getName()
	 */
	public QName getName() {
		return xmlStreamReader.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getLocalName()
	 */
	public String getLocalName() {
		return xmlStreamReader.getLocalName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#hasName()
	 */
	public boolean hasName() {
		return xmlStreamReader.hasName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getNamespaceURI()
	 */
	public String getNamespaceURI() {
		return xmlStreamReader.getNamespaceURI();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getPrefix()
	 */
	public String getPrefix() {
		return xmlStreamReader.getPrefix();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getVersion()
	 */
	public String getVersion() {
		return xmlStreamReader.getVersion();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#isStandalone()
	 */
	public boolean isStandalone() {
		return xmlStreamReader.isStandalone();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#standaloneSet()
	 */
	public boolean standaloneSet() {
		return xmlStreamReader.standaloneSet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getCharacterEncodingScheme()
	 */
	public String getCharacterEncodingScheme() {
		return xmlStreamReader.getCharacterEncodingScheme();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getPITarget()
	 */
	public String getPITarget() {
		return xmlStreamReader.getPITarget();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.stream.XMLStreamReader#getPIData()
	 */
	public String getPIData() {
		return xmlStreamReader.getPIData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.validation.Validatable#validateAgainst(org.codehaus.
	 * stax2.validation.XMLValidationSchema)
	 */
	public XMLValidator validateAgainst(XMLValidationSchema schema) throws Exception {
		return xmlStreamReader.validateAgainst(schema);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.validation.Validatable#stopValidatingAgainst(org.
	 * codehaus.stax2.validation.XMLValidationSchema)
	 */
	public XMLValidator stopValidatingAgainst(XMLValidationSchema schema) throws Exception {
		return xmlStreamReader.stopValidatingAgainst(schema);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.validation.Validatable#stopValidatingAgainst(org.
	 * codehaus.stax2.validation.XMLValidator)
	 */
	public XMLValidator stopValidatingAgainst(XMLValidator validator) throws Exception {
		return xmlStreamReader.stopValidatingAgainst(validator);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.validation.Validatable#setValidationProblemHandler(org
	 * .codehaus.stax2.validation.ValidationProblemHandler)
	 */
	public ValidationProblemHandler setValidationProblemHandler(ValidationProblemHandler h) {
		return xmlStreamReader.setValidationProblemHandler(h);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.XMLStreamReader2#isPropertySupported(java.lang.String)
	 */
	public boolean isPropertySupported(String name) {
		return xmlStreamReader.isPropertySupported(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.XMLStreamReader2#setProperty(java.lang.String,
	 * java.lang.Object)
	 */
	public boolean setProperty(String name, Object value) {
		return xmlStreamReader.setProperty(name, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.XMLStreamReader2#getFeature(java.lang.String)
	 */
	@Deprecated
	public Object getFeature(String name) {
		return xmlStreamReader.getFeature(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.XMLStreamReader2#setFeature(java.lang.String,
	 * java.lang.Object)
	 */
	@Deprecated
	public void setFeature(String name, Object value) {
		xmlStreamReader.setFeature(name, value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.XMLStreamReader2#skipElement()
	 */
	public void skipElement() throws Exception {
		xmlStreamReader.skipElement();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.XMLStreamReader2#getDTDInfo()
	 */
	public DTDInfo getDTDInfo() throws Exception {
		return xmlStreamReader.getDTDInfo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.XMLStreamReader2#getAttributeInfo()
	 */
	public AttributeInfo getAttributeInfo() throws Exception {
		return xmlStreamReader.getAttributeInfo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.XMLStreamReader2#getLocationInfo()
	 */
	public LocationInfo getLocationInfo() {
		return xmlStreamReader.getLocationInfo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.XMLStreamReader2#getText(java.io.Writer, boolean)
	 */
	public int getText(Writer w, boolean preserveContents) throws IOException, XMLStreamException {
		return xmlStreamReader.getText(w, preserveContents);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.XMLStreamReader2#isEmptyElement()
	 */
	public boolean isEmptyElement() throws Exception {
		return xmlStreamReader.isEmptyElement();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.XMLStreamReader2#getDepth()
	 */
	public int getDepth() {
		return xmlStreamReader.getDepth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codehaus.stax2.XMLStreamReader2#getNonTransientNamespaceContext()
	 */
	public NamespaceContext getNonTransientNamespaceContext() {
		return xmlStreamReader.getNonTransientNamespaceContext();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.XMLStreamReader2#getPrefixedName()
	 */
	public String getPrefixedName() {
		return xmlStreamReader.getPrefixedName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.stax2.XMLStreamReader2#closeCompletely()
	 */
	public void closeCompletely() throws Exception {
		xmlStreamReader.closeCompletely();
	}

}
