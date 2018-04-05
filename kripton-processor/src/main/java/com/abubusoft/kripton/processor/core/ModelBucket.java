/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
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
package com.abubusoft.kripton.processor.core;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelBucket.
 *
 * @param <T> the generic type
 * @param <E> the element type
 */
public class ModelBucket<T extends ModelEntity<?>, E extends Element> extends ModelEntity<E> {

	/**
	 * Find for contained element, using its name.
	 *
	 * @param name the name
	 * @return the t
	 */
	public T get(String name) {		
		String lcName = name.toLowerCase();
		for (T item : collection) {
			if (item.getName().toLowerCase().equals(lcName)) {
				return item;
			}
		}

		return null;
	}

	/**
	 * Find property by name.
	 *
	 * @param name the name
	 * @return the t
	 */
	public T findPropertyByName(String name) {
		return get(name);
	}

	/** The collection. */
	protected List<T> collection = new ArrayList<>();

	/**
	 * Instantiates a new model bucket.
	 *
	 * @param name the name
	 * @param element the element
	 */
	public ModelBucket(String name, E element) {
		super(name, element);
	}

	/**
	 * Gets the collection.
	 *
	 * @return the collection
	 */
	public List<T> getCollection() {
		return collection;
	}

	/**
	 * Adds the.
	 *
	 * @param value the value
	 */
	public void add(T value) {
		collection.add(value);
	}

	/**
	 * Contains.
	 *
	 * @param name the name
	 * @return true, if successful
	 */
	public boolean contains(String name) {
		return get(name) != null;
	}
}
