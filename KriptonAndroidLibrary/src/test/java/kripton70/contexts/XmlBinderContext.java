package kripton70.contexts;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import kripton70.core.BinderSerializer;
import kripton70.core.BinderType;
import kripton70.persistence.JacksonParser;
import kripton70.persistence.XmlSerializer;

import org.codehaus.stax2.XMLOutputFactory2;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.XMLStreamWriter2;

import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class XmlBinderContext extends AbstractBinderContext {

	@Override
	public BinderType getSupportedFormat() {
		return BinderType.XML;
	}

	@Override
	public JsonFactory createInnerFactory() {
		throw new KriptonRuntimeException("Not supported here!");
	}

	public BinderSerializer createSerializer(File file) {
		return createSerializer(file, JsonEncoding.UTF8);
	}

	public BinderSerializer createSerializer(File file, JsonEncoding encoding) {
		XMLOutputFactory2 xmlOutputFactory = (XMLOutputFactory2) XMLOutputFactory.newFactory();
        try {
			XMLStreamWriter2 xmlStreamWriter = (XMLStreamWriter2) xmlOutputFactory.createXMLStreamWriter(new FileOutputStream(file), encoding.toString());
			return new XmlSerializer(this, xmlStreamWriter, getSupportedFormat());
		} catch (XMLStreamException | FileNotFoundException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public BinderSerializer createSerializer(OutputStream out) {
		return createSerializer(out, JsonEncoding.UTF8);
	}

	public BinderSerializer createSerializer(OutputStream out, JsonEncoding encoding) {
		XMLOutputFactory2 xmlOutputFactory = (XMLOutputFactory2) XMLOutputFactory.newFactory();
        try {
			XMLStreamWriter2 xmlStreamWriter = (XMLStreamWriter2) xmlOutputFactory.createXMLStreamWriter(out, encoding.toString());
			return new XmlSerializer(this, xmlStreamWriter, getSupportedFormat());
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public BinderSerializer createSerializer(Writer writer) {
		XMLOutputFactory2 xmlOutputFactory = (XMLOutputFactory2) XMLOutputFactory.newFactory();
        try {
			XMLStreamWriter2 xmlStreamWriter = (XMLStreamWriter2) xmlOutputFactory.createXMLStreamWriter(writer, JsonEncoding.UTF8.toString());
			return new XmlSerializer(this, xmlStreamWriter, getSupportedFormat());
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}				
	}
	
	public JacksonParser createParser(byte[] data) {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
	    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
			XMLStreamReader2 xmlStreamReader = (XMLStreamReader2) inputFactory.createXMLStreamReader(inputStream);
			return new JacksonParser(this,xmlStreamReader, getSupportedFormat());
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public JacksonParser createParser(File file) {
        try {
        	FileInputStream inputStream = new FileInputStream(file);
    	    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			XMLStreamReader2 xmlStreamReader = (XMLStreamReader2) inputFactory.createXMLStreamReader(inputStream);
			return new JacksonParser(this,xmlStreamReader, getSupportedFormat());
		} catch (XMLStreamException | FileNotFoundException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public JacksonParser createParser(InputStream in) {
        try {
        	XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			XMLStreamReader2 xmlStreamReader = (XMLStreamReader2) inputFactory.createXMLStreamReader(in);
			return new JacksonParser(this,xmlStreamReader, getSupportedFormat());
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public JacksonParser createParser(Reader reader) {	    
        try {
        	XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			XMLStreamReader2 xmlStreamReader = (XMLStreamReader2) inputFactory.createXMLStreamReader(reader);
			return new JacksonParser(this,xmlStreamReader, getSupportedFormat());
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public JacksonParser createParser(String content) {		
        try {
        	ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes(JsonEncoding.UTF8.toString()));
    	    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			XMLStreamReader2 xmlStreamReader = (XMLStreamReader2) inputFactory.createXMLStreamReader(inputStream);
			return new JacksonParser(this,xmlStreamReader, getSupportedFormat());
		} catch (XMLStreamException | UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

}
