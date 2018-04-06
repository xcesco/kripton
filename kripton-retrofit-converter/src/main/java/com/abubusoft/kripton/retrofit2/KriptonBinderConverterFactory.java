/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.abubusoft.kripton.retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.abubusoft.kripton.BinderContext;
import com.abubusoft.kripton.BinderType;
import com.abubusoft.kripton.KriptonBinder;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating KriptonBinderConverter objects.
 */
public final class KriptonBinderConverterFactory extends Converter.Factory {

	/**
	 * Creates the.
	 *
	 * @return the kripton binder converter factory
	 */
	public static KriptonBinderConverterFactory create() {
		return new KriptonBinderConverterFactory();
	}
	
	/**
	 * Creates the.
	 *
	 * @param binderType the binder type
	 * @return the kripton binder converter factory
	 */
	public static KriptonBinderConverterFactory create(BinderType binderType) {
		return new KriptonBinderConverterFactory(binderType);
	}

	/** The binder context. */
	protected BinderContext binderContext;

	/**
	 * Instantiates a new kripton binder converter factory.
	 */
	private KriptonBinderConverterFactory() {
		binderContext=KriptonBinder.bind(BinderType.JSON);
	}
	
	/**
	 * Instantiates a new kripton binder converter factory.
	 *
	 * @param binderType the binder type
	 */
	private KriptonBinderConverterFactory(BinderType binderType) {
		binderContext=KriptonBinder.bind(binderType);
	}

	/* (non-Javadoc)
	 * @see retrofit2.Converter.Factory#responseBodyConverter(java.lang.reflect.Type, java.lang.annotation.Annotation[], retrofit2.Retrofit)
	 */
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

	/* (non-Javadoc)
	 * @see retrofit2.Converter.Factory#requestBodyConverter(java.lang.reflect.Type, java.lang.annotation.Annotation[], java.lang.annotation.Annotation[], retrofit2.Retrofit)
	 */
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
