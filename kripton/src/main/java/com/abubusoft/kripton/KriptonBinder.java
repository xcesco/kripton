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

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import com.abubusoft.kripton.exception.KriptonRuntimeException;

// TODO: Auto-generated Javadoc
/**
 * The Class KriptonBinder.
 *
 * @author xcesco
 */
public abstract class KriptonBinder {

	/** work costant. */
	public static final String MAPPER_CLASS_SUFFIX = "BindMap";

	/** The Constant jsonBinderContext. */
	private static final KriptonJsonContext jsonBinderContext = new KriptonJsonContext();

	/** The Constant xmlBinderContext. */
	private static final KriptonXmlContext xmlBinderContext = new KriptonXmlContext();

	/**
	 * registry a context inder.
	 *
	 * @param factory the factory
	 */
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
