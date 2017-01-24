package com.abubusoft.kripton.processor.sqlite.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Utility to order SQLEntity by their dependencies. It was build as generic class to test algorithm
 *  
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 * @param <E>
 * 		item to order 
 */
public abstract class EntityUtility<E> {

	private List<E> input;
	private ArrayList<E> output;

	public EntityUtility(List<E> input) {
		this.input = input;
		this.output = new ArrayList<E>();
	}

	public abstract Collection<E> getDependencies(E item);

	public abstract void generateError(E item);

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
