package com.abubusoft.kripton.retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.BinderJsonReader;
import com.abubusoft.kripton.BinderJsonWriter;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public final class KriptonJsonConverterFactory extends Converter.Factory {

	public static KriptonJsonConverterFactory create() {
		return create(KriptonBinder.getJsonWriter(), KriptonBinder.getJsonReader());
	}

	public static KriptonJsonConverterFactory create(BinderJsonWriter writer, BinderJsonReader reader) {
		return new KriptonJsonConverterFactory(writer, reader);
	}

	private BinderJsonWriter writer;
	
	private BinderJsonReader reader;

	private KriptonJsonConverterFactory(BinderJsonWriter writer, BinderJsonReader reader) {
		this.writer = writer;
		this.reader = reader;
		if (writer == null)
			throw new NullPointerException("writer == null");
		if (reader == null)
			throw new NullPointerException("reader == null");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
		if (type instanceof Class) {
			return new KriptonResponseBodyConverter<>((Class<?>) type, writer, reader);
		} else if (type instanceof ParameterizedType) {
			return new KriptonResponseBodyCollectionConverter((ParameterizedType) type, writer, reader);
		}

		return null;
	}

	@Override
	public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations,
			Annotation[] methodAnnotations, Retrofit retrofit) {
		if (type instanceof Class) {
			return new KriptonRequestBodyConverter<>((Class<?>) type, writer, reader);
		} else if (type instanceof ParameterizedType) {
			return new KriptonRequestBodyCollectionConverter<>((ParameterizedType) type, writer, reader);
		}

		return null;

	}

}