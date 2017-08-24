package com.abubusoft.kripton.processor.core;

import java.util.List;

public interface Finder<T> {

	T findByName(String name);

	List<T> getCollection();

	String getSimpleName();
}
