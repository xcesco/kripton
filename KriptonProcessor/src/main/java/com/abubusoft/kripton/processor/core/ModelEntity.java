package com.abubusoft.kripton.processor.core;

import javax.lang.model.element.Element;

public class ModelEntity<E extends Element> {
	
	protected E element;

	/**
	 * @return the element
	 */
	public E getElement() {
		return element;
	}

	protected String name;

	/**
	 * @return the simpleName
	 */
	public String getName() {
		return name;
	}

	public ModelEntity(String name, E element) {
		this.element=element;
		this.name = name;		
	}
	
	public ModelEntity() {		
	}

}
