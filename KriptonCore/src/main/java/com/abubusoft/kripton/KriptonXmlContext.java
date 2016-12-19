package com.abubusoft.kripton;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import org.codehaus.stax2.XMLOutputFactory2;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.XMLStreamWriter2;

import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.XmlWrapperParser;
import com.abubusoft.kripton.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.persistence.xml.internal.MXSerializer;
import com.ctc.wstx.stax.WstxInputFactory;
import com.ctc.wstx.stax.WstxOutputFactory;
import com.fasterxml.jackson.core.JsonEncoding;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class KriptonXmlContext extends AbstractContext {

	public XmlWrapperParser createParser(byte[] data) {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
		WstxInputFactory inputFactory = new WstxInputFactory();
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
        	WstxInputFactory inputFactory = new WstxInputFactory();
			XMLStreamReader2 xmlStreamReader = (XMLStreamReader2) inputFactory.createXMLStreamReader(inputStream);
			return new XmlWrapperParser(this,xmlStreamReader, getSupportedFormat());
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public XmlWrapperParser createParser(InputStream in) {
        try {
        	WstxInputFactory inputFactory = new WstxInputFactory();
			XMLStreamReader2 xmlStreamReader = (XMLStreamReader2) inputFactory.createXMLStreamReader(in);
			return new XmlWrapperParser(this,xmlStreamReader, getSupportedFormat());
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public XmlWrapperParser createParser(Reader reader) {	    
        try {
        	WstxInputFactory inputFactory = new WstxInputFactory();
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
        	WstxInputFactory inputFactory = new WstxInputFactory();
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
		//XMLOutputFactory2 xmlOutputFactory = (XMLOutputFactory2) new WstxOutputFactory();
        try {
        	
			//XMLStreamWriter2 xmlStreamWriter = (XMLStreamWriter2) xmlOutputFactory.createXMLStreamWriter(new FileOutputStream(file), encoding.toString());
        	MXSerializer xmlStreamWriter=new MXSerializer(new FileWriter(file));
			return new XmlWrapperSerializer(xmlStreamWriter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public XmlWrapperSerializer createSerializer(OutputStream out) {
		return createSerializer(out, JsonEncoding.UTF8);
	}

	public XmlWrapperSerializer createSerializer(OutputStream out, JsonEncoding encoding) {
		//XMLOutputFactory2 xmlOutputFactory = (XMLOutputFactory2) new WstxOutputFactory();
        try {
			//XMLStreamWriter2 xmlStreamWriter = (XMLStreamWriter2) xmlOutputFactory.createXMLStreamWriter(out, encoding.toString());
        	MXSerializer xmlStreamWriter=new MXSerializer(new OutputStreamWriter(out, encoding.getJavaName()));
			return new XmlWrapperSerializer(xmlStreamWriter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public XmlWrapperSerializer createSerializer(Writer writer) {
		//XMLOutputFactory2 xmlOutputFactory = (XMLOutputFactory2) new WstxOutputFactory();
        try {
			//XMLStreamWriter2 xmlStreamWriter = (XMLStreamWriter2) xmlOutputFactory.createXMLStreamWriter(writer, JsonEncoding.UTF8.toString());
        	MXSerializer xmlStreamWriter=new MXSerializer(writer);
			return new XmlWrapperSerializer(xmlStreamWriter);
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
