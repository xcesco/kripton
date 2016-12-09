package com.abubusoft.kripton.processor.core;

import javax.lang.model.element.Element;

import com.abubusoft.kripton.processor.bind.model.BindProperty;

public class ManagedModelProperty extends ModelProperty {

	public ManagedModelProperty(Element element) {
		super(element);
	}

	public BindProperty bindProperty;
}
