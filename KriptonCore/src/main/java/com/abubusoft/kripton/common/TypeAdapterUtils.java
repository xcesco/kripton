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
