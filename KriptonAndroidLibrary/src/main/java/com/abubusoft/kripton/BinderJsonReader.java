package com.abubusoft.kripton;

import java.io.InputStream;
import java.io.Reader;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;

public interface BinderJsonReader extends BinderReader {

	<E> List<E> readList(Class<E> type, String input) throws ReaderException;

	<E> List<E> readList(Class<E> type, InputStream source) throws ReaderException;

	<E> List<E> readList(Class<E> type, Reader source) throws ReaderException, MappingException;

	<L extends Collection<E>, E> L readCollection(L collection, Class<E> type, String input) throws ReaderException;

	<L extends Collection<E>, E> L readCollection(L collection, Class<E> type, InputStream source) throws ReaderException;

	<L extends Collection<E>, E> L readCollection(L collection, Class<E> type, Reader source) throws ReaderException, MappingException;
	
	<M extends Map<K, V>, K, V> M readMap(M map, Class<K> keyType, Class<V> valueType, String input) throws ReaderException;

	<M extends Map<K, V>, K, V> M readMap(M map, Class<K> keyType, Class<V> valueType, InputStream source) throws ReaderException;

	<M extends Map<K, V>, K, V> M readMap(M map, Class<K> keyType, Class<V> valueType, Reader source) throws ReaderException, MappingException;
	
}
