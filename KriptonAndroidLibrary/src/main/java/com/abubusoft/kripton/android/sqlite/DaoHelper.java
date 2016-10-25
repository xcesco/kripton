package com.abubusoft.kripton.android.sqlite;

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
public abstract class DaoHelper {
	
	/**
	 * Json writer. It's need to write embedded field
	 */
	protected static ThreadLocal<BinderJsonWriter> objWriter;
	
	/**
	 * json reader. It's need to read embedded field
	 */
	protected static ThreadLocal<BinderJsonReader> objReader;

	
	public static String toString(List<?> value)
	{
		BinderJsonWriter writer = getWriter();
		
		String result;
		try {
			result = writer.writeList(value);
			return result;
		} catch (MappingException e) {
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		}
		
		return null;		
	}
	
	public static byte[] toByteArray(List<?> value)
	{
		BinderJsonWriter writer = getWriter();
		
		String result;
		try {
			result = writer.writeList(value);
			return result.getBytes();
		} catch (MappingException e) {
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		}
		
		return null;		
	}

	/**
	 * @return
	 */
	private static BinderJsonWriter getWriter() {
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
	
	public static <E> List<E> toList(Class<E> clazz, byte[] input)
	{		
		BinderJsonReader reader = getReader();
		
		try {
			List<E> result = reader.readList(clazz, new String(input));
			return result;
		} catch (MappingException e) {
			e.printStackTrace();
		} catch (ReaderException e) {
			e.printStackTrace();
		} 
		
		return null;		
	}

	/**
	 * @return 
	 * 
	 */
	private static BinderJsonReader getReader() {
		if (objReader==null)
		{
			objReader=new ThreadLocal<BinderJsonReader>() {

				@Override
				protected BinderJsonReader initialValue() {
					return BinderFactory.getJSONReader(BinderOptions.build().encoding(BinderOptions.ENCODING_UTF_8));
				}
				
			};			
		}
		
		return objReader.get();
	}
}
