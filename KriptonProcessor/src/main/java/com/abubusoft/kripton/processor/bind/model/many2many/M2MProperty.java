package com.abubusoft.kripton.processor.bind.model.many2many;

import com.squareup.javapoet.TypeName;

public class M2MProperty extends M2MBase {
	
	public M2MProperty(String name, TypeName type) {
		this.name=name;
		this.type=type;
	}

	public TypeName type;
	
	public TypeName getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String name;
}
