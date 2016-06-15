/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.abubusoft.kripton.retrofit2;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderReader;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.binder.json.JsonReader;
import com.abubusoft.kripton.binder.json.JsonWriter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * A {@linkplain Converter.Factory converter} which uses Gson for JSON.
 * <p>
 * Because Gson is so flexible in the types it supports, this converter assumes that it can handle all types. If you are mixing JSON serialization with something else (such as protocol buffers), you must
 * {@linkplain Retrofit.Builder#addConverterFactory(Converter.Factory) add this instance} last to allow the other converters a chance to see their types.
 */
public final class KriptonConverterFactory extends Converter.Factory {

	/**
	 * Create an instance using a default {@link Gson} instance for conversion. Encoding to JSON and decoding from JSON (when no charset is specified by a header) will use UTF-8.
	 */
	public static KriptonConverterFactory create() {
		return create(BinderFactory.getJSONWriter(), BinderFactory.getJSONReader());
	}

	/**
	 * Create an instance using {@code gson} for conversion. Encoding to JSON and decoding from JSON (when no charset is specified by a header) will use UTF-8.
	 */
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
		// TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));

		return new KriptonRequestBodyConverter<>((Class<?>) type, writer, reader);
	}
}