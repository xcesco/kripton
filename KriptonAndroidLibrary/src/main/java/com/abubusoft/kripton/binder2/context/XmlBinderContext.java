package com.abubusoft.kripton.binder2.context;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import org.codehaus.stax2.XMLOutputFactory2;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.XMLStreamWriter2;

import com.abubusoft.kripton.binder2.BinderType;
import com.abubusoft.kripton.binder2.persistence.SerializerWrapper;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperParser;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonEncoding;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class XmlBinderContext extends AbstractContext {

	public XmlWrapperParser createParser(byte[] data) {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
	    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
			XMLStreamReader2 xmlStreamReader = (XMLStreamReader2) inputFactory.createXMLStreamReader(inputStream);
			return new XmlWrapperParser(this,xmlStreamReader, getSupportedFormat());
		} catch (XMLStreamException e) {
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
		} catch (XMLStreamException | FileNotFoundException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public XmlWrapperParser createParser(InputStream in) {
        try {
        	XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			XMLStreamReader2 xmlStreamReader = (XMLStreamReader2) inputFactory.createXMLStreamReader(in);
			return new XmlWrapperParser(this,xmlStreamReader, getSupportedFormat());
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public XmlWrapperParser createParser(Reader reader) {	    
        try {
        	XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			XMLStreamReader2 xmlStreamReader = (XMLStreamReader2) inputFactory.createXMLStreamReader(reader);
			return new XmlWrapperParser(this,xmlStreamReader, getSupportedFormat());
		} catch (XMLStreamException e) {
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
		} catch (XMLStreamException | UnsupportedEncodingException e) {
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
		} catch (XMLStreamException | FileNotFoundException e) {
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
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public XmlWrapperSerializer createSerializer(Writer writer) {
		XMLOutputFactory2 xmlOutputFactory = (XMLOutputFactory2) XMLOutputFactory.newFactory();
        try {
			XMLStreamWriter2 xmlStreamWriter = (XMLStreamWriter2) xmlOutputFactory.createXMLStreamWriter(writer, JsonEncoding.UTF8.toString());
			return new XmlWrapperSerializer(xmlStreamWriter, getSupportedFormat());
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}				
	}

	@Override
	public BinderType getSupportedFormat() {
		return BinderType.XML;
	}

	@Override
	public <L extends Collection<E>, E> L parseCollection(L collection, Class<E> type, byte[] source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <L extends Collection<E>, E> L parseCollection(L collection, Class<E> type, InputStream source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <L extends Collection<E>, E> L parseCollection(L collection, Class<E> type, Reader source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> parseList(Class<E> type, byte[] is) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> parseList(Class<E> type, InputStream source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> parseList(Class<E> type, Reader source) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public <E> String serializeCollection(Collection<E> collection, Class<E> objectClazz) {
		StringWriter sw = new StringWriter();

		SerializerWrapper serializer = createSerializer(sw);		
		mapperFor((Class<E>)objectClazz).serializeCollection(this, serializer, collection);
		serializer.close();
		return sw.toString();		
	}
	@Override
	public <E> void serializeCollection(Collection<E> list, Class<E> objectClazz, OutputStream source) {		
		SerializerWrapper serializer = createSerializer(source);		
		mapperFor(objectClazz).serializeCollection(this, serializer, list);
		serializer.close();		
	}

}
