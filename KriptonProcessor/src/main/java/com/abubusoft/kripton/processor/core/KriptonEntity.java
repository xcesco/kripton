package com.abubusoft.kripton.processor.core;

import javax.lang.model.element.Element;

public class KriptonEntity {
	
	protected Element element;

	/**
	 * @return the element
	 */
	public Element getElement() {
		return element;
	}

	protected String name;

	/**
	 * @return the simpleName
	 */
	public String getName() {
		return name;
	}

	public KriptonEntity(Element element) {
		this.element=element;
		this.name = element.getSimpleName().toString();		
	}
	
	public KriptonEntity() {		
	}

}
