package com.abubusoft.kripton;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.XmlWrapperParser;
import com.abubusoft.kripton.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.fasterxml.jackson.core.JsonEncoding;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class KriptonXmlContext extends AbstractContext {

	public XmlWrapperParser createParser(byte[] data) {		
        try {
        	ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
			return new XmlWrapperParser(this,inputStream, getSupportedFormat());
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public XmlWrapperParser createParser(File file) {
        try {
        	FileInputStream inputStream = new FileInputStream(file);
			return new XmlWrapperParser(this,inputStream, getSupportedFormat());
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public XmlWrapperParser createParser(InputStream inputStream) {
        try {
			return new XmlWrapperParser(this, inputStream, getSupportedFormat());
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public XmlWrapperParser createParser(Reader reader) {	    
        try {
			return new XmlWrapperParser(this, reader, getSupportedFormat());
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public XmlWrapperParser createParser(String content) {		
        try {
        	ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes(JsonEncoding.UTF8.toString()));
			return new XmlWrapperParser(this, inputStream, getSupportedFormat());
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public XmlWrapperSerializer createSerializer(File file) {
		return createSerializer(file, JsonEncoding.UTF8);
	}
	
	public XmlWrapperSerializer createSerializer(File file, JsonEncoding encoding) {
        try {
        	
        	XMLSerializer xmlStreamWriter=new XMLSerializer(new FileWriter(file));
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
        try {
        	XMLSerializer xmlStreamWriter=new XMLSerializer(new OutputStreamWriter(out, encoding.getJavaName()));
			return new XmlWrapperSerializer(xmlStreamWriter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public XmlWrapperSerializer createSerializer(Writer output) {
        try {
        	XMLSerializer xmlStreamWriter=new XMLSerializer(output);
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
