package com.abubusoft.kripton.binder2.context;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import com.abubusoft.kripton.binder2.BinderType;
//import com.abubusoft.kripton.binder2.core.ParameterizedType;
import com.abubusoft.kripton.binder2.persistence.ParserWrapper;
import com.abubusoft.kripton.binder2.persistence.SerializerWrapper;
import com.fasterxml.jackson.core.JsonEncoding;

public interface BinderContext<S extends SerializerWrapper, P extends ParserWrapper> {

	P createParser(byte[] data);

	P createParser(File file);

	P createParser(InputStream in);

	P createParser(Reader reader);

	P createParser(String content);

	S createSerializer(File file);

	S createSerializer(File file, JsonEncoding encoding);

	S createSerializer(OutputStream out);

	S createSerializer(OutputStream out, JsonEncoding encoding);

	S createSerializer(Writer writer);

	BinderType getSupportedFormat();

	<E> E parse(InputStream is, Class<E> objectClazz);
	
	<E> E parse(byte[] is, Class<E> objectClazz);

//	<E> E parse(InputStream is, ParameterizedType<E> objectType);

	<E> E parse(String buffer, Class<E> objectClazz);

	//<E> E parse(String buffer, ParameterizedType<E> objectType);

	<E> List<E> parseList(InputStream is, Class<E> objectClazz);

	<E> List<E> parseList(String jsonString, Class<E> objectClazz);

	<E> String serialize(E object);

	<E> void serialize(E object, OutputStream os);

	//<E> String serialize(E object, ParameterizedType<E> parameterizedType);

	//<E> void serialize(E object, ParameterizedType<E> parameterizedType, OutputStream os);

	<E> String serialize(List<E> list, Class<E> objectClazz);

	<E> void serialize(List<E> list, OutputStream os, Class<E> objectClazz);
}
