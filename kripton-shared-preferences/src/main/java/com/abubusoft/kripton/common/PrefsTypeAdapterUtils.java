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

import com.abubusoft.kripton.BindTypeAdapter;
import com.abubusoft.kripton.android.sharedprefs.BindPrefsTypeAdapter;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public abstract class PrefsTypeAdapterUtils {

	static ReentrantLock lock = new ReentrantLock();

	@SuppressWarnings("rawtypes")
	private static HashMap<Class<? extends BindPrefsTypeAdapter>, BindPrefsTypeAdapter> cache = new HashMap<>();

	@SuppressWarnings("unchecked")
	public static <D, J> J toJava(Class<? extends BindPrefsTypeAdapter<J, D>> clazz, D value) {
		BindPrefsTypeAdapter<J, D> adapter = cache.get(clazz);

		if (adapter == null) {
			adapter = TypeAdapterUtils.generateAdapter(cache, lock, clazz);
		}

		return adapter.toJava(value);
	}

	@SuppressWarnings("unchecked")
	public static <D, J> D toData(Class<? extends BindPrefsTypeAdapter<J, D>> clazz, J javaValue) {
		BindPrefsTypeAdapter<J, D> adapter = cache.get(clazz);

		if (adapter == null) {
			adapter = TypeAdapterUtils.generateAdapter(cache, lock, clazz);
		}

		return adapter.toData(javaValue);
	}

}
