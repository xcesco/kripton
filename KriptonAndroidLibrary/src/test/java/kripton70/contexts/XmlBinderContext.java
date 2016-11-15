package kripton70.contexts;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import org.codehaus.stax2.XMLOutputFactory2;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.XMLStreamWriter2;

import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;

import kripton70.core.BinderParser;
import kripton70.core.BinderSerializer;
import kripton70.core.BinderType;
import kripton70.persistence.XmlSerializer;

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

	public BinderSerializer createSerializer(File file) throws IOException {
		return createSerializer(file, JsonEncoding.UTF8);
	}

	public BinderSerializer createSerializer(File file, JsonEncoding encoding) throws IOException {
		XMLOutputFactory2 xmlOutputFactory = (XMLOutputFactory2) XMLOutputFactory.newFactory();
        try {
			XMLStreamWriter2 xmlStreamWriter = (XMLStreamWriter2) xmlOutputFactory.createXMLStreamWriter(new FileOutputStream(file), encoding.toString());
			return new XmlSerializer(this, xmlStreamWriter, getSupportedFormat());
		} catch (XMLStreamException e) {
			e.printStackTrace();
			return null;
		}
	}

	public BinderSerializer createSerializer(OutputStream out) throws IOException {
		return createSerializer(out, JsonEncoding.UTF8);
	}

	public BinderSerializer createSerializer(OutputStream out, JsonEncoding encoding) throws IOException {
		XMLOutputFactory2 xmlOutputFactory = (XMLOutputFactory2) XMLOutputFactory.newFactory();
        try {
			XMLStreamWriter2 xmlStreamWriter = (XMLStreamWriter2) xmlOutputFactory.createXMLStreamWriter(out, encoding.toString());
			return new XmlSerializer(this, xmlStreamWriter, getSupportedFormat());
		} catch (XMLStreamException e) {
			e.printStackTrace();
			return null;
		}
	}

	public BinderSerializer createSerializer(Writer writer) throws IOException {
		XMLOutputFactory2 xmlOutputFactory = (XMLOutputFactory2) XMLOutputFactory.newFactory();
        try {
			XMLStreamWriter2 xmlStreamWriter = (XMLStreamWriter2) xmlOutputFactory.createXMLStreamWriter(writer, JsonEncoding.UTF8.toString());
			return new XmlSerializer(this, xmlStreamWriter, getSupportedFormat());
		} catch (XMLStreamException e) {
			e.printStackTrace();
			return null;
		}				
	}
	
	public BinderParser createParser(byte[] data) throws IOException {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
	    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
			XMLStreamReader2 xmlStreamReader = (XMLStreamReader2) inputFactory.createXMLStreamReader(inputStream);
			return new BinderParser(this,xmlStreamReader, getSupportedFormat());
		} catch (XMLStreamException e) {
			e.printStackTrace();
			return null;
		}
	}

	public BinderParser createParser(File file) throws IOException {
		FileInputStream inputStream = new FileInputStream(file);
	    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
			XMLStreamReader2 xmlStreamReader = (XMLStreamReader2) inputFactory.createXMLStreamReader(inputStream);
			return new BinderParser(this,xmlStreamReader, getSupportedFormat());
		} catch (XMLStreamException e) {
			e.printStackTrace();
			return null;
		}
	}

	public BinderParser createParser(InputStream in) throws IOException {
	    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
			XMLStreamReader2 xmlStreamReader = (XMLStreamReader2) inputFactory.createXMLStreamReader(in);
			return new BinderParser(this,xmlStreamReader, getSupportedFormat());
		} catch (XMLStreamException e) {
			e.printStackTrace();
			return null;
		}
	}

	public BinderParser createParser(Reader reader) throws IOException {
	    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
			XMLStreamReader2 xmlStreamReader = (XMLStreamReader2) inputFactory.createXMLStreamReader(reader);
			return new BinderParser(this,xmlStreamReader, getSupportedFormat());
		} catch (XMLStreamException e) {
			e.printStackTrace();
			return null;
		}
	}

	public BinderParser createParser(String content) throws IOException {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes(JsonEncoding.UTF8.toString()));
	    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
			XMLStreamReader2 xmlStreamReader = (XMLStreamReader2) inputFactory.createXMLStreamReader(inputStream);
			return new BinderParser(this,xmlStreamReader, getSupportedFormat());
		} catch (XMLStreamException e) {
			e.printStackTrace();
			return null;
		}
	}

}
