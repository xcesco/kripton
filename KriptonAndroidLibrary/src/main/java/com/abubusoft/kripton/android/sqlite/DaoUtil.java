package com.abubusoft.kripton.android.sqlite;

import java.util.List;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderOptions;
import com.abubusoft.kripton.BinderReader;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.exception.WriterException;

/**
 * @author Francesco Benincasa
 *
 */
public abstract class DaoUtil {

	@BindType
	public static class WrappedArray<E>
	{
		public WrappedArray()
		{			
		}
		
		public WrappedArray(E[] value)
		{
			this.value=value;
		}
		
		public E[] value; 
	}
	
	@BindType
	public static class WrappedList<E>
	{
		public WrappedList()
		{
			
		}
		
		public WrappedList(List<E> value)
		{
			this.value=value;
		}
		
		public List<E> value; 
	}
	
	/**
	 * Json writer. It's need to write embedded field
	 */
	protected static ThreadLocal<BinderWriter> objWriter;
	
	/**
	 * json reader. It's need to read embedded field
	 */
	protected static ThreadLocal<BinderReader> objReader;

	
	public static <E> String writeToString(E[] value)
	{
		if (objWriter==null)
		{
			objWriter=new ThreadLocal<BinderWriter>() {

				@Override
				protected BinderWriter initialValue() {
					return BinderFactory.getJSONWriter(BinderOptions.build().encoding(BinderOptions.ENCODING_UTF_8));
				}
				
			};			
		}
		
		BinderWriter writer = objWriter.get();
		WrappedArray<E> w=new WrappedArray<E>(value);
		
		String result;
		try {
			result = writer.write(w);
			return result;
		} catch (MappingException e) {
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		}
		
		return null;		
	}
	
	@SuppressWarnings("unchecked")
	public static <E> E[] readFromByteArray(Class<E> clazz, byte[] input)
	{		
		BinderReader reader = getReader();
		WrappedArray<E> w=new WrappedArray<E>();
		
		try {
			w = reader.read(w.getClass(), new String(input));
			return w.value;
		} catch (MappingException e) {
			e.printStackTrace();
		} catch (ReaderException e) {
			e.printStackTrace();
		} 
		
		return null;		
	}
	
	@SuppressWarnings("unchecked")
	public static long[] readLongFromByteArray(byte[] input)
	{		
		BinderReader reader = getReader();
		WrappedArray<Long> w=new WrappedArray<Long>();
		
		try {
			w = reader.read(w.getClass(), new String(input));
			return w.value;        
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
	private static BinderReader getReader() {
		if (objReader==null)
		{
			objReader=new ThreadLocal<BinderReader>() {

				@Override
				protected BinderReader initialValue() {
					return BinderFactory.getJSONReader(BinderOptions.build().encoding(BinderOptions.ENCODING_UTF_8));
				}
				
			};			
		}
		
		return objReader.get();
	}
}
