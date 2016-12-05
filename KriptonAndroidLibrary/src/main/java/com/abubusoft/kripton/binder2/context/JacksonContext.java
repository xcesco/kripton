package com.abubusoft.kripton.binder2.context;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.abubusoft.kripton.binder2.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.binder2.persistence.ParserWrapper;
import com.abubusoft.kripton.binder2.persistence.SerializerWrapper;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;

public abstract class JacksonContext extends AbstractContext implements BinderContext {

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
	public <L extends Collection<E>, E> L parseCollection(L collection, Class<E> type, byte[] is) {
		JacksonWrapperParser wrappedParser = createParser(is);		
		return mapperFor(type).parseCollection(this, wrappedParser, collection);
	}
	
	@Override
	public <L extends Collection<E>, E> L parseCollection(L collection, Class<E> type, InputStream source) {
		JacksonWrapperParser wrappedParser = createParser(source);		
		return mapperFor(type).parseCollection(this, wrappedParser, collection);
	}
	
	@Override
	public <L extends Collection<E>, E> L parseCollection(L collection, Class<E> type, Reader source) {
		JacksonWrapperParser wrappedParser = createParser(source);		
		return mapperFor(type).parseCollection(this, wrappedParser, collection);
	}

	@Override
	public <E> List<E> parseList(Class<E> type, byte[] source) {
		JacksonWrapperParser wrappedParser = createParser(source);		
		return mapperFor(type).parseCollection(this, wrappedParser, new ArrayList<E>());
	}
	
	@Override
	public <E> List<E> parseList(Class<E> type, InputStream source) {
		JacksonWrapperParser wrappedParser = createParser(source);		
		return mapperFor(type).parseCollection(this, wrappedParser, new ArrayList<E>());
	}

/*	@Override
	public <E> E parse(InputStream is, ParameterizedType<E> objectType) {
		return mapperFor(objectType).parse(this, is);
	}*/

	@Override
	public <E> List<E> parseList(Class<E> type, Reader source) {
		JacksonWrapperParser wrappedParser = createParser(source);		
		return mapperFor(type).parseCollection(this, wrappedParser, new ArrayList<E>());
	}


	@SuppressWarnings("unchecked")
	@Override
	public <E> void serialize(E object, OutputStream os) {
		if (object==null) return;
		mapperFor((Class<E>) object.getClass()).serialize(this, object, os);		
	}

	@Override
	public <E> String serializeCollection(Collection<E> list, Class<E> objectClazz) {
		return mapperFor(objectClazz).serializeCollection(this, list);
	}

	@Override
	public <E> void serializeCollection(Collection<E> list, Class<E> objectClazz, OutputStream os) {
		mapperFor(objectClazz).serializeCollection(this, list, os);
	}

}
