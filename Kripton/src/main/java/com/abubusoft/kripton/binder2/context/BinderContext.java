package com.abubusoft.kripton.binder2.context;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.Collection;
import java.util.List;

import com.abubusoft.kripton.binder2.BinderType;

public interface BinderContext {

	BinderType getSupportedFormat();
	
	<E> E parse(byte[] is, Class<E> objectClazz);

	<E> E parse(File source, Class<E> objectClazz);
	
	<E> E parse(InputStream is, Class<E> objectClazz);
	
	<E> E parse(Reader source, Class<E> objectClazz);

	<E> E parse(String buffer, Class<E> objectClazz);
	
	<L extends Collection<E>, E> L parseCollection(L collection, Class<E> type, String source);
	
	<L extends Collection<E>, E> L parseCollection(L collection, Class<E> type, byte[] is);
	
	<L extends Collection<E>, E> L parseCollection(L collection, Class<E> type, InputStream source);	

	<L extends Collection<E>, E> L parseCollection(L collection, Class<E> type, Reader source);
	
	<E> List<E> parseList(Class<E> type, byte[] source);
	
	<E> List<E> parseList(Class<E> type, String source);
	
	<E> List<E> parseList(Class<E> type, InputStream source);	

	<E> List<E> parseList(Class<E> type, Reader source);

	<E> String serialize(E object);
	
	<E> void serialize(E object, File source);

	<E> void serialize(E object, OutputStream os);

	<E> String serializeCollection(Collection<E> list, Class<E> objectClazz);
	
	<E> void serializeCollection(Collection<E> list, Class<E> objectClazz, OutputStream source);
	
	<E> void serializeCollection(Collection<E> list, Class<E> objectClazz, File source);
	

}
