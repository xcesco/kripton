/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.arch.core.executor.internal;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;


/**
 * Poor's man LinkedHashMap, which supports modifications during iterations.
 * Takes more memory that SafeIterableMap
 * It is NOT thread safe.
 *
 * @param <K> Key type
 * @param <V> Value type
 */
public class FastSafeIterableMap<K, V> extends SafeIterableMap<K, V> {

    /** The m hash map. */
    private HashMap<K, Entry<K, V>> mHashMap = new HashMap<>();

    /* (non-Javadoc)
     * @see android.arch.core.internal.SafeIterableMap#get(java.lang.Object)
     */
    @Override
    protected Entry<K, V> get(K k) {
        return mHashMap.get(k);
    }

    /* (non-Javadoc)
     * @see android.arch.core.internal.SafeIterableMap#putIfAbsent(java.lang.Object, java.lang.Object)
     */
    @Override
    public V putIfAbsent(@NonNull K key, @NonNull V v) {
        Entry<K, V> current = get(key);
        if (current != null) {
            return current.mValue;
        }
        mHashMap.put(key, put(key, v));
        return null;
    }

    /* (non-Javadoc)
     * @see android.arch.core.internal.SafeIterableMap#remove(java.lang.Object)
     */
    @Override
    public V remove(@NonNull K key) {
        V removed = super.remove(key);
        mHashMap.remove(key);
        return removed;
    }

    /**
     * Returns {@code true} if this map contains a mapping for the specified
     * key.
     *
     * @param key the key
     * @return true, if successful
     */
    public boolean contains(K key) {
        return mHashMap.containsKey(key);
    }

    /**
     * Return an entry added to prior to an entry associated with the given key.
     *
     * @param k the key
     * @return the map. entry
     */
    public Map.Entry<K, V> ceil(K k) {
        if (contains(k)) {
            return mHashMap.get(k).mPrevious;
        }
        return null;
    }
}
