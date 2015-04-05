package com.abubusoft.kripton.database;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.abubusoft.kripton.annotation.BindQueryParams;
import com.abubusoft.kripton.binder.transform.Transformer;
import com.abubusoft.kripton.exception.MappingException;

/**
 * Utilty
 * 
 * @author xcesco
 *
 */
public class DatabaseHelper {

	/**
	 * Validate params and create parameters map.
	 * 
	 * @param paramsClass
	 */
	public static void validateParams(QueryParams params, Class<?> paramsClass) {
		BindQueryParams annotation = paramsClass.getAnnotation(BindQueryParams.class);

		if (annotation != null) {
			scanFields(params, paramsClass);

			Class<?> superType = paramsClass.getSuperclass();
			// scan super class fields
			while (superType != Object.class && superType != null) {
				scanFields(params, superType);
				superType = paramsClass.getSuperclass();
			}
		} else {
			throw (new MappingException("Class " + paramsClass + " is not annotated with @BindQueryParams, so it can not be used as parameter class for query"));

		}

	}

	/**
	 * @param params
	 * @param clazz
	 */
	private static void scanFields(QueryParams params, Class<?> clazz) {
		QueryParam param;

		for (Field item : clazz.getDeclaredFields()) {
			//
			if (Modifier.isStatic(item.getModifiers())) continue;
			
			if (!item.isAccessible()) item.setAccessible(true);
			
			if (!Transformer.isTransformable(item.getType())) {
				throw new MappingException("Can not use class " + item.getType() + " like params for query because field " + item.getName()
						+ " has not a string convertion.");
			}
			param = new QueryParam();
			param.name = item.getName();
			param.field = item;
			param.trans = Transformer.lookup(item.getType());
			param.type = item.getType();

			params.addParam(param);
		}

	}
}
