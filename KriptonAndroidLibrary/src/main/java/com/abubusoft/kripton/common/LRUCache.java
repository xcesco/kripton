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
package com.abubusoft.kripton.common;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A lightweight LRU(least recently used) cache implementation based on 
 * @see java.util.LinkedHashMap.
 * 
 * @author bulldog
 *
 * @param <K> key
 * @param <V> value
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

	private static final long serialVersionUID = -6185198284025124465L;
	private final int cacheSize;
	
	public LRUCache(final int size) {
		super(size + 1, 1.0f, true);
		this.cacheSize = size;
	}
	
	@Override
	protected boolean removeEldestEntry(final Map.Entry<K, V> eldest) {
		return super.size() > cacheSize;
	}
}
