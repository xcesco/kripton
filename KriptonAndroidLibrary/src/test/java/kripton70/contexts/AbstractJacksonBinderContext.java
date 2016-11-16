package kripton70.contexts;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import kripton70.core.BinderParser;
import kripton70.core.BinderSerializer;
import kripton70.persistence.JacksonSerializer;

import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;

public abstract class AbstractJacksonBinderContext extends AbstractBinderContext {
	public JsonFactory innerFactory;

	public AbstractJacksonBinderContext() {
		innerFactory = createInnerFactory();
	}
	
	public abstract JsonFactory createInnerFactory();
	
	public BinderParser createParser(byte[] data) {
		try {
			return new BinderParser(this, innerFactory.createParser(data), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public BinderParser createParser(File file) {
		try {
			return new BinderParser(this, innerFactory.createParser(file), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public BinderParser createParser(InputStream in) {
		try {
			return new BinderParser(this, innerFactory.createParser(in), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public BinderParser createParser(Reader reader) {
		try {
			return new BinderParser(this, innerFactory.createParser(reader), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public BinderParser createParser(String content) {
		try {
			return new BinderParser(this, innerFactory.createParser(content), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}
	
	public BinderSerializer createSerializer(File file, JsonEncoding encoding) {
		try {
			return new JacksonSerializer(this, innerFactory.createGenerator(file, encoding), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}
	
	public BinderSerializer createSerializer(OutputStream out, JsonEncoding encoding) {
		try {
			return new JacksonSerializer(this, innerFactory.createGenerator(out, encoding), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	public BinderSerializer createSerializer(Writer writer) {
		try {
			return new JacksonSerializer(this, innerFactory.createGenerator(writer), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}
	
}
