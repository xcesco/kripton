package com.abubusoft.kripton.processor.convert;

import javax.lang.model.element.TypeElement;

public class ConvertDefinition {
	
	private String name;
	private TypeElement typeElement;
	private String fullName;

	public TypeElement getTypeElement() {
		return typeElement;
	}

	public String getFullName() {
		return fullName;
	}

	public ConvertDefinition(TypeElement classElement)
	{
		this.typeElement = classElement;
		name = classElement.getSimpleName().toString();		
	}

	public String getName() {
		return name;
	}

}
