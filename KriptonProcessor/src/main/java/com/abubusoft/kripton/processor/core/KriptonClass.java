package com.abubusoft.kripton.processor.core;

import javax.lang.model.element.Element;

import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindAllFields
public class KriptonClass extends KriptonBucket<KriptonProperty> implements KriptonElement {
	
	public KriptonClass(Element element) {
		super(element);
	}

	@Override
	public void accept(KriptonElementVisitor visitor) throws Exception {
		visitor.visit(this);		
	}

}
