package kripton70.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;

import javax.xml.stream.XMLInputFactory;

import org.codehaus.stax2.XMLInputFactory2;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;

import kripton70.contexts.BinderContext;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class XmlBinder extends AbstractBinder {

	@Override
	public BinderType getSupportedFormat() {
		return BinderType.XML;
	}

	@Override
	public JsonFactory createInnerFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BinderSerializer createGenerator(File file) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BinderSerializer createGenerator(File file, JsonEncoding encoding) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BinderSerializer createGenerator(OutputStream out) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BinderSerializer createGenerator(OutputStream out, JsonEncoding encoding) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BinderSerializer createGenerator(Writer writer) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
