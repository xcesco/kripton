package kripton70.contexts;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;

import kripton70.core.BinderParser;
import kripton70.core.BinderSerializer;
import kripton70.persistence.JacksonSerializer;

public abstract class AbstractJacksonBinderContext extends AbstractBinderContext {
	public JsonFactory innerFactory;

	public AbstractJacksonBinderContext() {
		innerFactory = createInnerFactory();
	}
	
	public abstract JsonFactory createInnerFactory();
	
	public BinderParser createParser(byte[] data) throws IOException {
		return new BinderParser(this, innerFactory.createParser(data), getSupportedFormat());
	}

	public BinderParser createParser(File file) throws IOException {
		return new BinderParser(this, innerFactory.createParser(file), getSupportedFormat());
	}

	public BinderParser createParser(InputStream in) throws IOException {
		return new BinderParser(this, innerFactory.createParser(in), getSupportedFormat());
	}

	public BinderParser createParser(Reader reader) throws IOException {
		return new BinderParser(this, innerFactory.createParser(reader), getSupportedFormat());
	}

	public BinderParser createParser(String content) throws IOException {
		return new BinderParser(this, innerFactory.createParser(content), getSupportedFormat());
	}
	
	public BinderSerializer createSerializer(File file, JsonEncoding encoding) throws IOException {
		return new JacksonSerializer(this, innerFactory.createGenerator(file, encoding), getSupportedFormat());
	}
	
	public BinderSerializer createSerializer(OutputStream out, JsonEncoding encoding) throws IOException {
		return new JacksonSerializer(this, innerFactory.createGenerator(out, encoding), getSupportedFormat());
	}

	public BinderSerializer createSerializer(Writer writer) throws IOException {
		return new JacksonSerializer(this, innerFactory.createGenerator(writer), getSupportedFormat());
	}
	
}
