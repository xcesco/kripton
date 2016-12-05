package com.abubusoft.kripton.binder2.context;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.abubusoft.kripton.binder2.BinderType;
import com.abubusoft.kripton.binder2.KriptonBinder2;
import com.abubusoft.kripton.binder2.core.BinderMapper;
import com.abubusoft.kripton.binder2.persistence.ParserWrapper;
import com.abubusoft.kripton.binder2.persistence.SerializerWrapper;
//import com.abubusoft.kripton.binder2.core.ParameterizedType;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.exception.NoSuchMapperException;

public abstract class AbstractContext implements BinderContext  {

	@SuppressWarnings("rawtypes")
	private static final Map<Class, BinderMapper> OBJECT_MAPPERS = new ConcurrentHashMap<>();

	/**
	 * Returns a JsonMapper for a given class that has been annotated with @JsonObject.
	 *
	 * @param cls
	 *            The class for which the JsonMapper should be fetched.
	 */
	public <T, M extends BinderMapper<T>> M mapperFor(Class<T> cls) throws NoSuchMapperException {
		M mapper = getMapper(cls);

		if (mapper == null) {
			throw new NoSuchMapperException(cls, getSupportedFormat());
		} else {
			return mapper;
		}
	}
	
	public <E, M extends BinderMapper<E>> M mapperFor(ParameterizedType type) throws NoSuchMapperException {
		@SuppressWarnings("unchecked")
		M mapper = getMapper((Class<E>) type.getActualTypeArguments()[0]);
		if (mapper == null) {
			throw new NoSuchMapperException(type.getRawType(), getSupportedFormat());
		} else {
			return mapper;
		}
	}
	
	@Override
	public <E> E parse(Reader source, Class<E> objectClazz) {
		ParserWrapper parserWrapper=createParser(source);
		E result=mapperFor(objectClazz).parse(this, parserWrapper);
		parserWrapper.close();
		
		return result;
	}

	@Override
	public <E> E parse(InputStream source, Class<E> objectClazz) {
		ParserWrapper parserWrapper=createParser(source);
		E result=mapperFor(objectClazz).parse(this, parserWrapper);
		parserWrapper.close();
		
		return result;
	}
	
	@Override
	public <E> E parse(byte[] source, Class<E> objectClazz) {
		ParserWrapper parserWrapper=createParser(source);
		E result=mapperFor(objectClazz).parse(this, parserWrapper);
		parserWrapper.close();
		
		return result;
	}
	
	@Override
	public <E> E parse(File source, Class<E> objectClazz) {
		ParserWrapper parserWrapper=createParser(source);
		E result=mapperFor(objectClazz).parse(this, parserWrapper);
		parserWrapper.close();
		
		return result;
	}

	@Override
	public <E> E parse(String source, Class<E> objectClazz) {
		ParserWrapper parserWrapper=createParser(source);
		E result=mapperFor(objectClazz).parse(this, parserWrapper);
		parserWrapper.close();
		
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E> void serialize(E object, OutputStream source) {
		if (object==null) return;
		
		SerializerWrapper serializer = createSerializer(source);		
		mapperFor((Class<E>)object.getClass()).serialize(this, serializer, object);
		serializer.close();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E> void serialize(E object, File source) {
		if (object==null) return;
		
		SerializerWrapper serializer=createSerializer(source);
		mapperFor((Class<E>)object.getClass()).serialize(this, serializer, object);
		serializer.close();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E> String serialize(E object) {
		if (object==null) return null;
		
		StringWriter source=new StringWriter();
		SerializerWrapper serializer=createSerializer(source);
		mapperFor((Class<E>)object.getClass()).serialize(this, serializer, object);
		serializer.close();		
		
		return source.toString();
	}

	@Override
	public <E> void serialize(E object, ParameterizedType parameterizedType, OutputStream source) {
		
		
	}

	@Override
	public <L extends Collection<E>, E> L parseCollection(L collection, Class<E> type, String source) {
		if (collection==null || type==null) return null;
		
		ParserWrapper parser=createParser(source);
		L result = mapperFor(type).parseCollection(this, parser, collection);
		parser.close();		
		
		return result;
	}

	@Override
	public <L extends Collection<E>, E> L parseCollection(L collection, Class<E> type, byte[] source) {
		if (collection==null || type==null) return null;
		
		ParserWrapper parser=createParser(source);
		L result = mapperFor(type).parseCollection(this, parser, collection);
		parser.close();		
		
		return result;
	}

	@Override
	public <L extends Collection<E>, E> L parseCollection(L collection, Class<E> type, InputStream source) {
		if (collection==null || type==null) return null;
		
		ParserWrapper parser=createParser(source);
		L result = mapperFor(type).parseCollection(this, parser, collection);
		parser.close();		
		
		return result;
	}

	@Override
	public <L extends Collection<E>, E> L parseCollection(L collection, Class<E> type, Reader source) {
		if (collection==null || type==null) return null;
		
		ParserWrapper parser=createParser(source);
		L result = mapperFor(type).parseCollection(this, parser, collection);
		parser.close();		
		
		return result;
	}

	@Override
	public <E> List<E> parseList(Class<E> type, byte[] source) {
		return parseCollection(new ArrayList<E>(), type, source);
	}

	@Override
	public <E> List<E> parseList(Class<E> type, String source) {
		return parseCollection(new ArrayList<E>(), type, source);
	}

	@Override
	public <E> List<E> parseList(Class<E> type, InputStream source) {
		return parseCollection(new ArrayList<E>(), type, source);
	}

	@Override
	public <E> List<E> parseList(Class<E> type, Reader source) {
		return parseCollection(new ArrayList<E>(), type, source);
	}

	@Override
	public <E> String serializeCollection(Collection<E> collection, Class<E> objectClazz) {
		if (collection==null) return null;
		
		StringWriter sw = new StringWriter();
		SerializerWrapper serializer = createSerializer(sw);		
		mapperFor((Class<E>)objectClazz).serializeCollection(this, serializer, collection);
		serializer.close();
		return sw.toString();		
	}
	
	@Override
	public <E> void serializeCollection(Collection<E> collection, Class<E> objectClazz, OutputStream source) {
		if (collection==null) return;
		
		SerializerWrapper serializer = createSerializer(source);		
		mapperFor(objectClazz).serializeCollection(this, serializer, collection);
		serializer.close();		
	}
	
	@Override
	public <E> void serializeCollection(Collection<E> collection, Class<E> objectClazz, File source) {
		if (collection==null) return;
		
		SerializerWrapper serializer = createSerializer(source);		
		mapperFor(objectClazz).serializeCollection(this, serializer, collection);
		serializer.close();		
	}

	@SuppressWarnings("unchecked")
	public <E, M extends BinderMapper<E>> M getMapper(Class<E> cls) {
		M mapper = (M) OBJECT_MAPPERS.get(cls);
		if (mapper == null) {
			// The only way the mapper wouldn't already be loaded into
			// OBJECT_MAPPERS is if it was compiled separately, but let's handle
			// it anyway
			try {
				Class<E> mapperClass = (Class<E>) Class.forName(cls.getName() + KriptonBinder2.MAPPER_CLASS_SUFFIX);
				mapper = (M) mapperClass.newInstance();
				// mapper.
				OBJECT_MAPPERS.put(cls, mapper);
			} catch (Exception e) {
				e.printStackTrace();
				throw new KriptonRuntimeException(e);
			}
		}
		return mapper;
	}

	public abstract BinderType getSupportedFormat();

}
