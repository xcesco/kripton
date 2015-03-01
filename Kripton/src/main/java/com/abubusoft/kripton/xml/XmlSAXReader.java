package com.abubusoft.kripton.xml;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.abubusoft.kripton.binder.BinderReader;
import com.abubusoft.kripton.binder.Format;
import com.abubusoft.kripton.binder.exception.MappingException;
import com.abubusoft.kripton.binder.exception.ReaderException;
import com.abubusoft.kripton.binder.transform.Transformer;
import com.abubusoft.kripton.reflect.TypeReflector;


/**
 * BinderReader implementation using Java SAX parser
 * 
 * @author bulldog
 *
 */
public class XmlSAXReader implements BinderReader {
	
    private SAXParserFactory spf;
	private Format format;
	
	public XmlSAXReader() {
		this(new Format());
	}
	
	public XmlSAXReader(Format format) {
		this.format = format;
		spf = SAXParserFactory.newInstance();
		spf.setNamespaceAware(true);
	}

	public <T> T read(Class<? extends T> type, InputStream source)
			throws ReaderException, MappingException {
		try {
			return this.read(type, new InputStreamReader(source, format.getEncoding()));
		} catch (UnsupportedEncodingException e) {
			throw new ReaderException("Encoding is not supported", e);
		}
	}

	public <T> T read(Class<? extends T> type, String source)
			throws ReaderException, MappingException {
		return this.read(type, new StringReader(source));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> T read(Class<? extends T> type, Reader source)
			throws ReaderException, MappingException {
		
		validate(type, source);
		
		try {
			
			SAXParser saxParser = spf.newSAXParser();
			XMLReader xmlReader = saxParser.getXMLReader();
			
			XmlReaderHelper helper = new XmlReaderHelper();
			
			Constructor con = null;
			try {
				con = TypeReflector.getConstructor(type);
			} catch (NoSuchMethodException nsme) {
				throw new ReaderException("No-arg contructor is missing, type = " + type.getName());
			}
			Object obj = con.newInstance();
			helper.valueStack.push(obj);
			
			XmlReaderHandler saxHandler = new XmlReaderHandler(helper);
			xmlReader.setContentHandler(saxHandler);
			
			xmlReader.parse(new InputSource(source));
			
			if (helper.valueStack.size() == 1) { // has one and only one object left on the stack
				return (T)helper.valueStack.pop(); // read is successful, just return the object
			} else {
				throw new ReaderException("Error to read/descrialize object, no result to return");
			}
		} catch (SAXException se) {
			if (se.getException() instanceof MappingException) {
				MappingException me = (MappingException)(se.getException());
				throw me;
			}
			if (se.getException() instanceof ReaderException) {
				ReaderException re = (ReaderException)(se.getException());
				throw re;
			}
			throw new ReaderException("Error to read/descrialize object", se);
		} catch (ReaderException re) {
			throw re;
		} catch (Exception e) {
			throw new ReaderException("Error to read/descrialize object", e);
		}
		
	}
	
	private <T> void validate(Class<? extends T> type, Reader reader) throws ReaderException {
		if (type == null) {
			throw new ReaderException("Can not read, type is null!");
		}
		
		if (reader == null) {
			throw new ReaderException("Reader is null!");
		}
		
		if (Transformer.isPrimitive(type)) {
			throw new ReaderException("Can not read primitive or enum type object, " +
					"only Nano bindable complex type object can be accepted!");
		}
	}

}
