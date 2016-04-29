package com.abubusoft.kripton.processor.core;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;

public class KriptonBucket<E extends KriptonEntity> extends KriptonEntity {

	protected List<E> collection = new ArrayList<>();

	public KriptonBucket(Element element) {
		super(element);
	}

	public KriptonBucket() {
	}

	/**
	 * @return the collection
	 */
	public List<E> getCollection() {
		return collection;
	}

	public void add(E value) {
		collection.add(value);
	}

	public E get(String name) {
		for (E item : collection) {
			if (item.getName().equals(name)) {
				return item;
			}
		}

		return null;
	}

	public boolean contains(String name) {
		for (E item : collection) {
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
