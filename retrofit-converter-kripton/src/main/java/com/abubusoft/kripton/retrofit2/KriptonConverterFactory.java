package com.abubusoft.kripton.retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderReader;
import com.abubusoft.kripton.BinderWriter;

public final class KriptonConverterFactory extends Converter.Factory {

	public static KriptonConverterFactory create() {
		return create(BinderFactory.getJSONWriter(), BinderFactory.getJSONReader());
	}

	public static KriptonConverterFactory create(BinderWriter writer, BinderReader reader) {
		return new KriptonConverterFactory(writer, reader);
	}

	private BinderWriter writer;
	private BinderReader reader;

	private KriptonConverterFactory(BinderWriter writer, BinderReader reader) {
		this.writer = writer;
		this.reader = reader;
		if (writer == null)
			throw new NullPointerException("writer == null");
		if (reader == null)
			throw new NullPointerException("reader == null");
	}

	@Override
	public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
		return new KriptonResponseBodyConverter<>((Class<?>) type, writer, reader);
	}

	@Override
	public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
		return new KriptonRequestBodyConverter<>((Class<?>) type, writer, reader);
	}
}