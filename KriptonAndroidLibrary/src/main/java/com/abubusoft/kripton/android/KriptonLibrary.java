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
 * Refererrer of android context.
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class KriptonLibrary {

	public static Context context() { return context; };
	
	public static ExecutorService executorService() { return executerService; };
	
	private static Context context;
	
	private static ExecutorService executerService;
	
	public static final int THREAD_POOL_SIZE=3;

	/**
	 * Method to invoke during application initialization
	 * 
	 * @param contextValue
	 */
	public static void init(Context contextValue) {
		init(contextValue, THREAD_POOL_SIZE);
	}
	
	/**
	 * Method to invoke during application initialization
	 * 
	 * @param contextValue
	 */
	public static void init(Context contextValue, int threadPoolSize) {
		context = contextValue;
		
		//Schedulers.from(Executors.newFixedThreadPool(3));
		executerService=Executors.newFixedThreadPool(threadPoolSize);
	}
	
}
