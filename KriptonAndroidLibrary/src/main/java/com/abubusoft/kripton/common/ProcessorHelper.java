package com.abubusoft.kripton.common;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderJsonReader;
import com.abubusoft.kripton.BinderJsonWriter;
import com.abubusoft.kripton.BinderOptions;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.exception.WriterException;

/**
 * @author Francesco Benincasa
 *
 */
public abstract class ProcessorHelper {
	
	/**
	 * Json writer. It's need to write embedded field
	 */
	protected static ThreadLocal<BinderJsonWriter> objWriter;
	
	/**
	 * json reader. It's need to read embedded field
	 */
	protected static ThreadLocal<BinderJsonReader> objReader;

	
	public static String asString(Collection<?> value)
	{
		BinderJsonWriter writer = getWriter();
		
		String result;
		try {
			result = writer.writeCollection(value);
			return result;
		} catch (MappingException e) {
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		}
		
		return null;		
	}
	
	/**
	 * Convert a list in an UTF-8 byte array
	 * 
	 * @param value
	 * @return
	 * 		UTF-8 byte array
	 */
	public static byte[] asByteArray(Collection<?> value)
	{
		BinderJsonWriter writer = getWriter();
		
		String result;
		try {
			result = writer.writeCollection(value);
			return result.getBytes(StandardCharsets.UTF_8);
		} catch (MappingException e) {
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		}
		
		return null;		
	}
	
	public static byte[] asByteArray(Collection<?> value, Charset charset)
	{
		BinderJsonWriter writer = getWriter();
		
		String result;
		try {
			result = writer.writeCollection(value);
			return result.getBytes(charset);
		} catch (MappingException e) {
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		}
		
		return null;		
	}

	public static BinderJsonWriter getWriter() {
		if (objWriter==null)
		{
			objWriter=new ThreadLocal<BinderJsonWriter>() {

				@Override
				protected BinderJsonWriter initialValue() {
					return BinderFactory.getJSONWriter(BinderOptions.build().encoding(BinderOptions.ENCODING_UTF_8));
				}
				
			};			
		}
		
		BinderJsonWriter writer = objWriter.get();
		return writer;
	}
	
	/**
	 * convert blog (byte[]) in a list of element of type clazz
	 * @param clazz
	 * 			item class
	 * @param input
	 * 			blob
	 * @return
	 * 			list of element extracted from blob
	 */
	public static <E> List<E> asList(Class<E> clazz, byte[] input)
	{		
		BinderJsonReader reader = getReader();
		
		try {
			List<E> result = reader.readCollection(new ArrayList<E>(), clazz, new String(input));
			return result;
		} catch (MappingException e) {
			e.printStackTrace();
		} catch (ReaderException e) {
			e.printStackTrace();
		} 
		
		return null;		
	}
	
	/**
	 * convert blog (byte[]) in a list of element of type clazz
	 * @param clazz
	 * 			item class
	 * @param input
	 * 			blob
	 * @return
	 * 			list of element extracted from blob
	 */
	public static <E> List<E> asList(Class<E> clazz, String input)
	{		
		BinderJsonReader reader = getReader();
		
		try {
			List<E> result = reader.readCollection(new ArrayList<E>(),clazz, input);
			return result;
		} catch (MappingException e) {
			e.printStackTrace();
		} catch (ReaderException e) {
			e.printStackTrace();
		} 
		
		return null;		
	}
	
	/**
	 * convert blog (byte[]) in a collection of element of type clazz
	 * 
	 * @param collection
	 * @param clazz
	 * 			item class
	 * @param input
	 * 			blob
	 * @return
	 * 			list of element extracted from blob
	 */
	public static <E, L extends Collection<E>> L asCollection(L collection, Class<E> clazz, byte[] input)
	{		
		BinderJsonReader reader = getReader();
		
		try {
			L result = (L) reader.readCollection(collection, clazz, new String(input));
			return result;
		} catch (MappingException e) {
			e.printStackTrace();
		} catch (ReaderException e) {
			e.printStackTrace();
		} 
		
		return null;		
	}
	
	/**
	 * convert blog (byte[]) in a collection of element of type clazz
	 * 
	 * @param list
	 * @param clazz
	 * 			item class
	 * @param input
	 * 			blob
	 * @return
	 * 			list of element extracted from blob
	 */
	public static <E, L extends Collection<E>> L asCollection(L list, Class<E> clazz, String input)
	{		
		BinderJsonReader reader = getReader();
		
		try {
			L result = (L) reader.readCollection(list, clazz, input);
			return result;
		} catch (MappingException e) {
			e.printStackTrace();
		} catch (ReaderException e) {
			e.printStackTrace();
		} 
		
		return null;		
	}

	public static BinderJsonReader getReader() {
		if (objReader==null)
		{
			objReader=new ThreadLocal<BinderJsonReader>() {

				@Override
				protected BinderJsonReader initialValue() {
					return (BinderJsonReader) BinderFactory.getJSONReader(BinderOptions.build().encoding(BinderOptions.ENCODING_UTF_8));
				}
				
			};			
		}
		
		return objReader.get();
	}
}
