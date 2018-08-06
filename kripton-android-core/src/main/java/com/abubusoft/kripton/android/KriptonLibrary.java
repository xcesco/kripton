/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.android;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;

/**
 * Used to initialize library
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class KriptonLibrary {

	/**
	 * Context.
	 *
	 * @return the context
	 */
	public static Context getContext() {
		return context;
	};

	/**
	 * Executor service.
	 *
	 * @return the executor service
	 */
	public static ExecutorService getExecutorService() {
		return executerService;
	};

	/** The context. */
	private static Context context;

	/** The executer service. */
	private static ExecutorService executerService;

	/** The Constant THREAD_POOL_SIZE_DEFAULT. */
	public static final int THREAD_POOL_SIZE_DEFAULT = 3;

	/**
	 * Method to invoke during application initialization.
	 *
	 * @param contextValue
	 *            the context value
	 */
	public static void init(Context contextValue) {
		init(contextValue, null);
	}


	/**
	 * Method to invoke during application initialization.
	 *
	 * @param contextValue
	 *            the context value
	 * @param executorService
	 *            the executor service
	 */
	public static void init(Context contextValue, ExecutorService service) {
		context = contextValue;

		if (service == null) {
			executerService = Executors.newFixedThreadPool(THREAD_POOL_SIZE_DEFAULT);
		} else {
			executerService = service;
		}

	}

}
