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

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.abubusoft.kripton.BinderContext;

import okhttp3.ResponseBody;
import retrofit2.Converter;

// TODO: Auto-generated Javadoc
/**
 * The Class KriptonBinderResponseBodyCollectionConverter.
 *
 * @param <T> the generic type
 */
final class KriptonBinderResponseBodyCollectionConverter<T> implements Converter<ResponseBody, T> {
	
	/** The clazz. */
	private Class<?> clazz;
	
	/** The bean clazz. */
	private Class<?> beanClazz;
	
	/** The binder context. */
	private BinderContext binderContext;

	/**
	 * Instantiates a new kripton binder response body collection converter.
	 *
	 * @param binderContext the binder context
	 * @param collectionType the collection type
	 */
	KriptonBinderResponseBodyCollectionConverter(BinderContext binderContext, ParameterizedType collectionType) {
		this.binderContext=binderContext;		
		this.clazz = (Class<?>) collectionType.getRawType();
		this.beanClazz = (Class<?>) collectionType.getActualTypeArguments()[0];
		
		this.clazz=replaceInterface(clazz);
	}

	/**
	 * Replace interface.
	 *
	 * @param clazz the clazz
	 * @return the class
	 */
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

	/* (non-Javadoc)
	 * @see retrofit2.Converter#convert(java.lang.Object)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public T convert(ResponseBody value) throws IOException {
		try {
			return (T) binderContext.parseCollection(value.byteStream(), (Collection) clazz.newInstance(), beanClazz);
			//return (T) reader.readCollection((Collection) clazz.newInstance(), beanClazz, value.byteStream());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			value.close();
		}
	}
}
