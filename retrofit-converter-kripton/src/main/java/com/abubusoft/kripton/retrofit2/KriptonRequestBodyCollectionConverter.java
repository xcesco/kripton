package com.abubusoft.kripton.retrofit2;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import com.abubusoft.kripton.binder2.context.BinderContext;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

final class KriptonRequestBodyCollectionConverter<T> implements Converter<T, RequestBody> {
	private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
	private BinderContext binderContext;
	private Type clazz;

	KriptonRequestBodyCollectionConverter(BinderContext binderContext, ParameterizedType collectionType) {
		this.binderContext=binderContext;
		this.clazz=collectionType.getActualTypeArguments()[0];
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public RequestBody convert(T value) throws IOException {
		Buffer buffer = new Buffer();	
		
		try {	
			binderContext.serializeCollection((Collection) value, (Class)clazz,buffer.outputStream()); 			
			return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
		} catch (Exception e) {			
			e.printStackTrace();
			return null;
		} finally
		{
			buffer.close();
		}
		
		
	}
}