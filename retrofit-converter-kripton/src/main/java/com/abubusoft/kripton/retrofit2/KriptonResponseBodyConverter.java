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