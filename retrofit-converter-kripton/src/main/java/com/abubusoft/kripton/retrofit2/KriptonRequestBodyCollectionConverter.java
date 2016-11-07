package com.abubusoft.kripton.retrofit2;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import com.abubusoft.kripton.BinderJsonReader;
import com.abubusoft.kripton.BinderJsonWriter;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.WriterException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

final class KriptonRequestBodyCollectionConverter<T> implements Converter<T, RequestBody> {
	private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

	BinderJsonWriter writer;
	
	BinderJsonReader reader;

	//private Type beanClazz;

	//private Type clazz;

	KriptonRequestBodyCollectionConverter(ParameterizedType collectionType, BinderJsonWriter writer, BinderJsonReader reader) {
		this.writer = writer;
		this.reader = reader;
		//this.clazz = collectionType.getRawType();
		//this.beanClazz = collectionType.getActualTypeArguments()[0];
	}

	@Override
	public RequestBody convert(T value) throws IOException {
		Buffer buffer = new Buffer();	
		
		try {			
			writer.writeCollection((Collection<?>)value, buffer.outputStream());
			return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
		} catch (WriterException | MappingException e) {			
			e.printStackTrace();
			return null;
		} finally
		{
			buffer.close();
		}
		
		
	}
}