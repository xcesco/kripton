package com.abubusoft.kripton.common;

import java.util.concurrent.ConcurrentHashMap;

import com.abubusoft.kripton.BindTypeAdapter;

public abstract class TypeAdapterUtils {

	@SuppressWarnings("rawtypes")
	private static ConcurrentHashMap<Class<? extends BindTypeAdapter>, BindTypeAdapter> cache = new ConcurrentHashMap<>();

	@SuppressWarnings("unchecked")
	public static <D, J> J toJava(Class<? extends BindTypeAdapter<J, D>> clazz, D value) throws Exception {
		BindTypeAdapter<J, D> adapter = cache.get(clazz);

		if (adapter == null) {
			adapter = (BindTypeAdapter<J, D>) clazz.newInstance();
			cache.put(clazz, adapter);
		}

		return adapter.toJava(value);
	}

	@SuppressWarnings("unchecked")
	public static <D, J> D toData(Class<? extends BindTypeAdapter<J, D>> clazz, J javaValue) throws Exception {
		BindTypeAdapter<J, D> adapter = cache.get(clazz);

		if (adapter == null) {
			adapter = (BindTypeAdapter<J, D>) clazz.newInstance();
			cache.put(clazz, adapter);
		}

		return adapter.toData(javaValue);
	}

}
