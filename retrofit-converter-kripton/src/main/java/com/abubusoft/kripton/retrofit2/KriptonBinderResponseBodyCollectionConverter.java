package com.abubusoft.kripton.retrofit2;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.abubusoft.kripton.BinderJsonReader;
import com.abubusoft.kripton.BinderJsonWriter;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class KriptonBinderResponseBodyCollectionConverter<T> implements Converter<ResponseBody, T> {
	BinderJsonWriter writer;
	BinderJsonReader reader;
	private Class<?> clazz;
	private Class<?> beanClazz;

	KriptonBinderResponseBodyCollectionConverter(ParameterizedType collectionType, BinderJsonWriter writer,
			BinderJsonReader reader) {
		this.writer = writer;
		this.reader = reader;
		this.clazz = (Class<?>) collectionType.getRawType();
		this.beanClazz = (Class<?>) collectionType.getActualTypeArguments()[0];
		
		this.clazz=replaceInterface(clazz);
	}

	private Class<?> replaceInterface(Class<?> clazz) {
		if (clazz.equals(List.class))
		{
			return ArrayList.class;
		}
		
		if (clazz.equals(Set.class))
		{
			return HashSet.class;
		}
		
		return clazz;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public T convert(ResponseBody value) throws IOException {
		try {
			return (T) reader.readCollection((Collection) clazz.newInstance(), beanClazz, value.byteStream());
		} catch (ReaderException | MappingException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} finally {
			value.close();
		}
	}
}