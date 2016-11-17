package kripton70.contexts;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import kripton70.core.ParameterizedType;
import kripton70.persistence.JacksonParser;
import kripton70.persistence.JacksonSerializer;

import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;

public abstract class JacksonContext extends AbstractContext implements BinderContext<JacksonSerializer, JacksonParser> {

	public JsonFactory innerFactory;

	public JacksonContext() {
		innerFactory = createInnerFactory();
	}

	public abstract JsonFactory createInnerFactory();

	@Override
	public JacksonParser createParser(byte[] data) {
		return createParser(new ByteArrayInputStream(data));
	}

	@Override
	public JacksonParser createParser(File file) {
		try {
			return new JacksonParser(innerFactory.createParser(file), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	@Override
	public JacksonParser createParser(InputStream in) {
		try {
			return new JacksonParser(innerFactory.createParser(in), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	@Override
	public JacksonParser createParser(Reader reader) {
		try {
			return new JacksonParser(innerFactory.createParser(reader), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	@Override
	public JacksonParser createParser(String content) {
		try {
			return new JacksonParser(innerFactory.createParser(content), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	@Override
	public JacksonSerializer createSerializer(File file) {
		return createSerializer(file, JsonEncoding.UTF8);
	}

	@Override
	public JacksonSerializer createSerializer(File file, JsonEncoding encoding) {
		try {
			return new JacksonSerializer(innerFactory.createGenerator(file, encoding), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}
	
	@Override
	public JacksonSerializer createSerializer(OutputStream out) {
		return createSerializer(out, JsonEncoding.UTF8);
	}
	
	@Override
	public JacksonSerializer createSerializer(OutputStream out, JsonEncoding encoding) {
		try {
			return new JacksonSerializer(innerFactory.createGenerator(out, encoding), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	@Override
	public JacksonSerializer createSerializer(Writer writer) {
		try {
			return new JacksonSerializer(innerFactory.createGenerator(writer), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}
	
	@Override
	public <E> E parse(InputStream is, Class<E> objectClazz) {
		return mapperFor(objectClazz).parse(this, is);
	}

	@Override
	public <E> E parse(InputStream is, ParameterizedType<E> objectType) {
		return mapperFor(objectType).parse(this, is);
	}

	@Override
	public <E> E parse(String buffer, Class<E> objectClazz) {
		return mapperFor(objectClazz).parse(this, buffer);
	}

	@Override
	public <E> E parse(String buffer, ParameterizedType<E> objectType) {
		return mapperFor(objectType).parse(this, buffer);
	}

	@Override
	public <E> List<E> parseList(InputStream is, Class<E> objectClazz) {
		return mapperFor(objectClazz).parseList(this, is);
	}

	@Override
	public <E> List<E> parseList(String buffer, Class<E> objectClazz) {
		return mapperFor(objectClazz).parseList(this, buffer);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E> String serialize(E object) {
		return mapperFor((Class<E>)object.getClass()).serialize(this, object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E> void serialize(E object, OutputStream os) {
		mapperFor((Class<E>) object.getClass()).serialize(this, object, os);		
	}

	@Override
	public <E> String serialize(E object, ParameterizedType<E> parameterizedType) {
		return mapperFor(parameterizedType).serialize(this, object);
	}

	@Override
	public <E> void serialize(E object, ParameterizedType<E> parameterizedType, OutputStream os) {
		mapperFor(parameterizedType).serialize(this, object, os);
	}

	@Override
	public <E> String serialize(List<E> list, Class<E> objectClazz) {
		return mapperFor(objectClazz).serialize(this, list);
	}

	@Override
	public <E> void serialize(List<E> list, OutputStream os, Class<E> objectClazz) {
		mapperFor(objectClazz).serialize(this, list, os);
	}

}
