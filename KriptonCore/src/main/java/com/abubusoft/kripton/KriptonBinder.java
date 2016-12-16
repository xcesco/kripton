package com.abubusoft.kripton;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import com.abubusoft.kripton.exception.KriptonRuntimeException;

public abstract class KriptonBinder {
	
	public static final String MAPPER_CLASS_SUFFIX = "BindMap";

	private static final KriptonJsonContext jsonBinderContext = new KriptonJsonContext();

	public static void registryBinder(BinderContext factory) {
		binders.put(factory.getSupportedFormat(), factory);
	}

	private static final Map<BinderType, BinderContext> binders = new HashMap<>();

	static {
		registryBinder(jsonBinderContext);
		
		ServiceLoader<BinderContext> codecSetLoader = ServiceLoader.load(BinderContext.class);
		
		for (BinderContext context: codecSetLoader)
		{
			registryBinder(context);
		}
	}

	/**
	 * Return the JSON binder context.
	 * 
	 * @return
	 */
	public static KriptonJsonContext getJsonBinderContext() {
		return jsonBinderContext;
	}

	public static BinderContext getBinder(BinderType format) {
		BinderContext binder = binders.get(format);

		if (binder == null)
			throw new KriptonRuntimeException(String.format("%s format is not supported", format));

		return binder;
	}

}
