package com.abubusoft.kripton.retrofit2;

import java.io.IOException;

import com.abubusoft.kripton.BinderContext;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class KriptonBinderResponseBodyConverter<T> implements Converter<ResponseBody, T> {
	private Class<T> clazz;
	private BinderContext binderContext;

	KriptonBinderResponseBodyConverter(BinderContext binderContext, Class<T> clazz) {
		this.clazz = clazz;
		this.binderContext = binderContext;
	}

	@Override
	public T convert(ResponseBody value) throws IOException {
		try {
			return (T) binderContext.parse(value.byteStream(), clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			value.close();
		}
	}
}