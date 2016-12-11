package com.abubusoft.kripton.retrofit2;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.abubusoft.kripton.binder2.context.BinderContext;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class KriptonBinderResponseBodyCollectionConverter<T> implements Converter<ResponseBody, T> {
	private Class<?> clazz;
	private Class<?> beanClazz;
	private BinderContext binderContext;

	KriptonBinderResponseBodyCollectionConverter(BinderContext binderContext, ParameterizedType collectionType) {
		this.binderContext=binderContext;		
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
			return (T) binderContext.parseCollection((Collection) clazz.newInstance(), beanClazz, value.byteStream());
			//return (T) reader.readCollection((Collection) clazz.newInstance(), beanClazz, value.byteStream());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			value.close();
		}
	}
}