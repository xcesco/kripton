package com.abubusoft.kripton.android.sqlite.database;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * http://badalb.blogspot.it/2016/09/implement-lru-cache-in-java.html
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 * @param <K>
 * @param <V>
 */
public class LRUCache<K, V> {

	public interface OnRemoveListener<V> {
		void onRemove(V value);
	}

	private final int maxSize;

	private ConcurrentHashMap<K, V> map;

	private ConcurrentLinkedQueue<K> queue;

	private OnRemoveListener<V> listener;

	public LRUCache(final int maxSize, OnRemoveListener<V> listener) {
		this.maxSize = maxSize;
		this.listener = listener;
		map = new ConcurrentHashMap<K, V>(maxSize);
		queue = new ConcurrentLinkedQueue<K>();
	}

	public void put(final K key, final V value) {
		if (map.containsKey(key)) {
			// remove the key from the FIFO queue
			queue.remove(key);			
		}

		while (queue.size() >= maxSize) {
			K oldestKey = queue.poll();
			if (null != oldestKey) {
				if (listener!=null) {
				listener.onRemove(map.remove(oldestKey));
				} else {
					map.remove(oldestKey);
				}
			}
		}
		queue.add(key);
		map.put(key, value);
	}

	public V get(final K key) {

		if (map.containsKey(key)) {
			// remove from queue and add it again in FIFO queue
			queue.remove(key);
			queue.add(key);
		}
		return map.get(key);
	}
}