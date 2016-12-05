package com.abubusoft.kripton.binder2.context;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import com.abubusoft.kripton.binder2.BinderType;
import com.abubusoft.kripton.binder2.persistence.ParserWrapper;
import com.abubusoft.kripton.binder2.persistence.SerializerWrapper;
import com.fasterxml.jackson.core.JsonEncoding;

public interface BinderContext {

	ParserWrapper createParser(byte[] data);

	ParserWrapper createParser(File file);

	ParserWrapper createParser(InputStream in);

	ParserWrapper createParser(Reader reader);

	ParserWrapper createParser(String content);

	SerializerWrapper createSerializer(File file);

	SerializerWrapper createSerializer(File file, JsonEncoding encoding);

	SerializerWrapper createSerializer(OutputStream out);

	SerializerWrapper createSerializer(OutputStream out, JsonEncoding encoding);

	SerializerWrapper createSerializer(Writer writer);

	BinderType getSupportedFormat();
	
	<E> E parse(byte[] is, Class<E> objectClazz);

	<E> E parse(File source, Class<E> objectClazz);
	
	<E> E parse(InputStream is, Class<E> objectClazz);
	
	<E> E parse(Reader source, Class<E> objectClazz);

	<E> E parse(String buffer, Class<E> objectClazz);
	
	<L extends Collection<E>, E> L parseCollection(L collection, Class<E> type, byte[] is);
	
	<L extends Collection<E>, E> L parseCollection(L collection, Class<E> type, InputStream source);	

	<L extends Collection<E>, E> L parseCollection(L collection, Class<E> type, Reader source);
	
	<E> List<E> parseList(Class<E> type, byte[] is);
	
	<E> List<E> parseList(Class<E> type, InputStream source);	

	<E> List<E> parseList(Class<E> type, Reader source);

	//<E> String serialize(E object);
	
	<E> byte[] serialize(E object);

	<E> void serialize(E object, File source);

	<E> void serialize(E object, OutputStream os);

	<E> void serialize(E object, ParameterizedType parameterizedType, OutputStream os);
	
	<E> String serializeCollection(Collection<E> list, Class<E> objectClazz);
	
	<E> void serializeCollection(Collection<E> list, Class<E> objectClazz, OutputStream os);

}
