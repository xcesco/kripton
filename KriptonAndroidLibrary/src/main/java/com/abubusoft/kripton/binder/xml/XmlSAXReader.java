package com.abubusoft.kripton.binder.xml;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.abubusoft.kripton.BinderReader;
import com.abubusoft.kripton.BinderOptions;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.binder.transform.Transformer;
import com.abubusoft.kripton.common.TypeReflector;

/**
 * BinderReader implementation using Java SAX parser
 * 
 * @author bulldog
 * @author xcesco
 *
 */
public class XmlSAXReader implements BinderReader {

	private static SAXParserFactory spf;
	private BinderOptions format;

	/**
	 * holder for thread.
	 * 
	 * @author xcesco
	 *
	 */
	static class Holder {
		XmlReaderHelper helper;
		XmlReaderHandler saxHandler;
		XMLReader xmlReader;
	};

	protected static final ThreadLocal<Holder> localBuilder = new ThreadLocal<XmlSAXReader.Holder>() {

		@Override
		protected Holder initialValue() {
			Holder holder = new Holder();

			SAXParser saxParser;
			try {
				saxParser = spf.newSAXParser();

				holder.xmlReader = saxParser.getXMLReader();
				holder.helper = new XmlReaderHelper();
				holder.saxHandler = new XmlReaderHandler(holder.helper);

			} catch (ParserConfigurationException | SAXException e) {
				e.printStackTrace();
				throw (new RuntimeException("Cannot create SAX parser. Reason: " + e.getMessage()));
			}

			return holder;
		}

	};

	public XmlSAXReader() {
		this(new BinderOptions());
	}

	public XmlSAXReader(BinderOptions format) {
		this.format = format;
		spf = SAXParserFactory.newInstance();
		spf.setNamespaceAware(true);
	}

	public <T> T read(Class<? extends T> type, InputStream source) throws ReaderException, MappingException {
		try {
			return this.read(type, new InputStreamReader(source, format.getEncoding()));
		} catch (UnsupportedEncodingException e) {
			throw new ReaderException("Encoding is not supported", e);
		}
	}

	public <T> T read(Class<? extends T> type, String source) throws ReaderException, MappingException {
		return this.read(type, new StringReader(source));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> T read(Class<? extends T> type, Reader source) throws ReaderException, MappingException {

		validate(type, source);

		try {

			Holder holder = localBuilder.get();
			holder.helper.reset();

			Constructor con = null;
			try {
				con = TypeReflector.getConstructor(type);
			} catch (NoSuchMethodException nsme) {
				throw new ReaderException("No-arg contructor is missing, type = " + type.getName());
			}
			Object obj = con.newInstance();

			// put root object
			holder.helper.valueStack.push(obj);
			
			holder.xmlReader.setContentHandler(holder.saxHandler);
			holder.xmlReader.parse(new InputSource(source));

			if (holder.helper.valueStack.size() == 1) { 
				// has one and only one object
				// left on the stack
				return (T) holder.helper.valueStack.pop(); 
				// read is successful, just
				// return the object
			} else {
				throw new ReaderException("Error to read/descrialize object, no result to return");
			}
		} catch (SAXException se) {
			if (se.getException() instanceof MappingException) {
				MappingException me = (MappingException) (se.getException());
				throw me;
			}
			if (se.getException() instanceof ReaderException) {
				ReaderException re = (ReaderException) (se.getException());
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
			throw new ReaderException("Can not read primitive or enum type object, " + "only Nano bindable complex type object can be accepted!");
		}
	}

}
