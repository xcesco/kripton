package com.abubusoft.kripton.processor.core.reflect;

import javax.lang.model.element.Element;

import com.abubusoft.kripton.processor.core.ModelProperty;

public interface PropertyFactory<T extends ModelProperty> {

	T createProperty(Element element);
}
