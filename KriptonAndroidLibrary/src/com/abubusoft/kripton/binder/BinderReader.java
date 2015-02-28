package com.abubusoft.kripton.binder;

import java.io.InputStream;
import java.io.Reader;

import com.abubusoft.kripton.binder.exception.MappingException;
import com.abubusoft.kripton.binder.exception.ReaderException;


/**
 * BinderReader read or deserialize XML or JSON into POJO guided by schema.
 * 
 * @author bulldog
 *
 */
public interface BinderReader {
	
	/**
	 * Read or deserialize an input stream into a POJO of specific type.
	 * 
	 * @param type target type
	 * @param source an input stream
	 * @return POJO of target type
	 * @throws ReaderException if reads fail
	 */
	public <T> T read(Class<? extends T>type, InputStream source) throws ReaderException, MappingException;
	
	/**
	 * Read or deserialize an string into a POJO of specific type. 
	 * 
	 * @param type target type
	 * @param source a string
	 * @return POJO of target type
	 * @throws ReaderException if reads fail
	 */
	public <T> T read(Class<? extends T>type, String source) throws ReaderException, MappingException;
	
	/**
	 * Read or deserialize a character stream reader into a POJO of specific type.
	 * 
	 * @param type target type
	 * @param source a character stream reader
	 * @return POJO of target type
	 * @throws ReaderException if reads fail
	 */
	public <T> T read(Class<? extends T>type, Reader source) throws ReaderException, MappingException;

}
