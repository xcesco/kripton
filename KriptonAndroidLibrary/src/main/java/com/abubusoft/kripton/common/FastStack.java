/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.common;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * A lightweight stack implementation, this stack is not synchronized and
 * keeps track of elements using an array list.
 * 
 * @author bulldog
 *
 */
public class FastStack<T> extends ArrayList<T> {

	private static final long serialVersionUID = -439768300225351932L;

	/**
	 * Constructor for the FastStack
	 * 
	 * @param size initial size of the stack to use
	 */
	public FastStack(int size) {
		super(size);
	}
	
	/**
	 * Add an object to the top of the stack
	 * 
	 * @param o object to be added to the stack
	 */
	public void push(T o) {
		add(o);
	}
	
	/**
	 * Remove an object from the top of the stack,
	 * if the stack is empty, @see java.util.EmptyStackException will be thrown.
	 * 
	 * @return the object on the top of the stack
	 */
	public T pop() {
		if (empty()) throw new EmptyStackException();
		return remove(size() - 1);
	}
	
	/**
	 * Check if the stack is empty
	 * 
	 * @return true or false
	 */
	public boolean empty() {
		return size() == 0;
	}
	
	/**
	 * Peek the object at the top of the stack,
	 * if the stack is empty, @see java.util.EmptyStackException will be thrown.
	 * 
	 * @return the object at the top of the stack
	 */
	public T peek() {
		if (empty()) throw new EmptyStackException();
		return get(size() - 1);
	}	
}
