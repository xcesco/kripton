package com.abubusoft.kripton.binder2.context;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import com.abubusoft.kripton.binder2.core.ParameterizedType;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;

public abstract class JacksonContext extends AbstractContext implements BinderContext<JacksonWrapperSerializer, JacksonWrapperParser> {

	public JsonFactory innerFactory;

	public JacksonContext() {
		innerFactory = createInnerFactory();
	}

	public abstract JsonFactory createInnerFactory();

	@Override
	public JacksonWrapperParser createParser(byte[] data) {
		return createParser(new ByteArrayInputStream(data));
	}

	@Override
	public JacksonWrapperParser createParser(File file) {
		try {
			return new JacksonWrapperParser(innerFactory.createParser(file), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	@Override
	public JacksonWrapperParser createParser(InputStream in) {
		try {
			return new JacksonWrapperParser(innerFactory.createParser(in), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	@Override
	public JacksonWrapperParser createParser(Reader reader) {
		try {
			return new JacksonWrapperParser(innerFactory.createParser(reader), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	@Override
	public JacksonWrapperParser createParser(String content) {
		try {
			return new JacksonWrapperParser(innerFactory.createParser(content), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	@Override
	public JacksonWrapperSerializer createSerializer(File file) {
		return createSerializer(file, JsonEncoding.UTF8);
	}

	@Override
	public JacksonWrapperSerializer createSerializer(File file, JsonEncoding encoding) {
		try {
			return new JacksonWrapperSerializer(innerFactory.createGenerator(file, encoding), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}
	
	@Override
	public JacksonWrapperSerializer createSerializer(OutputStream out) {
		return createSerializer(out, JsonEncoding.UTF8);
	}
	
	@Override
	public JacksonWrapperSerializer createSerializer(OutputStream out, JsonEncoding encoding) {
		try {
			return new JacksonWrapperSerializer(innerFactory.createGenerator(out, encoding), getSupportedFormat());
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	@Override
	public JacksonWrapperSerializer createSerializer(Writer writer) {
		try {
			return new JacksonWrapperSerializer(innerFactory.createGenerator(writer), getSupportedFormat());
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
		if (object==null) return null;
		return mapperFor((Class<E>)object.getClass()).serialize(this, object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E> void serialize(E object, OutputStream os) {
		if (object==null) return;
		mapperFor((Class<E>) object.getClass()).serialize(this, object, os);		
	}

	@Override
	public <E> String serialize(E object, ParameterizedType<E> parameterizedType) {
		if (object==null) return null;
		return mapperFor(parameterizedType).serialize(this, object);
	}

	@Override
	public <E> void serialize(E object, ParameterizedType<E> parameterizedType, OutputStream os) {
		if (object==null) return;
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
