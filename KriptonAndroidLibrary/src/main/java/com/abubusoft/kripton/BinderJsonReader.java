package com.abubusoft.kripton;

import java.io.InputStream;
import java.io.Reader;
import java.util.Collection;

import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;

public interface BinderJsonReader extends BinderReader {

	<E> Collection<E> readCollection(Class<E> type, String input) throws ReaderException;

	<E> Collection<E> readCollection(Class<E> type, InputStream source) throws ReaderException;

	<E> Collection<E> readCollection(Class<E> type, Reader source) throws ReaderException, MappingException;

	<L extends Collection<E>, E> L readCollection(L list, Class<E> type, String input) throws ReaderException;

	<L extends Collection<E>, E> L readCollection(L list, Class<E> type, InputStream source) throws ReaderException;

	<L extends Collection<E>, E> L readCollection(L list, Class<E> type, Reader source) throws ReaderException, MappingException;
	
}
