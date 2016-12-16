package com.abubusoft.kripton;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;

import org.codehaus.stax2.XMLOutputFactory2;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.XMLStreamWriter2;

import com.abubusoft.kripton.AbstractContext;
import com.abubusoft.kripton.BinderType;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.XmlWrapperParser;
import com.abubusoft.kripton.persistence.XmlWrapperSerializer;
import com.fasterxml.jackson.core.JsonEncoding;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class KriptonXmlContext extends AbstractContext {

	public XmlWrapperParser createParser(byte[] data) {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
	    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
			XMLStreamReader2 xmlStreamReader = (XMLStreamReader2) inputFactory.createXMLStreamReader(inputStream);
			return new XmlWrapperParser(this,xmlStreamReader, getSupportedFormat());
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public XmlWrapperParser createParser(File file) {
        try {
        	FileInputStream inputStream = new FileInputStream(file);
    	    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			XMLStreamReader2 xmlStreamReader = (XMLStreamReader2) inputFactory.createXMLStreamReader(inputStream);
			return new XmlWrapperParser(this,xmlStreamReader, getSupportedFormat());
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public XmlWrapperParser createParser(InputStream in) {
        try {
        	XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			XMLStreamReader2 xmlStreamReader = (XMLStreamReader2) inputFactory.createXMLStreamReader(in);
			return new XmlWrapperParser(this,xmlStreamReader, getSupportedFormat());
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public XmlWrapperParser createParser(Reader reader) {	    
        try {
        	XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			XMLStreamReader2 xmlStreamReader = (XMLStreamReader2) inputFactory.createXMLStreamReader(reader);
			return new XmlWrapperParser(this,xmlStreamReader, getSupportedFormat());
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public XmlWrapperParser createParser(String content) {		
        try {
        	ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes(JsonEncoding.UTF8.toString()));
    	    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			XMLStreamReader2 xmlStreamReader = (XMLStreamReader2) inputFactory.createXMLStreamReader(inputStream);
			return new XmlWrapperParser(this,xmlStreamReader, getSupportedFormat());
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public XmlWrapperSerializer createSerializer(File file) {
		return createSerializer(file, JsonEncoding.UTF8);
	}
	
	public XmlWrapperSerializer createSerializer(File file, JsonEncoding encoding) {
		XMLOutputFactory2 xmlOutputFactory = (XMLOutputFactory2) XMLOutputFactory.newFactory();
        try {
			XMLStreamWriter2 xmlStreamWriter = (XMLStreamWriter2) xmlOutputFactory.createXMLStreamWriter(new FileOutputStream(file), encoding.toString());
			return new XmlWrapperSerializer(xmlStreamWriter, getSupportedFormat());
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public XmlWrapperSerializer createSerializer(OutputStream out) {
		return createSerializer(out, JsonEncoding.UTF8);
	}

	public XmlWrapperSerializer createSerializer(OutputStream out, JsonEncoding encoding) {
		XMLOutputFactory2 xmlOutputFactory = (XMLOutputFactory2) XMLOutputFactory.newFactory();
        try {
			XMLStreamWriter2 xmlStreamWriter = (XMLStreamWriter2) xmlOutputFactory.createXMLStreamWriter(out, encoding.toString());
			return new XmlWrapperSerializer(xmlStreamWriter, getSupportedFormat());
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public XmlWrapperSerializer createSerializer(Writer writer) {
		XMLOutputFactory2 xmlOutputFactory = (XMLOutputFactory2) XMLOutputFactory.newFactory();
        try {
			XMLStreamWriter2 xmlStreamWriter = (XMLStreamWriter2) xmlOutputFactory.createXMLStreamWriter(writer, JsonEncoding.UTF8.toString());
			return new XmlWrapperSerializer(xmlStreamWriter, getSupportedFormat());
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}				
	}

	@Override
	public BinderType getSupportedFormat() {
		return BinderType.XML;
	}
	


}
