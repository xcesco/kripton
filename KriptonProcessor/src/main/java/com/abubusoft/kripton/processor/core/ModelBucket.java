package com.abubusoft.kripton.processor.core;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;

public class ModelBucket<T extends ModelEntity<?>, E extends Element> extends ModelEntity<E> {

	protected List<T> collection = new ArrayList<>();

	public ModelBucket(String name, E element) {
		super(name, element);
	}

	public ModelBucket() {
	}

	/**
	 * @return the collection
	 */
	public List<T> getCollection() {
		return collection;
	}

	public void add(T value) {
		collection.add(value);
	}

	public T get(String name) {
		for (T item : collection) {
			if (item.getName().equals(name)) {
				return item;
			}
		}

		return null;
	}

	public boolean contains(String name) {
		for (T item : collection) {
			if (item.getName().equals(name)) {
				return true;
			}
		}

		return false;
	}
	
	public void clear()
	{
		collection.clear();
	}
}
