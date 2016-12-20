package com.abubusoft.kripton.common;

import java.util.HashMap;

import com.abubusoft.kripton.BindTypeAdapter;

public class TypeAdapterManager {

	private TypeAdapterManager() {
		map = new HashMap<>();
	}

	private static TypeAdapterManager instance;

	@SuppressWarnings("rawtypes")
	private HashMap<Class<? extends BindTypeAdapter>, BindTypeAdapter> map;

	public static TypeAdapterManager instance() {
		if (instance == null)
			instance = new TypeAdapterManager();

		return instance;
	}

	@SuppressWarnings("rawtypes")
	public BindTypeAdapter registry(Class<? extends BindTypeAdapter> typeAdapter) throws InstantiationException, IllegalAccessException {
		BindTypeAdapter adapter = typeAdapter.newInstance();
		map.put(typeAdapter, adapter);
		
		return adapter;
	}

	@SuppressWarnings("unchecked")
	public <J,D> D adaptJava2Data(Class<? extends BindTypeAdapter<J, D>> typeAdapterClazz, J field) {
		BindTypeAdapter<J, D> adapter=map.get(typeAdapterClazz);
		
		if (adapter==null) 
			adapter=map.put(typeAdapterClazz, adapter);
		
		return adapter.java2data(field);
	}
	
	@SuppressWarnings("unchecked")
	public <J,D> J adaptData2Java(Class<? extends BindTypeAdapter<J, D>> typeAdapterClazz, D data) {
		BindTypeAdapter<J, D> adapter=map.get(typeAdapterClazz);
		
		if (adapter==null) 
			adapter=map.put(typeAdapterClazz, adapter);
		
		return adapter.data2java(data);
	}
}
