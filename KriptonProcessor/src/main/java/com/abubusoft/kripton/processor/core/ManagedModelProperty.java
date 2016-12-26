package com.abubusoft.kripton.processor.core;

import javax.lang.model.element.Element;

import com.abubusoft.kripton.processor.bind.model.BindProperty;

public class ManagedModelProperty extends ModelProperty {

	@SuppressWarnings("rawtypes")
	public ManagedModelProperty(ModelEntity entity, Element element) {
		super(entity, element);
	}

	public BindProperty bindProperty;
}
