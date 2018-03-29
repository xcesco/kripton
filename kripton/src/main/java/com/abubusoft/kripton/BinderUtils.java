package com.abubusoft.kripton;

import java.lang.reflect.ParameterizedType;

import com.abubusoft.kripton.exception.NoSuchMapperException;

public abstract class BinderUtils {

	/**
	 * <p>
	 * This method will be used by bean mapper to persists embedded objects.
	 * </p>
	 *
	 * @param cls
	 *		The class for which the JsonMapper should be fetched.
	 */
	public static <T, M extends BinderMapper<T>> M mapperFor(Class<T> cls) throws NoSuchMapperException {
		return AbstractJacksonContext.mapperFor(cls);
	}

	/**
	 * <p>
	 * This method will be used by bean mapper to persists embedded objects.
	 * </p>
	 * 
	 * @param type
	 * @return
	 * @throws NoSuchMapperException
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
