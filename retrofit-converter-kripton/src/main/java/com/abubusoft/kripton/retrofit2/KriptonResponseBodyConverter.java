package com.abubusoft.kripton.retrofit2;

import java.io.IOException;

import com.abubusoft.kripton.binder2.context.BinderContext;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class KriptonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
	private BinderContext binderContext;
	private Class<T> clazz;

	KriptonResponseBodyConverter(BinderContext binderContext, Class<T> clazz) {
		this.binderContext = binderContext;
		this.clazz = clazz;
	}

	@Override
	public T convert(ResponseBody value) throws IOException {
		try {
			return binderContext.parse(value.byteStream(), clazz);
		} catch (ReaderException | MappingException e) {
			e.printStackTrace();
			return null;
		} finally {
			value.close();
		}
	}
}