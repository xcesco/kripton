package com.abubusoft.kripton;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import com.abubusoft.kripton.exception.KriptonRuntimeException;

/**
 * @author xcesco
 *
 */
public abstract class KriptonBinder {

	public static final String MAPPER_CLASS_SUFFIX = "BindMap";

	private static final KriptonJsonContext jsonBinderContext = new KriptonJsonContext();

	private static final KriptonXmlContext xmlBinderContext = new KriptonXmlContext();

	public static void registryBinder(BinderContext factory) {
		binders.put(factory.getSupportedFormat(), factory);
	}

	/**
	 * map of registered binder. JSON and XML are automatically registered.
	 */
	private static final Map<BinderType, BinderContext> binders = new HashMap<>();

	static {
		registryBinder(jsonBinderContext);
		registryBinder(xmlBinderContext);

		ServiceLoader<BinderContext> codecSetLoader = ServiceLoader.load(BinderContext.class);

		for (BinderContext context : codecSetLoader) {
			registryBinder(context);
		}
	}

	/**
	 * Retrieve JSON binder context. It equivalent to use
	 * {@link #bind(BinderType)} with {@link BinderType#JSON}.
	 * 
	 * @return returns the JSON binder context.
	 */
	public static KriptonJsonContext jsonBind() {
		return jsonBinderContext;
	}

	/**
	 * Retrieve XML binder context. It equivalent to use
	 * {@link #bind(BinderType)} with {@link BinderType#XML}
	 * 
	 * @return returns the XML binder context.
	 */
	public static KriptonXmlContext xmlBind() {
		return xmlBinderContext;
	}

	/**
	 * retrieve binding context for specified data format.
	 * 
	 * @param format
	 *            data format used to persist data
	 * @return binding context
	 */
	public static BinderContext bind(BinderType format) {
		BinderContext binder = binders.get(format);

		if (binder == null)
			throw new KriptonRuntimeException(String.format("%s format is not supported", format));

		return binder;
	}

}
