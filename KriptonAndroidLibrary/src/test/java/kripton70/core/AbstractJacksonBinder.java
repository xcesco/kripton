package kripton70.core;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;

public abstract class AbstractJacksonBinder extends AbstractBinder implements Binder2Json {
	public abstract JsonFactory createInnerFactory();
	
	public AbstractJacksonBinder() {
		innerFactory = createInnerFactory();
	}

	public JsonFactory innerFactory;

	public BinderGenerator createGenerator(DataOutput out) throws IOException {
		return createGenerator(out, JsonEncoding.UTF8);
	}

	public BinderGenerator createGenerator(OutputStream out) throws IOException {
		return createGenerator(out, JsonEncoding.UTF8);
	}

	public BinderGenerator createGenerator(Writer writer) throws IOException {
		return new BinderGenerator(this, innerFactory.createGenerator(writer), getSupportedFormat());
	}

	public BinderGenerator createGenerator(DataOutput out, JsonEncoding encoding) throws IOException {
		return new BinderGenerator(this, innerFactory.createGenerator(out, encoding), getSupportedFormat());
	}

	public BinderGenerator createGenerator(File file, JsonEncoding encoding) throws IOException {
		return new BinderGenerator(this, innerFactory.createGenerator(file, encoding), getSupportedFormat());
	}

	public BinderGenerator createGenerator(File file) throws IOException {
		return createGenerator(file, JsonEncoding.UTF8);
	}

	public BinderGenerator createGenerator(OutputStream out, JsonEncoding encoding) throws IOException {
		return new BinderGenerator(this, innerFactory.createGenerator(out, encoding), getSupportedFormat());
	}

	public BinderParser createParser(byte[] data) throws IOException {
		return new BinderParser(this, innerFactory.createParser(data), getSupportedFormat());
	}

	public BinderParser createParser(char[] content) throws IOException {
		return new BinderParser(this, innerFactory.createParser(content), getSupportedFormat());
	}

	public BinderParser createParser(DataInput in) throws IOException {
		return new BinderParser(this, innerFactory.createParser(in), getSupportedFormat());
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

	public BinderParser createParser(URL url) throws IOException {
		return new BinderParser(this, innerFactory.createParser(url), getSupportedFormat());
	}

}
