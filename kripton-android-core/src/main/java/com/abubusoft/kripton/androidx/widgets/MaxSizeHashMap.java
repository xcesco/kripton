package com.abubusoft.kripton.androidx.widgets;

import java.util.LinkedHashMap;
import java.util.Map;

public class MaxSizeHashMap<K, V> extends LinkedHashMap<K, V> {
	private static final long serialVersionUID = -17764934129747011L;
	private final int maxSize;

	public MaxSizeHashMap(int maxSize) {
		this.maxSize = maxSize;
	}

	@Override
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		return size() > maxSize;
	}
}