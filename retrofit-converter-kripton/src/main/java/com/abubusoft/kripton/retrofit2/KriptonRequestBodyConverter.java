package com.abubusoft.kripton.retrofit2;

import java.io.IOException;

import com.abubusoft.kripton.binder2.context.BinderContext;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.WriterException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

final class KriptonRequestBodyConverter<T> implements Converter<T, RequestBody> {
	private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
	private BinderContext binderContext;

	KriptonRequestBodyConverter(BinderContext binderContext, Class<T> clazz) {
		this.binderContext = binderContext;
	}

	@Override
	public RequestBody convert(T value) throws IOException {
		Buffer buffer = new Buffer();

		try {
			binderContext.serialize(value, buffer.outputStream());
			return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
		} catch (WriterException | MappingException e) {
			e.printStackTrace();
			return null;
		} finally {
			buffer.close();
		}

	}
}