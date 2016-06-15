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

import java.io.IOException;

import com.abubusoft.kripton.BinderReader;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class KriptonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
	BinderWriter writer;
	BinderReader reader;
	private Class<T> clazz;

	KriptonResponseBodyConverter(Class<T> clazz, BinderWriter writer, BinderReader reader) {
		this.writer = writer;
		this.reader = reader;
		this.clazz = clazz;
	}

	@Override
	public T convert(ResponseBody value) throws IOException {
		try {
			return (T) reader.read(clazz, value.byteStream());
		} catch (ReaderException | MappingException e) {
			e.printStackTrace();
			return null;
		} finally {
			value.close();		
		}
	}
}