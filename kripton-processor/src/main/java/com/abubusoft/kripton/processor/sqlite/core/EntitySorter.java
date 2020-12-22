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
package com.abubusoft.kripton.processor.sqlite.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Utility to order SQLEntity by their dependencies. It was build as generic class to test algorithm
 *  
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 * @param <E>
 * 		item to order 
 */
public abstract class EntitySorter<E> {

	/** The input. */
	private List<E> input;
	
	/** The output. */
	private ArrayList<E> output;

	/**
	 * Instantiates a new entity utility.
	 *
	 * @param input the input
	 */
	public EntitySorter(List<E> input) {
		this.input = input;
		this.output = new ArrayList<E>();
	}

	/**
	 * Gets the dependencies.
	 *
	 * @param item the item
	 * @return the dependencies
	 */
	public abstract Collection<E> getDependencies(E item);

	/**
	 * Generate error.
	 *
	 * @param item the item
	 */
	public abstract void generateError(E item);

	/**
	 * Order.
	 *
	 * @return the list
	 */
	public List<E> order() {
		int index = 0;
		for (E item : input) {
			index = output.indexOf(item);
			if (index == -1) {
				insert(item, item);
			}
		}

		return output;
	}

	/**
	 * Insert.
	 *
	 * @param master the master
	 * @param current the current
	 */
	private void insert(E master, E current) {
		Collection<E> dependencies = getDependencies(current);
		for (E item : dependencies) {
			if (item.equals(master)) {
				generateError(master);
			}
			insert(master, item);
		}

		// insert only element does not exists
		if (output.indexOf(current)==-1)
		{		
			output.add(current);
		}
	}
}
