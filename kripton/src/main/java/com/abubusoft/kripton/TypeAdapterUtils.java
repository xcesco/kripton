/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

import com.abubusoft.kripton.BindTypeAdapter;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

public abstract class TypeAdapterUtils {

	static ReentrantLock lock = new ReentrantLock();

	@SuppressWarnings("rawtypes")
	private static HashMap<Class<? extends BindTypeAdapter>, BindTypeAdapter> cache = new HashMap<>();

	@SuppressWarnings("unchecked")
	public static <D, J> J toJava(Class<? extends BindTypeAdapter<J, D>> clazz, D value) {
		BindTypeAdapter<J, D> adapter = cache.get(clazz);

		if (adapter == null) {
			try {
				lock.lock();
				adapter = clazz.newInstance();
				cache.put(clazz, adapter);
			} catch(Throwable e) {
				throw(new KriptonRuntimeException(e));
			} finally {
				lock.unlock();
			}
		}

		return adapter.toJava(value);
	}

	@SuppressWarnings("unchecked")
	public static <D, J> D toData(Class<? extends BindTypeAdapter<J, D>> clazz, J javaValue) {
		BindTypeAdapter<J, D> adapter = cache.get(clazz);

		if (adapter == null) {
			try {
				lock.lock();
				adapter = clazz.newInstance();
				cache.put(clazz, adapter);
			} catch(Throwable e) {
				throw(new KriptonRuntimeException(e));
			} finally {
				lock.unlock();
			}
		}

		return adapter.toData(javaValue);
	}

}
