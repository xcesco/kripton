package com.abubusoft.kripton.processor.core;

import javax.lang.model.element.Element;

public class ModelEntity<E extends Element> {
	
	protected E element;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((element == null) ? 0 : element.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelEntity other = (ModelEntity) obj;
		if (element == null) {
			if (other.element != null)
				return false;
		} else if (!element.equals(other.element))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

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
