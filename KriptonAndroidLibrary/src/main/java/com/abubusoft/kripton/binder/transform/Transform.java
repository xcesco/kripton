package com.abubusoft.kripton.binder.transform;

public interface Transform<T> {
	public T read(String value) throws Exception;

	public String write(T value) throws Exception;
}
