/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
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
package com.abubusoft.kripton;

import java.lang.reflect.ParameterizedType;

import com.abubusoft.kripton.exception.NoSuchMapperException;


/**
 * Binder Utils.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public abstract class BinderUtils {

	/**
	 * <p>
	 * This method will be used by bean mapper to persists embedded objects.
	 * </p>
	 *
	 * @param <T> the generic type
	 * @param <M> the generic type
	 * @param cls            The class for which the JsonMapper should be fetched.
	 * @return bind mapper
	 * @throws NoSuchMapperException the no such mapper exception
	 */
	public static <T, M extends BinderMapper<T>> M mapperFor(Class<T> cls) throws NoSuchMapperException {
		return AbstractJacksonContext.mapperFor(cls);
	}

	/**
	 * <p>
	 * This method will be used by bean mapper to persists embedded objects.
	 * </p>
	 *
	 * @param <E> the element type
	 * @param <M> the generic type
	 * @param type the type
	 * @return bind mapper associated to type
	 * @throws NoSuchMapperException the no such mapper exception
	 */
	public static <E, M extends BinderMapper<E>> M mapperFor(ParameterizedType type) throws NoSuchMapperException {
		@SuppressWarnings("unchecked")
		M mapper = AbstractContext.getMapper((Class<E>) type.getActualTypeArguments()[0]);
		if (mapper == null) {
			throw new NoSuchMapperException(type.getRawType());
		} else {
			return mapper;
		}
	}
}
