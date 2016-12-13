package com.abubusoft.kripton.binder;

import java.util.HashMap;
import java.util.Map;

import com.abubusoft.kripton.binder.context.BinderContext;
import com.abubusoft.kripton.binder.context.JsonBinderContext;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

public abstract class KriptonBinder {

	public static final String MAPPER_CLASS_SUFFIX = "BindMap";

	private static final JsonBinderContext jsonBinderContext = new JsonBinderContext();

	public static void registryBinder(BinderContext factory) {
		binders.put(factory.getSupportedFormat(), factory);
	}

	private static final Map<BinderType, BinderContext> binders = new HashMap<>();

	static {
		registryBinder(jsonBinderContext);
	}

	/**
	 * Return the JSON binder context.
	 * 
	 * @return
	 */
	public static JsonBinderContext getJsonBinderContext() {
		return jsonBinderContext;
	}

	public static BinderContext getBinder(BinderType format) {
		BinderContext binder = binders.get(format);

		if (binder == null)
			throw new KriptonRuntimeException(String.format("%s format is not supported", format));

		return binder;
	}

}
