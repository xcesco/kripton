package com.abubusoft.kripton.retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.abubusoft.kripton.binder.BinderType;
import com.abubusoft.kripton.binder.KriptonBinder;
import com.abubusoft.kripton.binder.context.BinderContext;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public final class KriptonBinderConverterFactory extends Converter.Factory {

	public static KriptonBinderConverterFactory create() {
		return new KriptonBinderConverterFactory();
	}

	protected BinderContext binderContext;

	private KriptonBinderConverterFactory() {
		binderContext=KriptonBinder.getBinder(BinderType.JSON);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
		if (type instanceof Class) {
			return new KriptonResponseBodyConverter<>(binderContext, (Class<?>) type);
		} else if (type instanceof ParameterizedType) {
			return new KriptonResponseBodyCollectionConverter(binderContext, (ParameterizedType) type);
		}

		return null;
	}

	@Override
	public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations,
			Annotation[] methodAnnotations, Retrofit retrofit) {
		if (type instanceof Class) {
			return new KriptonRequestBodyConverter<>(binderContext, (Class<?>) type);
		} else if (type instanceof ParameterizedType) {
			return new KriptonRequestBodyCollectionConverter<>(binderContext, (ParameterizedType) type);
		}

		return null;

	}

}